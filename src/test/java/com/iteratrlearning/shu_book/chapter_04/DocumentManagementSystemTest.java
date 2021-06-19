package com.iteratrlearning.shu_book.chapter_04;

import com.iteratrlearning.shu_book.chapter_04.exception.UnknownFileTypeException;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static com.iteratrlearning.shu_book.chapter_04.importer.Attributes.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class DocumentManagementSystemTest {
    private static final String RESOURCES = "src" + File.separator + "test" + File.separator + "resources" + File.separator;

    final DocumentManagementSystem system = new DocumentManagementSystem();

    public Document onlyDocument(){
        final List<Document> documents = system.contents();
        assertThat(documents, hasSize(1));
        return documents.get(0);
    }

    private void assertAttributeEquals(
            final Document document,
            final String attributeName,
            final String expectedValue
    ){
        assertEquals("Document has the wrong value for " + attributeName,expectedValue, document.getAttribute(attributeName));
    }

    @Test
    public void shouldImportFile() throws Exception{
        String path = "";
        system.importFile(path);

        final Document document = onlyDocument();

        assertAttributeEquals(document, PATIENT, "Joe Bloggs");
        assertAttributeEquals(document, ADDRESS,
                "123 Fake Street\n" +
                "Westminster\n" +
                "London\n" +
                "United Kingdom");
        assertAttributeEquals(document, BODY,
                "We are writing to you to confirm the re-scheduling of your appointment\n" +
                        "with Dr. Avaj from 29th December 2016 to 5th January 2017.");
        assertEquals("LETTER", document.getAttribute(TYPE));
    }

    @Test(expected = FileNotFoundException.class)
    public void shouldNotImportMissingFile() throws Exception{
        system.importFile("gobbledygook.txt");
    }

    @Test(expected = UnknownFileTypeException.class)
    public void shouldNotFileTypeException() throws Exception{
        system.importFile(RESOURCES + "unknowns.txt");
    }
}
