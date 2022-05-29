package ua.com.javarush.ppalekhov.cryptanalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class MyFileReader {
    private String pathToFile;
    private int numberOfLines;

    public MyFileReader(String path) {
        this.pathToFile = path;
    }

    public ArrayList<Character> readFile() {
        ArrayList<Character> list = new ArrayList<>();
        char[] charArray;
        String[] stringArray;

        try (FileReader reader = new FileReader(this.pathToFile);
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
            throw new FileProcessingException("Ошибка при чтении файла: " + pathToFile, e);
        }
        return list;
    }

    private int countOfLines() {
        try (FileReader reader = new FileReader(this.pathToFile);
             BufferedReader bufReader = new BufferedReader(reader)) {
            while (bufReader.readLine() != null) {
                numberOfLines++;
            }
        } catch (IOException e) {
            throw new FileProcessingException("Ошибка при чтении файла: " + pathToFile, e);
        }
        return numberOfLines;
    }
}
