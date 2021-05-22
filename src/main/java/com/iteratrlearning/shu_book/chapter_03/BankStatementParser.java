package com.iteratrlearning.shu_book.chapter_03;

import java.util.List;

public interface BankStatementParser {
    BankTransaction parserFrom(String line);
    List<BankTransaction> parserLinesFrom(List<String> lines);
}
