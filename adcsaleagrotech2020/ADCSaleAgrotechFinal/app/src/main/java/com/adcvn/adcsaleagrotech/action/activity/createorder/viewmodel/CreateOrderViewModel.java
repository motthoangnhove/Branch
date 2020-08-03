package com.adcvn.adcsaleagrotech.action.activity.createorder.viewmodel;

import android.content.Context;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.adcvn.adcsaleagrotech.action.activity.createorder.middleinterface.ICreateOrderViewModel;
import com.adcvn.adcsaleagrotech.application.ADCSaleAgrotechApplication;
import com.adcvn.adcsaleagrotech.common.SystemDateTime;
import com.adcvn.adcsaleagrotech.common.ToJsonString;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IMisaCustomerDebtCheck;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IOMCustomerList;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IOMEmployeeList;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IPriceListGet;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.ISalesOrderAction;
import com.adcvn.adcsaleagrotech.model.applogin.AppLoginDetail;
import com.adcvn.adcsaleagrotech.model.createorder.SalesOrderActionPost;
import com.adcvn.adcsaleagrotech.model.createorder.SalesOrderActionResult;
import com.adcvn.adcsaleagrotech.model.misacustomerdebtcheck.MisaCustomerDebtCheckPost;
import com.adcvn.adcsaleagrotech.model.misacustomerdebtcheck.MisaCustomerDebtCheckResult;
import com.adcvn.adcsaleagrotech.model.omcustomerlist.OMCustomer;
import com.adcvn.adcsaleagrotech.model.omcustomerlist.OMCustomerListResult;
import com.adcvn.adcsaleagrotech.model.omemployeelist.OMEmployee;
import com.adcvn.adcsaleagrotech.model.omemployeelist.OMEmployeeListResult;
import com.adcvn.adcsaleagrotech.model.ordertypelist.OrderTypeDetail;
import com.adcvn.adcsaleagrotech.model.pricelistget.PriceListGetPost;
import com.adcvn.adcsaleagrotech.model.pricelistget.PriceListGetResult;
import com.adcvn.adcsaleagrotech.model.pricelistget.ProductDetail;
import com.adcvn.adcsaleagrotech.server.CenterCallApi;
import java.util.ArrayList;
import java.util.List;

public class CreateOrderViewModel extends ViewModel implements ICreateOrderViewModel, IOMEmployeeList, IOMCustomerList, IMisaCustomerDebtCheck, IPriceListGet, ISalesOrderAction {
    private MutableLiveData<OrderTypeDetail> orderTypeDetailData = new MutableLiveData();
    private MutableLiveData<OMEmployeeListResult> oMEmployeeListResultData  = new MutableLiveData();
    private MutableLiveData<OMEmployee> employeeChooseData = new MutableLiveData();
    private MutableLiveData<OMCustomerListResult> oMCustomerListResultData  = new MutableLiveData();
    private MutableLiveData<OMCustomer> customerChooseData = new MutableLiveData();
    private MutableLiveData<MisaCustomerDebtCheckResult> misaCustomerDebtCheckResultData = new MutableLiveData();
    private MutableLiveData<PriceListGetResult> priceListGetResultData = new MutableLiveData();
    private MutableLiveData<ArrayList<ProductDetail>> productChooseData = new MutableLiveData();
    private  MutableLiveData<SalesOrderActionResult> salesOrderActionResultData = new MutableLiveData();
    private Context context;
    private CenterCallApi centerCallApi;
    private AppLoginDetail appLoginDetail;
    private FragmentManager fm;
    public CreateOrderViewModel() {

    }
    @Override
    public MutableLiveData<OrderTypeDetail> orderTypeDetailData() {
        return orderTypeDetailData;
    }

    @Override
    public MutableLiveData<OMEmployeeListResult> oMEmployeeListResultData() {
        return oMEmployeeListResultData;
    }

    @Override
    public MutableLiveData<OMEmployee> employeeChooseData() {
        return employeeChooseData;
    }

    @Override
    public MutableLiveData<OMCustomerListResult> oMCustomerListResultData() {
        return oMCustomerListResultData;
    }

    @Override
    public MutableLiveData<OMCustomer> customerChooseData() {
        return customerChooseData;
    }

    @Override
    public MutableLiveData<MisaCustomerDebtCheckResult> misaCustomerDebtCheckResultData() {
        return misaCustomerDebtCheckResultData;
    }

    @Override
    public MutableLiveData<PriceListGetResult> priceListGetResultData() {
        return priceListGetResultData;
    }

    @Override
    public MutableLiveData<ArrayList<ProductDetail>> productChooseData() {
        return productChooseData;
    }

    @Override
    public MutableLiveData<SalesOrderActionResult> salesOrderActionResultData() {
        return salesOrderActionResultData;
    }

    @Override
    public void setInitContext(Context context, FragmentManager fm) {
        this.context = context;
        centerCallApi = new CenterCallApi(context);
        appLoginDetail =((ADCSaleAgrotechApplication) context.getApplicationContext()).getAppLoginDetail();
        this.fm = fm;
    }

