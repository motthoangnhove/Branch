package com.adcvn.adcsaleagrotech.model.depositedamountaction;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DepositedAmountActionResult implements Parcelable
{
    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("value")
    @Expose
    private List<DepositedAmountActionDetail> depositedAmountActionDetail = new ArrayList<>();
    public final static Parcelable.Creator<DepositedAmountActionResult> CREATOR = new Creator<DepositedAmountActionResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DepositedAmountActionResult createFromParcel(Parcel in) {
            return new DepositedAmountActionResult(in);
        }

        public DepositedAmountActionResult[] newArray(int size) {
            return (new DepositedAmountActionResult[size]);
        }

    }
            ;

    protected DepositedAmountActionResult(Parcel in) {
        this.odataContext = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.depositedAmountActionDetail, (com.adcvn.adcsaleagrotech.model.depositedamountaction.DepositedAmountActionDetail.class.getClassLoader()));
    }

    public DepositedAmountActionResult() {
    }

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<DepositedAmountActionDetail> getDepositedAmountActionDetail() {
        return depositedAmountActionDetail;
    }

    public void setDepositedAmountActionDetail(List<DepositedAmountActionDetail> depositedAmountActionDetail) {
        this.depositedAmountActionDetail = depositedAmountActionDetail;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataContext);
        dest.writeList(depositedAmountActionDetail);
    }

    public int describeContents() {
        return 0;
    }

}