package com.bankcard.bank.pojo;

import java.time.LocalDateTime;

public class Card {
    private Integer id;
    private String cardHolder;
    private String pan;
    private LocalDateTime createTime;

    public Card(Integer id, String cardHolder, String pan, LocalDateTime createTime) {
        this.id = id;
        this.cardHolder = cardHolder;
        this.pan = pan;
        this.createTime = createTime;
    }

    public Card() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