    @Override
    public void setOrderTypeDetail(OrderTypeDetail orderTypeDetail) {
        orderTypeDetailData.setValue(orderTypeDetail);
    }

    @Override
    public void setEmployeeChoose(OMEmployee omEmployeeChoose) {
        employeeChooseData.setValue(omEmployeeChoose);
    }

    @Override
    public void setCustomerChoose(OMCustomer omCustomerChoose) {
        customerChooseData.setValue(omCustomerChoose);
    }

    @Override
    public void callApiOMEmployeeList(int skip, String search) {
       String filter = getEmployeeListFilter();
       centerCallApi.oMEmployeeList(this, skip, filter, search, fm);
    }

    //lấy filter danh sách đầu vào danh sách nhân viên
    private String getEmployeeListFilter() {
        String strFilter ="OrganizationId eq '"+appLoginDetail.getOrganizationId()+"'";
        return strFilter;
    }

    @Override
    public void callApiOMCustomerList(int skip, String search) {
       OMEmployee omEmployee = employeeChooseData.getValue();
       centerCallApi.oMCustomerList(this, omEmployee.getEmployeeId(), skip, search, fm);
    }

    @Override
    public void callApiMisaCustomerDebtCheck() {
        OMCustomer omCustomer = customerChooseData.getValue();
        MisaCustomerDebtCheckPost misaCustomerDebtCheckPost = new MisaCustomerDebtCheckPost(omCustomer.getCustomerCode());
        centerCallApi.misaCustomerDebtCheck(this, misaCustomerDebtCheckPost, fm);
    }

    @Override
    public void callApiPriceListGet() {
        OrderTypeDetail orderTypeDetail = orderTypeDetailData.getValue();
        OMCustomer omCustomer = customerChooseData.getValue();
        PriceListGetPost priceListGetPost = new PriceListGetPost(appLoginDetail.getOrganizationId(), orderTypeDetail.getOrderTypeId(), "", omCustomer.getPaymentTermDefault());
        centerCallApi.priceListGet(this, priceListGetPost, fm);
    }

    @Override
    public void setProducts(ArrayList<ProductDetail> products) {
        productChooseData.setValue(products);
    }

    @Override
    public void getMisaCustomerDebtCheck(MisaCustomerDebtCheckResult misaCustomerDebtCheckResult) {
        misaCustomerDebtCheckResultData.setValue(misaCustomerDebtCheckResult);
    }

    @Override
    public void getOMCustomerList(OMCustomerListResult omCustomerListResult) {
        oMCustomerListResultData.setValue(omCustomerListResult);
    }

    @Override
    public void getOMEmployeeList(OMEmployeeListResult omEmployeeListResult) {
        oMEmployeeListResultData.setValue(omEmployeeListResult);
    }

    @Override
    public void getPriceListGet(PriceListGetResult priceListGetResult) {
        priceListGetResultData.setValue(priceListGetResult);
    }

    @Override
    public void callApiSalesOrderAction(boolean receiveMoney, String deliveryDate, double totalAmount, String note){
        ADCSaleAgrotechApplication adcSaleAgrotechApplication= (ADCSaleAgrotechApplication) context.getApplicationContext();
        AppLoginDetail appLoginDetail = adcSaleAgrotechApplication.getAppLoginDetail();
        String employeeId = employeeChooseData.getValue().getEmployeeId();
        String contactName = "";
        String contactPhone = "";
        String customerId = customerChooseData.getValue().getCustomerId();
        String paymentTermId = customerChooseData.getValue().getPaymentTermDefault();
        String deliveryDateTranslate = SystemDateTime.formatDateToServer(deliveryDate);
        int isReceiveDelivery = receiveMoney ? 1 : 0;
        String organizationId = appLoginDetail.getOrganizationId();
        String userId = adcSaleAgrotechApplication.getUserId();
        ArrayList<ProductDetail> listProductChoose = productChooseData.getValue();
        for(int i = 0; i<listProductChoose.size(); i++){
            listProductChoose.get(i).setSeqNum(i+1);
        }
        List<Object> listProductChooseObject = new ArrayList<Object>();
        listProductChooseObject.addAll(productChooseData.getValue());
        String lineOrderDetail = ToJsonString.getJSONList(listProductChooseObject,
                "com.adcvn.adcsaleagrotech.model.pricelistget.ProductDetail",
                "getSeqNum",
       "getProductCode",
        "getQuantity",
        "getUnitPrice",
        "getUnitPriceAfterTax",
        "getVatRate");
        SalesOrderActionPost salesOrderActionPost = new SalesOrderActionPost("604CD4B2-AE70-4819-9C42-300922F1482D", totalAmount, employeeId, contactName, contactPhone, employeeId, customerId, deliveryDateTranslate, isReceiveDelivery, lineOrderDetail, organizationId, paymentTermId, employeeId, userId, note);
        centerCallApi.salesOrderAction(this, salesOrderActionPost, fm);
    }

    @Override
    public void getSalesOrderAction(SalesOrderActionResult salesOrderActionResult) {
        salesOrderActionResultData.setValue(salesOrderActionResult);
    }
}
