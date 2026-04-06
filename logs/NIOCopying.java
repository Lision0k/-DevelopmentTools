import java.io.*;
import java.nio.channels.FileChannel;
import java.util.logging.Logger;

class NIOCopying{
    private static final Logger logger = LoggerConfig.setup("NIOCopying");
    /** метод для последовательного копирования файлов **/
    static void consNIOCopy(File src, File dst){
        logger.info("NIO последовательное копирование: " + src.getName() + " → " + dst.getName());
        // Получаем FileChannel исходного файла и целевого файла
        try(FileChannel srcFileChannel  = new FileInputStream(src).getChannel();
            FileChannel dstFileChannel = new FileOutputStream(dst).getChannel()){
            // Размер текущего FileChannel
            long count = srcFileChannel.size();
            while(count > 0){
                /**
                 * Записать байты из FileChannel исходного файла в целевой FileChannel
                 * srcFileChannel.position (): начальная позиция в исходном файле не может быть отрицательной
                 * count: максимальное количество переданных байтов, не может быть отрицательным
                 * dstFileChannel: целевой файл
                 **/
                long transferred = srcFileChannel.transferTo(srcFileChannel.position(),
                        count, dstFileChannel);
                // После завершения переноса измените положение исходного файла на новое место
                srcFileChannel.position(srcFileChannel.position() + transferred);
                // Рассчитаем, сколько байтов осталось перенести
                count -= transferred;
            }
            logger.info("NIO копирование завершено: " + dst.getName());
        } catch (IOException ex) {
            logger.severe("Ошибка NIO копирования: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /** метод для параллельного копирования файлов **/
    static void parallNIOCopy(File src, File dst1, File dst2){
        logger.info("NIO параллельное копирование: " + src.getName() + " → " + dst1.getName() + ", " + dst2.getName());
        // Получаем FileChannel исходного файла и целевого файла
        try(FileChannel srcFileChannel  = new FileInputStream(src).getChannel();
            FileChannel dst1FileChannel = new FileOutputStream(dst1).getChannel();
            FileChannel dst2FileChannel = new FileOutputStream(dst2).getChannel()){
            // Размер текущего FileChannel
            long count = srcFileChannel.size();
            while(count > 0){
                srcFileChannel.transferTo(srcFileChannel.position(),
                        count, dst1FileChannel);
                long transferred = srcFileChannel.transferTo(srcFileChannel.position(),
                        count, dst2FileChannel);
                srcFileChannel.position(srcFileChannel.position() + transferred);
                count -= transferred;
            }
            logger.info("NIO параллельное копирование завершено");
        } catch (IOException ex) {
            logger.severe("Ошибка NIO параллельного копирования: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
