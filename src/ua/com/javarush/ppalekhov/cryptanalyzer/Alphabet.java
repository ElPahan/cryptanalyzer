package ua.com.javarush.ppalekhov.cryptanalyzer;

import java.util.ArrayList;
import java.util.Arrays;

public class Alphabet {
    private final Alphabet INSTANCE = new Alphabet();
    private static final ArrayList<Character> ALPHABET = new ArrayList<>(Arrays.asList('а', 'б', 'в',
            'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у',
            'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»',
            ':', '!', '?', ' '));
    private Alphabet() {}

    public static ArrayList<Character> getAlphabet() {
        return ALPHABET;
    }
}
