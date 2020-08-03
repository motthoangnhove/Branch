package com.adcvn.adcsaleagrotech.model.applogin;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppLoginPost implements Parcelable
{

    @SerializedName("UserId")
    @Expose
    private String userId;
    @SerializedName("AndroidPushId")
    @Expose
    private String androidPushId;
    public final static Creator<AppLoginPost> CREATOR = new Creator<AppLoginPost>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AppLoginPost createFromParcel(Parcel in) {
            return new AppLoginPost(in);
        }

        public AppLoginPost[] newArray(int size) {
            return (new AppLoginPost[size]);
        }

    }
            ;

    protected AppLoginPost(Parcel in) {
        this.userId = ((String) in.readValue((String.class.getClassLoader())));
        this.androidPushId = ((String) in.readValue((String.class.getClassLoader())));
    }

    public AppLoginPost() {
    }
    public AppLoginPost(String userId, String androidPushId) {
        this.userId = userId;
        this.androidPushId = androidPushId;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAndroidPushId() {
        return androidPushId;
    }

    public void setAndroidPushId(String androidPushId) {
        this.androidPushId = androidPushId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(userId);
        dest.writeValue(androidPushId);
    }

    public int describeContents() {
        return 0;
    }

}