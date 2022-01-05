package com.task.trainee.repositories.loaders;

import com.task.trainee.models.Sensor;

import java.util.List;

public abstract class Loader {
    public abstract List<Sensor> load();
}
