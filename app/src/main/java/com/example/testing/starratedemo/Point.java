package com.example.testing.starratedemo;

/**
 * 点坐标
 * Created by jiaozi on 2016/12/7.
 */

public class Point {

    public float mX;
    public float mY;

    @Override
    public String toString() {
        String logString = String.format("(x=%1s,%2s)", mX, mY);
        return logString;
    }
}
