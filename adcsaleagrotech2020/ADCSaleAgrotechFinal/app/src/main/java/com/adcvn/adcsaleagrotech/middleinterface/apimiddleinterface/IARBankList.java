package com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface;

import com.adcvn.adcsaleagrotech.model.arbanklist.ARBankListResult;

public interface IARBankList {
    void getARBankList(int type, ARBankListResult arBankListResult);
}
