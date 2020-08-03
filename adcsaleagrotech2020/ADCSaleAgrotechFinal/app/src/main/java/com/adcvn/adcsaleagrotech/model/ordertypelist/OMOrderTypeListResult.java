package com.adcvn.adcsaleagrotech.model.ordertypelist;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OMOrderTypeListResult implements Parcelable
{
    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("value")
    @Expose
    private List<OrderTypeDetail> orderTypeDetail = new ArrayList<>();
    public final static Parcelable.Creator<OMOrderTypeListResult> CREATOR = new Creator<OMOrderTypeListResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OMOrderTypeListResult createFromParcel(Parcel in) {
            return new OMOrderTypeListResult(in);
        }

        public OMOrderTypeListResult[] newArray(int size) {
            return (new OMOrderTypeListResult[size]);
        }

    }
            ;

    protected OMOrderTypeListResult(Parcel in) {
        this.odataContext = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.orderTypeDetail, (com.adcvn.adcsaleagrotech.model.ordertypelist.OrderTypeDetail.class.getClassLoader()));
    }

    public OMOrderTypeListResult() {
    }

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<OrderTypeDetail> getOrderTypeDetail() {
        return orderTypeDetail;
    }

    public void setOrderTypeDetail(List<OrderTypeDetail> orderTypeDetail) {
        this.orderTypeDetail = orderTypeDetail;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataContext);
        dest.writeList(orderTypeDetail);
    }

    public int describeContents() {
        return 0;
    }

}