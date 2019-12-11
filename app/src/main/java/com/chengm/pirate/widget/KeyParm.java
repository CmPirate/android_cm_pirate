package com.chengm.pirate.widget;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * author : ChenWJ
 * date : 2019/12/10 23:10
 * description :
 */
public class KeyParm implements Parcelable {

    public String key;

    public Rect rect;

    public float corner;

    public float clip;

    public KeyParm() {

    }

    public KeyParm(String key, Rect rect) {
        this.key = key;
        this.rect = rect;
    }

    protected KeyParm(Parcel in) {
        key = in.readString();
        rect = in.readParcelable(Rect.class.getClassLoader());
        corner = in.readFloat();
        clip = in.readFloat();
    }

    public static final Creator<KeyParm> CREATOR = new Creator<KeyParm>() {
        @Override
        public KeyParm createFromParcel(Parcel in) {
            return new KeyParm(in);
        }

        @Override
        public KeyParm[] newArray(int size) {
            return new KeyParm[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeParcelable(rect, flags);
        dest.writeFloat(corner);
        dest.writeFloat(clip);
    }
}
