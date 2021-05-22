package com.iteratrlearning.shu_book.chapter_03;

import java.time.Month;

public class BankTransactionIsFebruaryAndExpensive implements BankTransactionFilter{
    @Override
    public boolean test(final BankTransaction bankTransaction){
        return bankTransaction.getDate().getMonth() == Month.FEBRUARY
                && bankTransaction.getAmount() >= 1_000;
    }
}
