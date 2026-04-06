import java.io.*;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = LoggerConfig.setup("Main");

    public static void main(String[] args) {
        logger.info("=== Запуск приложения ===");
        File source = createFileObject("text.txt");
        File dest1 = createFileObject("textcopy1.txt");
        File dest2 = createFileObject("textcopy2.txt");

        //IO
        //последовательное копирование
        long time = System.nanoTime();
        IOCopying.consIOCopy(source, dest1);
        IOCopying.consIOCopy(source, dest2);
        long elapsed = System.nanoTime() - time;
        logger.info("Время IO последовательного копирования: " + elapsed + " нс");
        System.out.print("Время последовательного копирования: ");
        System.out.println(elapsed);

        //параллельное копирование
        time = System.nanoTime();
        IOCopying.parallIOCopy(source, dest1, dest2);
        elapsed = System.nanoTime() - time;
        logger.info("Время IO параллельного копирования: " + elapsed + " нс");
        System.out.print("Время параллельного копирования: ");
        System.out.println(elapsed);

        //NIO
        //последовательное копирование
        time = System.nanoTime();
        NIOCopying.consNIOCopy(source, dest1);
        NIOCopying.consNIOCopy(source, dest2);
        elapsed = System.nanoTime() - time;
        logger.info("Время NIO последовательного копирования: " + elapsed + " нс");
        System.out.print("Время последовательного копирования: ");
        System.out.println(elapsed);

        //параллельное копирование
        time = System.nanoTime();
        NIOCopying.parallNIOCopy(source, dest1, dest2);
        elapsed = System.nanoTime() - time;
        logger.info("Время NIO параллельного копирования: " + elapsed + " нс");
        System.out.print("Время параллельного копирования: ");
        System.out.println(elapsed);

        logger.info("=== Приложение завершено ===");
    }

    static File createFileObject(String name) {
        File f = new File(name);
        if (!f.exists()) {
            try {
                f.createNewFile();
                logger.info("Файл создан: " + name);
            } catch (IOException e) {
                logger.severe("Ошибка создания файла: " + name + " — " + e.getMessage());
                System.out.println("Error of creating");
            }
        } else {
            logger.info("Файл уже существует: " + name);
        }
        return f;
    }
}
