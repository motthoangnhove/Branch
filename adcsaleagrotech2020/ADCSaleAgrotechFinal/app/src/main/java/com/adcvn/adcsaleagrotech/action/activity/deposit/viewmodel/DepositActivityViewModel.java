package com.adcvn.adcsaleagrotech.action.activity.deposit.viewmodel;

import android.content.Context;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.adcvn.adcsaleagrotech.action.activity.deposit.middleinterface.IDepositActivityViewModel;
import com.adcvn.adcsaleagrotech.application.ADCSaleAgrotechApplication;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IARBankList;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IDepositedAmountAction;
import com.adcvn.adcsaleagrotech.model.applogin.AppLoginDetail;
import com.adcvn.adcsaleagrotech.model.arbanklist.ARBank;
import com.adcvn.adcsaleagrotech.model.arbanklist.ARBankListResult;
import com.adcvn.adcsaleagrotech.model.depositedamountaction.DepositedAmountActionPost;
import com.adcvn.adcsaleagrotech.model.depositedamountaction.DepositedAmountActionResult;
import com.adcvn.adcsaleagrotech.server.CenterCallApi;

import java.util.ArrayList;
import java.util.List;

public class DepositActivityViewModel extends ViewModel implements IDepositActivityViewModel, IARBankList, IDepositedAmountAction {
    private MutableLiveData<List<ARBank>> initBankData = new MutableLiveData<>();
    private MutableLiveData<List<ARBank>> chooseBankData = new MutableLiveData<>();
    private MutableLiveData<ARBank> bankChooseData = new MutableLiveData<>();
    private MutableLiveData<DepositedAmountActionPost> depositedAmountActionPostData = new MutableLiveData<>();
    private MutableLiveData<DepositedAmountActionResult> depositedAmountActionResultData = new MutableLiveData<>();
    private FragmentManager fm;
    private Context context;
    private CenterCallApi centerCallApi;

    public DepositActivityViewModel() {
        List<ARBank> listInitBank = new ArrayList<ARBank>();
        initBankData.setValue(listInitBank);
        ArrayList<ARBank> listBankChoose = new ArrayList<>();
        chooseBankData.setValue(listBankChoose);
        ARBank bank = new ARBank();
        bankChooseData.setValue(bank);
        DepositedAmountActionPost depositedAmountActionPost = new DepositedAmountActionPost();
        depositedAmountActionPostData.setValue(depositedAmountActionPost);
        DepositedAmountActionResult depositedAmountActionResult = new DepositedAmountActionResult();
        depositedAmountActionResultData.setValue(depositedAmountActionResult);
    }


    @Override
    public MutableLiveData<List<ARBank>> getInitBankData() {
        return initBankData;
    }

    @Override
    public MutableLiveData<List<ARBank>> getBankDataChoose() {
        return chooseBankData;
    }

    @Override
    public MutableLiveData<ARBank> getBankChooseData() {
        return bankChooseData;
    }

    @Override
    public MutableLiveData<DepositedAmountActionPost> getDepositCreatePostData() {
        return depositedAmountActionPostData;
    }

    @Override
    public MutableLiveData<DepositedAmountActionResult> getDepositCreateResultData() {
        return depositedAmountActionResultData;
    }

    @Override
    public void setInitApi(Context context, FragmentManager fm) {
        centerCallApi = new CenterCallApi(context);
        this.context = context;
        this.fm = fm;
    }

    @Override
    public void setBank(ARBank bank) {
        bankChooseData.setValue(bank);
        DepositedAmountActionPost depositedAmountActionPost = depositedAmountActionPostData.getValue();
        depositedAmountActionPost.setBankId(bank.getId());
        depositedAmountActionPostData.setValue(depositedAmountActionPost);
    }

    @Override
    public void setDepositedAmount(long amountMoneyPayment) {
        DepositedAmountActionPost depositedAmountActionPost = depositedAmountActionPostData.getValue();
        depositedAmountActionPost.setDepositedAmount(amountMoneyPayment);
        depositedAmountActionPostData.setValue(depositedAmountActionPost);
    }

    @Override
    public void setPhotoLink(String photoLink) {
        DepositedAmountActionPost depositedAmountActionPost = depositedAmountActionPostData.getValue();
        depositedAmountActionPost.setPhotoLink(photoLink);
        depositedAmountActionPostData.setValue(depositedAmountActionPost);
    }

    @Override
    public void callApiBank(int type, int skip, String search) {
        centerCallApi.arBankList(this,type, skip, search, fm);
    }

    @Override
    public void callApiDepositedAmountAction() {
        ADCSaleAgrotechApplication adcSaleAgrotechApplication = (ADCSaleAgrotechApplication) context.getApplicationContext();
        AppLoginDetail appLoginDetail = adcSaleAgrotechApplication.getAppLoginDetail();
        DepositedAmountActionPost depositedAmountActionPost = depositedAmountActionPostData.getValue();
        depositedAmountActionPost.setAssigneeId(appLoginDetail.getEmployeeId());
        depositedAmountActionPost.setCreatorId(appLoginDetail.getEmployeeId());
        depositedAmountActionPost.setOrganizitionId(appLoginDetail.getOrganizationId());
        depositedAmountActionPost.setReporterId(appLoginDetail.getEmployeeId());
        depositedAmountActionPost.setUserId(adcSaleAgrotechApplication.getUserId());
        centerCallApi.depositedAmountAction(this,depositedAmountActionPost, fm);
    }

    @Override
    public void getARBankList(int type, ARBankListResult arBankListResult) {
        if(type == 0) {
            initBankData.setValue(arBankListResult.getARBank());
        }else if(type == 1){
            chooseBankData.setValue(arBankListResult.getARBank());
        }
    }

    @Override
    public void getDepositedAmountAction(DepositedAmountActionResult depositedAmountActionResult) {
        depositedAmountActionResultData.setValue(depositedAmountActionResult);
    }
}
