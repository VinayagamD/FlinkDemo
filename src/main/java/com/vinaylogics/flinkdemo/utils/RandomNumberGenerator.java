package com.vinaylogics.flinkdemo.utils;

public final class RandomNumberGenerator {

    public static int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }
}
