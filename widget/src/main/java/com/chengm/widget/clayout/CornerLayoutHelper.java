package com.chengm.widget.clayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import com.chengm.widget.R;

/**
 * author : ChenWJ
 * date : 2019/12/10 22:06
 * description :
 */
public class CornerLayoutHelper {

    private Paint mPaint = new Paint();
    private Path mClipPath = new Path();

    private RectF mRect = new RectF();
    private RectF mRectClip = new RectF();

    private Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SCREEN);

    public float mCorner;
    public float mClip;
    public float lClip;
    public float tClip;
    public float rClip;
    public float bClip;

    public int mWidth;
    public int mHeight;

    public CornerLayoutHelper() {
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    public void initAttr(Context ctx, View view, AttributeSet attrs) {
        view.setWillNotDraw(true);
        view.setLayerType(View.LAYER_TYPE_HARDWARE, mPaint);

        TypedArray array = ctx.obtainStyledAttributes(attrs, R.styleable.CornerLayout);

        mCorner = array.getDimension(R.styleable.CornerLayout_cs_corner, 0);
        mClip = array.getDimension(R.styleable.CornerLayout_cs_clip, 0);

        array.recycle();
    }

    private void computePath() {
        float[] radii = new float[]{mCorner, mCorner, mCorner, mCorner, mCorner, mCorner, mCorner, mCorner};
        mClipPath.reset();
        mRect.set(0f, 0f, 0f + mWidth, 0f + mHeight);

        mRectClip.set(lClip, tClip, mWidth - rClip, mHeight - bClip);
        mClipPath.addRoundRect(mRectClip, radii, Path.Direction.CW);

//        float ageFix = 10f;
//        mClipPath.moveTo(-ageFix, -ageFix);
//        mClipPath.moveTo(mWidth + ageFix, mHeight + ageFix);
    }

    public void refresh() {
        computePath();
    }

    public void drawBefore(Canvas c, boolean isEditMode) {
        if (c == null) return;
        c.save();
        if (isEditMode) {
            c.clipPath(mClipPath);
            return;
        }
        c.clipPath(mClipPath);
    }

    public void drawAfter(Canvas c, boolean isEditMode) {
        if (c == null) return;
        // 减轻圆角的锯齿效果
        if (mCorner > 0) {
            mPaint.setStrokeWidth(1f);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.WHITE);
            mPaint.setXfermode(xfermode);
            c.drawPath(mClipPath, mPaint);
            mPaint.setXfermode(null);
        }
        c.restore();
    }

    public void onSizeChange(View view, int width, int height) {
        mWidth = width;
        mHeight = height;
        computePath();
    }

}
