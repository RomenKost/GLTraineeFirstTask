package com.task.trainee.exceptions;

/**
 * @author Roman Kostenko (roman.kostenko@globallogic.com).
 *
 * This exception throws, when it's impossible to save anything.
 */
public class SaveException extends Exception {
    public SaveException(String message) {
        super(String.format("Exception until saving data: %s", message));
    }
}
