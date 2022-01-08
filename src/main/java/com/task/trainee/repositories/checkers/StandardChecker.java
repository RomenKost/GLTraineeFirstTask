package com.task.trainee.repositories.checkers;

import com.task.trainee.exceptions.SensorException;
import com.task.trainee.models.Sensor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Roman Kostenko (roman.kostenko@globallogic.com).
 *
 * This class is standard implemetation of {@link Checker}.
 */
@Component
@PropertySource("classpath:properties/regexes.properties")
public class StandardChecker extends Checker {
    private final Pattern statusPattern;
    private final Map<Pattern, Pattern> patternValueMap;
    private final Pattern emailPattern;
    private final Pattern idPattern;

    public StandardChecker(@Value("${sensor.status.regexp}") String statusPattern,
                           @Value("#{${pattern.value}}") Map<String, String> patternValueMap,
                           @Value("${pattern.email.regexp}") String emailPattern,
                           @Value("${pattern.id.regexp}") String idPattern) {
        this.statusPattern = Pattern.compile(statusPattern);
        this.patternValueMap = patternValueMap.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> Pattern.compile(entry.getKey()),
                        entry -> Pattern.compile(entry.getValue())
                ));
        this.emailPattern = Pattern.compile(emailPattern);
        this.idPattern = Pattern.compile(idPattern);
    }

    @Override
    public void checkValues(Sensor sensor) throws SensorException {
        checkBatteryPercentage(sensor.getBatteryPercentage());
        checkTime(sensor.getCreatedTime(), sensor.getModifiedTime());
        checkValue(sensor.getType(), sensor.getSensorValue());

        checkUsingPattern("status", sensor.getSensorStatus(), statusPattern);
        checkUsingPattern("id", sensor.getId(), idPattern);

        checkEmail(sensor.getCreator(), "creator");
        checkEmail(sensor.getModifier(), "modifier");
    }

    private void checkBatteryPercentage(int batteryPercentage) throws SensorException {
        if (batteryPercentage > 100 || batteryPercentage < 0) {
            throw new SensorException("batteryPercentage", String.valueOf(batteryPercentage));
        }
    }

    private void checkTime(long createdTime, long modifiedTime) throws SensorException {
        if (createdTime > modifiedTime) {
            throw new SensorException("createdTime", String.valueOf(createdTime));
        }
    }

    private void checkUsingPattern(String key, String value, Pattern pattern) throws SensorException {
        if (!pattern.asPredicate().test(value)) {
            throw new SensorException(key, String.valueOf(value));
        }
    }

    private void checkValue(String type, String value) throws SensorException {
        for (Pattern key : patternValueMap.keySet()) {
            if (key.asPredicate().test(type)) {
                if (patternValueMap.get(key).asPredicate().test(value)) {
                    break;
                } else {
                    throw new SensorException("sensorValue", String.valueOf(value));
                }
            }
        }
    }

    private void checkEmail(String email, String key) throws SensorException {
        if (!emailPattern.asPredicate().test(email)) {
            throw new SensorException(key, email);
        }
    }
}
