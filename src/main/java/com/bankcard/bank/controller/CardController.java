package com.bankcard.bank.controller;

import com.bankcard.bank.pojo.Card;
import com.bankcard.bank.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardController {
    @Autowired
    private CardService cardService;

    /**
     * @return All cards from the database
     */
    @GetMapping("/getAll")
    public List<Card> getAll() {
        return cardService.getAll();
    }

    @GetMapping("/search")
    public List<Card> search(@RequestParam String keyword) {
        return cardService.search(keyword);
    }
}
