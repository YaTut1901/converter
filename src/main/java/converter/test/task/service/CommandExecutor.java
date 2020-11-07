package converter.test.task.service;

import converter.test.task.exception.WrongInputFormatException;

import java.io.IOException;
import java.util.Map;

public class CommandExecutor {
    private final InputParser inputParser;
    private Converter converter;

    public CommandExecutor(Map<String, Double> storage, InputParser inputParser) {
        try {
            converter = Converter.of(storage);
        } catch (IOException e) {
            System.out.println("No such way exist");
        }
        this.inputParser = inputParser;
    }

    public String execute(String input) {
        String[] command = null;
        try {
            command = inputParser.parse(input);
        } catch (WrongInputFormatException e) {
            return e.getMessage();
        }
        switch (command[0]) {
            case "add":
                return converter.addUnits(command[1])
                        + "\n" + "Available units: "
                        + "\n" + converter.getAvailableUnits();
            case "convert":
                return converter.convert(command[1]);
            case "available":
                return converter.getAvailableUnits();
            default:
                return "No such command available";
        }
    }
}
