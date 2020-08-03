package com.adcvn.adcsaleagrotech.model.history;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryResult implements Parcelable
{

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("@odata.count")
    @Expose
    private Integer odataCount;
    @SerializedName("value")
    @Expose
    private List<History> history = new ArrayList<History>();
    public final static Creator<TransactionHistoryResult> CREATOR = new Creator<TransactionHistoryResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TransactionHistoryResult createFromParcel(Parcel in) {
            return new TransactionHistoryResult(in);
        }

        public TransactionHistoryResult[] newArray(int size) {
            return (new TransactionHistoryResult[size]);
        }

    }
            ;

    protected TransactionHistoryResult(Parcel in) {
        this.odataContext = ((String) in.readValue((String.class.getClassLoader())));
        this.odataCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.history, (com.adcvn.adcsaleagrotech.model.history.History.class.getClassLoader()));
    }

    public TransactionHistoryResult() {
    }

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public Integer getOdataCount() {
        return odataCount;
    }

    public void setOdataCount(Integer odataCount) {
        this.odataCount = odataCount;
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataContext);
        dest.writeValue(odataCount);
        dest.writeList(history);
    }

    public int describeContents() {
        return 0;
    }

}