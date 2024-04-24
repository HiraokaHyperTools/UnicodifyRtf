using Antlr4.Runtime;
using Antlr4.Runtime.Misc;
using Antlr4.Runtime.Tree;
using CommandLine;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Xml.Linq;
using static Rtf1Parser;

namespace UnicodifyRtf
{
    internal class Program
    {
        [Verb("convert")]
        private class ConvertOpt
        {
            [Value(0, Required = true, HelpText = "Input rtf")]
            public string InputRtf { get; set; }

            [Value(1, Required = true, HelpText = "Output rtf")]
            public string OutputRtf { get; set; }
        }

        private static int Main(string[] args)
        {
            return CommandLine.Parser.Default.ParseArguments<ConvertOpt>(args)
                .MapResult<ConvertOpt, int>(
                    DoConvert,
                    ex => 1
                );
        }

        private static int DoConvert(ConvertOpt opt)
        {
            var inputRtf = opt.InputRtf ?? throw new NullReferenceException();

            var writer = new StringWriter();

            var stream = FromString(
                File.ReadAllText(inputRtf, Encoding.GetEncoding("latin1")),
                opt.InputRtf
            );
            var lexer = new Rtf1Lexer(stream);
            var tokens = new CommonTokenStream(lexer);
            var parser = new Rtf1Parser(tokens);
            var document = parser.document();

            var bytes = new MemoryStream();
            var depth = 0;
            var nextFirstTag = false;
            var activeFontCharset = 1252;
            var enableConversion = false;

            var fToCharset = new Dictionary<string, int>();

            var watcher1 = GetRootWatcher1(
                onFCharset: (fTag, fcharset) =>
                {
                    fToCharset[fTag.ToString()] = fcharset;
                }
            );
            var watcher2 = GetRootWatcher2(
                f => enableConversion = f
            );
            var tagTree = new List<WordN>();

            Encoding GetEncodingFromActiveFontCharset()
            {
                switch (activeFontCharset)
                {
                    case 2: return Encoding.GetEncoding(42);
                    case 77: return Encoding.GetEncoding(2);
                    case 128: return Encoding.GetEncoding(932);
                    case 129: return Encoding.GetEncoding(949);
                    case 130: return Encoding.GetEncoding(1361);
                    case 134: return Encoding.GetEncoding(936);
                    case 136: return Encoding.GetEncoding(950);
                    case 161: return Encoding.GetEncoding(1253);
                    case 162: return Encoding.GetEncoding(1254);
                    case 163: return Encoding.GetEncoding(1258);
                    case 177: return Encoding.GetEncoding(1255);
                    case 178: return Encoding.GetEncoding(1256);
                    case 186: return Encoding.GetEncoding(1257);
                    case 204: return Encoding.GetEncoding(1251);
                    case 238: return Encoding.GetEncoding(1250);
                    default: return Encoding.GetEncoding(1252);
                }
            }

            void ConsumeText(string text)
            {
                if (!enableConversion)
                {
                    // e.g.
                    // {\rtf1 ... {\fonttbl{\f0\fnil\fcharset128 Arial Unicode MS;} ... }
                    writer.Write(text);
                }
                else
                {
                    // {\rtf1 ... text ... }

                    foreach (var chr in text)
                    {
                        bytes.WriteByte((byte)chr);
                    }
                }
            }
            void FlushText()
            {
                foreach (var chr in GetEncodingFromActiveFontCharset().GetString(bytes.ToArray()))
                {
                    if (chr < 0x20 || 0x80 <= chr)
                    {
                        writer.Write("\\u{0}?", (int)chr);
                    }
                    else
                    {
                        writer.Write(chr);
                    }
                }
                bytes.SetLength(0);
            }

            var watchers = new List<PositionedWatcher>();
            watchers.Add(new PositionedWatcher(watcher1, 0));
            watchers.Add(new PositionedWatcher(watcher2, 0));

            foreach (var node in document.node())
            {
                if (false) { }
                else if (node.Open() != null)
                {
                    FlushText();
                    writer.Write("{");
                    depth += 1;
                    tagTree.Add(null);
                    nextFirstTag = true;
                }
                else if (node.Close() != null)
                {
                    FlushText();
                    writer.Write("}");
                    watchers
                        .Where(it => it.Depth == depth)
                        .ToArray()
                        .ForEach(
                            pair =>
                            {
                                pair.Watcher.Close?.Invoke();
                                watchers.Remove(pair);
                            }
                        );
                    depth -= 1;
                    tagTree.RemoveAt(tagTree.Count() - 1);
                    nextFirstTag = false;
                }
                else if (node.escape() is EscapeContext escapeContext && escapeContext != null)
                {
                    void MayOpen(WordN tag)
                    {
                        if (nextFirstTag)
                        {
                            var newWatchers = new List<PositionedWatcher>();
                            watchers
                                .Where(it => it.Depth == depth - 1)
                                .ForEach(
                                    pair => pair.Watcher.TryToOpen?.Invoke(
                                        tag,
                                        it => newWatchers.Add(new PositionedWatcher(it, depth))
                                    )
                                );
                            tagTree[tagTree.Count() - 1] = tag;
                            watchers
                                .ForEach(
                                    pair => pair.Watcher.TryToOpenTree?.Invoke(
                                        tagTree.ToArray(),
                                        it => newWatchers.Add(new PositionedWatcher(it, depth))
                                    )
                                );
                            watchers.AddRange(newWatchers);
                        }
                        else
                        {
                            watchers
                                .Where(it => it.Depth == depth)
                                .ForEach(it => it.Watcher.Tag?.Invoke(tag));
                        }
                    }

                    if (escapeContext.Asterisk() is ITerminalNode aNode && aNode != null)
                    {
                        var raw = aNode.GetText();
                        FlushText();
                        writer.Write("\\" + raw);

                        MayOpen(new WordN(raw, null));

                        nextFirstTag = false;
                    }
                    if (escapeContext.Hex() is ITerminalNode hexNode && hexNode != null)
                    {
                        var raw = hexNode.GetText();
                        var n = Convert.ToByte(raw.Substring(1), 16);
                        ConsumeText("" + (char)n);
                        watchers
                            .Where(it => it.Depth == depth)
                            .ForEach(it => it.Watcher.Tag?.Invoke(new WordN(raw.Substring(0, 1), n)));
                        nextFirstTag = false;
                    }
                    if (escapeContext.Control() is ITerminalNode controlNode && controlNode != null)
                    {
                        FlushText();
                        writer.Write("\\" + controlNode.GetText());

                        MayOpen(WordN.Parse(controlNode.GetText()));

                        if (fToCharset.TryGetValue(controlNode.GetText(), out var fcharset))
                        {
                            activeFontCharset = fcharset;
                        }

                        nextFirstTag = false;
                    }
                }
                else if (node.Text() is ITerminalNode textNode && textNode != null)
                {
                    ConsumeText(textNode.GetText());
                    watchers
                        .Where(it => it.Depth == depth)
                        .ForEach(it => it.Watcher.Text?.Invoke(textNode.GetText()));
                    nextFirstTag = false;
                }
                else if (node.NL() is ITerminalNode nlNode && nlNode != null)
                {
                    FlushText();
                    writer.Write(nlNode.GetText());
                    watchers
                        .Where(it => it.Depth == depth)
                        .ForEach(it => it.Watcher.Text?.Invoke(nlNode.GetText()));
                    nextFirstTag = false;
                }
            }
            FlushText();

            File.WriteAllText(
                opt.OutputRtf,
                writer.ToString(),
                new UTF8Encoding(false)
            );

            return 0;
        }

