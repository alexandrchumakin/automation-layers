package service;

import org.apache.commons.lang3.EnumUtils;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class StringExtension {
    private static String generateString(int len) {
        String alphChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < len) {
            int index = (int) (rnd.nextFloat() * alphChars.length());
            salt.append(alphChars.charAt(index));
        }
        return salt.toString();
    }

    public static int generateNumber(int minLen, int maxLen) {
        return ThreadLocalRandom.current().nextInt(minLen, maxLen + 1);
    }

    public static String generateString() {
        int randomNum = generateNumber(6, 8);
        return generateString(randomNum);
    }

    public static String generateEmail() {
        return generateString() + "@test.com";
    }

    public static String generatePhone() {
        return Integer.toString(StringExtension.generateNumber((int) Math.pow(10, 6), (int) Math.pow(10, 7)));
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        Random random = new Random();
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public static String[] enumToArray(Class clazz) {
        List<Object> enumList = EnumUtils.getEnumList(clazz);
        ArrayList<String> ss = (ArrayList<String>) enumList.stream().map(Object::toString).collect(Collectors.toList());
        return ss.toArray(new String[0]);
    }

    public static String formatMessage(Exception ex) {
        String message = ex.getMessage();

        if (message == null) return ex.toString();

        int index = message.indexOf("\n");
        index = index != -1 ? index : ex.getMessage().length();
        return ex.getClass().toString() + ": " + ex.getMessage().substring(0, index);
    }
}