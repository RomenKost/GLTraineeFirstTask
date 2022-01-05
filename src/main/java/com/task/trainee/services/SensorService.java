package com.task.trainee.services;

import com.task.trainee.constants.JSONInputFields;
import com.task.trainee.models.Report;
import com.task.trainee.models.Sensor;
import com.task.trainee.repositories.SensorsRepository;
import com.task.trainee.services.report_savers.JSONSaver;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;


@Service
public class SensorService {
    private final SensorsRepository repository;
    private final JSONInputFields jsonFields;
    private final JSONSaver saver;

    private final String STATUS_ON;

    public SensorService(@Autowired SensorsRepository repository,
                         @Autowired JSONInputFields jsonFields,
                         @Autowired JSONSaver saver,
                         @Value("${sensor.status.on}") String statusOn) {
        this.repository = repository;
        this.jsonFields = jsonFields;
        this.saver = saver;

        this.STATUS_ON = statusOn;
    }

    @SuppressWarnings("unchecked")
    public JSONArray getSensors(List<Predicate<Sensor>> filters) {
        JSONArray response = new JSONArray();
        repository.stream()
                .filter(Objects::nonNull)
                .filter(sensor -> filters.stream().allMatch(filter -> filter.test(sensor)))
                .map(sensor -> {
                    JSONObject jsonObject = new JSONObject();

                    jsonObject.put(jsonFields.JSON_ID, sensor.getId());
                    jsonObject.put(jsonFields.JSON_NAME, sensor.getName());
                    jsonObject.put(jsonFields.JSON_DESCRIPTION, sensor.getDescription());
                    jsonObject.put(jsonFields.JSON_TYPE, sensor.getType());
                    jsonObject.put(jsonFields.JSON_BATTERY, sensor.getBatteryPercentage());

                    JSONObject jsonStatus = new JSONObject();
                    jsonStatus.put(jsonFields.JSON_STATUS, sensor.getSensorStatus());
                    jsonStatus.put(jsonFields.JSON_VALUE, sensor.getSensorValue());
                    jsonObject.put(jsonFields.JSON_SENSOR_STATUS, jsonStatus);

                    jsonObject.put(jsonFields.JSON_MODIFIED_TIME, sensor.getModifiedTime());
                    jsonObject.put(jsonFields.JSON_MODIFIED_BY, sensor.getModifier());
                    jsonObject.put(jsonFields.JSON_CREATED_TIME, sensor.getCreatedTime());
                    jsonObject.put(jsonFields.JSON_CREATED_BY, sensor.getCreator());

                    return jsonObject;
                })
                .forEach(response::add);
        return response;
    }

    public void generateReport() {
        saver.save(
                Report.builder()
                        .numberOfSensors((int) (repository.stream().count() + repository.getCountBrokenSensors()))
                        .numberOfBrokenSensors(repository.getCountBrokenSensors())
                        .numberOfOnlineSensors(
                                (int) repository.stream()
                                        .filter(sensor -> Objects.equals(sensor.getSensorStatus(), STATUS_ON))
                                        .count()
                        ).build()
        );
    }
}
