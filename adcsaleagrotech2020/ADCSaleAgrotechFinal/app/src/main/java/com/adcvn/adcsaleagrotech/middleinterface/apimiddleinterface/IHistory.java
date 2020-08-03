package com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface;

import com.adcvn.adcsaleagrotech.model.history.TransactionHistoryResult;

public interface IHistory {
    void getHistory(TransactionHistoryResult historyResult);
}
