package converter.test.task.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import converter.test.task.model.RequestDto;
import converter.test.task.model.ResponseDto;
import converter.test.task.model.Unit;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

//public class Converter {
//    public static final String DEFAULT_UNITS_URL = "src/main/resources/unit.json";
//    public static final String RESPONSE_PATH = "src/main/resources/response.json";
//    private final CoefficientStorage coefficientStorage;
//
//    public String addUnits(String path) {
//        try {
//            Arrays.stream(read(path, Unit[].class)).forEach(coefficientStorage::addUnit);
//        } catch (IOException e) {
//            return "No such way exist";
//        }
//        return "Units successfully added to converter";
//    }
//
//    public String convert(String path) {
//        Request request = null;
//        try {
//            request = read(path, Request.class);
//        } catch (IOException e) {
//            return "No such way exist";
//        }
//        Double responseValue = toUnit(toStandart(request), request.getConvertTo());
//        Response response = new Response(Math.round(responseValue * Math.pow(10, 2)) / Math.pow(10, 2),
//                request.getConvertTo());
//        ObjectMapper objectMapper = new ObjectMapper();
//        File file = new File(RESPONSE_PATH);
//        try {
//            objectMapper.writer().writeValue(file, response);
//        } catch (IOException e) {
//            throw new RuntimeException("No such way exist");
//        }
//        return "Data has been written into file response.json";
//    }
//
//    public String getAvailableUnits() {
//        return String.join(", ", coefficientStorage.getAvailableUnits());
//    }
//
//    private static <T> T read(String path, Class<T> clazz) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        File file = new File(path);
//        return objectMapper.readValue(file, clazz);
//    }
//
//    private Double toStandart(Request request) {
//        return request.getValue() * coefficientStorage.getCoefficient(request.getUnit());
//    }
//
//    private Double toUnit(Double value, String unit) {
//        return value / coefficientStorage.getCoefficient(unit);
//    }
//}
public class Converter {
    private String responsePath;
    private final CoefficientStorage coefficientStorage;

    public Converter(CoefficientStorage coefficientStorage) {
        this.coefficientStorage = coefficientStorage;
    }

    public void setResponsePath(String responsePath) {
        this.responsePath = responsePath;
    }

    public String addUnits(String path) {
        try {
            Arrays.stream(read(path, Unit[].class)).forEach(coefficientStorage::addUnit);
        } catch (IOException e) {
            return "No such way exist";
        }
        return "Units successfully added to converter";
    }

    public String getAvailableUnits() {
        return String.join(", ", coefficientStorage.getAvailableUnits());
    }

    public String convert(String path) {
        RequestDto requestDto = null;
        try {
            requestDto = read(path, RequestDto.class);
        } catch (IOException e) {
            return "No such way exist";
        }
        Double responseValue = toUnit(toStandart(requestDto), requestDto.getConvertTo());
        ResponseDto responseDto = new ResponseDto(Math.round(responseValue * Math.pow(10, 2)) / Math.pow(10, 2),
                requestDto.getConvertTo());
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(responsePath);
        try {
            objectMapper.writer().writeValue(file, responseDto);
        } catch (IOException e) {
            throw new RuntimeException("No such way exist");
        }
        return "Data has been written into file response.json";
    }

    private Double toStandart(RequestDto request) {
        return request.getValue() * coefficientStorage.getCoefficient(request.getUnit());
    }

    private Double toUnit(Double value, String unit) {
        return value / coefficientStorage.getCoefficient(unit);
    }

    private static <T> T read(String path, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(path);
        return objectMapper.readValue(file, clazz);
    }
}
