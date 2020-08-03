package com.adcvn.adcsaleagrotech.action.activity.verifyotp.model;

public class VerifyOtpModel {
    private String verifyCode;
    private String authenticationId;

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }
}
