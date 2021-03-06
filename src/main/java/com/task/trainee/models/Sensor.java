package com.task.trainee.models;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Roman Kostenko (roman.kostenko@globallogic.com).
 *
 * This class is immutable model of sensor.
 */
@Builder
@Getter(AccessLevel.PUBLIC)
public class Sensor {
    private String id;
    private String name;
    private String description;
    private String type;
    private int batteryPercentage;

    private String sensorStatus;
    private String sensorValue;

    private long modifiedTime;
    private long createdTime;

    private String modifier;
    private String creator;
}
