package com.adcvn.adcsaleagrotech.model.receivemoney.deposit;

import android.os.Parcel;
import android.os.Parcelable;

import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectThreeLabel;

public class BankAccountModel implements Parcelable, ISelectThreeLabel {
    private String bankName;
    private String accountName;
    private String accountNumber;

    protected BankAccountModel(Parcel in) {
        bankName = in.readString();
        accountName = in.readString();
        accountNumber = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bankName);
        dest.writeString(accountName);
        dest.writeString(accountNumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BankAccountModel> CREATOR = new Creator<BankAccountModel>() {
        @Override
        public BankAccountModel createFromParcel(Parcel in) {
            return new BankAccountModel(in);
        }

        @Override
        public BankAccountModel[] newArray(int size) {
            return new BankAccountModel[size];
        }
    };

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BankAccountModel() {
    }

    public BankAccountModel(String bankName, String accountName, String accountNumber) {
        this.bankName = bankName;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
    }

    @Override
    public String getFirstLabel() {
        return bankName;
    }

    @Override
    public String getSecondLabel() {
        return accountName;
    }

    @Override
    public String getThirdLabel() {
        return accountNumber;
    }
}
