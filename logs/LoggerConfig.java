import java.io.IOException;
import java.util.logging.*;

public class LoggerConfig {
    public static Logger setup(String loggerName) {
        Logger logger = Logger.getLogger(loggerName);
        logger.setLevel(Level.ALL);

        // Лог в консоль
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new SimpleFormatter());

        // Лог в файл
        try {
            FileHandler fileHandler = new FileHandler("app.log", true); // true = дописывать, не перезаписывать
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.warning("Не удалось создать файл лога: " + e.getMessage());
        }

        logger.addHandler(consoleHandler);
        logger.setUseParentHandlers(false); // отключаем дублирование в root logger
        return logger;
    }
}
