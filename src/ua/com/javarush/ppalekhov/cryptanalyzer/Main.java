package ua.com.javarush.ppalekhov.cryptanalyzer;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Character> sourceText = null;
        ArrayList<Character> alphabet = new ArrayList<>(Alphabet.getAlphabet());
        Scanner console = new Scanner(System.in);
        int maxValueKey = alphabet.size() - 1;
        int key;
        String operation;

        String filename = readFilePath(console, System.out, "Введите полный путь к файлу с текстом");
        MyFileReader myFileReader = new MyFileReader(filename);
        try {
            sourceText = myFileReader.readFile();
        } catch (FileProcessingException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("Введите режим работы: 'шифровка' или 'расшифровка'");
        operation = console.nextLine();
        if ("шифровка".equalsIgnoreCase(operation)) {
            System.out.println("Введите ключ, для шифровки от нуля до " + maxValueKey);
            while (true) {
                String value = console.nextLine();
                if (isInteger(value, maxValueKey)) {
                    key = Integer.parseInt(value);
                    break;
                }
            }
            Encode encode = new Encode(sourceText, alphabet, key, filename);
            System.out.println(encode.enCoding());
        }


        else if ("расшифровка".equalsIgnoreCase(operation)) {
            System.out.format("Ключ шифра лежит в диапозоне: от 0 до %d \nВведите значение ключа или 'взлом'\n",
                    maxValueKey);
            operation = console.nextLine();
            if ("взлом".equalsIgnoreCase(operation)) {
                BruteForce bruteForce = new BruteForce(sourceText, alphabet, maxValueKey, filename);
                System.out.println(bruteForce.breacking(console));
            } else if (isInteger(operation, maxValueKey)) {
                key = Integer.parseInt(operation);
                Decode decode = new Decode(sourceText, alphabet, key, filename);
                System.out.println(decode.decoding());

            } else {
                System.out.println("Операция не распознана. Попробуйте снова");
                System.exit(0);
            }
        }


        else {
            System.out.println("Операция не распознана. Попробуйте снова");
            System.exit(0);
        }

    }

    private static String readFilePath(Scanner console, PrintStream out, String message) {
        out.println(message);
        String filename = console.nextLine();
        try {
            Path path = Path.of(filename);
            if (Files.isDirectory(path)) {
                throw new IllegalArgumentException("Указанный путь - директория!");
            }
            if (!Files.isReadable(path)) {
                throw new IllegalArgumentException("Файл по указанному пути не читается!");
            }
        } catch (InvalidPathException e) {
            throw new IllegalArgumentException("Указанный путь не корректен!", e);
        }
        return filename;
    }

    private static boolean isInteger(String value, int maxValueKey) {
        try {
            int key = Integer.parseInt(value);
            if (key <= 0 || key > maxValueKey) {
                System.out.println("Введите значение согласно условию!");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Вы ввели не цифру!");
            return false;
        }
    }

}
