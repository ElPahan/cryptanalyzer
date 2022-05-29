package ua.com.javarush.ppalekhov.cryptanalyzer;

class FileProcessingException extends RuntimeException{
    public FileProcessingException() {
    }
    public FileProcessingException(String message) {
        super(message);
    }
    public FileProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}

