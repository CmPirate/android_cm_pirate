package com.chengm.pirate.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Rect;
import android.util.Pair;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.core.math.MathUtils;

/**
 * author : ChenWJ
 * date : 2019/12/10 23:13
 * description :
 */
public class ShareAnim {

    private static KeyParm createKeyParm(String key, View v) {
        KeyParm keyParm = new KeyParm(key, rectInWindow(v));
        if (v instanceof ICornerLayout) {
            keyParm.corner = ((ICornerLayout) v).helper().mCorner;
            keyParm.clip = ((ICornerLayout) v).helper().mClip;
        }

        return keyParm;
    }

    public static Intent createIntentDef(Intent intent, Pair<String, View>... pairs) {
        if (intent == null || pairs == null || pairs.length <= 0) {
            return intent;
        }
        List<KeyParm> keyParms = new ArrayList<>();
        for (Pair<String, View> pair : pairs) {
            keyParms.add(createKeyParm(pair.first, pair.second));
        }

        for (KeyParm parm : keyParms) {
            intent.putExtra(parm.key, parm);
        }

        return intent;
    }

    private static Rect rectInWindow(View v) {
        Rect rect = new Rect();
        int[] arrInt = new int[2];
        v.getLocationInWindow(arrInt);
        rect.set(arrInt[0], arrInt[1], arrInt[0] + v.getWidth(), arrInt[1] + v.getHeight());
        return rect;
    }

    public static Animator createAnimator(boolean isStart, Intent intent, String key, View view) {
        Animator animator;
        if (view instanceof ICornerLayout) {
            KeyParm from = intent.getParcelableExtra(key);
            animator = createCornerAnimator(isStart, (ICornerLayout) view, from, new KeyParm(key, rectInWindow(view)));
        } else {
            KeyParm from = intent.getParcelableExtra(key);
            animator = createViewAnim(isStart, view, from, new KeyParm(key, rectInWindow(view)));
        }
        return animator;
    }

    private static ValueAnimator createViewAnim(boolean isStart, final View v, KeyParm from, KeyParm to) {
        ValueAnimator valueAnimator = new ValueAnimator();
        float start = isStart ? 1f : 0f;
        valueAnimator.setFloatValues(start, 1 - start);

        final float translationX = from.rect.centerX() - to.rect.centerX();
        final float translationY = from.rect.centerY() - to.rect.centerY();
        final float scale = from.rect.width() * 1f / to.rect.width();

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                float clamp = MathUtils.clamp(value, 0f, 1f);
                if (translationX != 0) {
                    v.setTranslationX(translationX * clamp);
                }
                if (translationY != 0) {
                    v.setTranslationY(translationY * value);
                }
                if (scale != 1f) {
                    v.setScaleX(1 - (1 - scale) * value);
                    v.setScaleY(1 - (1 - scale) * value);
                }
            }
        });
        return valueAnimator;
    }

    public static ValueAnimator createCornerAnimator(boolean isStart, final ICornerLayout v, final KeyParm from, KeyParm to) {
        ValueAnimator valueAnimator = new ValueAnimator();
        final CornerLayoutHelper helper = v.helper();
        float start = isStart ? 1f : 0f;
        valueAnimator.setFloatValues(start, 1 - start);
        final float lClip = from.rect.left;
        final float tClip = from.rect.top;
        final float rClip = from.rect.left;
        final float bClip = helper.mHeight - from.rect.bottom;
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                float bais = MathUtils.clamp(value, 0f, 1f);
                helper.lClip = lClip * bais;
                helper.tClip = tClip * bais;
                helper.rClip = rClip * bais;
                helper.bClip = bClip * bais;
                helper.mCorner = from.corner * bais;
                helper.refresh();
                ((View) v).invalidate();
            }
        });
        return valueAnimator;
    }

    public static Animator startShareAnim(Animator[] animators, SimpleAnimLis lis) {
        return shareAnim(animators, lis);
    }

    private static Animator shareAnim(Animator[] animators, SimpleAnimLis lis) {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animators);
        set.addListener(lis);
        set.start();
        return set;
    }

}
