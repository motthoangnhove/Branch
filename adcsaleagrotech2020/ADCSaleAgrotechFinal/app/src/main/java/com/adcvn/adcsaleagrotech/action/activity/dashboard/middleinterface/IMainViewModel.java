package com.adcvn.adcsaleagrotech.action.activity.dashboard.middleinterface;

import android.content.Context;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import com.adcvn.adcsaleagrotech.model.ordertypelist.OMOrderTypeListResult;

public interface IMainViewModel {

    MutableLiveData<OMOrderTypeListResult> getOrderTypeResultData();

    void setInitApi(Context context, FragmentManager fm);

    void callApiOrderType();
}
