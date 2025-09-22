package com.bankcard.bank.service;

import com.bankcard.bank.mapper.CardMapper;
import com.bankcard.bank.pojo.Card;
import com.bankcard.bank.utils.AESutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CardService {
    @Autowired
    private CardMapper cardMapper;
//    secret key
    private final String key = "1234567890123456";

    public List<Card> getAll() throws Exception {
        List<Card> cards = cardMapper.getAllCards();
        for (Card card : cards) {
            String descrypted = AESutils.decrypt(card.getPan(),key);
            card.setPan(descrypted);
        }
        return cards;
    }


    public List<Card> search(String keyword) {
        return cardMapper.search(keyword);
    }

    public String addCard(Card card) throws Exception {
//        check if the card number provided is already exsits in database;
        Card result = cardMapper.getByPan(card.getPan());
        if (result != null) {
            return "PAN exists";
        }

        LocalDateTime now = LocalDateTime.now();
        card.setCreateTime(now);
//        encrypted the PAN
        String encrypted = AESutils.encrypt(card.getPan(), key);
        card.setPan(encrypted);
        cardMapper.addCard(card);
        return "Card added";
    }
}
