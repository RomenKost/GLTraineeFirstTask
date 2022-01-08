package com.task.trainee.repositories.loaders;


import com.task.trainee.constants.JSONInputFields;
import com.task.trainee.models.Sensor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Roman Kostenko (roman.kostenko@globallogic.com).
 * <p>
 * This class in implemetation of {@link Loader} that loads sensors from folder.
 */
@Component
@PropertySource("classpath:properties/settings.properties")
public class JSONLoader extends Loader {
    /**
     * To change folder path, you should change settings.properties.
     */
    private final String SENSORS_PATH;
    private final JSONInputFields jsonFields;

    public JSONLoader(@Autowired JSONInputFields jsonFields,
                      @Value("${path.sensors}") String sensorsPath) {
        this.jsonFields = jsonFields;
        this.SENSORS_PATH = sensorsPath;
    }

    @Override
    public List<Sensor> load() {
        File[] files = new File(SENSORS_PATH).listFiles();

        List<Sensor> sensors = new ArrayList<>();
        if (files != null) {
            Sensor sensor;
            for (final File file : files) {
                sensor = processJSON(file);
                sensors.add(sensor);
            }
        }
        return sensors;
    }

    /**
     * This method parses json from file.
     *
     * @param file - json file
     * @return null if sensor is broken. In other case returns loaded {@link Sensor}.
     */
    private Sensor processJSON(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            JSONObject json = new JSONObject(reader.lines().collect(Collectors.joining()));
            JSONObject jsonStatus = json.getJSONObject(jsonFields.JSON_SENSOR_STATUS);

            return Sensor.builder()
                    .id(json.getString(jsonFields.JSON_ID))
                    .name(json.getString(jsonFields.JSON_NAME))
                    .description(json.getString(jsonFields.JSON_DESCRIPTION))
                    .type(json.getString(jsonFields.JSON_TYPE))
                    .batteryPercentage(json.getInt(jsonFields.JSON_BATTERY))

                    .sensorStatus(jsonStatus.getString(jsonFields.JSON_STATUS))
                    .sensorValue(jsonStatus.getString(jsonFields.JSON_VALUE))

                    .modifiedTime(json.getLong(jsonFields.JSON_MODIFIED_TIME))
                    .modifier(json.getString(jsonFields.JSON_MODIFIED_BY))
                    .createdTime(json.getLong(jsonFields.JSON_CREATED_TIME))
                    .creator(json.getString(jsonFields.JSON_CREATED_BY))

                    .build();
        } catch (IOException | JSONException e) {
            return null;
        }
    }
}
