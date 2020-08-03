package com.adcvn.adcsaleagrotech.model.receiptedtransfercustomeraction;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReceiptedTransferCustomerActionPost implements Parcelable
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
    private Long receiptedAmount;

    protected ReceiptedTransferCustomerActionPost(Parcel in) {
        userId = in.readString();
        reporterId = in.readString();
        creatorId = in.readString();
        assigneeId = in.readString();
        receivableId = in.readString();
        if (in.readByte() == 0) {
            receiptedAmount = null;
        } else {
            receiptedAmount = in.readLong();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(reporterId);
        dest.writeString(creatorId);
        dest.writeString(assigneeId);
        dest.writeString(receivableId);
        if (receiptedAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(receiptedAmount);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ReceiptedTransferCustomerActionPost> CREATOR = new Creator<ReceiptedTransferCustomerActionPost>() {
        @Override
        public ReceiptedTransferCustomerActionPost createFromParcel(Parcel in) {
            return new ReceiptedTransferCustomerActionPost(in);
        }

        @Override
        public ReceiptedTransferCustomerActionPost[] newArray(int size) {
            return new ReceiptedTransferCustomerActionPost[size];
        }
    };

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

    public Long getReceiptedAmount() {
        return receiptedAmount;
    }

    public void setReceiptedAmount(Long receiptedAmount) {
        this.receiptedAmount = receiptedAmount;
    }

    public ReceiptedTransferCustomerActionPost() {
    }

    public ReceiptedTransferCustomerActionPost(String userId, String reporterId, String creatorId, String assigneeId, String receivableId, Long receiptedAmount) {
        this.userId = userId;
        this.reporterId = reporterId;
        this.creatorId = creatorId;
        this.assigneeId = assigneeId;
        this.receivableId = receivableId;
        this.receiptedAmount = receiptedAmount;
    }
}