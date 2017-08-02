package com.swifty.physicsanim;

import android.content.Intent;
import android.os.Bundle;
import android.support.animation.DynamicAnimation;
import android.support.animation.FlingAnimation;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;


public class FlingActivity extends AppCompatActivity {

    private static final String TAG = FlingActivity.class.getSimpleName();

    private ImageView mViewTobeFlung;
    private int maxTranslationX;
    private int maxTranslationY;
    private View root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewTobeFlung = findViewById(R.id.object);
        root = findViewById(android.R.id.content);
        final GestureDetector gestureDetector = new GestureDetector(this, mGestureListener);

        mViewTobeFlung.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                maxTranslationX = root.getWidth() - mViewTobeFlung.getWidth();
                maxTranslationY = root.getHeight() - mViewTobeFlung.getHeight();
                root.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private GestureDetector.OnGestureListener mGestureListener = new GestureDetector.SimpleOnGestureListener() {

        //Constants
        private static final int MIN_DISTANCE_MOVED = 0;
        private static final float MIN_TRANSLATION = 0;
        private static final float FRICTION = 1f;

        @Override
        public boolean onDown(MotionEvent arg0) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float velocityX, float velocityY) {
            //downEvent : when user puts his finger down on the view
            //moveEvent : when user lifts his finger at the end of the movement
            float distanceInX = Math.abs(moveEvent.getRawX() - downEvent.getRawX());
            float distanceInY = Math.abs(moveEvent.getRawY() - downEvent.getRawY());
            if (distanceInX > MIN_DISTANCE_MOVED) {
                //Fling Right/Left
                FlingAnimation flingX = new FlingAnimation(mViewTobeFlung, DynamicAnimation.TRANSLATION_X);
                flingX.setStartVelocity(velocityX)
                        .setMinValue(MIN_TRANSLATION) // minimum translationX property
                        .setMaxValue(maxTranslationX)  // maximum translationX property
                        .setFriction(FRICTION)
                        .start();
            }
            if (distanceInY > MIN_DISTANCE_MOVED) {
                //Fling Down/Up
                FlingAnimation flingY = new FlingAnimation(mViewTobeFlung, DynamicAnimation.TRANSLATION_Y);
                flingY.setStartVelocity(velocityY)
                        .setMinValue(MIN_TRANSLATION)  // minimum translationY property
                        .setMaxValue(maxTranslationY) // maximum translationY property
                        .setFriction(FRICTION)
                        .start();
            }

            return true;
        }
    };
}