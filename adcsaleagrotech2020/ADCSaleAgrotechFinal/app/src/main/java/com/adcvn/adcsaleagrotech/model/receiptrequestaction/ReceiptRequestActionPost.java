package com.adcvn.adcsaleagrotech.model.receiptrequestaction;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReceiptRequestActionPost implements Parcelable {

    @SerializedName("UserId")
    @Expose
    private String userId;
    @SerializedName("CreatorId")
    @Expose
    private String creatorId;
    @SerializedName("ReporterId")
    @Expose
    private String reporterId;
    @SerializedName("AssigneeId")
    @Expose
    private String assigneeId;
    @SerializedName("ReceivableId")
    @Expose
    private String receivableId;

    public ReceiptRequestActionPost() {
    }

    protected ReceiptRequestActionPost(Parcel in) {
        userId = in.readString();
        creatorId = in.readString();
        reporterId = in.readString();
        assigneeId = in.readString();
        receivableId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(creatorId);
        dest.writeString(reporterId);
        dest.writeString(assigneeId);
        dest.writeString(receivableId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ReceiptRequestActionPost> CREATOR = new Creator<ReceiptRequestActionPost>() {
        @Override
        public ReceiptRequestActionPost createFromParcel(Parcel in) {
            return new ReceiptRequestActionPost(in);
        }

        @Override
        public ReceiptRequestActionPost[] newArray(int size) {
            return new ReceiptRequestActionPost[size];
        }
    };

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getReporterId() {
        return reporterId;
    }

    public void setReporterId(String reporterId) {
        this.reporterId = reporterId;
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

}