package com.bankcard.bank.controller;

import com.bankcard.bank.pojo.Card;
import com.bankcard.bank.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {
    @Autowired
    private CardService cardService;

    /**
     * @return All cards from the database
     */
    @GetMapping("/getAll")
    public List<Card> getAll() throws Exception {
        return cardService.getAll();
    }

    /**
     * @param keyword the keyword for searching the last 4 digits users entered
     * @return List of cards
     */
    @GetMapping("/search")
    public List<Card> search(@RequestParam String keyword) throws Exception {
        return cardService.search(keyword);
    }

    /**
     * @param card the card submitted by user
     */
    @PostMapping("/addCard")
    public String addCard(@RequestBody Card card) throws Exception {
        return cardService.addCard(card);
    }
}
