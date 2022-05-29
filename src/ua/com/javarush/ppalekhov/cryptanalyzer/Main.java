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
        int maxValueKey = alphabet.size() - 1;
        int key;
        Scanner console = new Scanner(System.in);

        String filename = readFilePath(console, System.out, "Введите путь к файлу с текстом");
        MyFileReader myFileReader = new MyFileReader(filename);

        try {
            sourceText = myFileReader.readFile();
        } catch (FileProcessingException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("Введите ключ, для шифровки от нуля до " + maxValueKey);
        while (true) {
            String value = console.nextLine();
            if (isInteger(value, maxValueKey)) {
                key = Integer.parseInt(value);
                break;
            }
        }

        Encode result = new Encode(sourceText, alphabet, key);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\projects\\newfile.txt")) ) {
            writer.write(result.enCoding());
        } catch (IOException e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }

        Decode decode = new Decode(result.enCoding(), alphabet, key);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\projects\\newfile.txt")) ) {
            writer.write(decode.decoding());
        } catch (IOException e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
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
