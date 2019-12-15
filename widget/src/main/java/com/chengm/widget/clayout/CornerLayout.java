package com.chengm.widget.clayout;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * author : ChenWJ
 * date : 2019/12/10 22:05
 * description :
 */
public class CornerLayout extends FrameLayout implements ICornerLayout {

    private CornerLayoutHelper mHelper = new CornerLayoutHelper();

    public CornerLayout(@NonNull Context context) {
        super(context);
    }

    public CornerLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mHelper.initAttr(context, this, attrs);
    }

    public CornerLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHelper.initAttr(context, this, attrs);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        mHelper.drawBefore(canvas, isInEditMode());
        super.dispatchDraw(canvas);
        mHelper.drawAfter(canvas, isInEditMode());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHelper.onSizeChange(this, w, h);
    }

    @Override
    public CornerLayoutHelper helper() {
        return mHelper;
    }
}
