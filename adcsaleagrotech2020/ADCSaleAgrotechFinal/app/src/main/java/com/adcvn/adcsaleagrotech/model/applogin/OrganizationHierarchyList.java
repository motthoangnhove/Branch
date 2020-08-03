package com.adcvn.adcsaleagrotech.model.applogin;

import android.os.Parcel;
import android.os.Parcelable;
import com.adcvn.adcsaleagrotech.model.applogin.OrganizationHierarchy;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class OrganizationHierarchyList implements Parcelable
{

    @SerializedName("OrganizationHierarchy")
    @Expose
    private List<OrganizationHierarchy> organizationHierarchy = null;
    public final static Creator<OrganizationHierarchyList> CREATOR = new Creator<OrganizationHierarchyList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OrganizationHierarchyList createFromParcel(Parcel in) {
            return new OrganizationHierarchyList(in);
        }

        public OrganizationHierarchyList[] newArray(int size) {
            return (new OrganizationHierarchyList[size]);
        }

    }
            ;

    protected OrganizationHierarchyList(Parcel in) {
        in.readList(this.organizationHierarchy, (OrganizationHierarchy.class.getClassLoader()));
    }

    public OrganizationHierarchyList() {
    }

    public List<OrganizationHierarchy> getOrganizationHierarchy() {
        return organizationHierarchy;
    }

    public void setOrganizationHierarchy(List<OrganizationHierarchy> organizationHierarchy) {
        this.organizationHierarchy = organizationHierarchy;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(organizationHierarchy);
    }

    public int describeContents() {
        return 0;
    }
}