package com.bankcard.bank.mapper;

import com.bankcard.bank.pojo.Card;
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

    @Select("select * from card where right(pan,4) like concat('%',#{keyword},'%')")
    List<Card> search(String keyword);
}
