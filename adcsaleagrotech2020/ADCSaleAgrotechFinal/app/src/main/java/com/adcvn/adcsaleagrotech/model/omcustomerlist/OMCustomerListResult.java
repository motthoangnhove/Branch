package com.adcvn.adcsaleagrotech.model.omcustomerlist;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OMCustomerListResult implements Parcelable
{

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("@odata.count")
    @Expose
    private Integer odataCount;
    @SerializedName("value")
    @Expose
    private List<OMCustomer> oMCustomer = new ArrayList<OMCustomer>();
    public final static Parcelable.Creator<OMCustomerListResult> CREATOR = new Creator<OMCustomerListResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OMCustomerListResult createFromParcel(Parcel in) {
            return new OMCustomerListResult(in);
        }

        public OMCustomerListResult[] newArray(int size) {
            return (new OMCustomerListResult[size]);
        }

    }
            ;

    protected OMCustomerListResult(Parcel in) {
        this.odataContext = ((String) in.readValue((String.class.getClassLoader())));
        this.odataCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.oMCustomer, (com.adcvn.adcsaleagrotech.model.omcustomerlist.OMCustomer.class.getClassLoader()));
    }

    public OMCustomerListResult() {
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

    public List<OMCustomer> getOMCustomer() {
        return oMCustomer;
    }

    public void setOMCustomer(List<OMCustomer> oMCustomer) {
        this.oMCustomer = oMCustomer;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataContext);
        dest.writeValue(odataCount);
        dest.writeList(oMCustomer);
    }

    public int describeContents() {
        return 0;
    }

}