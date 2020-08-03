package com.adcvn.adcsaleagrotech.model.history;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class History implements Parcelable
{

    @SerializedName("SeqNum")
    @Expose
    private Integer seqNum;
    @SerializedName("ActionName")
    @Expose
    private String actionName;
    @SerializedName("TransactionId")
    @Expose
    private String transactionId;
    @SerializedName("Comment")
    @Expose
    private String comment;
    @SerializedName("CreatedTime")
    @Expose
    private String createdTime;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("CreatorName")
    @Expose
    private String creatorName;
    public final static Creator<History> CREATOR = new Creator<History>() {


        @SuppressWarnings({
                "unchecked"
        })
        public History createFromParcel(Parcel in) {
            return new History(in);
        }

        public History[] newArray(int size) {
            return (new History[size]);
        }

    }
            ;

    protected History(Parcel in) {
        this.seqNum = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.actionName = ((String) in.readValue((String.class.getClassLoader())));
        this.transactionId = ((String) in.readValue((String.class.getClassLoader())));
        this.comment = ((String) in.readValue((String.class.getClassLoader())));
        this.createdTime = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.creatorName = ((String) in.readValue((String.class.getClassLoader())));
    }

    public History() {
    }

    public History(String creatorName, String createdTime, String actionName, String comment) {
        this.creatorName = creatorName;
        this.createdTime = createdTime;
        this.actionName = actionName;
        this.comment = comment;
    }

    public Integer getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(seqNum);
        dest.writeValue(actionName);
        dest.writeValue(transactionId);
        dest.writeValue(comment);
        dest.writeValue(createdTime);
        dest.writeValue(type);
        dest.writeValue(creatorName);
    }

    public int describeContents() {
        return 0;
    }

}