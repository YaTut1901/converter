package converter.test.task.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import converter.test.task.model.Request;
import converter.test.task.model.Response;
import converter.test.task.model.Unit;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class Converter {
    public static final String DEFAULT_UNITS_URL = "src/main/resources/unit.json";
    public static final String RESPONSE_PATH = "src/main/resources/response.json";
    private final CoefficientStorage coefficientStorage;

    private Converter(Map<String, Double> storage) {
        coefficientStorage = new CoefficientStorage(storage);
    }

    public static Converter of(Map<String, Double> storage) throws IOException {
        Unit[] units = read(DEFAULT_UNITS_URL, Unit[].class);
        Arrays.stream(units)
                .forEach(unit -> storage.put(unit.getUnitName(), unit.getCoefficient()));
        return new Converter(storage);
    }

    public String addUnits(String path) {
        try {
            Arrays.stream(read(path, Unit[].class)).forEach(coefficientStorage::addUnit);
        } catch (IOException e) {
            return "No such way exist";
        }
        return "Units successfully added to converter";
    }

    public String convert(String path) {
        Request request = null;
        try {
            request = read(path, Request.class);
        } catch (IOException e) {
            return "No such way exist";
        }
        Double responseValue = toUnit(toStandart(request), request.getConvertTo());
        Response response = new Response(Math.round(responseValue * Math.pow(10, 2)) / Math.pow(10, 2),
                request.getConvertTo());
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(RESPONSE_PATH);
        try {
            objectMapper.writer().writeValue(file, response);
        } catch (IOException e) {
            throw new RuntimeException("No such way exist");
        }
        return "Data has been written into file response.json";
    }

    public String getAvailableUnits() {
        return String.join(", ", coefficientStorage.getAvailableUnits());
    }

    private static <T> T read(String path, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(path);
        return objectMapper.readValue(file, clazz);
    }

    private Double toStandart(Request request) {
        return request.getValue() * coefficientStorage.getCoefficient(request.getUnit());
    }

    private Double toUnit(Double value, String unit) {
        return value / coefficientStorage.getCoefficient(unit);
    }
}
