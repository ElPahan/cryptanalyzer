package ua.com.javarush.ppalekhov.cryptanalyzer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class Encode {
    private final ArrayList<Character> SOURCE_TEXT;
    private final ArrayList<Character> ALPHABET;
    private final int KEY;
    private final String SOURCE_PATH;

    public Encode(ArrayList<Character> sourceText, ArrayList<Character> alphabet, int key, String sourcePath) {
        this.SOURCE_TEXT = sourceText;
        this.ALPHABET = alphabet;
        this.KEY = key;
        this.SOURCE_PATH = sourcePath;
    }

    public String enCoding () {
        int baseIndex;
        int keyIndex;
        char[] encodeText = new char[SOURCE_TEXT.size()];
        Path path = Path.of(SOURCE_PATH);
        String destPath = path.getParent().toString() + "\\resultEncode.txt";

        for (int i = 0; i < SOURCE_TEXT.size(); i++) {
            baseIndex = ALPHABET.indexOf(SOURCE_TEXT.get(i));
            keyIndex = baseIndex + KEY;
            if (keyIndex > ALPHABET.size() - 1) {
                keyIndex -= ALPHABET.size();
            }
            encodeText[i] = ALPHABET.get(keyIndex);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destPath))) {
            writer.write(encodeText);
            return "Файл зашифрован и лежит по адресу: " + destPath;
        } catch (IOException e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
        return  "Операция не выполнена. Попробуйте снова";
    }
}
