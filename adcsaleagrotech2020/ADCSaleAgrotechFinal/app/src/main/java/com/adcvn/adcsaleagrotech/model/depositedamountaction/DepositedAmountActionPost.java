package com.adcvn.adcsaleagrotech.model.depositedamountaction;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DepositedAmountActionPost implements Parcelable
{
    @SerializedName("AssigneeId")
    @Expose
    private String assigneeId;
    @SerializedName("CreatorId")
    @Expose
    private String creatorId;
    @SerializedName("DepositedAmount")
    @Expose
    private Long depositedAmount;
    @SerializedName("OrganizitionId")
    @Expose
    private String organizitionId;
    @SerializedName("PhotoLink")
    @Expose
    private String photoLink = "";
    @SerializedName("ReporterId")
    @Expose
    private String reporterId;
    @SerializedName("BankId")
    @Expose
    private String bankId;
    @SerializedName("UserId")
    @Expose
    private String userId;

    public DepositedAmountActionPost() {
    }

    public DepositedAmountActionPost(String assigneeId, String creatorId, Long depositedAmount, String organizitionId, String photoLink, String reporterId, String bankId, String userId) {
        this.assigneeId = assigneeId;
        this.creatorId = creatorId;
        this.depositedAmount = depositedAmount;
        this.organizitionId = organizitionId;
        this.photoLink = photoLink;
        this.reporterId = reporterId;
        this.bankId = bankId;
        this.userId = userId;
    }

    protected DepositedAmountActionPost(Parcel in) {
        assigneeId = in.readString();
        creatorId = in.readString();
        if (in.readByte() == 0) {
            depositedAmount = null;
        } else {
            depositedAmount = in.readLong();
        }
        organizitionId = in.readString();
        photoLink = in.readString();
        reporterId = in.readString();
        bankId = in.readString();
        userId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(assigneeId);
        dest.writeString(creatorId);
        if (depositedAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(depositedAmount);
        }
        dest.writeString(organizitionId);
        dest.writeString(photoLink);
        dest.writeString(reporterId);
        dest.writeString(bankId);
        dest.writeString(userId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DepositedAmountActionPost> CREATOR = new Creator<DepositedAmountActionPost>() {
        @Override
        public DepositedAmountActionPost createFromParcel(Parcel in) {
            return new DepositedAmountActionPost(in);
        }

        @Override
        public DepositedAmountActionPost[] newArray(int size) {
            return new DepositedAmountActionPost[size];
        }
    };

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public Long getDepositedAmount() {
        return depositedAmount;
    }

    public void setDepositedAmount(Long depositedAmount) {
        this.depositedAmount = depositedAmount;
    }

    public String getOrganizitionId() {
        return organizitionId;
    }

    public void setOrganizitionId(String organizitionId) {
        this.organizitionId = organizitionId;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public String getReporterId() {
        return reporterId;
    }

    public void setReporterId(String reporterId) {
        this.reporterId = reporterId;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
