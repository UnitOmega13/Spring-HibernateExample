package utils;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.security.MessageDigest;

public class SHA256HashUtil {
    private static final Logger logger = Logger.getLogger(SHA256HashUtil.class);

    @Test
    public static String getSha256(String value) {
        try {
            if (value.isEmpty()) {
                throw new IllegalArgumentException("Empty string for SHA256 hashing");
            }
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(value.getBytes());
            return bytesToHex(messageDigest.digest());
        } catch (Exception e) {
            logger.error("Error while hashing password!", e);
            throw new RuntimeException(e);
        }
    }

    @Test
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes)
            result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }
}
