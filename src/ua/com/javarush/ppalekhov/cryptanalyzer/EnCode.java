package ua.com.javarush.ppalekhov.cryptanalyzer;

import java.util.ArrayList;

public class EnCode {
    private final ArrayList<Character> sourceText;
    private final ArrayList<Character> alphabet;
    private final int key;

    public EnCode(ArrayList<Character> sourceText, ArrayList<Character> alphabet, int key) {
        this.sourceText = sourceText;
        this.alphabet = alphabet;
        this.key = key;
    }

    public ArrayList<Character> enCoding () {
        int baseIndex;
        int keyIndex;

        for (int i = 0; i < sourceText.size() - 1; i++) {
            baseIndex = alphabet.indexOf(sourceText.get(i));
            keyIndex = baseIndex + key;
            if (keyIndex > alphabet.size() - 1) {
                keyIndex -= alphabet.size();
            }
            sourceText.set(i, alphabet.get(keyIndex));
        }
        return sourceText;
    }
}
