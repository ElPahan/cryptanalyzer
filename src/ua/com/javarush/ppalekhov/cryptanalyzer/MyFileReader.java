package ua.com.javarush.ppalekhov.cryptanalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class MyFileReader {
    private final String PATH_TO_FILE;
    private int numberOfLines;

    public MyFileReader(String path) {
        this.PATH_TO_FILE = path;
    }

    public ArrayList<Character> readFile() {
        ArrayList<Character> list = new ArrayList<>();
        char[] charArray;
        String[] stringArray;

        try (FileReader reader = new FileReader(this.PATH_TO_FILE);
             BufferedReader bufReader = new BufferedReader(reader)) {
            stringArray = new String[countOfLines()];
            for (int i = 0; i < stringArray.length; i++) {
                stringArray[i] = bufReader.readLine().toLowerCase();
                charArray = stringArray[i].toCharArray();
                for (int j = 0; j < charArray.length; j++) {
                    list.add(charArray[j]);
                }
            }
        }  catch (IOException e) {
            throw new FileProcessingException("Ошибка при чтении файла: " + PATH_TO_FILE, e);
        }
        return list;
    }

    private int countOfLines() {
        try (FileReader reader = new FileReader(this.PATH_TO_FILE);
             BufferedReader bufReader = new BufferedReader(reader)) {
            while (bufReader.readLine() != null) {
                numberOfLines++;
            }
        } catch (IOException e) {
            throw new FileProcessingException("Ошибка при чтении файла: " + PATH_TO_FILE, e);
        }
        return numberOfLines;
    }
}
