/*
 * Copyright (c) 2020, Rían Errity. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 3 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 3 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 3 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Rían Errity <rian@paradaux.io> or visit https://paradaux.io
 * if you need additional information or have any questions.
 * See LICENSE.md for more details.
 */

package io.paradaux.ai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

/**
 * Modifications Copyright Rían Errity, 2021, https://paradaux.io
 * Copyright Paul James Mutton, 2001-2004, http://www.jibble.org/
 *
 * @author pjm2
 * @author Rían Errity
 * <p>
 * This software is dual-licensed, allowing you to choose between the GNU
 * General Public License (GPL) and the www.jibble.org Commercial License.
 * Since the GPL may be too restrictive for use in a proprietary application,
 * a commercial license is also provided. Full license information can be found at http://www.jibble.org/licenses/
 *
 *
 */
public class MarkovMegaHal {

    // These are valid chars for words. Anything else is treated as punctuation.
    public static final String WORD_CHARS = "abcdefghijklmnopqrstuvwxyz"
            + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789";

    public static final String END_CHARS = ".!?";

    private final HashMap<String, HashSet<Quad>> words = new HashMap<>();
    private final HashMap<Quad, Quad> quads = new HashMap<>();
    private final HashMap<Quad, HashSet<String>> next = new HashMap<>();
    private final HashMap<Quad, HashSet<String>> previous = new HashMap<>();
    private final Random rand = new Random();

    /**
     * MarkovMegaHal is a fork of JMegaHal maintained by Rían Errity.
     *
     */
    public MarkovMegaHal() {

    }

    /**
     * Adds an entire documents to the 'brain'. Useful for feeding in
     * stray theses, but be careful not to put too much in, or you may
     * run out of memory!
     *
     * @param uri The location of the document you wish to add.
     */
    public void addDocument(String uri) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(uri).openStream()));
        StringBuilder buffer = new StringBuilder();

        int ch;

        while ((ch = reader.read()) != -1) {
            buffer.append((char) ch);
            if (END_CHARS.indexOf((char) ch) >= 0) {
                String sentence = buffer.toString();
                sentence = sentence.replace('\r', ' ');
                sentence = sentence.replace('\n', ' ');
                add(sentence);
                buffer = new StringBuilder();
            }
        }
        add(buffer.toString());
        reader.close();
    }

    /**
     * Adds a new sentence to the 'brain.'
     *
     * @param sentence The sentence you wish to "teach" the A.I.
     */
    public void add(String sentence) {
        sentence = sentence.trim();
        ArrayList<String> parts = new ArrayList<>();
        char[] chars = sentence.toCharArray();
        int i = 0;
        boolean punctuation = false;
        StringBuilder buffer = new StringBuilder();
        while (i < chars.length) {
            char ch = chars[i];
            if ((WORD_CHARS.indexOf(ch) >= 0) == punctuation) {
                punctuation = !punctuation;
                String token = buffer.toString();
                if (!token.isEmpty()) {
                    parts.add(token);
                }
                buffer = new StringBuilder();
                //i++;
                continue;
            }
            buffer.append(ch);
            i++;
        }

        String lastToken = buffer.toString();
        if (!lastToken.isEmpty()) {
            parts.add(lastToken);
        }

        if (parts.size() >= 4) {
            for (i = 0; i < parts.size() - 3; i++) {

                Quad quad = new Quad(parts.get(i), parts.get(i + 1), parts.get(i + 2), parts.get(i + 3));

                if (quads.containsKey(quad)) {
                    quad = quads.get(quad);
                } else {
                    quads.put(quad, quad);
                }

                if (i == 0) {
                    quad.setCanStart(true);
                }

                if (i == parts.size() - 4) {
                    quad.setCanEnd(true);
                }

                for (int n = 0; n < 4; n++) {
                    String token = parts.get(i + n);
                    if (!words.containsKey(token)) {
                        words.put(token, new HashSet<>(1));
                    }
                    HashSet<Quad> set = words.get(token);
                    set.add(quad);
                }

                if (i > 0) {
                    String previousToken = parts.get(i - 1);
                    if (!previous.containsKey(quad)) {
                        previous.put(quad, new HashSet<>(1));
                    }

                    HashSet<String> set = previous.get(quad);
                    set.add(previousToken);
                }

                if (i < parts.size() - 4) {
                    String nextToken = parts.get(i + 4);
                    if (!next.containsKey(quad)) {
                        next.put(quad, new HashSet<>(1));
                    }
                    HashSet<String> set = next.get(quad);
                    set.add(nextToken);
                }

            }
        }

    }

    /**
     * Generate an ai-generated sentence.
     *
     * @return String containing the ai-generated sentence.
     */
    public String getSentence() {
        return getSentence(null);
    }

    /**
     * Generate a sentence that includes (if possible) the specified word.
     *
     * @param word The specified word you wish to generate a sentence containing
     * @return String containing the ai-generated sentence.
     */
    public String getSentence(String word) {
        LinkedList<String> parts = new LinkedList<>();

        Quad[] quads;
        if (words.containsKey(word)) {
            quads = (words.get(word)).toArray(new Quad[0]);
        } else {
            quads = this.quads.keySet().toArray(new Quad[0]);
        }

        if (quads.length == 0) {
            return "";
        }

        Quad middleQuad = quads[rand.nextInt(quads.length)];
        Quad quad = middleQuad;

        for (int i = 0; i < 4; i++) {
            parts.add(quad.getToken(i));
        }

        while (!quad.canEnd()) {
            String[] nextTokens = (next.get(quad)).toArray(new String[0]);
            String nextToken = nextTokens[rand.nextInt(nextTokens.length)];
            quad = this.quads.get(new Quad(quad.getToken(1), quad.getToken(2), quad.getToken(3), nextToken));
            parts.add(nextToken);
        }

        quad = middleQuad;
        while (!quad.canStart()) {
            String[] previousTokens = (previous.get(quad)).toArray(new String[0]);
            String previousToken = previousTokens[rand.nextInt(previousTokens.length)];
            quad = this.quads.get(new Quad(previousToken, quad.getToken(0), quad.getToken(1), quad.getToken(2)));
            parts.addFirst(previousToken);
        }

        StringBuilder sentence = new StringBuilder();

        for (String part : parts) {
            sentence.append(part);
        }

        return sentence.toString();
    }
}