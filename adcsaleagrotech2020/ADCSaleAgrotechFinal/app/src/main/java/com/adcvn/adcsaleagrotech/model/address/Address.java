package com.adcvn.adcsaleagrotech.model.address;

import android.os.Parcel;
import android.os.Parcelable;

import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectOneLabel;


public class Address implements Parcelable, ISelectOneLabel {

    private String name;

    public Address(){

    }
    public Address(String name){
        this.name = name;
    }
    protected Address(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getLabel() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }
}
