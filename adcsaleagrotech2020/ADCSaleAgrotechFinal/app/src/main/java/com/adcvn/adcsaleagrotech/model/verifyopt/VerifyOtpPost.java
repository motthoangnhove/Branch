package com.adcvn.adcsaleagrotech.model.verifyopt;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyOtpPost implements Parcelable {

    @SerializedName("AuthenticationId")
    @Expose
    private String authenticationId;

    @SerializedName("OTP")
    @Expose
    private String oTP;

    protected VerifyOtpPost(Parcel in) {
        authenticationId = in.readString();
        oTP = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(authenticationId);
        dest.writeString(oTP);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VerifyOtpPost> CREATOR = new Creator<VerifyOtpPost>() {
        @Override
        public VerifyOtpPost createFromParcel(Parcel in) {
            return new VerifyOtpPost(in);
        }

        @Override
        public VerifyOtpPost[] newArray(int size) {
            return new VerifyOtpPost[size];
        }
    };

    public String getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }

    public String getOTP() {
        return oTP;
    }

    public void setOTP(String oTP) {
        this.oTP = oTP;
    }

    public VerifyOtpPost(String authenticationId, String oTP) {
        this.authenticationId = authenticationId;
        this.oTP = oTP;
    }

    public VerifyOtpPost() {
    }
}
