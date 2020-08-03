package com.adcvn.adcsaleagrotech.action.activity.debtcustomerdetail.viewmodel;

import android.content.Context;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import com.adcvn.adcsaleagrotech.action.activity.debtcustomerdetail.middleinterface.IDebtCustomerDetail;
import com.adcvn.adcsaleagrotech.server.CenterCallApi;

public class DebtCustomerDetail extends ViewModel implements IDebtCustomerDetail {
    private CenterCallApi centerCallApi;
    private FragmentManager fm;

    public DebtCustomerDetail() {

    }

    @Override
    public void setInitApi(Context context, FragmentManager fm) {
        centerCallApi = new CenterCallApi(context);
        this.fm = fm;
    }
}