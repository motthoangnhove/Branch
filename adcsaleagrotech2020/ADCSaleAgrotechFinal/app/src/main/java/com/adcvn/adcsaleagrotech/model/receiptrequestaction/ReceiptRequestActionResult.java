package com.adcvn.adcsaleagrotech.model.receiptrequestaction;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReceiptRequestActionResult implements Parcelable
{

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("value")
    @Expose
    private List<ReceiptRequestActionDetail> receiptRequestActionDetail = new ArrayList<ReceiptRequestActionDetail>();
    public final static Parcelable.Creator<ReceiptRequestActionResult> CREATOR = new Creator<ReceiptRequestActionResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ReceiptRequestActionResult createFromParcel(Parcel in) {
            return new ReceiptRequestActionResult(in);
        }

        public ReceiptRequestActionResult[] newArray(int size) {
            return (new ReceiptRequestActionResult[size]);
        }

    }
            ;

    protected ReceiptRequestActionResult(Parcel in) {
        this.odataContext = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.receiptRequestActionDetail, (com.adcvn.adcsaleagrotech.model.receiptrequestaction.ReceiptRequestActionDetail.class.getClassLoader()));
    }

    public ReceiptRequestActionResult() {
    }

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<ReceiptRequestActionDetail> getReceiptRequestActionDetail() {
        return receiptRequestActionDetail;
    }

    public void setReceiptRequestActionDetail(List<ReceiptRequestActionDetail> receiptRequestActionDetail) {
        this.receiptRequestActionDetail = receiptRequestActionDetail;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataContext);
        dest.writeList(receiptRequestActionDetail);
    }

    public int describeContents() {
        return 0;
    }

}