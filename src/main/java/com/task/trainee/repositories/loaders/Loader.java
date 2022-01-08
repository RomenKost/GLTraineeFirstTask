package com.task.trainee.repositories.loaders;

import com.task.trainee.models.Sensor;

import java.util.List;

/**
 * @author Roman Kostenko (roman.kostenko@globallogic.com).
 *
 * This class should load sensors.
 */
public abstract class Loader {
    public abstract List<Sensor> load();
}
