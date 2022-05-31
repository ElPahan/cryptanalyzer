package ua.com.javarush.ppalekhov.cryptanalyzer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class Decode {
    private final ArrayList<Character> sourceText;
    private final ArrayList<Character> alphabet;
    private final int key;
    private final String sourcePath;

    public Decode(ArrayList<Character> sourceText, ArrayList<Character> alphabet, int key, String sourcePath) {
        this.sourceText = sourceText;
        this.alphabet = alphabet;
        this.key = key;
        this.sourcePath = sourcePath;
    }

    public String decoding() {
        int baseIndex;
        int keyIndex;
        char[] encodeText = new char[sourceText.size()];
        Path path = Path.of(sourcePath);
        String destPath = path.getParent().toString() + "\\resultDecode.txt";

        for (int i = 0; i < sourceText.size(); i++) {
            baseIndex = alphabet.indexOf(sourceText.get(i));
            keyIndex = baseIndex - key;
            if (keyIndex < 0) {
                keyIndex += alphabet.size();
            }
            encodeText[i] = alphabet.get(keyIndex);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destPath)) ) {
            writer.write(encodeText);
            return "Файл разшифрован и лежит по адресу: " + destPath;
        } catch (IOException e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
        return  "Операция не выполнена. Попробуйте снова";
    }
}
