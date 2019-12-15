package com.chengm.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.chengm.http.base.BaseObserver;
import com.chengm.http.utils.NetUtil;

import io.reactivex.disposables.Disposable;

/**
 * author : ChenWJ
 * date : 2019/12/15 16:07
 * description : 添加加载对话框
 */
public abstract class LoadingObserver<T> extends BaseObserver<T> {

    private boolean mShowDialog;
    private ProgressDialog dialog;
    private Context mContext;
    private Disposable d;

    public LoadingObserver(Context context, Boolean showDialog) {
        mContext = context;
        mShowDialog = showDialog;
    }

    public LoadingObserver(Context context) {
        this(context, true);
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
        if (!NetUtil.isConnected(mContext)) {
            Toast.makeText(mContext, mContext.getString(R.string.check_net), Toast.LENGTH_LONG).show();
            if (d.isDisposed()) {
                d.dispose();
            }
        } else {
            if (dialog == null && mShowDialog) {
                dialog = new ProgressDialog(mContext);
                dialog.setMessage(mContext.getString(R.string.loading));
                dialog.show();
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        if (d.isDisposed()) {
            d.dispose();
        }
        hidDialog();
        super.onError(e);
    }

    @Override
    public void onComplete() {
        if (d.isDisposed()) {
            d.dispose();
        }
        hidDialog();
        super.onComplete();
    }

    /**
     * 关闭dialog
     */
    public void hidDialog() {
        if (dialog != null && mShowDialog) {
            dialog.dismiss();
        }
        dialog = null;
    }

    /**
     * 取消订阅
     */
    public void cancleRequest() {
        if (d != null && d.isDisposed()) {
            d.dispose();
            hidDialog();
        }
    }

}
