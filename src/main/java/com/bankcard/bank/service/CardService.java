package com.bankcard.bank.service;

import com.bankcard.bank.mapper.CardMapper;
import com.bankcard.bank.pojo.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    @Autowired
    private CardMapper cardMapper;

    /**
     * @return All cards from the database
     */
    public List<Card> getAll() {
        return cardMapper.getAllCards();
    }


    public List<Card> search(String keyword) {
        return cardMapper.search(keyword);
    }
}
