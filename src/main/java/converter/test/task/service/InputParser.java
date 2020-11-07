package converter.test.task.service;

import converter.test.task.exception.WrongInputFormatException;

public class InputParser {
    public String[] parse(String input) {
        String[] parsedInput = input.split(" ");
        if (parsedInput.length == 2) {
            return parsedInput;
        }
        throw new WrongInputFormatException("Wrong argument amount");
    }
}
