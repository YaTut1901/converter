package converter.test.task.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class RequestDto {
    @JsonProperty("convert_to")
    private String convertTo;
    private String unit;
    private double value;

    public RequestDto() {
    }

    @JsonProperty("distance")
    private void distanceDeserializer(Map<String, Object> distance) {
        this.unit = (String) distance.get("unit");
        this.value = (Double) distance.get("value");
    }

    public String getConvertTo() {
        return convertTo;
    }

    public void setConvertTo(String convertTo) {
        this.convertTo = convertTo;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
