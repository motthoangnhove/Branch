package com.adcvn.adcsaleagrotech.action.fragment.saleorder.middleinterface;

import android.content.Context;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;

import com.adcvn.adcsaleagrotech.model.omsalesorderlist.OMSalesOrderListResult;
import com.adcvn.adcsaleagrotech.model.salesorder.SalesOrder;

public interface ISaleOrderViewModel {
    MutableLiveData<OMSalesOrderListResult> getOMSalesOrderListResultData();
    MutableLiveData<SalesOrder> getSalesOrderResultData();
    void setInitContext(Context context, FragmentManager fm);
    void callApiOMSalesOrderList(int skip, String search);
    void callApiSalesOrder(String salesOrderId);
}
