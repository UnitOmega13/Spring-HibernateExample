package utils;

public class IdGenerator {
    private static Long generatedID;
    public static long generateId(){
        return generatedID++;
    }
}
