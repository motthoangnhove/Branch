package com.adcvn.adcsaleagrotech.model.omsalesorderlist;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OMSalesOrderListResult implements Parcelable
{

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("@odata.count")
    @Expose
    private Integer odataCount;
    @SerializedName("value")
    @Expose
    private List<OMSalesOrder> oMSalesOrder = new ArrayList<>();
    public final static Parcelable.Creator<OMSalesOrderListResult> CREATOR = new Creator<OMSalesOrderListResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OMSalesOrderListResult createFromParcel(Parcel in) {
            return new OMSalesOrderListResult(in);
        }

        public OMSalesOrderListResult[] newArray(int size) {
            return (new OMSalesOrderListResult[size]);
        }

    }
            ;

    protected OMSalesOrderListResult(Parcel in) {
        this.odataContext = ((String) in.readValue((String.class.getClassLoader())));
        this.odataCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.oMSalesOrder, (com.adcvn.adcsaleagrotech.model.omsalesorderlist.OMSalesOrder.class.getClassLoader()));
    }

    public OMSalesOrderListResult() {
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

    public List<OMSalesOrder> getOMSalesOrder() {
        return oMSalesOrder;
    }

    public void setOMSalesOrder(List<OMSalesOrder> oMSalesOrder) {
        this.oMSalesOrder = oMSalesOrder;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataContext);
        dest.writeValue(odataCount);
        dest.writeList(oMSalesOrder);
    }

    public int describeContents() {
        return 0;
    }

}