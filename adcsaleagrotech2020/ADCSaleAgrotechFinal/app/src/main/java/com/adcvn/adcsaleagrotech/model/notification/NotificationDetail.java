package com.adcvn.adcsaleagrotech.model.notification;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationDetail implements Parcelable
{

    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("NewFlag")
    @Expose
    private Integer newFlag;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("LinkPDF")
    @Expose
    private String linkPDF;
    @SerializedName("Sequence")
    @Expose
    private Integer sequence;
    @SerializedName("ShowFlag")
    @Expose
    private Integer showFlag;
    public final static Parcelable.Creator<NotificationDetail> CREATOR = new Creator<NotificationDetail>() {


        @SuppressWarnings({
                "unchecked"
        })
        public NotificationDetail createFromParcel(Parcel in) {
            return new NotificationDetail(in);
        }

        public NotificationDetail[] newArray(int size) {
            return (new NotificationDetail[size]);
        }

    }
            ;

    protected NotificationDetail(Parcel in) {
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.newFlag = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.linkPDF = ((String) in.readValue((String.class.getClassLoader())));
        this.sequence = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.showFlag = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public NotificationDetail() {
    }

    public NotificationDetail(String name, String description, String linkPDF, int newFlag) {
        this.name = name;
        this.description = description;
        this.linkPDF = linkPDF;
        this.newFlag = newFlag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNewFlag() {
        return newFlag;
    }

    public void setNewFlag(Integer newFlag) {
        this.newFlag = newFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkPDF() {
        return linkPDF;
    }

    public void setLinkPDF(String linkPDF) {
        this.linkPDF = linkPDF;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(Integer showFlag) {
        this.showFlag = showFlag;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(description);
        dest.writeValue(newFlag);
        dest.writeValue(name);
        dest.writeValue(linkPDF);
        dest.writeValue(sequence);
        dest.writeValue(showFlag);
    }

    public int describeContents() {
        return 0;
    }

}