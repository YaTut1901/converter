package converter.test.task.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Unit {
    @JsonProperty("unit_name")
    private String unitName;
    private Double coefficient;

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }
}
