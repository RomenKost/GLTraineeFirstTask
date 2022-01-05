package com.task.trainee.exceptions;

public class SaveException extends Exception {
    public SaveException(String message) {
        super(String.format("Exception until saving data: %s", message));
    }
}
