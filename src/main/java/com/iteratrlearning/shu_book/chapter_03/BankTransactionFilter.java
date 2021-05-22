package com.iteratrlearning.shu_book.chapter_03;

import java.time.Month;

@FunctionalInterface
public interface BankTransactionFilter {
    boolean test(BankTransaction bankTransaction);

}
