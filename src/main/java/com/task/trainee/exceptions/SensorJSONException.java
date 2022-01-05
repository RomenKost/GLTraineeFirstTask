package com.task.trainee.exceptions;

public class SensorJSONException extends Exception{
    public SensorJSONException(String key){
        super(String.format("Sensor is broken. Key \"%s\" is missing.", key));
    }
}
