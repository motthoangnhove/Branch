package com.adcvn.adcsaleagrotech.model.paymentcreate;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentCreate implements Parcelable
{

    @SerializedName("paymentId")
    @Expose
    private String paymentId;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("errorCode")
    @Expose
    private Integer errorCode;
    public final static Creator<PaymentCreate> CREATOR = new Creator<PaymentCreate>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PaymentCreate createFromParcel(Parcel in) {
            return new PaymentCreate(in);
        }

        public PaymentCreate[] newArray(int size) {
            return (new PaymentCreate[size]);
        }

    }
            ;

    protected PaymentCreate(Parcel in) {
        this.paymentId = ((String) in.readValue((String.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.errorCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public PaymentCreate() {
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(paymentId);
        dest.writeValue(message);
        dest.writeValue(errorCode);
    }

    public int describeContents() {
        return 0;
    }

}