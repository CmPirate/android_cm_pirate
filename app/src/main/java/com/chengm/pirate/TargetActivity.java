package com.chengm.pirate;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chengm.pirate.widget.CornerLayout;
import com.chengm.pirate.widget.KeyParm;
import com.chengm.pirate.widget.ShareAnim;
import com.chengm.pirate.widget.SimpleAnimLis;

/**
 * Target
 * 2019-11-09
 */
public class TargetActivity extends Activity {

    private CornerLayout csLayout;
    private ImageView image;
    private LinearLayout linearContent;
    private TextView text_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        // 初始化
        initView();
    }

    private void initView() {
        csLayout = findViewById(R.id.cslayout);
        image = findViewById(R.id.image);
        linearContent = findViewById(R.id.view_content);
        text_title = findViewById(R.id.text_title);

        // 设置图片宽高
        KeyParm parm = getIntent().getParcelableExtra("image");
        ViewGroup.LayoutParams lp = image.getLayoutParams();
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        lp.height = (int) (point.x * (parm.rect.height() * 1f / parm.rect.width()));
        image.setLayoutParams(lp);

        // 数据
        Banner banner = null;
        Intent intent = getIntent();
        if (intent != null) {
            banner = (Banner) intent.getSerializableExtra("data");
        }

        if (banner != null) {
            image.setImageResource(banner.getResId());
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }
    }

    private Animator anim;
    private boolean isFirst = true;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (isFirst && hasFocus) {
            ValueAnimator animator1 = (ValueAnimator) ShareAnim.createAnimator(true, getIntent(), "image", image);
            animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    linearContent.setTranslationY(image.getTranslationY());
                    linearContent.setTranslationX(image.getTranslationX());
                }
            });

            ValueAnimator animator2 = (ValueAnimator) ShareAnim.createAnimator(true, getIntent(), "title", text_title);
            ValueAnimator animator3 = (ValueAnimator) ShareAnim.createAnimator(true, getIntent(), "csLayout", csLayout);
            Animator[] animators = new Animator[]{animator1,animator2, animator3};
            anim = ShareAnim.startShareAnim(animators, new SimpleAnimLis());
        }
        isFirst = false;
    }

    @Override
    public void onBackPressed() {
        if (anim != null && anim.isRunning()) {
            return;
        }
        ValueAnimator animator1 = (ValueAnimator) ShareAnim.createAnimator(false, getIntent(), "image", image);
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                linearContent.setTranslationY(image.getTranslationY());
                linearContent.setTranslationX(image.getTranslationX());
            }
        });

        ValueAnimator animator2 = (ValueAnimator) ShareAnim.createAnimator(false, getIntent(), "title", text_title);
        ValueAnimator animator3 = (ValueAnimator) ShareAnim.createAnimator(false, getIntent(), "csLayout", csLayout);
        Animator[] animators = new Animator[]{animator1,animator2, animator3};
        ShareAnim.startShareAnim(animators, new SimpleAnimLis() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                finish();
                overridePendingTransition(0, R.anim.exit_fade);
            }
        });
    }
}
