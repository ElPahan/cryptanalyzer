package ua.com.javarush.ppalekhov.cryptanalyzer;

import java.util.ArrayList;
import java.util.Arrays;

public class Alphabet {

    private static final ArrayList<Character> ALPHABET = new ArrayList<>(Arrays.asList('а', 'б', 'в',
            'г', 'д', 'е', 'ж', 'з', 'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у',
            'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»',
            ':', '!', '?', ' '));

    public ArrayList<Character> getAlphabet() {
        return ALPHABET;
    }
}
