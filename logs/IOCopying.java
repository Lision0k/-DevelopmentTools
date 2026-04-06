import java.io.*;
import java.util.logging.Logger;

class IOCopying {
    private static final Logger logger = LoggerConfig.setup("IOCopying");
    static void consIOCopy(File src, File dst) {
        logger.info("IO последовательное копирование: " + src.getName() + " → " + dst.getName());
        try {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dst);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.close();
            logger.info("Копирование завершено: " + dst.getName());
        } catch (IOException ex) {
            logger.severe("Ошибка IO копирования: " + ex.getMessage());
            System.out.println("Error");
        }
    }

    static void parallIOCopy(File src, File dst1, File dst2) {
        logger.info("IO параллельное копирование: " + src.getName() + " → " + dst1.getName() + ", " + dst2.getName());
        try {
            InputStream in = new FileInputStream(src);
            OutputStream out1 = new FileOutputStream(dst1);
            OutputStream out2 = new FileOutputStream(dst2);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out1.write(buffer, 0, length);
                out2.write(buffer, 0, length);
            }
            in.close();
            out1.close();
            out2.close();
            logger.info("Параллельное копирование завершено");
        } catch (IOException ex) {
            logger.severe("Ошибка параллельного IO копирования: " + ex.getMessage());
            System.out.println("Error");
        }
    }
}
