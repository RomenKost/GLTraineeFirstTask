package com.task.trainee.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:properties/json_input.properties")
public class JSONInputFields {
    public final String JSON_ID;
    public final String JSON_NAME;
    public final String JSON_DESCRIPTION;
    public final String JSON_TYPE;
    public final String JSON_BATTERY;

    public final String JSON_SENSOR_STATUS;
    public final String JSON_STATUS;
    public final String JSON_VALUE;

    public final String JSON_MODIFIED_TIME;
    public final String JSON_MODIFIED_BY;
    public final String JSON_CREATED_TIME;
    public final String JSON_CREATED_BY;

    public JSONInputFields(@Value("${json.id}") String id,
                           @Value("${json.name}") String name,
                           @Value("${json.description}") String description,
                           @Value("${json.type}") String type,
                           @Value("${json.battery.percentage}") String battery,

                           @Value("${json.sensor.status}") String sensorStatus,
                           @Value("${json.sensor.status.status}") String status,
                           @Value("${json.sensor.status.value}") String value,

                           @Value("${json.modified.time}") String modifiedTime,
                           @Value("${json.modified.by}") String modifiedBy,
                           @Value("${json.created.time}") String createdTime,
                           @Value("${json.created.by}") String createdBy
    ) {
        JSON_ID = id;
        JSON_NAME = name;
        JSON_DESCRIPTION = description;
        JSON_TYPE = type;
        JSON_BATTERY = battery;

        JSON_SENSOR_STATUS = sensorStatus;
        JSON_STATUS = status;
        JSON_VALUE = value;

        JSON_MODIFIED_TIME = modifiedTime;
        JSON_MODIFIED_BY = modifiedBy;
        JSON_CREATED_TIME = createdTime;
        JSON_CREATED_BY = createdBy;
    }
}
