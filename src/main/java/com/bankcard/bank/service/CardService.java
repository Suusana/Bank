package com.bankcard.bank.service;

import com.bankcard.bank.mapper.CardMapper;
import com.bankcard.bank.pojo.Card;
import com.bankcard.bank.utils.AESutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CardService {
    @Autowired
    private CardMapper cardMapper;

    // get iv from application.properties
    @Value("${aes.iv}")
    private String iv;

    // get secret key from application.properties
    @Value("${aes.key}")
    private String key;

    public List<Card> getAll() throws Exception {
        return cardMapper.getAllCards();
    }

    public List<Card> search(String keyword) throws Exception {
        return cardMapper.search(keyword);
    }

    public Integer addCard(Card card) throws Exception {
        String pan = card.getPan();
        String last4Digits = pan.substring(pan.length() - 4);
//        check if the card number provided is already exsits in database;
        //        encrypted the PAN
        String encrypted = AESutils.encrypt(pan, key,iv);
        Card result = cardMapper.getByPan(encrypted);
        if (result != null) {
            return 0; // Card already exists
        }

        LocalDateTime now = LocalDateTime.now();
        card.setCreateTime(now);
        card.setPan(encrypted);
        card.setLast4Digits(last4Digits);

        return cardMapper.addCard(card);// output: 1, means card already inserted to the database
    }
}
