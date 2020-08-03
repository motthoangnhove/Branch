package com.adcvn.adcsaleagrotech.action.activity.dashboard.viewmodel;

import android.content.Context;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.adcvn.adcsaleagrotech.action.activity.dashboard.middleinterface.IMainViewModel;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IOMOrderTypeList;
import com.adcvn.adcsaleagrotech.model.ordertypelist.OMOrderTypeListResult;
import com.adcvn.adcsaleagrotech.server.CenterCallApi;

public class MainViewModel extends ViewModel implements IMainViewModel, IOMOrderTypeList {
    private MutableLiveData<OMOrderTypeListResult> orderTypeResultData = new MutableLiveData<>();
    private OMOrderTypeListResult orderType = new OMOrderTypeListResult();
    private FragmentManager fm;
    private Context context;
    private CenterCallApi centerCallApi;

    public MainViewModel() {
        orderTypeResultData.setValue(orderType);
    }

    @Override
    public MutableLiveData<OMOrderTypeListResult> getOrderTypeResultData() {
        return orderTypeResultData;
    }

    @Override
    public void setInitApi(Context context, FragmentManager fm) {
        centerCallApi = new CenterCallApi(context);
        this.context = context;
        this.fm = fm;
    }

    @Override
    public void callApiOrderType() {
        centerCallApi.oMOrderTypeList(this, fm);
    }

    @Override
    public void getOMOrderTypeList(OMOrderTypeListResult omOrderTypeListResult) {
        orderTypeResultData.setValue(omOrderTypeListResult);
    }
}
