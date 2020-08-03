package com.adcvn.adcsaleagrotech.model.receivableaction;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReceiptedActionResult implements Parcelable
{

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("value")
    @Expose
    private List<ReceiptedActionDetail> receiptedActionDetail = new ArrayList<ReceiptedActionDetail>();
    public final static Parcelable.Creator<ReceiptedActionResult> CREATOR = new Creator<ReceiptedActionResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ReceiptedActionResult createFromParcel(Parcel in) {
            return new ReceiptedActionResult(in);
        }

        public ReceiptedActionResult[] newArray(int size) {
            return (new ReceiptedActionResult[size]);
        }

    }
            ;

    protected ReceiptedActionResult(Parcel in) {
        this.odataContext = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.receiptedActionDetail, (com.adcvn.adcsaleagrotech.model.receivableaction.ReceiptedActionDetail.class.getClassLoader()));
    }

    public ReceiptedActionResult() {
    }

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<ReceiptedActionDetail> getReceiptedActionDetail() {
        return receiptedActionDetail;
    }

    public void setReceiptedActionDetail(List<ReceiptedActionDetail> receiptedActionDetail) {
        this.receiptedActionDetail = receiptedActionDetail;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataContext);
        dest.writeList(receiptedActionDetail);
    }

    public int describeContents() {
        return 0;
    }

}