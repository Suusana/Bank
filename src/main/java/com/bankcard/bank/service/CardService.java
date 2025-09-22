package com.bankcard.bank.service;

import com.bankcard.bank.mapper.CardMapper;
import com.bankcard.bank.pojo.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CardService {
    @Autowired
    private CardMapper cardMapper;

    public List<Card> getAll() {
        return cardMapper.getAllCards();
    }


    public List<Card> search(String keyword) {
        return cardMapper.search(keyword);
    }

    public String addCard(Card card) {
//        check if the card number provided is already exsits in database;
        Card result = cardMapper.getByPan(card.getPan());
        if (result != null) {
            return "PAN exists";
        }

        LocalDateTime now = LocalDateTime.now();
        card.setCreateTime(now);
        cardMapper.addCard(card);
        return "Card added";
    }
}
