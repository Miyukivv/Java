package com.example.PixelGuardApplication;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Token {
    private int id;
    private LocalDateTime timeOfCreation;

    private static int actualValueOfToken=0;

    private static List<Token> tokens = new ArrayList<Token>();

    public Token() {
        id=actualValueOfToken++;
        timeOfCreation=LocalDateTime.now();
        tokens.add(this);
    }

    public static List<Token> getTokens() {
        return tokens;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getTimeOfCreation() {
        return timeOfCreation;
    }


    public boolean isTokenActive() {
        LocalDateTime now = LocalDateTime.now();
        long elapsedMinutes= ChronoUnit.MINUTES.between(timeOfCreation, now);

        return elapsedMinutes<5;
    }

    public static void removeToken(int id){
        for (Token token : tokens){
            if (token.getId()==id){
                tokens.remove(token);
            }
        }
    }

    static class TokenDTO {
        public int id;
        public LocalDateTime timeOfCreation;
        public boolean isActive;

        public TokenDTO(Token token) {
            this.id = token.id;
            this.timeOfCreation = token.timeOfCreation;
            this.isActive = token.isTokenActive();
        }

    }
}