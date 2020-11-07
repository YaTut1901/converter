package converter.test.task;

import java.util.HashMap;
import java.util.Scanner;
import converter.test.task.service.CommandExecutor;
import converter.test.task.service.InputParser;

public class Main {
    public static final String INTRO_MESSAGE = "------------------------------------------------------------WELCOME TO CONVERTER-------------------------------------------------------------" + "\n"
            + "---------------------------------------------------------------------------------------------------------------------------------------------" + "\n"
            + "---                                                       ENTER FOLLOWING COMMANDS:                                                       ---" + "\n"
            + "---                                            add {file_path} - add new units to converter from file                                     ---" + "\n"
            + "---                                         convert {file_path} - convert input JSON object to required units                             ---" + "\n"
            + "---                                                   available units - get available units                                               ---" + "\n"
            + "---                                                      quit program - quit program                                                      ---" + "\n"
            + "---------------------------------------------------------------------------------------------------------------------------------------------" + "\n"
            + "---------------------------------------------------------------------------------------------------------------------------------------------" + "\n";
    private static final CommandExecutor commandExecutor = new CommandExecutor(new HashMap<>(), new InputParser());
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(INTRO_MESSAGE + "\n" + "\n" + "Available units" + "\n" + commandExecutor.execute("available units"));
        while(true) {
            String input = scanner.nextLine();
            System.out.println(commandExecutor.execute(input));
        }
    }
}
