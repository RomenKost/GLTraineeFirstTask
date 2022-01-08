package com.task.trainee.exceptions;

/**
 * @author Roman Kostenko (roman.kostenko@globallogic.com).
 *
 * This exception is throwned when the sensor has wrong field.
 */
public class SensorException extends Exception {
    public SensorException(String key, String value) {
        super(String.format("Value(\"%s\"), that contains by key \"%s\" is wrong.", key, value));
    }
}
