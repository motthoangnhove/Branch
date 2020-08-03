package com.adcvn.adcsaleagrotech.model.misacustomerdebtcheck;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MisaCustomerDebtCheck implements Parcelable {

    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Result")
    @Expose
    private String result;
    @SerializedName("ErrorCode")
    @Expose
    private Integer errorCode;
    public final static Parcelable.Creator<MisaCustomerDebtCheck> CREATOR = new Creator<MisaCustomerDebtCheck>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MisaCustomerDebtCheck createFromParcel(Parcel in) {
            return new MisaCustomerDebtCheck(in);
        }

        public MisaCustomerDebtCheck[] newArray(int size) {
            return (new MisaCustomerDebtCheck[size]);
        }

    };

    protected MisaCustomerDebtCheck(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.result = ((String) in.readValue((String.class.getClassLoader())));
        this.errorCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public MisaCustomerDebtCheck() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeValue(result);
        dest.writeValue(errorCode);
    }

    public int describeContents() {
        return 0;
    }
}