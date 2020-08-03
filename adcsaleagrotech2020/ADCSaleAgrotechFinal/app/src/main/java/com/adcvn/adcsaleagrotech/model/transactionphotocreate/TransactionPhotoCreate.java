package com.adcvn.adcsaleagrotech.model.transactionphotocreate;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionPhotoCreate implements Parcelable
{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("errorCode")
    @Expose
    private Integer errorCode;
    public final static Creator<TransactionPhotoCreate> CREATOR = new Creator<TransactionPhotoCreate>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TransactionPhotoCreate createFromParcel(Parcel in) {
            return new TransactionPhotoCreate(in);
        }

        public TransactionPhotoCreate[] newArray(int size) {
            return (new TransactionPhotoCreate[size]);
        }

    }
            ;

    protected TransactionPhotoCreate(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.errorCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public TransactionPhotoCreate() {
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
        dest.writeValue(message);
        dest.writeValue(errorCode);
    }

    public int describeContents() {
        return 0;
    }

}