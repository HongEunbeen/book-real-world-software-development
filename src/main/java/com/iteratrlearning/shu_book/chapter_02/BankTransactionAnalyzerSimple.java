package com.iteratrlearning.shu_book.chapter_02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankTransactionAnalyzerSimple {
    static final String RESOURCES = "src/main/resources";

    public static void main(final String... args) throws IOException {

        final BankStatementCSVParser bankStatementParser = new BankStatementCSVParser();

        final String fileName = args[0];
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFromCSV(lines);


    }


}
