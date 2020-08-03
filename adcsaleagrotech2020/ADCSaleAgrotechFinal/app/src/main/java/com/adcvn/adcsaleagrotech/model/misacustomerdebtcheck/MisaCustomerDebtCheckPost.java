package com.adcvn.adcsaleagrotech.model.misacustomerdebtcheck;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MisaCustomerDebtCheckPost implements Parcelable
{

    @SerializedName("customercode")
    @Expose
    private String customercode;
    public final static Parcelable.Creator<MisaCustomerDebtCheckPost> CREATOR = new Creator<MisaCustomerDebtCheckPost>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MisaCustomerDebtCheckPost createFromParcel(Parcel in) {
            return new MisaCustomerDebtCheckPost(in);
        }

        public MisaCustomerDebtCheckPost[] newArray(int size) {
            return (new MisaCustomerDebtCheckPost[size]);
        }

    }
            ;

    protected MisaCustomerDebtCheckPost(Parcel in) {
        this.customercode = ((String) in.readValue((String.class.getClassLoader())));
    }

    public MisaCustomerDebtCheckPost() {
    }
    public MisaCustomerDebtCheckPost(String customercode) {
        this.customercode = customercode;
    }
    public String getCustomercode() {
        return customercode;
    }

    public void setCustomercode(String customercode) {
        this.customercode = customercode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(customercode);
    }

    public int describeContents() {
        return 0;
    }

}