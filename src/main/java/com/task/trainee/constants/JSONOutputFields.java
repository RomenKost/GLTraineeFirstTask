package com.task.trainee.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:json_output.properties")
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
