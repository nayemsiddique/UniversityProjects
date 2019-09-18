package com.nayemsiddique.humanresourceserver.exception;

public class FileAlreadyExistsExceptions extends Exception {
    public FileAlreadyExistsExceptions(String message) {
        super(message + "Already Exists");
    }
}
