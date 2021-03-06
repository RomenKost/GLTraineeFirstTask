package com.task.trainee.controllers;

import com.task.trainee.exceptions.SaveException;
import com.task.trainee.models.Sensor;
import com.task.trainee.services.SensorService;
import com.task.trainee.services.StandardSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author Roman Kostenko (roman.kostenko@globallogic.com).
 * <p>
 * This class is main REST controller of all application. Contains next endpoints: /sensors, /status.
 * /sensors ({@link APIController#getSensors(String, Integer, String)});
 * /status ({@link APIController#getStatus()});
 */
@RestController
public class APIController {
    private final SensorService sensorService;

    @Autowired
    public APIController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    /**
     * This method generates list of sensors, filtered by status, min battery percentage and type.
     *
     * @return list of sensors
     */
    @GetMapping("/sensors")
    public String getSensors(@RequestParam String status,
                             @RequestParam(required = false) Integer minBattery,
                             @RequestParam(required = false) String type) {
        List<Predicate<Sensor>> filters = new ArrayList<>();
        filters.add(sensor -> Objects.equals(sensor.getSensorStatus(), status));
        if (minBattery != null) {
            filters.add(sensor -> sensor.getBatteryPercentage() >= minBattery);
        }
        if (type != null) {
            filters.add(sensor -> Objects.equals(sensor.getType(), type));
        }
        return sensorService.getSensors(filters);
    }

    /**
     * This method generates report.
     */
    @PostMapping("/status")
    public ResponseEntity<String> getStatus() {
        try {
            sensorService.generateReport();
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (SaveException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
