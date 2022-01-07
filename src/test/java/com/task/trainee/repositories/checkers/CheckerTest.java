package com.task.trainee.repositories.checkers;


import com.task.trainee.SensorFields;
import com.task.trainee.exceptions.SensorException;
import com.task.trainee.models.Sensor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("classpath:properties/test.properties")
@Import({SensorFields.class})
public class CheckerTest {
    @Autowired
    private Checker checker;

    @Autowired
    private SensorFields fields;

    @Test
    public void sensorWithWrongEmailTest() {
        Sensor sensor = getCorrectSensorBuilder()
                .modifier(fields.WRONG_EMAIL)
                .build();
        Assertions.assertThrows(SensorException.class, () -> checker.checkValues(sensor));
        Assertions.assertTrue(checker.isBroken(sensor));
    }

    @Test
    public void correctSensorTest() {
        Sensor sensor = getCorrectSensorBuilder()
                .build();
        Assertions.assertFalse(checker.isBroken(sensor));
    }

    @Test
    public void sensorWithWrongIdTest() {
        Sensor sensor = getCorrectSensorBuilder()
                .id(fields.WRONG_ID)
                .build();
        Assertions.assertThrows(SensorException.class, () -> checker.checkValues(sensor));
        Assertions.assertTrue(checker.isBroken(sensor));
    }

    @Test
    public void sensorWithWrongValueTest() {
        Sensor sensor = getCorrectSensorBuilder()
                .sensorValue(fields.WRONG_VALUE)
                .build();
        Assertions.assertThrows(SensorException.class, () -> checker.checkValues(sensor));
        Assertions.assertTrue(checker.isBroken(sensor));
    }

    @Test
    public void sensorWithWrongTimeTest() {
        Sensor sensor = getCorrectSensorBuilder()
                .modifiedTime(fields.FIRST_TIME)
                .createdTime(fields.LAST_TIME)
                .build();
        Assertions.assertThrows(SensorException.class, () -> checker.checkValues(sensor));
        Assertions.assertTrue(checker.isBroken(sensor));
    }

    @Test
    public void sensorWithWrongBatteryPercentageTest1() {
        Sensor sensor = getCorrectSensorBuilder()
                .batteryPercentage(fields.WRONG_BATTERY_PERCENTAGE_1)
                .build();
        Assertions.assertThrows(SensorException.class, () -> checker.checkValues(sensor));
        Assertions.assertTrue(checker.isBroken(sensor));
    }

    @Test
    public void sensorWithWrongBatteryPercentageTest2() {
        Sensor sensor = getCorrectSensorBuilder()
                .batteryPercentage(fields.WRONG_BATTERY_PERCENTAGE_2)
                .build();
        Assertions.assertThrows(SensorException.class, () -> checker.checkValues(sensor));
        Assertions.assertTrue(checker.isBroken(sensor));
    }

    public Sensor.SensorBuilder getCorrectSensorBuilder(){
        return Sensor.builder()
                .id(fields.CORRECT_ID)
                .name(fields.CORRECT_NAME)
                .description(fields.CORRECT_DESCRIPTION)
                .type(fields.CORRECT_TYPE)
                .batteryPercentage(fields.CORRECT_BATTERY_PERCENTAGE)

                .sensorStatus(fields.CORRECT_STATUS)
                .sensorValue(fields.CORRECT_VALUE)

                .createdTime(fields.FIRST_TIME)
                .modifiedTime(fields.LAST_TIME)
                .creator(fields.CORRECT_EMAIL)
                .modifier(fields.CORRECT_EMAIL);
    }
}
