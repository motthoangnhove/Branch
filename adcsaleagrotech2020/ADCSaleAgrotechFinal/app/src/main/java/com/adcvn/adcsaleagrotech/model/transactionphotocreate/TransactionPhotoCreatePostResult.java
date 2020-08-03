package com.adcvn.adcsaleagrotech.model.transactionphotocreate;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransactionPhotoCreatePostResult implements Parcelable
{

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("value")
    @Expose
    private List<TransactionPhotoCreate> transactionPhotoCreate = null;
    public final static Creator<TransactionPhotoCreatePostResult> CREATOR = new Creator<TransactionPhotoCreatePostResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TransactionPhotoCreatePostResult createFromParcel(Parcel in) {
            return new TransactionPhotoCreatePostResult(in);
        }

        public TransactionPhotoCreatePostResult[] newArray(int size) {
            return (new TransactionPhotoCreatePostResult[size]);
        }

    }
            ;

    protected TransactionPhotoCreatePostResult(Parcel in) {
        this.odataContext = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.transactionPhotoCreate, (com.adcvn.adcsaleagrotech.model.transactionphotocreate.TransactionPhotoCreate.class.getClassLoader()));
    }

    public TransactionPhotoCreatePostResult() {
    }

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<TransactionPhotoCreate> getTransactionPhotoCreate() {
        return transactionPhotoCreate;
    }

    public void setTransactionPhotoCreate(List<TransactionPhotoCreate> transactionPhotoCreate) {
        this.transactionPhotoCreate = transactionPhotoCreate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataContext);
        dest.writeList(transactionPhotoCreate);
    }

    public int describeContents() {
        return 0;
    }

}