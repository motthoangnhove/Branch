package com.adcvn.adcsaleagrotech.model.receivableaction;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReceiptedActionDetail implements Parcelable
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
    public final static Parcelable.Creator<ReceiptedActionDetail> CREATOR = new Creator<ReceiptedActionDetail>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ReceiptedActionDetail createFromParcel(Parcel in) {
            return new ReceiptedActionDetail(in);
        }

        public ReceiptedActionDetail[] newArray(int size) {
            return (new ReceiptedActionDetail[size]);
        }

    }
            ;

    protected ReceiptedActionDetail(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.receiptId = ((String) in.readValue((String.class.getClassLoader())));
        this.errorCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public ReceiptedActionDetail() {
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