        private static Watcher GetRootWatcher2(Action<bool> onEnableConversion)
        {
            int enabler = 0;

            void AddToEnabler(int count)
            {
                enabler += count;

                onEnableConversion?.Invoke(1 <= enabler);
            }

            var watcher = new Watcher
            {
                TryToOpenTree = (tagTree, add) =>
                {
                    if (false) { }
                    else if (tagTree.Length == 1
                        && tagTree[0].ToString() == "rtf1"
                    )
                    {
                        AddToEnabler(1);

                        add(
                            new Watcher
                            {
                                Close = () =>
                                {
                                    AddToEnabler(-1);
                                },
                            }
                        );
                    }
                    else if (tagTree.Length == 2
                        && tagTree[0].ToString() == "rtf1"
                        && (tagTree[1].ToString() == "fonttbl" || tagTree[1].ToString() == "*")
                    )
                    {
                        AddToEnabler(-1);

                        add(
                            new Watcher
                            {
                                Close = () =>
                                {
                                    AddToEnabler(1);
                                },
                            }
                        );
                    }
                },
            };
            return watcher;
        }

        private static Watcher GetRootWatcher1(Action<WordN, int> onFCharset)
        {
            return new Watcher
            {
                TryToOpenTree = (tagTree, add) =>
                {
                    if (3 == tagTree.Length
                        && tagTree[0].ToString() == "rtf1"
                        && tagTree[1].ToString() == "fonttbl"
                    )
                    {
                        var fTag = tagTree[2];

                        add(
                            new Watcher
                            {
                                Tag = anyTag =>
                                {
                                    if (anyTag.Word == "fcharset" && anyTag.N.HasValue)
                                    {
                                        onFCharset?.Invoke(
                                            fTag,
                                            anyTag.N.Value
                                        );
                                    }
                                },
                            }
                        );
                    }
                },
            };
        }

        private static ICharStream FromString(string script, string sourceName)
        {
            var stream = CharStreams.fromString(script);
            if (stream is CodePointCharStream charStream)
            {
                charStream.name = sourceName;
            }
            return stream;
        }

        private class WordN
        {
            public string Word { get; set; }
            public int? N { get; set; }

            public WordN(string word, int? n)
            {
                Word = word;
                N = n;
            }

            public static WordN Parse(string text)
            {
                int x = 0, cx = text.Length;

                while (true)
                {
                    if (x == cx)
                    {
                        return new WordN(text, null);
                    }

                    if (char.IsDigit(text[x]))
                    {
                        return new WordN(text.Substring(0, x), int.Parse(text.Substring(x)));
                    }

                    x++;
                }
            }

            public override string ToString()
            {
                return N.HasValue ? Word + N : Word;
            }
        }

        private class PositionedWatcher
        {
            public PositionedWatcher(Watcher watcher, int depth)
            {
                Watcher = watcher;
                Depth = depth;
            }

            public Watcher Watcher { get; }
            public int Depth { get; }
        }

        private class Watcher
        {
            public Action<WordN, Action<Watcher>> TryToOpen { get; set; }
            public Action<WordN[], Action<Watcher>> TryToOpenTree { get; set; }
            public Action<WordN> Tag { get; set; }
            public Action<string> Text { get; set; }
            public Action Close { get; set; }
        }
    }
}