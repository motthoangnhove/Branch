package com.adcvn.adcsaleagrotech.model.login;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class LoginResult implements Parcelable
{

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;

    @SerializedName("value")
    @Expose
    private List<LoginDetail> loginDetail = new ArrayList<LoginDetail>();

    public final static Creator<LoginResult> CREATOR = new Creator<LoginResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public LoginResult createFromParcel(Parcel in) {
            return new LoginResult(in);
        }

        public LoginResult[] newArray(int size) {
            return (new LoginResult[size]);
        }

    }
            ;

    protected LoginResult(Parcel in) {
        this.odataContext = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.loginDetail, (LoginResult.class.getClassLoader()));
    }

    public LoginResult() {
    }

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<LoginDetail> getLoginDetail() {
        return loginDetail;
    }

    public void setLoginDetail(List<LoginDetail> loginDetail) {
        this.loginDetail = loginDetail;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataContext);
        dest.writeList(loginDetail);
    }

    public int describeContents() {
        return 0;
    }

}