package com.adcvn.adcsaleagrotech.action.activity.receivableactivity.viewmodel;

import android.content.Context;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.adcvn.adcsaleagrotech.action.activity.receivableactivity.middleinterface.IReceivableActivityViewModel;
import com.adcvn.adcsaleagrotech.application.ADCSaleAgrotechApplication;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IReceiptRequestAction;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IReceiptedAction;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IReceiptedTransferCustomerAction;
import com.adcvn.adcsaleagrotech.model.applogin.AppLoginDetail;
import com.adcvn.adcsaleagrotech.model.receiptedtransfercustomeraction.ReceiptedTransferCustomerActionPost;
import com.adcvn.adcsaleagrotech.model.receiptedtransfercustomeraction.ReceiptedTransferCustomerActionResult;
import com.adcvn.adcsaleagrotech.model.receiptrequestaction.ReceiptRequestActionPost;
import com.adcvn.adcsaleagrotech.model.receiptrequestaction.ReceiptRequestActionResult;
import com.adcvn.adcsaleagrotech.model.receivableaction.ReceiptedActionPost;
import com.adcvn.adcsaleagrotech.model.receivableaction.ReceiptedActionResult;
import com.adcvn.adcsaleagrotech.model.receivemoney.receivablelist.ReceivableDetail;
import com.adcvn.adcsaleagrotech.server.CenterCallApi;

public class ReceivableActivityViewModel extends ViewModel implements IReceivableActivityViewModel, IReceiptedAction, IReceiptRequestAction, IReceiptedTransferCustomerAction {
    private MutableLiveData<ReceivableDetail> getReceivableDetailData = new MutableLiveData<>();
    private MutableLiveData<ReceiptedActionResult> receiptedActionResultData = new MutableLiveData<>();
    private MutableLiveData<ReceiptRequestActionResult> receiptRequestActionResultData = new MutableLiveData<>();
    private MutableLiveData<ReceiptedTransferCustomerActionResult> receiptedTransferCustomerActionResultData = new MutableLiveData<>();
    private CenterCallApi centerCallApi;
    private FragmentManager fm;
    private AppLoginDetail appLoginDetail;
    private String userId, reporterId, creatorId, assigneeId;

    public ReceivableActivityViewModel() {
        ReceiptedActionResult receiptedActionResult = new ReceiptedActionResult();
        receiptedActionResultData.setValue(receiptedActionResult);
        ReceiptRequestActionResult receiptedRequestActionResult = new ReceiptRequestActionResult();
        receiptRequestActionResultData.setValue(receiptedRequestActionResult);
        ReceiptedTransferCustomerActionResult receiptedTransferCustomerActionResult = new ReceiptedTransferCustomerActionResult();
        receiptedTransferCustomerActionResultData.setValue(receiptedTransferCustomerActionResult);
    }

    @Override
    public MutableLiveData<ReceivableDetail> getReceivableDetailData() {
        return getReceivableDetailData;
    }

    @Override
    public MutableLiveData<ReceiptedActionResult> getReceiptedActionResultData() {
        return receiptedActionResultData;
    }

    @Override
    public MutableLiveData<ReceiptRequestActionResult> getReceiptRequestActionResultData() {
        return receiptRequestActionResultData;
    }

    @Override
    public MutableLiveData<ReceiptedTransferCustomerActionResult> getReceiptedTransferCustomerActiontResultData() {
        return receiptedTransferCustomerActionResultData;
    }

    @Override
    public void setInitApi(Context context, FragmentManager fm) {
        appLoginDetail = ((ADCSaleAgrotechApplication) context.getApplicationContext()).getAppLoginDetail();
        userId = ((ADCSaleAgrotechApplication) context.getApplicationContext()).getUserId();
        reporterId = creatorId = assigneeId = appLoginDetail.getEmployeeId();
        centerCallApi = new CenterCallApi(context);
        this.fm = fm;
    }

    @Override
    public void callApiReceiptedAction(double receiptedAmount) {
        ReceiptedActionPost receiptedActionPost = new ReceiptedActionPost();
        receiptedActionPost.setUserId(userId);
        receiptedActionPost.setReporterId(reporterId);
        receiptedActionPost.setCreatorId(creatorId);
        receiptedActionPost.setAssigneeId(assigneeId);
        receiptedActionPost.setReceivableId(getReceivableDetailData.getValue().getId());
        receiptedActionPost.setReceiptedAmount(receiptedAmount);
        centerCallApi.receiptedAction(this,receiptedActionPost,fm);
    }

    @Override
    public void callApiReceiptRequestAction() {
        ReceiptRequestActionPost receiptRequestActionPost = new ReceiptRequestActionPost();
        receiptRequestActionPost.setUserId(userId);
        receiptRequestActionPost.setReporterId(reporterId);
        receiptRequestActionPost.setCreatorId(creatorId);
        receiptRequestActionPost.setAssigneeId(assigneeId);
        receiptRequestActionPost.setReceivableId(getReceivableDetailData.getValue().getId());
        centerCallApi.receiptRequestAction(this,receiptRequestActionPost,fm);
    }

    @Override
    public void callApiReceiptedTransferCustomerAction(long receiptedAmount) {
        ReceiptedTransferCustomerActionPost receiptedTransferCustomerActionPost = new ReceiptedTransferCustomerActionPost();
        receiptedTransferCustomerActionPost.setUserId(userId);
        receiptedTransferCustomerActionPost.setReporterId(reporterId);
        receiptedTransferCustomerActionPost.setCreatorId(creatorId);
        receiptedTransferCustomerActionPost.setAssigneeId(assigneeId);
        receiptedTransferCustomerActionPost.setReceivableId(getReceivableDetailData.getValue().getId());
        receiptedTransferCustomerActionPost.setReceiptedAmount(receiptedAmount);
        centerCallApi.receiptedTransferCustomerAction(this, receiptedTransferCustomerActionPost,fm);

    }

    @Override
    public void setReceivableDetail(ReceivableDetail receivableDetail) {
        getReceivableDetailData.setValue(receivableDetail);
    }

    @Override
    public void getReceiptRequestAction(ReceiptRequestActionResult receiptRequestActionResult) {
        receiptRequestActionResultData.setValue(receiptRequestActionResult);
    }

    @Override
    public void getReceiptedAction(ReceiptedActionResult receiptedActionResult) {
        receiptedActionResultData.setValue(receiptedActionResult);
    }

    @Override
    public void getReceiptedTransferCustomerAction(ReceiptedTransferCustomerActionResult receiptedTransferCustomerActionResult) {
        receiptedTransferCustomerActionResultData.setValue(receiptedTransferCustomerActionResult);
    }
}
