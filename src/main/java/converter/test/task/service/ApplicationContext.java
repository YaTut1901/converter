package converter.test.task.service;

import java.util.HashMap;

public class ApplicationContext {
    private static CoefficientStorage storage;
    private static Converter converter;
    private static PostProcessor postProcessor;
    private static CommandExecutor commandExecutor;

    public static CommandExecutor getCommandExecutor() {
        if (commandExecutor == null) {
            commandExecutor = new CommandExecutor(new InputParser(), getConverter());
        }
        return commandExecutor;
    }

    public static boolean initPostProcessor(String input) {
        String[] paths = input.split(" ");
        if (paths.length != 2) {
            return false;
        }
        if (postProcessor == null) {
            postProcessor = new PostProcessor(paths[0], paths[1]);
            if (postProcessor.isValid()) {
                return true;
            }
            return false;
        }
        throw new RuntimeException("PostProcessor has already been initialized");
    }

    private static CoefficientStorage getStorage() {
        if (storage == null) {
            storage = new CoefficientStorage(new HashMap<>());
            if (postProcessor == null) {
                throw new RuntimeException("PostProcessor has not been initialized yet");
            }
            postProcessor.processStorage(storage);
        }
        return storage;
    }

    private static Converter getConverter() {
        if (converter == null) {
            converter = new Converter(getStorage());
            if (postProcessor == null) {
                throw new RuntimeException("PostProcessor has not been initialized yet");
            }
            postProcessor.processConverter(converter);
        }
        return converter;
    }


}
