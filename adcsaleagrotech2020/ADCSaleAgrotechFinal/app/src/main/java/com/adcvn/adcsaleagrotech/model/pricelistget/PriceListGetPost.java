package com.adcvn.adcsaleagrotech.model.pricelistget;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PriceListGetPost implements Parcelable
{

    @SerializedName("customerGroupId")
    @Expose
    private String customerGroupId;
    @SerializedName("orderTypeId")
    @Expose
    private String orderTypeId;
    @SerializedName("organizationId")
    @Expose
    private String organizationId;
    @SerializedName("paymentTermId")
    @Expose
    private String paymentTermId;
    public final static Parcelable.Creator<PriceListGetPost> CREATOR = new Creator<PriceListGetPost>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PriceListGetPost createFromParcel(Parcel in) {
            return new PriceListGetPost(in);
        }

        public PriceListGetPost[] newArray(int size) {
            return (new PriceListGetPost[size]);
        }

    }
            ;

    protected PriceListGetPost(Parcel in) {
        this.customerGroupId = ((String) in.readValue((String.class.getClassLoader())));
        this.orderTypeId = ((String) in.readValue((String.class.getClassLoader())));
        this.organizationId = ((String) in.readValue((String.class.getClassLoader())));
        this.paymentTermId = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PriceListGetPost() {
    }
    public PriceListGetPost(String organizationId, String orderTypeId, String customerGroupId, String paymentTermId) {
        this.organizationId = organizationId;
        this.orderTypeId = orderTypeId;
        this.customerGroupId = customerGroupId;
        this.paymentTermId = paymentTermId;
    }

    public String getCustomerGroupId() {
        return customerGroupId;
    }

    public void setCustomerGroupId(String customerGroupId) {
        this.customerGroupId = customerGroupId;
    }

    public String getOrderTypeId() {
        return orderTypeId;
    }

    public void setOrderTypeId(String orderTypeId) {
        this.orderTypeId = orderTypeId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getPaymentTermId() {
        return paymentTermId;
    }

    public void setPaymentTermId(String paymentTermId) {
        this.paymentTermId = paymentTermId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(customerGroupId);
        dest.writeValue(orderTypeId);
        dest.writeValue(organizationId);
        dest.writeValue(paymentTermId);
    }

    public int describeContents() {
        return 0;
    }

}