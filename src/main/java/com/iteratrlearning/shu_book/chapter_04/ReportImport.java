package com.iteratrlearning.shu_book.chapter_04;

import com.iteratrlearning.shu_book.chapter_04.importer.Importer;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import static com.iteratrlearning.shu_book.chapter_04.importer.Attributes.*;

public class ReportImport implements Importer {
    @Override
    public Document importFile(File file) throws IOException {
        final TextFile textFile = new TextFile(file);

        textFile.addLineSuffix(NAME_PREFIX, PATIENT);
        textFile.addLineSuffix(AMOUNT_PREFIX, AMOUNT);

        final Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, "INVOICE");
        return new Document(attributes);
    }
}
