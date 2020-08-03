package com.adcvn.adcsaleagrotech.model.paymentcreate;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentCreatePost implements Parcelable
{

    @SerializedName("organizationId")
    @Expose
    private String organizationId;
    @SerializedName("assigneeId")
    @Expose
    private String assigneeId;
    @SerializedName("bankId")
    @Expose
    private String bankId;
    @SerializedName("photoLink")
    @Expose
    private String photoLink;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("userId")
    @Expose
    private String userId;
    public final static Creator<PaymentCreatePost> CREATOR = new Creator<PaymentCreatePost>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PaymentCreatePost createFromParcel(Parcel in) {
            return new PaymentCreatePost(in);
        }

        public PaymentCreatePost[] newArray(int size) {
            return (new PaymentCreatePost[size]);
        }

    }
            ;

    protected PaymentCreatePost(Parcel in) {
        this.organizationId = ((String) in.readValue((String.class.getClassLoader())));
        this.assigneeId = ((String) in.readValue((String.class.getClassLoader())));
        this.bankId = ((String) in.readValue((String.class.getClassLoader())));
        this.photoLink = ((String) in.readValue((String.class.getClassLoader())));
        this.amount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.userId = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PaymentCreatePost() {
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(organizationId);
        dest.writeValue(assigneeId);
        dest.writeValue(bankId);
        dest.writeValue(photoLink);
        dest.writeValue(amount);
        dest.writeValue(userId);
    }

    public int describeContents() {
        return 0;
    }

}