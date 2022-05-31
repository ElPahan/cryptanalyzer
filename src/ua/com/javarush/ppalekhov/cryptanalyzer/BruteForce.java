package ua.com.javarush.ppalekhov.cryptanalyzer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class BruteForce {
    private final ArrayList<Character> sourceText;
    private final ArrayList<Character> alphabet;
    private final String sourcePath;
    private final int maxValueKey;
    private int shift;
    private boolean flag;

    public BruteForce(ArrayList<Character> sourceText, ArrayList<Character> alphabet, int maxValueKey, String sourcePath) {
        this.sourceText = sourceText;
        this.alphabet = alphabet;
        this.sourcePath = sourcePath;
        this.maxValueKey = maxValueKey;
    }

    public String breacking(Scanner console) {
        int variableOfKey;
        char[] testBreacking = new char[sourceText.size() / 10];
        char[] resultBreacking = new char[sourceText.size()];
        Path path = Path.of(sourcePath);
        String operation;
        String destPath = path.getParent().toString() + "\\resultBreacking.txt";

        for (int i = 0; i < maxValueKey; i++) {
            for (int j = 0; j < sourceText.size() / 10; j++) {
                variableOfKey = alphabet.indexOf(sourceText.get(j)) + i;
                if (variableOfKey > alphabet.size() - 1) {
                    variableOfKey -= alphabet.size();
                }
                testBreacking[j] = alphabet.get(variableOfKey);
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
                for (int k = 0; k < sourceText.size(); k++) {
                    key = alphabet.indexOf(sourceText.get(k)) + shift;
                    if (key > alphabet.size() - 1) {
                        key -= alphabet.size();
                    }
                    resultBreacking[k] = alphabet.get(key);
                }
                break;
            }
            if (i == maxValueKey - 1) {
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
