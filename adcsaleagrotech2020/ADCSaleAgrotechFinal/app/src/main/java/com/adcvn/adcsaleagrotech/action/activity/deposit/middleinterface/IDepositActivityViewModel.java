package com.adcvn.adcsaleagrotech.action.activity.deposit.middleinterface;

import android.content.Context;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import com.adcvn.adcsaleagrotech.model.arbanklist.ARBank;
import com.adcvn.adcsaleagrotech.model.depositedamountaction.DepositedAmountActionPost;
import com.adcvn.adcsaleagrotech.model.depositedamountaction.DepositedAmountActionResult;
import java.util.List;

public interface IDepositActivityViewModel {
    MutableLiveData<List<ARBank>> getInitBankData();
    MutableLiveData<List<ARBank>> getBankDataChoose();
    MutableLiveData<ARBank> getBankChooseData();
    MutableLiveData<DepositedAmountActionPost> getDepositCreatePostData();
    MutableLiveData<DepositedAmountActionResult> getDepositCreateResultData();
    void setInitApi(Context context, FragmentManager fm);
    void setBank(ARBank bank);
    void setDepositedAmount(long amountMoneyPayment);
    void setPhotoLink(String photoLink);
    void callApiBank(int type, int skip, String search);
    void callApiDepositedAmountAction();
}
