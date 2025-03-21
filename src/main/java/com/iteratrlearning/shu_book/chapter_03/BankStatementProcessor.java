package com.iteratrlearning.shu_book.chapter_03;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
interface BankTransactionSummarizer{
    double summarize(double accumulator, BankTransaction bankTransaction);
}

@FunctionalInterface
interface BankTransactionFilter{
    boolean test(BankTransaction bankTransaction);
}


public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double summarizeTransactions(final BankTransactionSummarizer bankTransactionSummarizer){
        double result = 0;
        for(final BankTransaction bankTransaction : bankTransactions) {
            result = bankTransactionSummarizer.summarize(result,bankTransaction);
        }
        return result;
    }

    public double calculateTotalInMonth(final Month month){
        return summarizeTransactions((acc, bankTransaction) ->
                bankTransaction.getDate().getMonth() == month ? acc + bankTransaction.getAmount() : acc
        );
    }

    public double calculateTotalForCategory(final String category){
        return summarizeTransactions((acc , bankTransaction) ->
            bankTransaction.getDescription().equals(category) ? acc + bankTransaction.getAmount() : acc
        );
    }

    public double calculateTotalAmount(){
        return summarizeTransactions((acc, bankTransaction) ->
                acc + bankTransaction.getAmount()
        );
    }

    public List<BankTransaction> findTransactions(final BankTransactionFilter bankTransactionFilter){
        final List<BankTransaction> result = new ArrayList<>();
        for(final BankTransaction bankTransaction : bankTransactions){
            if(bankTransactionFilter.test(bankTransaction)){
                result.add(bankTransaction);
            }
        }
        return bankTransactions;
    }

    public List<BankTransaction> findTransactionGreaterThanEqual(final int amount){
        return findTransactions(bankTransaction ->
                bankTransaction.getAmount() >= amount
        );
    }

}
