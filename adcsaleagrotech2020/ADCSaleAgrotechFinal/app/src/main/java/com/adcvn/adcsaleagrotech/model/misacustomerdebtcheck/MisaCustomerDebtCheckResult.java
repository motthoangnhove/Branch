package com.adcvn.adcsaleagrotech.model.misacustomerdebtcheck;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MisaCustomerDebtCheckResult implements Parcelable
{

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("value")
    @Expose
    private List<MisaCustomerDebtCheck> misaCustomerDebtCheckDetail = new ArrayList<>();
    public final static Parcelable.Creator<MisaCustomerDebtCheckResult> CREATOR = new Creator<MisaCustomerDebtCheckResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MisaCustomerDebtCheckResult createFromParcel(Parcel in) {
            return new MisaCustomerDebtCheckResult(in);
        }

        public MisaCustomerDebtCheckResult[] newArray(int size) {
            return (new MisaCustomerDebtCheckResult[size]);
        }

    }
            ;

    protected MisaCustomerDebtCheckResult(Parcel in) {
        this.odataContext = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.misaCustomerDebtCheckDetail, (MisaCustomerDebtCheck.class.getClassLoader()));
    }

    public MisaCustomerDebtCheckResult() {
    }

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<MisaCustomerDebtCheck> getMisaCustomerDebtCheckDetail() {
        return misaCustomerDebtCheckDetail;
    }

    public void setMisaCustomerDebtCheckDetail(List<MisaCustomerDebtCheck> misaCustomerDebtCheckDetail) {
        this.misaCustomerDebtCheckDetail = misaCustomerDebtCheckDetail;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataContext);
        dest.writeList(misaCustomerDebtCheckDetail);
    }

    public int describeContents() {
        return 0;
    }

}