package com.example.klient;


import java.time.LocalDateTime;
import java.util.ArrayList;


public class WordContainer {
    private ArrayList<Word> wordList = new ArrayList<>();
    private ArrayList<Word> wordsNotAdded = new ArrayList<>();
    private int wordCountLabel = 0;

    private WordFilter wordFilter;



    public WordContainer(WordFilter wordFilter) {
        this.wordFilter = wordFilter;
    }

    public void addWord(String wordString){
        if(!wordFilter.isWordMatching(wordString)){
            wordsNotAdded.add(new Word(wordString, LocalDateTime.now()));
            return;
        }
        Word word = new Word(wordString, LocalDateTime.now());
        wordList.add(word);

        wordList.sort((w1, w2) -> w1.getWordString().compareTo(w2.getWordString()));

        wordCountLabel = wordList.size();
    }
    public void setFilter(WordFilter wordFilter) {
        this.wordFilter = wordFilter;
    }
    @Override
    public String toString() {
        String result = "";
        for(Word w : wordList){
            result += w.getWordString() + " " + w.getTime() + "\n";
        }
        return result;
    }

    public ArrayList<Word> getWords() {
        return wordList;
    }

    public int getWordCountLabel() {
        return wordCountLabel;
    }

    public String getWordsNotAddedString() {
        String output = "";
        for(Word w : wordsNotAdded){
            output += w.getWordString() + " " + w.getTime() + "\n";
        }
        return output;
    }

    class Word {
        private String wordString;
        private LocalDateTime time;

        public Word(String wordString, LocalDateTime time) {
            this.wordString = wordString;
            this.time = time;
        }

        public String getWordString() {
            return wordString;
        }

        public LocalDateTime getTime() {
            return time;
        }

    }
}