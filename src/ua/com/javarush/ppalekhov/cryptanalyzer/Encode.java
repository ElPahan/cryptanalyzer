package ua.com.javarush.ppalekhov.cryptanalyzer;

import java.util.ArrayList;

public class Encode {
    private final ArrayList<Character> sourceText;
    private final ArrayList<Character> alphabet;
    private final int key;

    public Encode(ArrayList<Character> sourceText, ArrayList<Character> alphabet, int key) {
        this.sourceText = sourceText;
        this.alphabet = alphabet;
        this.key = key;
    }

    public char[] enCoding () {
        int baseIndex;
        int keyIndex;
        char[] encodeText = new char[sourceText.size()];

        for (int i = 0; i < sourceText.size(); i++) {
            baseIndex = alphabet.indexOf(sourceText.get(i));
            keyIndex = baseIndex + key;
            if (keyIndex > alphabet.size() - 1) {
                keyIndex -= alphabet.size();
            }
            encodeText[i] = alphabet.get(keyIndex);
        }
        return encodeText;
    }
}
