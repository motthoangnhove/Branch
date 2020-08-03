package com.adcvn.adcsaleagrotech.model.transactionphotocreate;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionPhotoCreatePost implements Parcelable
{

    @SerializedName("transactionId")
    @Expose
    private String transactionId;
    @SerializedName("photoLink")
    @Expose
    private String photoLink;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("userId")
    @Expose
    private String userId;
    public final static Creator<TransactionPhotoCreatePost> CREATOR = new Creator<TransactionPhotoCreatePost>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TransactionPhotoCreatePost createFromParcel(Parcel in) {
            return new TransactionPhotoCreatePost(in);
        }

        public TransactionPhotoCreatePost[] newArray(int size) {
            return (new TransactionPhotoCreatePost[size]);
        }

    }
            ;

    protected TransactionPhotoCreatePost(Parcel in) {
        this.transactionId = ((String) in.readValue((String.class.getClassLoader())));
        this.photoLink = ((String) in.readValue((String.class.getClassLoader())));
        this.comment = ((String) in.readValue((String.class.getClassLoader())));
        this.userId = ((String) in.readValue((String.class.getClassLoader())));
    }

    public TransactionPhotoCreatePost() {
    }
    public TransactionPhotoCreatePost(String transactionId, String photoLink, String comment, String userId) {
        this.transactionId = transactionId;
        this.photoLink = photoLink;
        this.comment = comment;
        this.userId = userId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(transactionId);
        dest.writeValue(photoLink);
        dest.writeValue(comment);
        dest.writeValue(userId);
    }

    public int describeContents() {
        return 0;
    }

}