package com.adcvn.adcsaleagrotech.model.transactionphoto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionPhoto implements Parcelable
{
    @SerializedName("SeqNum")
    @Expose
    private Integer seqNum;
    @SerializedName("PhotoLink")
    @Expose
    private String photoLink;
    @SerializedName("Comment")
    @Expose
    private String comment;
    @SerializedName("CreatedTime")
    @Expose
    private String createdTime;
    @SerializedName("TransactionId")
    @Expose
    private String transactionId;
    @SerializedName("CreatorName")
    @Expose
    private String creatorName;
    public final static Creator<TransactionPhoto> CREATOR = new Creator<TransactionPhoto>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TransactionPhoto createFromParcel(Parcel in) {
            return new TransactionPhoto(in);
        }

        public TransactionPhoto[] newArray(int size) {
            return (new TransactionPhoto[size]);
        }

    }
            ;

    protected TransactionPhoto(Parcel in) {
        this.seqNum = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.photoLink = ((String) in.readValue((String.class.getClassLoader())));
        this.comment = ((String) in.readValue((String.class.getClassLoader())));
        this.createdTime = ((String) in.readValue((String.class.getClassLoader())));
        this.transactionId = ((String) in.readValue((String.class.getClassLoader())));
        this.creatorName = ((String) in.readValue((String.class.getClassLoader())));
    }

    public TransactionPhoto() {
    }

    public Integer getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
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

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(seqNum);
        dest.writeValue(photoLink);
        dest.writeValue(comment);
        dest.writeValue(createdTime);
        dest.writeValue(transactionId);
        dest.writeValue(creatorName);
    }

    public int describeContents() {
        return 0;
    }

}