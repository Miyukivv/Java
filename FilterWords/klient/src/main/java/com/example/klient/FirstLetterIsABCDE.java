package com.example.klient;

public class FirstLetterIsABCDE implements WordFilter {
    @Override
    public boolean isWordMatching(String word) {
        if (word == null || word.isEmpty()) return false;
        char firstChar = Character.toUpperCase(word.charAt(0));
        return firstChar == 'A' || firstChar == 'B' || firstChar == 'C' || firstChar == 'D' || firstChar == 'E';
    }
}
