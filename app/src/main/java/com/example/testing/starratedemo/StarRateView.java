package com.example.testing.starratedemo;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * 五星评分的自定义View
 * Created by jiaozi on 2016/12/7.
 */

public class StarRateView extends LinearLayout implements View.OnClickListener {

    private static final String LOG_ATG = "StarRateView";

    private ImageView[] mStars = new ImageView[5];
    private StarLocation[] mStarLocations = new StarLocation[5];

    // 当次点击记录
    private Point mDownPoint;
    private int mCurrentStarCount;
    private StarLocation mCurrentStarLocation;

    private Point mTempPoint = new Point();

    // 满分点击回调
    private FullRateListener mFullRateListener;

    public StarRateView(Context context) {
        this(context, null);
    }

    public StarRateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StarRateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mDownPoint = new Point();
        setOnClickListener(this);
    }

    public void setFullRateListener(FullRateListener fullRateListener) {
        mFullRateListener = fullRateListener;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mStars[0] = (ImageView) findViewById(R.id.rate_local_stars_one);
        mStars[1] = (ImageView) findViewById(R.id.rate_local_stars_two);
        mStars[2] = (ImageView) findViewById(R.id.rate_local_stars_three);
        mStars[3] = (ImageView) findViewById(R.id.rate_local_stars_four);
        mStars[4] = (ImageView) findViewById(R.id.rate_local_stars_five);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        achieveStarsArea();
        super.dispatchDraw(canvas);
    }


    /**
     * 获取各个Star的位置范围
     */
    private void achieveStarsArea() {
        mStarLocations[0] = new StarLocation();
        mStarLocations[0].mStartX = mStars[0].getX();
        mStarLocations[0].mTopY = mStars[0].getY();
        mStarLocations[0].mEndX = mStars[0].getX() + mStars[0].getWidth();
        mStarLocations[0].mBottomY = mStars[0].getY() + mStars[0].getHeight();
        Log.i(LOG_ATG, "star1" + mStarLocations[0].toString());

        mStarLocations[1] = new StarLocation();
        mStarLocations[1].mStartX = mStars[1].getX();
        mStarLocations[1].mTopY = mStars[1].getY();
        mStarLocations[1].mEndX = mStars[1].getX() + mStars[1].getWidth();
        mStarLocations[1].mBottomY = mStars[1].getY() + mStars[1].getHeight();
        Log.i(LOG_ATG, "star2" + mStarLocations[1].toString());

        mStarLocations[2] = new StarLocation();
        mStarLocations[2].mStartX = mStars[2].getX();
        mStarLocations[2].mTopY = mStars[2].getY();
        mStarLocations[2].mEndX = mStars[2].getX() + mStars[2].getWidth();
        mStarLocations[2].mBottomY = mStars[2].getY() + mStars[2].getHeight();
        Log.i(LOG_ATG, "star3" + mStarLocations[2].toString());

        mStarLocations[3] = new StarLocation();
        mStarLocations[3].mStartX = mStars[3].getX();
        mStarLocations[3].mTopY = mStars[3].getY();
        mStarLocations[3].mEndX = mStars[3].getX() + mStars[3].getWidth();
        mStarLocations[3].mBottomY = mStars[3].getY() + mStars[3].getHeight();
        Log.i(LOG_ATG, "star3" + mStarLocations[3].toString());

        mStarLocations[4] = new StarLocation();
        mStarLocations[4].mStartX = mStars[4].getX();
        mStarLocations[4].mTopY = mStars[4].getY();
        mStarLocations[4].mEndX = mStars[4].getX() + mStars[4].getWidth();
        mStarLocations[4].mBottomY = mStars[4].getY() + mStars[4].getHeight();
        mStars[4].setOnClickListener(this);
        Log.i(LOG_ATG, "star4" + mStarLocations[4].toString());

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            clearStars();
            mCurrentStarLocation = null;
            mDownPoint.mX = event.getX();
            mDownPoint.mY = event.getY();
            Log.i(LOG_ATG, "Down时的坐标:" + mDownPoint.toString());
            StarLocation starLocation;
            // 遍历Star,判断点击在哪个Star
            for (int i = 0; i < 5; i++) {
                starLocation = mStarLocations[i];
                if (inArea(mDownPoint, starLocation)) {
                    mCurrentStarCount = i;
                    mCurrentStarLocation = starLocation;
                    break;
                }
            }
            // 用户的down事件是在star范围内
            if (mCurrentStarLocation != null) {
                //  改变前N个star图片
                fillStars(mCurrentStarCount);
            }

        } else {
            if (mCurrentStarLocation != null) {
                mTempPoint.mX = event.getX();
                mTempPoint.mY = event.getY();
                if (!inArea(mTempPoint, mCurrentStarLocation)) {
                    clearStars();
                }
            }
        }

        return super.dispatchTouchEvent(event);
    }

    /**
     * 是否在Star的范围内
     *
     * @param point
     * @param area
     * @return
     */
    private boolean inArea(Point point, StarLocation area) {
        boolean inArea = false;
        if ((area.mStartX <= point.mX) && (point.mX <= area.mEndX)) {
            if ((area.mTopY <= point.mY) && (point.mY <= area.mBottomY)) {
                inArea = true;
            }
        }
        return inArea;
    }

    /**
     * 生成评分视图
     *
     * @param count
     */
    private void fillStars(int count) {
        for (int i = 0; i <= count; i++) {
            mStars[i].setImageResource(R.drawable.rate_local_star_full);
        }
    }

    /**
     * 清除评分效果
     */
    private void clearStars() {
        for (ImageView star : mStars) {
            star.setImageResource(R.drawable.rate_local_star_empty);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rate_local_stars_five:
                if (mFullRateListener != null) {
                    mFullRateListener.onFiveStarClick();
                }
                break;
            default:
                break;
        }
    }


}
