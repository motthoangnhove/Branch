package com.adcvn.adcsaleagrotech.model.ordertypelist;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderTypeDetail implements Parcelable
{

    @SerializedName("OrderTypeName")
    @Expose
    private String orderTypeName;
    @SerializedName("OrderTypeId")
    @Expose
    private String orderTypeId;
    public final static Parcelable.Creator<OrderTypeDetail> CREATOR = new Creator<OrderTypeDetail>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OrderTypeDetail createFromParcel(Parcel in) {
            return new OrderTypeDetail(in);
        }

        public OrderTypeDetail[] newArray(int size) {
            return (new OrderTypeDetail[size]);
        }

    }
            ;

    protected OrderTypeDetail(Parcel in) {
        this.orderTypeName = ((String) in.readValue((String.class.getClassLoader())));
        this.orderTypeId = ((String) in.readValue((String.class.getClassLoader())));
    }

    public OrderTypeDetail() {
    }
    public OrderTypeDetail(String orderTypeName, String orderTypeId){
        this.orderTypeName = orderTypeName;
        this.orderTypeId = orderTypeId;
    }

    public String getOrderTypeName() {
        return orderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }

    public String getOrderTypeId() {
        return orderTypeId;
    }

    public void setOrderTypeId(String orderTypeId) {
        this.orderTypeId = orderTypeId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(orderTypeName);
        dest.writeValue(orderTypeId);
    }

    public int describeContents() {
        return 0;
    }

}