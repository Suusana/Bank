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
            String descrypted = AESutils.decrypt(card.getPan(), key);
            card.setPan(descrypted);
        }
        return cards;
    }

    public List<Card> search(String keyword) throws Exception {
        List<Card> cards = cardMapper.search(keyword);
        // descrypted the PAN
        for (Card card : cards) {
            String descrypted = AESutils.decrypt(card.getPan(), key);
            card.setPan(descrypted);
        }
        return cards;
    }

    public String addCard(Card card) throws Exception {
        String pan = card.getPan();
        String last4Digits = pan.substring(pan.length() - 4);
//        check if the card number provided is already exsits in database;
        //        encrypted the PAN
        String encrypted = AESutils.encrypt(pan, key);
        Card result = cardMapper.getByPan(encrypted);
        if (result != null) {
            return "PAN exists";
        }

        LocalDateTime now = LocalDateTime.now();
        card.setCreateTime(now);
        card.setPan(encrypted);
        card.setLast4Digits(last4Digits);

        cardMapper.addCard(card);
        return "Card added";
    }
}
