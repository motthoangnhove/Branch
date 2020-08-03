package com.adcvn.adcsaleagrotech.model.createorder;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SalesOrderActionResult implements Parcelable
{

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("value")
    @Expose
    private List<SalesOrderActionDetail> salesOrderActionDetail = new ArrayList<SalesOrderActionDetail>();
    public final static Parcelable.Creator<SalesOrderActionResult> CREATOR = new Creator<SalesOrderActionResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SalesOrderActionResult createFromParcel(Parcel in) {
            return new SalesOrderActionResult(in);
        }

        public SalesOrderActionResult[] newArray(int size) {
            return (new SalesOrderActionResult[size]);
        }

    }
            ;

    protected SalesOrderActionResult(Parcel in) {
        this.odataContext = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.salesOrderActionDetail, (com.adcvn.adcsaleagrotech.model.createorder.SalesOrderActionDetail.class.getClassLoader()));
    }

    public SalesOrderActionResult() {
    }

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<SalesOrderActionDetail> getSalesOrderActionDetail() {
        return salesOrderActionDetail;
    }

    public void setSalesOrderActionDetail(List<SalesOrderActionDetail> salesOrderActionDetail) {
        this.salesOrderActionDetail = salesOrderActionDetail;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataContext);
        dest.writeList(salesOrderActionDetail);
    }

    public int describeContents() {
        return 0;
    }

}