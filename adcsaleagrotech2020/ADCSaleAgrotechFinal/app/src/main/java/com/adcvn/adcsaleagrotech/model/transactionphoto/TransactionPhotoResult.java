package com.adcvn.adcsaleagrotech.model.transactionphoto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransactionPhotoResult implements Parcelable
{

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("@odata.count")
    @Expose
    private Integer odataCount;
    @SerializedName("value")
    @Expose
    private List<TransactionPhoto> transactionPhoto = null;
    public final static Creator<TransactionPhotoResult> CREATOR = new Creator<TransactionPhotoResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TransactionPhotoResult createFromParcel(Parcel in) {
            return new TransactionPhotoResult(in);
        }

        public TransactionPhotoResult[] newArray(int size) {
            return (new TransactionPhotoResult[size]);
        }

    }
            ;

    protected TransactionPhotoResult(Parcel in) {
        this.odataContext = ((String) in.readValue((String.class.getClassLoader())));
        this.odataCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.transactionPhoto, (com.adcvn.adcsaleagrotech.model.transactionphoto.TransactionPhoto.class.getClassLoader()));
    }

    public TransactionPhotoResult() {
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

    public List<TransactionPhoto> getTransactionPhoto() {
        return transactionPhoto;
    }

    public void setTransactionPhoto(List<TransactionPhoto> transactionPhoto) {
        this.transactionPhoto = transactionPhoto;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataContext);
        dest.writeValue(odataCount);
        dest.writeList(transactionPhoto);
    }

    public int describeContents() {
        return 0;
    }

}