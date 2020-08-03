package com.adcvn.adcsaleagrotech.action.activity.login.viewmodel;

import android.content.Context;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.adcvn.adcsaleagrotech.action.activity.login.middleinterface.ILoginViewModel;
import com.adcvn.adcsaleagrotech.action.activity.login.model.UserLogin;
import com.adcvn.adcsaleagrotech.action.model.device.DeviceInfo;
import com.adcvn.adcsaleagrotech.common.Cache;
import com.adcvn.adcsaleagrotech.common.Common;
import com.adcvn.adcsaleagrotech.common.Constant;
import com.adcvn.adcsaleagrotech.common.TranslateText;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IAppLogin;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.ILogin;
import com.adcvn.adcsaleagrotech.model.applogin.AppLoginPost;
import com.adcvn.adcsaleagrotech.model.applogin.AppLoginResult;
import com.adcvn.adcsaleagrotech.model.login.LoginPost;
import com.adcvn.adcsaleagrotech.model.login.LoginResult;
import com.adcvn.adcsaleagrotech.server.CenterCallApi;

public class LoginViewModel extends ViewModel implements ILoginViewModel, ILogin, IAppLogin {
    private MutableLiveData<UserLogin> userLoginData;
    private MutableLiveData<LoginResult> loginResultData;
    private MutableLiveData<AppLoginResult> appLoginResultData;
    private Context context;
    private FragmentManager fm;
    private CenterCallApi centerCallApi;

    public LoginViewModel() {
        UserLogin initUserLogin = new UserLogin();
        userLoginData = new MutableLiveData();
        userLoginData.setValue(initUserLogin);
        LoginResult initLoginResult = new LoginResult();
        loginResultData = new MutableLiveData();
        loginResultData.setValue(initLoginResult);
        AppLoginResult initAppLoginResult = new AppLoginResult();
        appLoginResultData = new MutableLiveData();
        appLoginResultData.setValue(initAppLoginResult);
    }

    @Override
    public MutableLiveData<UserLogin> getUserLoginData() {
        return userLoginData;
    }

    @Override
    public MutableLiveData<LoginResult> getLoginResultData() {
        return loginResultData;
    }

    @Override
    public MutableLiveData<AppLoginResult> getAppLoginResultData() {
        return appLoginResultData;
    }

    @Override
    public void setInitContext(Context context, FragmentManager fm) {
        centerCallApi = new CenterCallApi(context);
        this.context = context;
        this.fm = fm;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        UserLogin userData = userLoginData.getValue();
        userData.setNumberPhone(phoneNumber);
        userLoginData.setValue(userData);
    }

    @Override
    public void callApiLogin() {
        Common.token = null;
        String loginKey = "";
        String phoneNumber = TranslateText.getNumberPhoneFromMask(userLoginData.getValue().getNumberPhone());
        String phoneNumberCache = Cache.loadPreferenceData(context, Constant.SHARE_PRE_CACHE_LOGIN, "phoneNumber");
        if (phoneNumberCache != "" && phoneNumberCache.equals(phoneNumber)) {
            loginKey = Cache.loadPreferenceData(context, Constant.SHARE_PRE_CACHE_LOGIN, "loginKey");
        }
        DeviceInfo deviceInfo = new DeviceInfo(context);
        LoginPost loginPost = new LoginPost(TranslateText.getNumberPhoneFromMask(userLoginData.getValue().getNumberPhone()), loginKey, deviceInfo.GetDeviceIMEI(), "Android");
        centerCallApi.login(this, loginPost, fm);
    }

    @Override
    public void getLogin(LoginResult loginResult) {
        String phoneNumber = TranslateText.getNumberPhoneFromMask(userLoginData.getValue().getNumberPhone());
        if (Cache.loadPreferenceData(context, Constant.SHARE_PRE_CACHE_LOGIN, "phoneNumber") != phoneNumber) {
            Cache.savePreferenceData(context, Constant.SHARE_PRE_CACHE_LOGIN, "phoneNumber", phoneNumber);
        }
        Common.token = loginResult.getLoginDetail().get(0).getToken();
        loginResultData.setValue(loginResult);
    }

    @Override
    public void callApiAppLogin(AppLoginPost appLoginPost) {
        centerCallApi.appLogin(this, appLoginPost, fm);
    }

    @Override
    public void getAppLogin(AppLoginResult appLoginResult) {
        appLoginResultData.setValue(appLoginResult);
    }
}
