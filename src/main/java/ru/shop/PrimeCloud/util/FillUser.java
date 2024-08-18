package ru.shop.PrimeCloud.util;

import ru.shop.PrimeCloud.models.User;

import java.util.Random;

public class FillUser {
    private final static Random rnd = new Random();

    public static void fillUser(User user) {
        user.setEmail(generateRandomCharSequence() + "@gmail.com");
        user.setPassword(generateRandomCharSequence());
        user.setPhoneNumber(generateRandomNumberSequence());
        user.setName(generateRandomCharSequence());
    }

    private static String generateRandomCharSequence() {
        int length = rnd.nextInt(10 + 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) (97 + rnd.nextInt(26)));
        }
        return sb.toString();
    }

    private static String generateRandomNumberSequence(){
        int length = rnd.nextInt(10 + 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++){
            sb.append(48 + rnd.nextInt(9));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(generateRandomCharSequence());
        System.out.println(generateRandomNumberSequence());
    }
}
