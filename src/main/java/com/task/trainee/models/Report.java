package com.task.trainee.models;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter(AccessLevel.PUBLIC)
public class Report {
    private int numberOfSensors;
    private int numberOfBrokenSensors;
    private int numberOfOnlineSensors;
}
