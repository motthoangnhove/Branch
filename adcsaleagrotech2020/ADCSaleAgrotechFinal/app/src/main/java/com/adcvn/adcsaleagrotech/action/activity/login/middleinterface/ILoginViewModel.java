package com.adcvn.adcsaleagrotech.action.activity.login.middleinterface;

import android.content.Context;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import com.adcvn.adcsaleagrotech.action.activity.login.model.UserLogin;
import com.adcvn.adcsaleagrotech.model.applogin.AppLoginPost;
import com.adcvn.adcsaleagrotech.model.applogin.AppLoginResult;
import com.adcvn.adcsaleagrotech.model.login.LoginResult;

public interface ILoginViewModel {
    MutableLiveData<UserLogin> getUserLoginData();
    MutableLiveData<LoginResult> getLoginResultData();
    MutableLiveData<AppLoginResult> getAppLoginResultData();
    void setInitContext(Context context, FragmentManager fm);
    void setPhoneNumber(String phoneNumber);
    void callApiLogin();
    void callApiAppLogin(AppLoginPost appLoginPost);
}
