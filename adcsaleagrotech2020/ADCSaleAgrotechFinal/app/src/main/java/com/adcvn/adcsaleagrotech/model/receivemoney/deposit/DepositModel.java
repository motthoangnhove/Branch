package com.adcvn.adcsaleagrotech.model.receivemoney.deposit;

import android.os.Parcel;
import android.os.Parcelable;

public class DepositModel implements Parcelable {
    private String bankName;
    private String depositNumber;
    private String date;

    protected DepositModel(Parcel in) {
        bankName = in.readString();
        depositNumber = in.readString();
        date = in.readString();
        depositAmount = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bankName);
        dest.writeString(depositNumber);
        dest.writeString(date);
        dest.writeInt(depositAmount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DepositModel> CREATOR = new Creator<DepositModel>() {
        @Override
        public DepositModel createFromParcel(Parcel in) {
            return new DepositModel(in);
        }

        @Override
        public DepositModel[] newArray(int size) {
            return new DepositModel[size];
        }
    };

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getDepositNumber() {
        return depositNumber;
    }

    public void setDepositNumber(String depositNumber) {
        this.depositNumber = depositNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(int depositAmount) {
        this.depositAmount = depositAmount;
    }

    public DepositModel() {
    }

    private int depositAmount;

    public DepositModel(String bankName, String depositNumber, String date, int depositAmount) {
        this.bankName = bankName;
        this.depositNumber = depositNumber;
        this.date = date;
        this.depositAmount = depositAmount;
    }
}
