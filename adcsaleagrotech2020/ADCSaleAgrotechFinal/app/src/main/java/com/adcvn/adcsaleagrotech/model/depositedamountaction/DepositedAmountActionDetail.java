package com.adcvn.adcsaleagrotech.model.depositedamountaction;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DepositedAmountActionDetail implements Parcelable
{
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("DepositedId")
    @Expose
    private String depositedId;
    @SerializedName("ErrorCode")
    @Expose
    private Integer errorCode;
    public final static Parcelable.Creator<DepositedAmountActionDetail> CREATOR = new Creator<DepositedAmountActionDetail>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DepositedAmountActionDetail createFromParcel(Parcel in) {
            return new DepositedAmountActionDetail(in);
        }

        public DepositedAmountActionDetail[] newArray(int size) {
            return (new DepositedAmountActionDetail[size]);
        }

    }
            ;

    protected DepositedAmountActionDetail(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.depositedId = ((String) in.readValue((String.class.getClassLoader())));
        this.errorCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public DepositedAmountActionDetail() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDepositedId() {
        return depositedId;
    }

    public void setDepositedId(String depositedId) {
        this.depositedId = depositedId;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeValue(depositedId);
        dest.writeValue(errorCode);
    }

    public int describeContents() {
        return 0;
    }

}