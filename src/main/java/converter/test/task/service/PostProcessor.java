package converter.test.task.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import converter.test.task.model.Unit;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class PostProcessor {
    private final String requestPath;
    private final String responsePath;

    public PostProcessor(String requestPath, String responsePath) {
        this.requestPath = requestPath;
        this.responsePath = responsePath;
    }

    public CoefficientStorage processStorage(CoefficientStorage storage) {
        Arrays.stream(getUnitsFromFile()).forEach(storage::addUnit);
        return storage;
    }

    public Converter processConverter(Converter converter) {
        converter.setResponsePath(responsePath);
        return converter;
    }

    private Unit[] getUnitsFromFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(requestPath);
        try {
            return objectMapper.readValue(file, Unit[].class);
        } catch (IOException e) {
            throw new RuntimeException("No such way exist!");
        }
    }

    public boolean isValid() {
        File requestFile = new File(requestPath);
        File responseFile = new File(responsePath);
        return requestFile.exists() && responseFile.exists();
    }
}
