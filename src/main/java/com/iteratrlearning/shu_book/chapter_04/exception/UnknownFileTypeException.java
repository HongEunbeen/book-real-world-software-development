package com.iteratrlearning.shu_book.chapter_04.exception;

import java.io.IOException;

public class UnknownFileTypeException extends IOException {
    public UnknownFileTypeException(){
        super();
    }
    public UnknownFileTypeException(String msg) {
        super(msg);
    }
}
