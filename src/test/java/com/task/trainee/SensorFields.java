package com.task.trainee;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.context.TestPropertySource;

@TestComponent
public class SensorFields {
    public final String CORRECT_ID;
    public final String CORRECT_NAME;
    public final String CORRECT_DESCRIPTION;
    public final String CORRECT_TYPE;
    public final int CORRECT_BATTERY_PERCENTAGE;

    public final String CORRECT_STATUS;
    public final String CORRECT_VALUE;

    public final String CORRECT_EMAIL;
    public final long FIRST_TIME;
    public final long LAST_TIME;

    public final String WRONG_ID;
    public final String WRONG_EMAIL;
    public final String WRONG_STATUS;
    public final String WRONG_VALUE;
    public final int WRONG_BATTERY_PERCENTAGE_1;
    public final int WRONG_BATTERY_PERCENTAGE_2;

    public SensorFields(@Value("${json.correct.id}") String correctId,
                        @Value("${json.correct.name}") String correctName,
                        @Value("${json.correct.description}") String correctDescription,
                        @Value("${json.correct.type}") String correctType,
                        @Value("${json.correct.battery-percentage}") int correctBatteryPercentage,

                        @Value("${json.correct.status}") String correctStatus,
                        @Value("${json.correct.value}") String correctValue,

                        @Value("${json.correct.email}") String correctEmail,
                        @Value("${json.time.first}") long firstTime,
                        @Value("${json.time.last}") long lastTime,

                        @Value("${json.wrong.id}") String wrongId,
                        @Value("${json.wrong.email}") String wrongEmail,
                        @Value("${json.wrong.status}") String wrongStatus,
                        @Value("${json.wrong.value}") String wrongValue,
                        @Value("${json.wrong.battery_percentage.1}") int wrongBatteryPercentage1,
                        @Value("${json.wrong.battery_percentage.2}") int wrongBatteryPercentage2) {
        CORRECT_ID = correctId;
        CORRECT_NAME = correctName;
        CORRECT_DESCRIPTION = correctDescription;
        CORRECT_TYPE = correctType;
        CORRECT_BATTERY_PERCENTAGE = correctBatteryPercentage;

        CORRECT_STATUS = correctStatus;
        CORRECT_VALUE = correctValue;

        CORRECT_EMAIL = correctEmail;
        FIRST_TIME = firstTime;
        LAST_TIME = lastTime;

        WRONG_ID = wrongId;
        WRONG_EMAIL = wrongEmail;
        WRONG_STATUS = wrongStatus;
        WRONG_VALUE = wrongValue;
        WRONG_BATTERY_PERCENTAGE_1 = wrongBatteryPercentage1;
        WRONG_BATTERY_PERCENTAGE_2 = wrongBatteryPercentage2;
    }
}
