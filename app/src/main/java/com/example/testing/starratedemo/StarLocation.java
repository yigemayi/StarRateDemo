package com.example.testing.starratedemo;

/**
 * Star的坐标
 * Created by jiaozi on 2016/12/7.
 */

public class StarLocation {

    public float mStartX;
    public float mEndX;

    public float mTopY;
    public float mBottomY;


    @Override
    public String toString() {
        String logString = String.format("star的范围:x(%1s,%2s),y(%3s,%4s)", mStartX, mEndX, mTopY, mBottomY);
        return logString;
    }
}
