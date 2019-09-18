package com.nayemsiddique.studentadmissionserver.exception;

public class FileAlreadyExistsExceptions extends Exception {
    public FileAlreadyExistsExceptions(String message) {
        super(message + "Already Exists");
    }
}
