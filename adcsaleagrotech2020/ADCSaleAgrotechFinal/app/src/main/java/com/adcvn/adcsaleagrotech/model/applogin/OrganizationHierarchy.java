package com.adcvn.adcsaleagrotech.model.applogin;

import android.os.Parcel;
import android.os.Parcelable;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectOneLabel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrganizationHierarchy implements Parcelable, ISelectOneLabel
{

    @SerializedName("OrganizationId")
    @Expose
    private String organizationId;
    @SerializedName("OrganizationName")
    @Expose
    private String organizationName;
    @SerializedName("OrganizationCode")
    @Expose
    private String organizationCode;
    public final static Creator<OrganizationHierarchy> CREATOR = new Creator<OrganizationHierarchy>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OrganizationHierarchy createFromParcel(Parcel in) {
            return new OrganizationHierarchy(in);
        }

        public OrganizationHierarchy[] newArray(int size) {
            return (new OrganizationHierarchy[size]);
        }

    }
            ;

    protected OrganizationHierarchy(Parcel in) {
        this.organizationId = ((String) in.readValue((String.class.getClassLoader())));
        this.organizationName = ((String) in.readValue((String.class.getClassLoader())));
        this.organizationCode = ((String) in.readValue((String.class.getClassLoader())));
    }

    public OrganizationHierarchy() {
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(organizationId);
        dest.writeValue(organizationName);
        dest.writeValue(organizationCode);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String getLabel() {
        return organizationName;
    }
}