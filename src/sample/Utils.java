package sample;

import java.util.Random;

public class Utils {
    private static Random random = new Random();

    public static int getRandomArrayElement(int size){
        return random.nextInt(size);
    }

    public static int getRandomValue(int size) {
        return random.nextInt(size + 1);
    }

    public static int getRandomValue(int left, int right) {
        return left + random.nextInt(right - left + 1);
    }
}