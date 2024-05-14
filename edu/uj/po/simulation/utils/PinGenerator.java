package edu.uj.po.simulation.utils;

import java.util.Random;

public class PinGenerator {
    private static final Random random = new Random();
    public static int generatePinNumber(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
