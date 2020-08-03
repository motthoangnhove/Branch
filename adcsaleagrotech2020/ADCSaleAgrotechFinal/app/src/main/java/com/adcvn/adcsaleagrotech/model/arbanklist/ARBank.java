package com.adcvn.adcsaleagrotech.model.arbanklist;

import android.os.Parcel;
import android.os.Parcelable;

import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectThreeLabel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ARBank implements Parcelable, ISelectThreeLabel
{

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("ChangeNumber")
    @Expose
    private String changeNumber;
    @SerializedName("ChangeAccount")
    @Expose
    private String changeAccount;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("Branch")
    @Expose
    private String branch;
    @SerializedName("Name")
    @Expose
    private String name;
    public final static Parcelable.Creator<ARBank> CREATOR = new Creator<ARBank>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ARBank createFromParcel(Parcel in) {
            return new ARBank(in);
        }

        public ARBank[] newArray(int size) {
            return (new ARBank[size]);
        }

    }
            ;

    protected ARBank(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.changeNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.changeAccount = ((String) in.readValue((String.class.getClassLoader())));
        this.code = ((String) in.readValue((String.class.getClassLoader())));
        this.branch = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ARBank() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChangeNumber() {
        return changeNumber;
    }

    public void setChangeNumber(String changeNumber) {
        this.changeNumber = changeNumber;
    }

    public String getChangeAccount() {
        return changeAccount;
    }

    public void setChangeAccount(String changeAccount) {
        this.changeAccount = changeAccount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(changeNumber);
        dest.writeValue(changeAccount);
        dest.writeValue(code);
        dest.writeValue(branch);
        dest.writeValue(name);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String getFirstLabel() {
        return name;
    }

    @Override
    public String getSecondLabel() {
        return changeAccount;
    }

    @Override
    public String getThirdLabel() {
        return changeNumber;
    }
}