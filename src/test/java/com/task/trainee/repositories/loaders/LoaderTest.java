package com.task.trainee.repositories.loaders;

import com.task.trainee.SensorFields;
import com.task.trainee.models.Sensor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;

import java.util.List;

@SpringBootTest
@TestPropertySource({"classpath:properties/settings.properties", "classpath:properties/test.properties"})
@Import({SensorFields.class})
public class LoaderTest {
    private final List<Sensor> sensorList;
    private final SensorFields sensorFields;

    public LoaderTest(@Autowired Loader loader, @Autowired SensorFields sensorFields) {
        sensorList = loader.load();
        this.sensorFields = sensorFields;
    }

    @Test
    public void sizeLoadedListTest() {
        Assertions.assertEquals(3, sensorList.size());
    }

    @Test
    public void loadBrokenSensorTest() {
        Assertions.assertNull(sensorList.get(0));
    }

    @Test
    public void loadCorrectSensorTest() {
        Sensor correctSensor = sensorList.get(1);

        Assertions.assertEquals(correctSensor.getId(), sensorFields.CORRECT_ID);
        Assertions.assertEquals(correctSensor.getName(), sensorFields.CORRECT_NAME);
        Assertions.assertEquals(correctSensor.getDescription(), sensorFields.CORRECT_DESCRIPTION);
        Assertions.assertEquals(correctSensor.getType(), sensorFields.CORRECT_TYPE);
        Assertions.assertEquals(correctSensor.getBatteryPercentage(), sensorFields.CORRECT_BATTERY_PERCENTAGE);

        Assertions.assertEquals(correctSensor.getSensorStatus(), sensorFields.CORRECT_STATUS);
        Assertions.assertEquals(correctSensor.getSensorValue(), sensorFields.CORRECT_VALUE);

        Assertions.assertEquals(correctSensor.getCreator(), sensorFields.CORRECT_EMAIL);
        Assertions.assertEquals(correctSensor.getModifier(), sensorFields.CORRECT_EMAIL);
        Assertions.assertEquals(correctSensor.getCreatedTime(), sensorFields.FIRST_TIME);
        Assertions.assertEquals(correctSensor.getModifiedTime(), sensorFields.LAST_TIME);
    }

    @Test
    public void loadSensorWithWrongFieldsTest() {
        Sensor correctSensor = sensorList.get(2);
        Assertions.assertNotNull(correctSensor);
        Assertions.assertNotNull(correctSensor.getId());
        Assertions.assertNotNull(correctSensor.getCreator());
    }
}
