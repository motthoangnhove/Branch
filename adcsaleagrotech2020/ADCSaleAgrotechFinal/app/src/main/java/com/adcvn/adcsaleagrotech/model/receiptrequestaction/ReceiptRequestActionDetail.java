package com.adcvn.adcsaleagrotech.model.receiptrequestaction;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReceiptRequestActionDetail implements Parcelable
{

    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("ReceiptId")
    @Expose
    private String receiptId;
    @SerializedName("ErrorCode")
    @Expose
    private Integer errorCode;
    public final static Parcelable.Creator<ReceiptRequestActionDetail> CREATOR = new Creator<ReceiptRequestActionDetail>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ReceiptRequestActionDetail createFromParcel(Parcel in) {
            return new ReceiptRequestActionDetail(in);
        }

        public ReceiptRequestActionDetail[] newArray(int size) {
            return (new ReceiptRequestActionDetail[size]);
        }

    }
            ;

    protected ReceiptRequestActionDetail(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.receiptId = ((String) in.readValue((String.class.getClassLoader())));
        this.errorCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public ReceiptRequestActionDetail() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeValue(receiptId);
        dest.writeValue(errorCode);
    }

    public int describeContents() {
        return 0;
    }

}