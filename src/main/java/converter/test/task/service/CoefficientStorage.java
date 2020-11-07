package converter.test.task.service;

import converter.test.task.model.Unit;
import java.util.Map;
import java.util.Set;

public class CoefficientStorage {
    private final Map<String, Double> coefficientStorage;

    public CoefficientStorage(Map<String, Double> coefficientStorage) {
        this.coefficientStorage = coefficientStorage;
    }

    public Double getCoefficient(String unitName) {
        return coefficientStorage.get(unitName);
    }

    public void addUnit(Unit unit) {
        coefficientStorage.put(unit.getUnitName(), unit.getCoefficient());
    }

    public Set<String> getAvailableUnits() {
        return coefficientStorage.keySet();
    }
}
