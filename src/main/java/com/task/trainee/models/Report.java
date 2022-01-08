package com.task.trainee.models;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Roman Kostenko (roman.kostenko@globallogic.com).
 *
 * This class is immutable model of report.
 */
@Builder
@Getter(AccessLevel.PUBLIC)
public class Report {
    private int numberOfSensors;
    private int numberOfBrokenSensors;
    private int numberOfOnlineSensors;
}
