package com.adcvn.adcsaleagrotech.model.createorder;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SalesOrderActionDetail implements Parcelable
{

    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("RefId")
    @Expose
    private String refId;
    @SerializedName("ErrorCode")
    @Expose
    private Integer errorCode;
    public final static Parcelable.Creator<SalesOrderActionDetail> CREATOR = new Creator<SalesOrderActionDetail>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SalesOrderActionDetail createFromParcel(Parcel in) {
            return new SalesOrderActionDetail(in);
        }

        public SalesOrderActionDetail[] newArray(int size) {
            return (new SalesOrderActionDetail[size]);
        }

    }
            ;

    protected SalesOrderActionDetail(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.refId = ((String) in.readValue((String.class.getClassLoader())));
        this.errorCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public SalesOrderActionDetail() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeValue(refId);
        dest.writeValue(errorCode);
    }

    public int describeContents() {
        return 0;
    }

}