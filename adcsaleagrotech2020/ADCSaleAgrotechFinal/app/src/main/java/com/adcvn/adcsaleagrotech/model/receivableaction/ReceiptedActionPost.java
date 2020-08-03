package com.adcvn.adcsaleagrotech.model.receivableaction;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReceiptedActionPost implements Parcelable
{

    @SerializedName("UserId")
    @Expose
    private String userId;
    @SerializedName("ReporterId")
    @Expose
    private String reporterId;
    @SerializedName("CreatorId")
    @Expose
    private String creatorId;
    @SerializedName("AssigneeId")
    @Expose
    private String assigneeId;
    @SerializedName("ReceivableId")
    @Expose
    private String receivableId;
    @SerializedName("ReceiptedAmount")
    @Expose
    private Double receiptedAmount;
    public final static Parcelable.Creator<ReceiptedActionPost> CREATOR = new Creator<ReceiptedActionPost>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ReceiptedActionPost createFromParcel(Parcel in) {
            return new ReceiptedActionPost(in);
        }

        public ReceiptedActionPost[] newArray(int size) {
            return (new ReceiptedActionPost[size]);
        }

    }
            ;

    protected ReceiptedActionPost(Parcel in) {
        this.userId = ((String) in.readValue((String.class.getClassLoader())));
        this.reporterId = ((String) in.readValue((String.class.getClassLoader())));
        this.creatorId = ((String) in.readValue((String.class.getClassLoader())));
        this.assigneeId = ((String) in.readValue((String.class.getClassLoader())));
        this.receivableId = ((String) in.readValue((String.class.getClassLoader())));
        this.receiptedAmount = ((Double) in.readValue((Double.class.getClassLoader())));
    }

    public ReceiptedActionPost() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReporterId() {
        return reporterId;
    }

    public void setReporterId(String reporterId) {
        this.reporterId = reporterId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getReceivableId() {
        return receivableId;
    }

    public void setReceivableId(String receivableId) {
        this.receivableId = receivableId;
    }

    public Double getReceiptedAmount() {
        return receiptedAmount;
    }

    public void setReceiptedAmount(Double receiptedAmount) {
        this.receiptedAmount = receiptedAmount;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(userId);
        dest.writeValue(reporterId);
        dest.writeValue(creatorId);
        dest.writeValue(assigneeId);
        dest.writeValue(receivableId);
        dest.writeValue(receiptedAmount);
    }

    public int describeContents() {
        return 0;
    }

}