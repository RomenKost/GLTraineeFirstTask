package com.task.trainee.repositories.loaders;


import com.task.trainee.constants.JSONInputFields;
import com.task.trainee.exceptions.SensorJSONException;
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


@Component
@PropertySource("classpath:sensors.properties")
public class JSONLoader extends Loader {
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

    private Sensor processJSON(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            JSONObject json = new JSONObject(reader.lines().collect(Collectors.joining()));
            check(json);
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
        } catch (IOException | SensorJSONException e) {
            return null;
        }
    }

    public void check(JSONObject json) throws SensorJSONException {
        for (String key : new String[]{
                jsonFields.JSON_ID, jsonFields.JSON_NAME, jsonFields.JSON_DESCRIPTION, jsonFields.JSON_TYPE,
                jsonFields.JSON_BATTERY, jsonFields.JSON_SENSOR_STATUS,
                jsonFields.JSON_MODIFIED_BY, jsonFields.JSON_MODIFIED_TIME,
                jsonFields.JSON_CREATED_BY, jsonFields.JSON_CREATED_TIME
        }) {
            if (!json.has(key)) {
                throw new SensorJSONException(key);
            }
        }

        String key = jsonFields.JSON_SENSOR_STATUS;
        try {
            JSONObject sensorStatus = json.getJSONObject(key);
            if (!sensorStatus.has(key = jsonFields.JSON_STATUS) || !sensorStatus.has(key = jsonFields.JSON_VALUE)) {
                throw new SensorJSONException(key);
            }
        } catch (JSONException e) {
            throw new SensorJSONException(key);
        }
    }
}
