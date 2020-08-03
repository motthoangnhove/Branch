package com.adcvn.adcsaleagrotech.action.fragment.receivablelist.middleinterface;

import android.content.Context;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;

import com.adcvn.adcsaleagrotech.model.receivemoney.receivablelist.ReceivableListResult;

public interface IReceivableListViewModel {
    MutableLiveData<ReceivableListResult> getNowReceivableData();
    MutableLiveData<ReceivableListResult> getFutureReceivableData();
    void setInitContext(Context context, FragmentManager fm);
    void callApiReceivableList(int skip, int isCurrent);
}
