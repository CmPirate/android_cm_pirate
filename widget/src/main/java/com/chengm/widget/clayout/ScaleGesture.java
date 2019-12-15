package com.chengm.widget.clayout;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.util.Property;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * author : ChenWJ
 * date : 2019/12/11 23:32
 * description :
 */
public class ScaleGesture extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener, View.OnAttachStateChangeListener {

    private View mView;

    private GestureDetector gesture;

    private ObjectAnimator animScale;
    private ObjectAnimator animReview;

    private float scale = 0.93f;// 默认缩放比例

    private IClickLis mIClickLis;

    public void onClick(IClickLis iClickLis) {
        this.mIClickLis = iClickLis;
    }

    public ScaleGesture(Context context) {
        gesture = new GestureDetector(context, this);
        animScale();
        animReview();
    }

    public ScaleGesture bindToView(View touchView, View scaleView) {
        if (touchView == null || scaleView == null) return this;
        touchView.setOnTouchListener(this);
        touchView.addOnAttachStateChangeListener(this);
        mView = scaleView;
        return this;
    }

    @Override
    public void onViewAttachedToWindow(View v) {

    }

    @Override
    public void onViewDetachedFromWindow(View v) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                toScale();
                break;
            case MotionEvent.ACTION_UP:
                toRecoverClick();
                break;
            case MotionEvent.ACTION_CANCEL:
                toRecover();
                break;
        }
        return gesture.onTouchEvent(event);
    }

    //恢复
    private void toRecoverClick() {
        if (isNVersion()) {
            mIClickLis.onClick(mView);
            return;
        }
        if (animScale.isRunning()) {
            animScale.cancel();
        }
        if (!animReview.isRunning()) {
            if (animReview.getListeners() == null || !animReview.getListeners().contains(lis)) {
                animReview.addListener(lis);
            }
            animReview.start();
        }
    }

    // 恢复
    private void toRecover() {
        if (!animReview.isRunning()) {
            animReview.removeAllListeners();
            animReview.start();
        }
    }

    //缩放
    private void toScale() {
        if (isNVersion())
            return;
        if (!animScale.isRunning())
            animScale.start();
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    private void animScale() {
        animScale = ObjectAnimator.ofFloat(mView, propertyScale, 1f, scale);
        animScale.setDuration(80);
    }

    private void animReview() {
        animReview = ObjectAnimator.ofFloat(mView, propertyScale, scale, 1f);
        animReview.setDuration(80);
    }

    public ScaleGesture setCustomScale(float scale) {
        this.scale = scale;
        return this;
    }

    private boolean isNVersion() {
        return Build.VERSION.SDK_INT == Build.VERSION_CODES.N;
    }

    private Animator.AnimatorListener lis = new Animator.AnimatorListener() {

        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            mIClickLis.onClick(mView);
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

    private Property<View, Float> propertyScale = new Property<View, Float>(Float.TYPE, "scale") {

        @Override
        public Float get(View object) {
            return mView.getScaleX();
        }

        @Override
        public void set(View object, Float value) {
            mView.setScaleX(value);
            mView.setScaleY(value);
        }
    };

    public interface IClickLis {

        void onClick(View view);
    }
}
