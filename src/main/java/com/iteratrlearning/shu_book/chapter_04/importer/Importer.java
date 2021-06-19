package com.iteratrlearning.shu_book.chapter_04.importer;

import com.iteratrlearning.shu_book.chapter_04.Document;

import java.io.File;
import java.io.IOException;

public interface Importer {
    Document importFile(File file) throws IOException;
}
