package com.adcvn.adcsaleagrotech.application;


import android.content.Context;


import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.adcvn.adcsaleagrotech.model.applogin.AppLoginDetail;

public class ADCSaleAgrotechApplication extends MultiDexApplication {
    private String userId;
    private AppLoginDetail appLoginDetail;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public AppLoginDetail getAppLoginDetail() {
        return appLoginDetail;
    }

    public void setAppLoginDetail(AppLoginDetail appLoginDetail) {
        this.appLoginDetail = appLoginDetail;
    }
}