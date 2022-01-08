package com.task.trainee.repositories.checkers;

import com.task.trainee.exceptions.SensorException;
import com.task.trainee.models.Sensor;

/**
 * @author Roman Kostenko (roman.kostenko@globallogic.com).
 *
 * This class should check fields of sensor and decide is it broken or not.
 */
public abstract class Checker {
    public abstract void checkValues(Sensor sensor) throws SensorException;

    public boolean isBroken(Sensor sensor) {
        boolean status = false;
        try {
            checkValues(sensor);
        } catch (SensorException e) {
            status = true;
        }
        return status;
    }
}
