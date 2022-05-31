package ua.com.javarush.ppalekhov.cryptanalyzer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class BruteForce {
    private final ArrayList<Character> SOURCE_TEXT;
    private final ArrayList<Character> ALPHABET;
    private final String SOURCE_PATH;
    private final int MAX_VALUE_KEY;
    private int shift;
    private boolean flag;

    public BruteForce(ArrayList<Character> sourceText, ArrayList<Character> alphabet, int maxValueKey, String sourcePath) {
        this.SOURCE_TEXT = sourceText;
        this.ALPHABET = alphabet;
        this.SOURCE_PATH = sourcePath;
        this.MAX_VALUE_KEY = maxValueKey;
    }

    public String breacking(Scanner console) {
        int variableOfKey;
        char[] testBreacking = new char[SOURCE_TEXT.size() / 10];
        char[] resultBreacking = new char[SOURCE_TEXT.size()];
        Path path = Path.of(SOURCE_PATH);
        String operation;
        String destPath = path.getParent().toString() + "\\resultBreacking.txt";

        for (int i = 0; i < MAX_VALUE_KEY; i++) {
            for (int j = 0; j < SOURCE_TEXT.size() / 10; j++) {
                variableOfKey = ALPHABET.indexOf(SOURCE_TEXT.get(j)) + i;
                if (variableOfKey > ALPHABET.size() - 1) {
                    variableOfKey -= ALPHABET.size();
                }
                testBreacking[j] = ALPHABET.get(variableOfKey);
                System.out.print(testBreacking[j]);
            }

            System.out.println("\nВведите '+', если вариант ключа подходит.\nИли нажмите 'enter' для слудеющей попытки. ");
            operation = console.nextLine();

            if ("+".equals(operation)) {
                flag = true;
                shift = i;
            }
            if (flag) {
                int key;
                for (int k = 0; k < SOURCE_TEXT.size(); k++) {
                    key = ALPHABET.indexOf(SOURCE_TEXT.get(k)) + shift;
                    if (key > ALPHABET.size() - 1) {
                        key -= ALPHABET.size();
                    }
                    resultBreacking[k] = ALPHABET.get(key);
                }
                break;
            }
            if (i == MAX_VALUE_KEY - 1) {
                return ("Перебор всех возможных ключей завершен. Проверьте внимаетельнее");
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destPath))) {
            writer.write(resultBreacking);
            return "Файл разшифрован и лежит по адресу: " + destPath;
        } catch (IOException e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
        return "Операция не выполнена. Попробуйте снова";
    }
}
