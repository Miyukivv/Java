package com.example.PixelGuardApplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TokenController {

    @PostMapping("/register")
    public Map<String, String> register() {
        Token token = new Token();

        Map<String, String> map = new HashMap<>();
        map.put("token", String.valueOf(token.getId()));
        map.put("dateOfCreation", token.getTimeOfCreation().toString());

        return map;
    }

    @GetMapping("/tokens")
    public ArrayList<Token.TokenDTO> getTokens() {
        List<Token.TokenDTO> tokensDTOs = new ArrayList<>();
        for (Token t : Token.getTokens()) {
            tokensDTOs.add(new Token.TokenDTO(t));
        }
        return (ArrayList<Token.TokenDTO>) tokensDTOs;
    }
}
