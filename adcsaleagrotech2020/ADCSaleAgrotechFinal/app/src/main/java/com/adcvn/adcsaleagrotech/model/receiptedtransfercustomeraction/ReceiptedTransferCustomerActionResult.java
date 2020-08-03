package com.adcvn.adcsaleagrotech.model.receiptedtransfercustomeraction;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReceiptedTransferCustomerActionResult implements Parcelable
{

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("value")
    @Expose
    private List<ReceiptedTransferCustomerActionDetail> receiptedTransferCustomerActionDetail = new ArrayList<>();
    public final static Parcelable.Creator<ReceiptedTransferCustomerActionResult> CREATOR = new Creator<ReceiptedTransferCustomerActionResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ReceiptedTransferCustomerActionResult createFromParcel(Parcel in) {
            return new ReceiptedTransferCustomerActionResult(in);
        }

        public ReceiptedTransferCustomerActionResult[] newArray(int size) {
            return (new ReceiptedTransferCustomerActionResult[size]);
        }

    }
            ;

    protected ReceiptedTransferCustomerActionResult(Parcel in) {
        this.odataContext = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.receiptedTransferCustomerActionDetail, (com.adcvn.adcsaleagrotech.model.receiptedtransfercustomeraction.ReceiptedTransferCustomerActionDetail.class.getClassLoader()));
    }

    public ReceiptedTransferCustomerActionResult() {
    }

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<ReceiptedTransferCustomerActionDetail> getReceiptedTransferCustomerActionDetail() {
        return receiptedTransferCustomerActionDetail;
    }

    public void setReceiptedTransferCustomerActionDetail(List<ReceiptedTransferCustomerActionDetail> receiptedTransferCustomerActionDetail) {
        this.receiptedTransferCustomerActionDetail = receiptedTransferCustomerActionDetail;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataContext);
        dest.writeList(receiptedTransferCustomerActionDetail);
    }

    public int describeContents() {
        return 0;
    }

}