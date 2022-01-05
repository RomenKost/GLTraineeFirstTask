package com.task.trainee.services.report_savers;

import com.task.trainee.constants.JSONOutputFields;
import com.task.trainee.exceptions.SaveException;
import com.task.trainee.models.Report;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

@Component
@PropertySource("classpath:sensors.properties")
public class JSONSaver extends Saver {
    private final JSONOutputFields jsonFields;
    private final String REPORTS_FOLDER_PATH;
    private final String FILENAME_PATTERN;

    public JSONSaver(@Autowired JSONOutputFields jsonFields,
                     @Value("${path.reports}") String reportsFolderPath,
                     @Value("${filename.report.pattern}") String filenamePattern) {
        this.jsonFields = jsonFields;
        this.REPORTS_FOLDER_PATH = reportsFolderPath;
        this.FILENAME_PATTERN = filenamePattern;
    }

    @Override
    public void save(Report report) throws SaveException {
        File filePath = new File(REPORTS_FOLDER_PATH);
        if (filePath.exists() || filePath.mkdir()) {
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(REPORTS_FOLDER_PATH, generateFilename()))) {
                writer.write(generateJSON(report).toJSONString());
            } catch (IOException e) {
                throw new SaveException(e.getMessage());
            }
        } else {
            throw new SaveException(String.format("Impossible to work with directory %s", REPORTS_FOLDER_PATH));
        }
    }

    @SuppressWarnings("unchecked")
    private JSONObject generateJSON(Report report) {
        JSONObject jsonReport = new JSONObject();
        jsonReport.put(jsonFields.JSON_ALL_SENSORS, report.getNumberOfSensors());
        jsonReport.put(jsonFields.JSON_BROKEN_SENSORS, report.getNumberOfBrokenSensors());
        jsonReport.put(jsonFields.JSON_ONLINE_SENSORS, report.getNumberOfOnlineSensors());
        return jsonReport;
    }

    private String generateFilename() {
        return String.format(FILENAME_PATTERN, new Date().getTime());
    }
}
