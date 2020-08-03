package com.adcvn.adcsaleagrotech.action.activity.receivableactivity.middleinterface;

import android.content.Context;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;

import com.adcvn.adcsaleagrotech.model.receiptedtransfercustomeraction.ReceiptedTransferCustomerActionResult;
import com.adcvn.adcsaleagrotech.model.receiptrequestaction.ReceiptRequestActionPost;
import com.adcvn.adcsaleagrotech.model.receiptrequestaction.ReceiptRequestActionResult;
import com.adcvn.adcsaleagrotech.model.receivableaction.ReceiptedActionPost;
import com.adcvn.adcsaleagrotech.model.receivableaction.ReceiptedActionResult;
import com.adcvn.adcsaleagrotech.model.receivemoney.receivablelist.ReceivableDetail;

public interface IReceivableActivityViewModel {
    MutableLiveData<ReceivableDetail> getReceivableDetailData();
    MutableLiveData<ReceiptedActionResult> getReceiptedActionResultData();
    MutableLiveData<ReceiptRequestActionResult> getReceiptRequestActionResultData();
    MutableLiveData<ReceiptedTransferCustomerActionResult> getReceiptedTransferCustomerActiontResultData();
    void setInitApi(Context context, FragmentManager fm);
    void callApiReceiptedAction(double receiptedAmount);
    void callApiReceiptRequestAction();
    void callApiReceiptedTransferCustomerAction(long receiptedAmount);
    void setReceivableDetail(ReceivableDetail receivableDetail);
}
