package com.adcvn.adcsaleagrotech.model.omcustomerlist;
import android.os.Parcel;
import android.os.Parcelable;

import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectThreeLabel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OMCustomer implements Parcelable, ISelectThreeLabel
{

    @SerializedName("PaymentTermDefault")
    @Expose
    private String paymentTermDefault;
    @SerializedName("CustomerCode")
    @Expose
    private String customerCode;
    @SerializedName("Avatar")
    @Expose
    private String avatar;
    @SerializedName("AvatarText")
    @Expose
    private String avatarText;
    @SerializedName("SearchString")
    @Expose
    private String searchString;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("CustomerId")
    @Expose
    private String customerId;
    @SerializedName("PaymentTermName")
    @Expose
    private String paymentTermName;
    @SerializedName("GroupId")
    @Expose
    private String groupId;
    @SerializedName("CustomerName")
    @Expose
    private String customerName;
    @SerializedName("SalespersonId")
    @Expose
    private String salespersonId;
    public final static Parcelable.Creator<OMCustomer> CREATOR = new Creator<OMCustomer>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OMCustomer createFromParcel(Parcel in) {
            return new OMCustomer(in);
        }

        public OMCustomer[] newArray(int size) {
            return (new OMCustomer[size]);
        }

    }
            ;

    protected OMCustomer(Parcel in) {
        this.paymentTermDefault = ((String) in.readValue((String.class.getClassLoader())));
        this.customerCode = ((String) in.readValue((String.class.getClassLoader())));
        this.avatar = ((String) in.readValue((String.class.getClassLoader())));
        this.avatarText = ((String) in.readValue((String.class.getClassLoader())));
        this.searchString = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.customerId = ((String) in.readValue((String.class.getClassLoader())));
        this.paymentTermName = ((String) in.readValue((String.class.getClassLoader())));
        this.groupId = ((String) in.readValue((String.class.getClassLoader())));
        this.customerName = ((String) in.readValue((String.class.getClassLoader())));
        this.salespersonId = ((String) in.readValue((String.class.getClassLoader())));
    }

    public OMCustomer() {
    }

    public String getPaymentTermDefault() {
        return paymentTermDefault;
    }

    public void setPaymentTermDefault(String paymentTermDefault) {
        this.paymentTermDefault = paymentTermDefault;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatarText() {
        return avatarText;
    }

    public void setAvatarText(String avatarText) {
        this.avatarText = avatarText;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPaymentTermName() {
        return paymentTermName;
    }

    public void setPaymentTermName(String paymentTermName) {
        this.paymentTermName = paymentTermName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSalespersonId() {
        return salespersonId;
    }

    public void setSalespersonId(String salespersonId) {
        this.salespersonId = salespersonId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(paymentTermDefault);
        dest.writeValue(customerCode);
        dest.writeValue(avatar);
        dest.writeValue(avatarText);
        dest.writeValue(searchString);
        dest.writeValue(address);
        dest.writeValue(customerId);
        dest.writeValue(paymentTermName);
        dest.writeValue(groupId);
        dest.writeValue(customerName);
        dest.writeValue(salespersonId);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String getFirstLabel() {
        return avatarText;
    }

    @Override
    public String getSecondLabel() {
        return customerName;
    }

    @Override
    public String getThirdLabel() {
        return address;
    }
}