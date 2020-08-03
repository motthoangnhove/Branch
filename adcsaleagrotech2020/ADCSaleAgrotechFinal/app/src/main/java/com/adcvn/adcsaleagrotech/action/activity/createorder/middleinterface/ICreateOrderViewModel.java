package com.adcvn.adcsaleagrotech.action.activity.createorder.middleinterface;

import android.content.Context;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;

import com.adcvn.adcsaleagrotech.model.createorder.SalesOrderActionResult;
import com.adcvn.adcsaleagrotech.model.misacustomerdebtcheck.MisaCustomerDebtCheckResult;
import com.adcvn.adcsaleagrotech.model.omcustomerlist.OMCustomer;
import com.adcvn.adcsaleagrotech.model.omcustomerlist.OMCustomerListResult;
import com.adcvn.adcsaleagrotech.model.omemployeelist.OMEmployee;
import com.adcvn.adcsaleagrotech.model.omemployeelist.OMEmployeeListResult;
import com.adcvn.adcsaleagrotech.model.ordertypelist.OrderTypeDetail;
import com.adcvn.adcsaleagrotech.model.pricelistget.PriceListGetResult;
import com.adcvn.adcsaleagrotech.model.pricelistget.ProductDetail;

import java.util.ArrayList;

public interface ICreateOrderViewModel {
    MutableLiveData<OrderTypeDetail> orderTypeDetailData();
    MutableLiveData<OMEmployeeListResult> oMEmployeeListResultData();
    MutableLiveData<OMEmployee> employeeChooseData();
    MutableLiveData<OMCustomerListResult> oMCustomerListResultData();
    MutableLiveData<OMCustomer> customerChooseData();
    MutableLiveData<MisaCustomerDebtCheckResult> misaCustomerDebtCheckResultData();
    MutableLiveData<PriceListGetResult> priceListGetResultData();
    MutableLiveData<ArrayList<ProductDetail>> productChooseData();
    MutableLiveData<SalesOrderActionResult> salesOrderActionResultData();
    void setInitContext(Context context, FragmentManager fm);
    void setOrderTypeDetail(OrderTypeDetail orderTypeDetail);
    void setEmployeeChoose(OMEmployee omEmployeeChoose);
    void setCustomerChoose(OMCustomer  omCustomerChoose);
    void callApiOMEmployeeList(int skip, String search);
    void callApiOMCustomerList(int skip, String search);
    void callApiMisaCustomerDebtCheck();
    void callApiPriceListGet();
    void setProducts(ArrayList<ProductDetail> products);
    void callApiSalesOrderAction(boolean receiveMoney, String deliveryDate, double totalAmount, String note);
}
