package com.adcvn.adcsaleagrotech.action.activity.orderdetail.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.adcvn.adcsaleagrotech.action.activity.orderdetail.middleinterface.IOrderDetailViewModel;
import com.adcvn.adcsaleagrotech.model.salesorder.SalesOrder;

public class OrderDetailViewModel extends  ViewModel implements IOrderDetailViewModel {
    private MutableLiveData<SalesOrder> salesOrderData = new MutableLiveData();

    public OrderDetailViewModel() {

    }

    @Override
    public MutableLiveData<SalesOrder> salesOrderData() {
        return salesOrderData;
    }

    @Override
    public void setSalesOrder(SalesOrder salesOrder) {
        salesOrderData.setValue(salesOrder);
    }

}
