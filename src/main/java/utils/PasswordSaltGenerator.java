package utils;

public class PasswordSaltGenerator {
    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String getSalt() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int number = (int) (Math.random() * alphabet.length());
            stringBuilder.append(alphabet.charAt(number));
        }
        return stringBuilder.toString();
    }

    public static String saltPassword(String password, String salt) {
        int mid = salt.length() / 2;
        String[] parseSalt = {salt.substring(0, mid), salt.substring(mid)};
        return parseSalt[0] + password + parseSalt[1];
    }
}
