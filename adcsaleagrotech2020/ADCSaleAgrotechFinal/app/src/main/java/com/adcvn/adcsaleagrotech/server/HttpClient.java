package com.adcvn.adcsaleagrotech.server;

import com.adcvn.adcsaleagrotech.common.Constant;
import com.adcvn.adcsaleagrotech.model.applogin.AppLoginPost;
import com.adcvn.adcsaleagrotech.model.applogin.AppLoginResult;
import com.adcvn.adcsaleagrotech.model.arbanklist.ARBankListResult;
import com.adcvn.adcsaleagrotech.model.createorder.SalesOrderActionPost;
import com.adcvn.adcsaleagrotech.model.createorder.SalesOrderActionResult;
import com.adcvn.adcsaleagrotech.model.depositedamountaction.DepositedAmountActionPost;
import com.adcvn.adcsaleagrotech.model.depositedamountaction.DepositedAmountActionResult;
import com.adcvn.adcsaleagrotech.model.login.LoginPost;
import com.adcvn.adcsaleagrotech.model.login.LoginResult;
import com.adcvn.adcsaleagrotech.model.misacustomerdebtcheck.MisaCustomerDebtCheckPost;
import com.adcvn.adcsaleagrotech.model.misacustomerdebtcheck.MisaCustomerDebtCheckResult;
import com.adcvn.adcsaleagrotech.model.omcustomerlist.OMCustomerListResult;
import com.adcvn.adcsaleagrotech.model.omemployeelist.OMEmployeeListResult;
import com.adcvn.adcsaleagrotech.model.omsalesorderlist.OMSalesOrderListResult;
import com.adcvn.adcsaleagrotech.model.ordertypelist.OMOrderTypeListResult;
import com.adcvn.adcsaleagrotech.model.pricelistget.PriceListGetPost;
import com.adcvn.adcsaleagrotech.model.pricelistget.PriceListGetResult;
import com.adcvn.adcsaleagrotech.model.receiptedtransfercustomeraction.ReceiptedTransferCustomerActionPost;
import com.adcvn.adcsaleagrotech.model.receiptedtransfercustomeraction.ReceiptedTransferCustomerActionResult;
import com.adcvn.adcsaleagrotech.model.receiptrequestaction.ReceiptRequestActionPost;
import com.adcvn.adcsaleagrotech.model.receiptrequestaction.ReceiptRequestActionResult;
import com.adcvn.adcsaleagrotech.model.receivableaction.ReceiptedActionPost;
import com.adcvn.adcsaleagrotech.model.receivableaction.ReceiptedActionResult;
import com.adcvn.adcsaleagrotech.model.receivemoney.receivablelist.ReceivableListResult;
import com.adcvn.adcsaleagrotech.model.salesorder.SalesOrder;
import com.adcvn.adcsaleagrotech.model.verifyopt.VerifyOtpPost;
import com.adcvn.adcsaleagrotech.model.verifyopt.VerifyOtpResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface HttpClient {

    @POST(Constant.LINK_REQUEST+"_Login")
    Call<LoginResult> login(@Body LoginPost body);

    @POST(Constant.LINK_REQUEST+"_LoginOTPCheck")
    Call<VerifyOtpResult> loginOTPCheck(@Body VerifyOtpPost body);

    @POST(Constant.LINK_REQUEST + "_AppLogin")
    Call<AppLoginResult> appLogin(@Body AppLoginPost body);

    @GET
    Call<ReceivableListResult> receivableList(@Url String url);

    @POST(Constant.LINK_REQUEST + "_ReceiptedAction")
    Call<ReceiptedActionResult> receiptedAction(@Body ReceiptedActionPost body);

    @POST(Constant.LINK_REQUEST + "_ReceiptRequestAction")
    Call<ReceiptRequestActionResult> receiptRequestAction(@Body ReceiptRequestActionPost body);

    @GET
    Call<OMOrderTypeListResult> oMOrderTypeList(@Url String url);

    @GET
    Call<OMEmployeeListResult> oMEmployeeList(@Url String url);

    @GET
    Call<OMCustomerListResult> oMCustomerList(@Url String url);

    @POST(Constant.LINK_REQUEST + "_MisaCustomerDebtCheck")
    Call<MisaCustomerDebtCheckResult> misaCustomerDebtCheck(@Body MisaCustomerDebtCheckPost body);

    @POST(Constant.LINK_REQUEST + "_PricelistGet")
    Call<PriceListGetResult> pricelistGet(@Body PriceListGetPost body);

    @POST(Constant.LINK_REQUEST + "_SalesOrderAction")
    Call<SalesOrderActionResult> salesOrderAction(@Body SalesOrderActionPost body);

    @POST(Constant.LINK_REQUEST + "_ReceiptedTransferCustomerAction")
    Call<ReceiptedTransferCustomerActionResult> receiptedTransferCustomerAction(@Body ReceiptedTransferCustomerActionPost body);

    @GET
    Call<ARBankListResult> arBankList(@Url String url);

    @POST(Constant.LINK_REQUEST + "_DepositedAmountAction")
    Call<DepositedAmountActionResult> depositedAmountAction(@Body DepositedAmountActionPost body);

    @GET
    Call<OMSalesOrderListResult> oMSalesOrderList(@Url String url);

    @GET
    Call<SalesOrder> salesOrder(@Url String url);

}
