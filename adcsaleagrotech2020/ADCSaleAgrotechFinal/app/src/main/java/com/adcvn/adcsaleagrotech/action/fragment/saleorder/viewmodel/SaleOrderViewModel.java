package com.adcvn.adcsaleagrotech.action.fragment.saleorder.viewmodel;

import android.content.Context;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.adcvn.adcsaleagrotech.action.fragment.saleorder.middleinterface.ISaleOrderViewModel;
import com.adcvn.adcsaleagrotech.application.ADCSaleAgrotechApplication;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IOMSalesOrderList;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.ISalesOrder;
import com.adcvn.adcsaleagrotech.model.applogin.AppLoginDetail;
import com.adcvn.adcsaleagrotech.model.omsalesorderlist.OMSalesOrderListResult;
import com.adcvn.adcsaleagrotech.model.salesorder.SalesOrder;
import com.adcvn.adcsaleagrotech.server.CenterCallApi;

public class SaleOrderViewModel extends ViewModel implements ISaleOrderViewModel, IOMSalesOrderList, ISalesOrder {
    private MutableLiveData<OMSalesOrderListResult> omSalesOrderListResultData;
    private MutableLiveData<SalesOrder> salesOrderData = new MutableLiveData<>();
    private FragmentManager fm;
    private CenterCallApi centerCallApi;
    private AppLoginDetail appLoginDetail;

    public SaleOrderViewModel() {
        OMSalesOrderListResult initOrderDeliveryDetailItemData = new OMSalesOrderListResult();
        omSalesOrderListResultData = new MutableLiveData();
        omSalesOrderListResultData.setValue(initOrderDeliveryDetailItemData);
    }

    @Override
    public MutableLiveData<OMSalesOrderListResult> getOMSalesOrderListResultData() {
        return omSalesOrderListResultData;
    }

    @Override
    public MutableLiveData<SalesOrder> getSalesOrderResultData() {
        return salesOrderData;
    }

    @Override
    public void setInitContext(Context context, FragmentManager fm) {
        centerCallApi = new CenterCallApi(context);
        appLoginDetail =((ADCSaleAgrotechApplication) context.getApplicationContext()).getAppLoginDetail();
        this.fm = fm;
    }

    @Override
    public void callApiOMSalesOrderList(int skip, String search) {
        String filter = getOrderListFilter();
        centerCallApi.omSalesOrderList(this, skip, filter, search , fm);
    }

    @Override
    public void callApiSalesOrder(String salesOrderId) {
        centerCallApi.salesOrder(this, salesOrderId, fm);
    }

    @Override
    public void getOMSalesOrderList(OMSalesOrderListResult omSalesOrderListResult) {
      omSalesOrderListResultData.setValue(omSalesOrderListResult);
    }

    //lấy filter danh sách đầu vào danh sách đơn hàng
    private String getOrderListFilter() {
        String strFilter ="OrganizationId eq '"+appLoginDetail.getOrganizationId()+"'";
        if(appLoginDetail.getIsManager() == 0) {
            strFilter += " and AssigneeId eq '"+appLoginDetail.getEmployeeId()+"'";
        }
        strFilter+= "&$orderby=SeqNum desc";
        return strFilter;
    }

    @Override
    public void getSalesOrder(SalesOrder salesOrderResult) {
        salesOrderData.setValue(salesOrderResult);
    }
}

