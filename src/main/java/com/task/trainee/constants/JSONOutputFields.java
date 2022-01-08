package com.task.trainee.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Roman Kostenko (roman.kostenko@globallogic.com).
 *
 * This class contains constants, those are matched json-fields of output files. If you want to change format of output
 * json file, you should change matched properties file.
 */
@Component
@PropertySource("classpath:properties/json_output.properties")
public class JSONOutputFields {
    public final String JSON_ALL_SENSORS;
    public final String JSON_BROKEN_SENSORS;
    public final String JSON_ONLINE_SENSORS;

    public JSONOutputFields(@Value("${json.sensors.all}") String jsonAllSensors,
                            @Value("${json.sensors.broken}") String jsonBrokenSensors,
                            @Value("${json.sensors.online}") String jsonOnlineSensors) {
        JSON_ALL_SENSORS = jsonAllSensors;
        JSON_BROKEN_SENSORS = jsonBrokenSensors;
        JSON_ONLINE_SENSORS = jsonOnlineSensors;
    }
}
