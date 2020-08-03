package com.adcvn.adcsaleagrotech.action.activity.verifyotp.viewmodel;

import android.content.Context;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.adcvn.adcsaleagrotech.action.activity.verifyotp.middleinterface.IVerifyOtpViewModel;
import com.adcvn.adcsaleagrotech.action.activity.verifyotp.model.VerifyOtpModel;
import com.adcvn.adcsaleagrotech.common.Cache;
import com.adcvn.adcsaleagrotech.common.Common;
import com.adcvn.adcsaleagrotech.common.Constant;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IAppLogin;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IVerifyOtp;
import com.adcvn.adcsaleagrotech.model.applogin.AppLoginPost;
import com.adcvn.adcsaleagrotech.model.applogin.AppLoginResult;
import com.adcvn.adcsaleagrotech.model.verifyopt.VerifyOtpPost;
import com.adcvn.adcsaleagrotech.model.verifyopt.VerifyOtpResult;
import com.adcvn.adcsaleagrotech.model.verifyopt.VerifyOtpResultDetail;
import com.adcvn.adcsaleagrotech.server.CenterCallApi;

public class VerifyOtpViewModel extends ViewModel implements IVerifyOtpViewModel, IVerifyOtp, IAppLogin {
    private MutableLiveData<VerifyOtpModel> verifyOtpModelMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<VerifyOtpResult> verifyOtpResultMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<AppLoginResult> appLoginResultData;
    private VerifyOtpModel initVerifyOtpModel = new VerifyOtpModel();
    private VerifyOtpResult verifyOtpResult = new VerifyOtpResult();
    private FragmentManager fm;
    private Context context;
    private CenterCallApi centerCallApi;

    public VerifyOtpViewModel() {
        verifyOtpModelMutableLiveData.setValue(initVerifyOtpModel);
        verifyOtpResultMutableLiveData.setValue(verifyOtpResult);
        AppLoginResult initAppLoginResult = new AppLoginResult();
        appLoginResultData = new MutableLiveData();
        appLoginResultData.setValue(initAppLoginResult);
    }

    @Override
    public MutableLiveData<VerifyOtpModel> getVerifyOtpModelMutableLiveData() {
        return verifyOtpModelMutableLiveData;
    }

    @Override
    public MutableLiveData<VerifyOtpResult> getVerifyOtpResultMutableLiveData() {
        return verifyOtpResultMutableLiveData;
    }

    @Override
    public MutableLiveData<AppLoginResult> getAppLoginResultData() {
        return appLoginResultData ;
    }

    @Override
    public void setInitContext(Context context, FragmentManager fm) {
        centerCallApi = new CenterCallApi(context);
        this.context = context;
        this.fm = fm;
    }

    @Override
    public void setVerifyCode(String verifyCode) {
        VerifyOtpModel verifyData = verifyOtpModelMutableLiveData.getValue();
        verifyData.setVerifyCode(verifyCode);
        verifyOtpModelMutableLiveData.setValue(verifyData);
    }

    @Override
    public void setAuthenticationId(String authenticationId) {
        VerifyOtpModel verifyData = verifyOtpModelMutableLiveData.getValue();
        verifyData.setAuthenticationId(authenticationId);
        verifyOtpModelMutableLiveData.setValue(verifyData);
    }

    @Override
    public void callApiVerifyOtp() {
        VerifyOtpPost verifyOtpPost = new VerifyOtpPost(
                verifyOtpModelMutableLiveData.getValue().getAuthenticationId(),
                verifyOtpModelMutableLiveData.getValue().getVerifyCode());
        centerCallApi.loginOTPCheck(this, verifyOtpPost, fm);
    }

    @Override
    public void callApiAppLogin(AppLoginPost appLoginPost) {
        centerCallApi.appLogin(this, appLoginPost, fm);
    }

    @Override
    public void getVerifyOtpResult(VerifyOtpResult verifyOtpResult) {
        verifyOtpResultMutableLiveData.setValue(verifyOtpResult);
        VerifyOtpResultDetail resultDetail = verifyOtpResult.getValue().get(0);
        if (resultDetail.getErrorCode() == 0) {
            Common.token = resultDetail.getToken();
            Cache.savePreferenceData(context, Constant.SHARE_PRE_CACHE_LOGIN, "loginKey", resultDetail.getLoginKey());
        }
    }

    @Override
    public void getAppLogin(AppLoginResult appLoginResult) {
        appLoginResultData.setValue(appLoginResult);
    }
}
