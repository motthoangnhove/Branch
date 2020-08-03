package com.adcvn.adcsaleagrotech.model.verifyopt;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyOtpResultDetail implements Parcelable {

    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("UserId")
    @Expose
    private String userId;
    @SerializedName("Token")
    @Expose
    private String token;
    @SerializedName("IsExpire")
    @Expose
    private Integer isExpire;
    @SerializedName("LoginKey")
    @Expose
    private String loginKey;
    @SerializedName("ErrorCode")
    @Expose
    private Integer errorCode;

    public final static Creator<VerifyOtpResultDetail> CREATOR = new Creator<VerifyOtpResultDetail>() {
        @SuppressWarnings({
                "unchecked"
        })
        public VerifyOtpResultDetail createFromParcel(Parcel in) {
            return new VerifyOtpResultDetail(in);
        }

        public VerifyOtpResultDetail[] newArray(int size) {
            return (new VerifyOtpResultDetail[size]);
        }

    };

    protected VerifyOtpResultDetail(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.userId = ((String) in.readValue((String.class.getClassLoader())));
        this.token = ((String) in.readValue((String.class.getClassLoader())));
        this.isExpire = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.loginKey = ((String) in.readValue((String.class.getClassLoader())));
        this.errorCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public VerifyOtpResultDetail() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getIsExpire() {
        return isExpire;
    }

    public void setIsExpire(Integer isExpire) {
        this.isExpire = isExpire;
    }

    public String getLoginKey() {
        return loginKey;
    }

    public void setLoginKey(String loginKey) {
        this.loginKey = loginKey;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeValue(userId);
        dest.writeValue(token);
        dest.writeValue(isExpire);
        dest.writeValue(loginKey);
        dest.writeValue(errorCode);
    }

    public int describeContents() {
        return 0;
    }

}