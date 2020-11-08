package converter.test.task.service;

import converter.test.task.exception.WrongInputFormatException;

public class CommandExecutor {
    private final InputParser inputParser;
    private final Converter converter;

    public CommandExecutor(InputParser inputParser,
                           Converter converter) {
        this.inputParser = inputParser;
        this.converter = converter;
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
            case "quit":
                System.exit(0);
            default:
                return "No such command available";
        }
    }
}
