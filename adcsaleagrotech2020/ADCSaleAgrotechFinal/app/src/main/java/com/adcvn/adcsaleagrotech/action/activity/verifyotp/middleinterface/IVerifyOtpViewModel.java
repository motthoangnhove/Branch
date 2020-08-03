package com.adcvn.adcsaleagrotech.action.activity.verifyotp.middleinterface;

import android.content.Context;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;

import com.adcvn.adcsaleagrotech.action.activity.verifyotp.model.VerifyOtpModel;
import com.adcvn.adcsaleagrotech.model.applogin.AppLoginPost;
import com.adcvn.adcsaleagrotech.model.applogin.AppLoginResult;
import com.adcvn.adcsaleagrotech.model.verifyopt.VerifyOtpResult;

public interface IVerifyOtpViewModel {

    MutableLiveData<VerifyOtpModel> getVerifyOtpModelMutableLiveData();

    MutableLiveData<VerifyOtpResult> getVerifyOtpResultMutableLiveData();

    MutableLiveData<AppLoginResult> getAppLoginResultData();

    void setInitContext(Context context, FragmentManager fm);

    void setVerifyCode(String verifyCode);

    void setAuthenticationId(String authenticationId);

    void callApiVerifyOtp();

    void callApiAppLogin(AppLoginPost appLoginPost);

}
