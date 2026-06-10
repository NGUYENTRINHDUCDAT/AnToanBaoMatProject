package utils;

import java.util.Random;

public class RandomNumberGenerator {

    private static final int OTP_LENGTH = 6;
    private static final Random RANDOM = new Random();

    public static String generateRandomNumbersString() {
        StringBuilder randomNumbersString = new StringBuilder();

        for (int i = 0; i < OTP_LENGTH; i++) {
            int randomNumber = RANDOM.nextInt(10);
            randomNumbersString.append(randomNumber);
        }

        return randomNumbersString.toString();
    }

    public static void main(String[] args) {
        int count = 6;
        String randomNumbersString = generateRandomNumbersString();

        System.out.println("Generated Random Numbers:");
        System.out.println(randomNumbersString);
    }
}

