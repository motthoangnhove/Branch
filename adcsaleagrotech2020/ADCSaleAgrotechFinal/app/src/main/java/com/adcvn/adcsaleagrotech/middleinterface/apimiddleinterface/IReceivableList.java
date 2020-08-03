package com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface;

import com.adcvn.adcsaleagrotech.model.receivemoney.receivablelist.ReceivableListResult;

public interface IReceivableList {
    void getReceivableList(ReceivableListResult receivableListResult, int isCurrent);
}
