package com.task.trainee.exceptions;

public class SensorException extends Exception {
    public SensorException(String key, String value) {
        super(String.format("Value(\"%s\"), that contains by key \"%s\" is wrong.", key, value));
    }
}
