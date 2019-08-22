package utils;

public class DigitalCodeGenerator {
    public static String generateCode() {
        return String.valueOf((int) (Math.random() * 1000));
    }
}
