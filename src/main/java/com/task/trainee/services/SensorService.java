package com.task.trainee.services;

import com.task.trainee.exceptions.SaveException;
import com.task.trainee.models.Sensor;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Roman Kostenko (roman.kostenko@globallogic.com).
 *
 * This class should process user requests about sensors.
 */
public abstract class SensorService {
    public abstract String getSensors(List<Predicate<Sensor>> filters);

    public abstract void generateReport() throws SaveException;
}
