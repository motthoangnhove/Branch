package com.adcvn.adcsaleagrotech.action.fragment.receivablelist.viewmodel;

import android.content.Context;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.adcvn.adcsaleagrotech.action.fragment.receivablelist.middleinterface.IReceivableListViewModel;
import com.adcvn.adcsaleagrotech.application.ADCSaleAgrotechApplication;
import com.adcvn.adcsaleagrotech.common.SystemDateTime;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IReceivableList;
import com.adcvn.adcsaleagrotech.model.applogin.AppLoginDetail;
import com.adcvn.adcsaleagrotech.model.receivemoney.receivablelist.ReceivableListResult;
import com.adcvn.adcsaleagrotech.server.CenterCallApi;

public class ReceivableListViewModel extends ViewModel implements IReceivableListViewModel, IReceivableList {
    private MutableLiveData<ReceivableListResult> nowReceivableListResultData;
    private MutableLiveData<ReceivableListResult> futureReceivableListResultData;
    private Context context;
    private FragmentManager fm;
    private CenterCallApi centerCallApi;
    private AppLoginDetail appLoginDetail;

    public ReceivableListViewModel() {
        ReceivableListResult nowReceivabel = new ReceivableListResult();
        nowReceivableListResultData = new MutableLiveData<>();
        nowReceivableListResultData.setValue(nowReceivabel);
        ReceivableListResult futureReceivabel = new ReceivableListResult();
        futureReceivableListResultData = new MutableLiveData<>();
        futureReceivableListResultData.setValue(futureReceivabel);
    }

    @Override
    public MutableLiveData<ReceivableListResult> getNowReceivableData() {
        return nowReceivableListResultData;
    }

    @Override
    public MutableLiveData<ReceivableListResult> getFutureReceivableData() {
        return futureReceivableListResultData;
    }

    @Override
    public void setInitContext(Context context, FragmentManager fm) {
        appLoginDetail = ((ADCSaleAgrotechApplication) context.getApplicationContext()).getAppLoginDetail();
        centerCallApi = new CenterCallApi(context);
        this.context = context;
        this.fm = fm;
    }

    @Override
    public void callApiReceivableList(int skip, int isCurrent) {
        String search = "";
        String filter = getOrderListFilter(isCurrent);
        centerCallApi.receivableList(isCurrent, this, skip, search, filter, fm);
    }

    //lấy filter danh sách đầu vào danh sách đơn hàng và thu hộ
    private String getOrderListFilter(int isCurrent) {
        String strFilter = "OrganizationId eq '" + appLoginDetail.getOrganizationId() + "' and StatusCode eq 'Awaiting Amount'";
        if (isCurrent == 1) {
            strFilter += " and DueDay le '";
        } else {
            strFilter += " and DueDay gt '";
        }
        strFilter += SystemDateTime.getDateTimeCurrent() + "'";
        return strFilter;
    }

    @Override
    public void getReceivableList(ReceivableListResult receivableListResult, int isCurrent) {
        if (isCurrent == 1) {
            nowReceivableListResultData.setValue(receivableListResult);
        } else {
            futureReceivableListResultData.setValue(receivableListResult);
        }
    }
}
