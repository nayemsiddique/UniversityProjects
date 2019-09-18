package com.nayemsiddique.studentgradeserver.exception;

public class FileAlreadyExistsExceptions extends Exception {
    public FileAlreadyExistsExceptions(String message) {
        super(message + "Already Exists");
    }
}
