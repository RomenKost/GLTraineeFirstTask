package com.task.trainee.services.report_savers;

import com.task.trainee.exceptions.SaveException;
import com.task.trainee.models.Report;

/**
 * @author Roman Kostenko (roman.kostenko@globallogic.com).
 *
 * This class should save {@link Report}.
 */
public abstract class Saver {
    public abstract void save(Report report) throws SaveException;
}
