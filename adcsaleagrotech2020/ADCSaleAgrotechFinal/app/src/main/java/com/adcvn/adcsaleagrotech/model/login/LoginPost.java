package com.adcvn.adcsaleagrotech.model.login;

public class LoginPost {
    private String deviceId, loginKey, oSType, phoneNumber;

    public LoginPost(){
    }
    public LoginPost(String phoneNumber, String loginKey, String deviceId, String oSType) {
        this.phoneNumber = phoneNumber;
        this.loginKey = loginKey;
        this.deviceId = deviceId;
        this.oSType = oSType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getLoginKey() {
        return loginKey;
    }

    public void setLoginKey(String loginKey) {
        this.loginKey = loginKey;
    }

    public String getoSType() {
        return oSType;
    }

    public void setoSType(String oSType) {
        this.oSType = oSType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
