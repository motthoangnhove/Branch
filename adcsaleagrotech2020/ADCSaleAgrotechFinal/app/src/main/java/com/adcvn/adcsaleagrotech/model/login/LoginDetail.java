package com.adcvn.adcsaleagrotech.model.login;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDetail implements Parcelable
{

    @SerializedName("OTPRequire")
    @Expose
    private Integer oTPRequire;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("AuthenticationId")
    @Expose
    private String authenticationId;
    @SerializedName("RegisterStatus")
    @Expose
    private Integer registerStatus;
    @SerializedName("Token")
    @Expose
    private String token;
    @SerializedName("UserId")
    @Expose
    private String userId;
    @SerializedName("ErrorCode")
    @Expose
    private Integer errorCode;
    public final static Creator<LoginDetail> CREATOR = new Creator<LoginDetail>() {


        @SuppressWarnings({
                "unchecked"
        })
        public LoginDetail createFromParcel(Parcel in) {
            return new LoginDetail(in);
        }

        public LoginDetail[] newArray(int size) {
            return (new LoginDetail[size]);
        }

    }
            ;

    protected LoginDetail(Parcel in) {
        this.oTPRequire = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.authenticationId = ((String) in.readValue((String.class.getClassLoader())));
        this.userId = ((String) in.readValue((String.class.getClassLoader())));
        this.token = ((String) in.readValue((String.class.getClassLoader())));
        this.registerStatus = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.errorCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public LoginDetail() {
    }

    public Integer getOTPRequire() {
        return oTPRequire;
    }

    public void setOTPRequire(Integer oTPRequire) {
        this.oTPRequire = oTPRequire;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
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

    public Integer getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(Integer registerStatus) {
        this.registerStatus = registerStatus;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(oTPRequire);
        dest.writeValue(message);
        dest.writeValue(authenticationId);
        dest.writeValue(userId);
        dest.writeValue(token);
        dest.writeValue(registerStatus);
        dest.writeValue(errorCode);
    }

    public int describeContents() {
        return 0;
    }

}