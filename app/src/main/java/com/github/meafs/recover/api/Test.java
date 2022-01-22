package com.github.meafs.recover.api;

import android.os.Build;
import android.util.Base64;

import androidx.annotation.RequiresApi;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Test {
    private String MasterToken = "master";
    private String masterKey;
    private String TokenVersion = "1.0";
    private String resType = "docs";
    private String databaseId;
    private String containerId;
    private static final String HEX = "0123456789ABCDEF";

    public Test(String masterKey, String databaseId, String containerId) {
        this.masterKey = masterKey;
        this.databaseId = databaseId;
        this.containerId = containerId;
    }

    public String getMethod(String requestMethod) {
        return requestMethod.toLowerCase();
    }

    public String getResourceId() {
        return "dbs/" + databaseId + "/colls/" + containerId;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getDate() {
        return DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneOffset.UTC)).toLowerCase();
    }

//    public static byte[] ToByteArray(String HexString) {
//        int NumberChars = HexString.Length;
//        byte[] bytes = new byte[NumberChars / 2];
//        for (int i = 0; i < NumberChars; i += 2) {
//            bytes[i / 2] = Convert.ToByte(HexString.Substring(i, 2), 16);
//        }
//        return bytes;
//    }

    private String generateHashWithHmac256(String message, String key) {
        // This function is alright

        try {
            final String hashingAlgorithm = "HmacSHA256"; //or "HmacSHA1", "HmacSHA512"

            byte[] bytes = hmac(hashingAlgorithm, key.getBytes(), message.getBytes());

            final String messageDigest = bytesToHex(bytes);

            System.out.println(messageDigest);
            return messageDigest;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("I'm here");
            return "Error";
        }
    }

    public static byte[] hmac(String algorithm, byte[] key, byte[] message) throws InvalidKeyException, NoSuchAlgorithmException {
        Mac mac = Mac.getInstance(algorithm);
        mac.init(new SecretKeySpec(key, algorithm));
        return mac.doFinal(message);
    }

    public static String bytesToHex(byte[] bytes) {
        final char[] hexArray = "0123456789abcdef".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0, v; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getTexts() {

        return getMethod("GET") + "\n" +
                resType.toLowerCase() + "\n" +
                getResourceId() + "\n" +
                getDate().toLowerCase() + "\n" +
                "" + "\n";

    }

    public String encodeToBase64(String key) {
        // Correct
        return new String(Base64.encode(key.getBytes(), Base64.DEFAULT));
    }

//    public String encodeToBase64FromArray(String hashFromSHA156) {
//        return Base64.encodeToString(hashFromSHA156.getBytes(), Base64.DEFAULT);
//
//    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String encodeURI() {

        System.out.println("H: "+ generateHashWithHmac256("text", "text"));
        System.out.println("Result: " + encodeURIComponent(("type=" +
                MasterToken + "&ver=" +
                TokenVersion + "&sig=" +
                encodeURIComponent(generateHashWithHmac256(getMethod("GET") + "\n" +
                                "docs" + "\n" +
                                "dbs/" + databaseId + "/colls/" + containerId + "\n" +
                                "sat, 22 jan 2022 12:41:08 gmt" + "\n" +
                                "" + "\n",
                        "c70bb4e883836c00a43317d578f1d0e23d496485e9d600173bd252eb6d952c5c18b95d555aaf7a1e07ac4c85735b5611b5e467cfc4c46d0223682cdf86bdcc14")))));
        return encodeURIComponent(("type=" +
                MasterToken + "&ver=" +
                TokenVersion + "&sig=" +
                encodeToBase64(generateHashWithHmac256(getTexts(),
                        "c70bb4e883836c00a43317d578f1d0e23d496485e9d600173bd252eb6d952c5c18b95d555aaf7a1e07ac4c85735b5611b5e467cfc4c46d0223682cdf86bdcc14"))));
    }

    public static String encodeURIComponent(String str) {

        // This one works too!!
        if (str == null) return null;

        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        StringBuilder builder = new StringBuilder(bytes.length);

        for (byte c : bytes) {
            if (c >= 'a' ? c <= 'z' || c == '~' :
                    c >= 'A' ? c <= 'Z' || c == '_' :
                            c >= '0' ? c <= '9' : c == '-' || c == '.')
                builder.append((char) c);
            else
                builder.append('%')
                        .append(HEX.charAt(c >> 4 & 0xf))
                        .append(HEX.charAt(c & 0xf));
        }

        return builder.toString();
    }

}
