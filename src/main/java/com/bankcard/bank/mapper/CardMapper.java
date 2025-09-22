package com.bankcard.bank.mapper;

import com.bankcard.bank.pojo.Card;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CardMapper {

    /**
     * @return All cards from the database
     */
    @Select("select * from Card")
    List<Card> getAllCards();


    /**
     * @param keyword the keyword for searching the last 4 digits users entered
     * @return List of cards
     */
    @Select("select * from card where last_4_digits like concat('%',#{keyword},'%')")
    List<Card> search(String keyword);

    /**
     * @param card the card submitted by user
     */
    @Insert("insert into card (card_holder,PAN,create_time,last_4_digits) values (#{cardHolder},#{pan},#{createTime},#{last4Digits})")
    void addCard(Card card);


    /**
     * @param pan the pan from new card provided
     * @return the card that contains the same PAN
     */
    @Select("select * from card where PAN = #{pan}")
    Card getByPan(String pan);
}
