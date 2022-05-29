package ua.com.javarush.ppalekhov.cryptanalyzer;

import java.util.ArrayList;

public class Decode {
    private final char[] sourceText;
    private final ArrayList<Character> alphabet;
    private final int key;

    public Decode(char[] sourceText, ArrayList<Character> alphabet, int key) {
        this.sourceText = sourceText;
        this.alphabet = alphabet;
        this.key = key;
    }

    public char[] decoding() {
        int baseIndex;
        int keyIndex;
        char[] encodeText = new char[sourceText.length];

        for (int i = 0; i < sourceText.length; i++) {
            baseIndex = alphabet.indexOf(sourceText[i]);
            keyIndex = baseIndex - key;
            if (keyIndex < 0) {
                keyIndex += alphabet.size();
            }
            encodeText[i] = alphabet.get(keyIndex);
        }
        return encodeText;
    }
}
