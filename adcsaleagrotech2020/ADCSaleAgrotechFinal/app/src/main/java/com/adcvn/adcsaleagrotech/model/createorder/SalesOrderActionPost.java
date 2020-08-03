package com.adcvn.adcsaleagrotech.model.createorder;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SalesOrderActionPost implements Parcelable
{

    @SerializedName("RefId")
    @Expose
    private String refId;
    @SerializedName("Amount")
    @Expose
    private double amount;
    @SerializedName("AssigneeId")
    @Expose
    private String assigneeId;
    @SerializedName("ContactName")
    @Expose
    private String contactName;
    @SerializedName("ContactPhone")
    @Expose
    private String contactPhone;
    @SerializedName("CreatorId")
    @Expose
    private String creatorId;
    @SerializedName("CustomerId")
    @Expose
    private String customerId;
    @SerializedName("DeliveryDate")
    @Expose
    private String deliveryDate;
    @SerializedName("Note")
    @Expose
    private String note;
    @SerializedName("IsReceiptDelivery")
    @Expose
    private Integer isReceiptDelivery;
    @SerializedName("LineOrderDetail")
    @Expose
    private String lineOrderDetail;
    @SerializedName("OrganizationId")
    @Expose
    private String organizationId;
    @SerializedName("PaymentTermId")
    @Expose
    private String paymentTermId;
    @SerializedName("ReporterId")
    @Expose
    private String reporterId;
    @SerializedName("UserId")
    @Expose
    private String userId;
    public final static Parcelable.Creator<SalesOrderActionPost> CREATOR = new Creator<SalesOrderActionPost>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SalesOrderActionPost createFromParcel(Parcel in) {
            return new SalesOrderActionPost(in);
        }

        public SalesOrderActionPost[] newArray(int size) {
            return (new SalesOrderActionPost[size]);
        }

    }
            ;

    protected SalesOrderActionPost(Parcel in) {
        this.refId = ((String) in.readValue((String.class.getClassLoader())));
        this.amount = ((double) in.readValue((double.class.getClassLoader())));
        this.assigneeId = ((String) in.readValue((String.class.getClassLoader())));
        this.contactName = ((String) in.readValue((String.class.getClassLoader())));
        this.contactPhone = ((String) in.readValue((String.class.getClassLoader())));
        this.creatorId = ((String) in.readValue((String.class.getClassLoader())));
        this.customerId = ((String) in.readValue((String.class.getClassLoader())));
        this.deliveryDate = ((String) in.readValue((String.class.getClassLoader())));
        this.note = ((String) in.readValue((String.class.getClassLoader())));
        this.isReceiptDelivery = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.lineOrderDetail = ((String) in.readValue((String.class.getClassLoader())));
        this.organizationId = ((String) in.readValue((String.class.getClassLoader())));
        this.paymentTermId = ((String) in.readValue((String.class.getClassLoader())));
        this.reporterId = ((String) in.readValue((String.class.getClassLoader())));
        this.userId = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SalesOrderActionPost() {

    }

    public SalesOrderActionPost(String refId, double amount, String assigneeId, String contactName, String contactPhone, String creatorId, String customerId, String deliveryDate, int isReceiptDelivery,  String lineOrderDetail, String organizationId, String paymentTermId, String reporterId, String userId, String note) {
        this.refId = refId;
        this.amount = amount;
        this.assigneeId = assigneeId;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.creatorId = creatorId;
        this.customerId = customerId;
        this.deliveryDate = deliveryDate;
        this.isReceiptDelivery = isReceiptDelivery;
        this.lineOrderDetail = lineOrderDetail;
        this.organizationId = organizationId;
        this.paymentTermId = paymentTermId;
        this.reporterId = reporterId;
        this.userId = userId;
        this.note = note;
    }
    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getIsReceiptDelivery() {
        return isReceiptDelivery;
    }

    public void setIsReceiptDelivery(Integer isReceiptDelivery) {
        this.isReceiptDelivery = isReceiptDelivery;
    }

    public String getLineOrderDetail() {
        return lineOrderDetail;
    }

    public void setLineOrderDetail(String lineOrderDetail) {
        this.lineOrderDetail = lineOrderDetail;
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

    public String getReporterId() {
        return reporterId;
    }

    public void setReporterId(String reporterId) {
        this.reporterId = reporterId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(refId);
        dest.writeValue(amount);
        dest.writeValue(assigneeId);
        dest.writeValue(contactName);
        dest.writeValue(contactPhone);
        dest.writeValue(creatorId);
        dest.writeValue(customerId);
        dest.writeValue(deliveryDate);
        dest.writeValue(note);
        dest.writeValue(isReceiptDelivery);
        dest.writeValue(lineOrderDetail);
        dest.writeValue(organizationId);
        dest.writeValue(paymentTermId);
        dest.writeValue(reporterId);
        dest.writeValue(userId);
    }

    public int describeContents() {
        return 0;
    }

}