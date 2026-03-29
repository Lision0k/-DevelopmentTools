// Кастомное исключение для пустого файла
class EmptyFileException extends Exception {
    public EmptyFileException(String message) {
        super(message);
    }
}

// Кастомное исключение для неверного формата данных
class InvalidDataFormatException extends Exception {
    public InvalidDataFormatException(String message) {
        super(message);
    }
}
