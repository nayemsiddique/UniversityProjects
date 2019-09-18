package com.nayemsiddique.studentadmissionserver.exception;

public class FileDoesnotExistsException extends Exception {
    public FileDoesnotExistsException(String message) {
        super(message + "Doesn't Exists");
    }
}
