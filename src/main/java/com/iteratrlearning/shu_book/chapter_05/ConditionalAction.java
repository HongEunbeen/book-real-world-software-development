package com.iteratrlearning.shu_book.chapter_05;

public interface ConditionalAction {
    boolean evaluate(Facts facts);
    void perform(Facts facts);
}
