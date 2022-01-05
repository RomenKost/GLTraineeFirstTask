package com.task.trainee.repositories;

import com.task.trainee.models.Sensor;
import com.task.trainee.repositories.checkers.Checker;
import com.task.trainee.repositories.loaders.Loader;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Repository
public class SensorsRepository {
    private final List<Sensor> sensors = new ArrayList<>();
    @Getter
    private int countBrokenSensors;

    @Autowired
    public SensorsRepository(Loader loader, Checker checker) {
        loader.load().forEach(sensor -> {
            if (sensor == null || checker.isBroken(sensor)) {
                countBrokenSensors++;
            } else {
                sensors.add(sensor);
            }
        });
    }

    public Stream<Sensor> stream() {
        return sensors.stream();
    }
}
        