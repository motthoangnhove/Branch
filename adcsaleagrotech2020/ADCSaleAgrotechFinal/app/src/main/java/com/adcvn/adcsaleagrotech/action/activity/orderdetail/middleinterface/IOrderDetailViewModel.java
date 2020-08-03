package com.adcvn.adcsaleagrotech.action.activity.orderdetail.middleinterface;

import androidx.lifecycle.MutableLiveData;
import com.adcvn.adcsaleagrotech.model.salesorder.SalesOrder;

public interface IOrderDetailViewModel {
    MutableLiveData<SalesOrder> salesOrderData();
    void setSalesOrder(SalesOrder salesOrder);
}
