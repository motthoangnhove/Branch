package com.adcvn.adcsaleagrotech.model.verifyopt;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class VerifyOtpResult implements Parcelable {

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;

    @SerializedName("value")
    @Expose
    private List<VerifyOtpResultDetail> value = new ArrayList<>();

    public VerifyOtpResult() {
    }

    public VerifyOtpResult(String odataContext, List<VerifyOtpResultDetail> value) {
        super();
        this.odataContext = odataContext;
        this.value = value;
    }

    private VerifyOtpResult(Parcel in) {
        odataContext = in.readString();
        value = in.createTypedArrayList(VerifyOtpResultDetail.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(odataContext);
        dest.writeTypedList(value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VerifyOtpResult> CREATOR = new Creator<VerifyOtpResult>() {
        @Override
        public VerifyOtpResult createFromParcel(Parcel in) {
            return new VerifyOtpResult(in);
        }

        @Override
        public VerifyOtpResult[] newArray(int size) {
            return new VerifyOtpResult[size];
        }
    };

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<VerifyOtpResultDetail> getValue() {
        return value;
    }

    public void setValue(List<VerifyOtpResultDetail> value) {
        this.value = value;
    }
}
