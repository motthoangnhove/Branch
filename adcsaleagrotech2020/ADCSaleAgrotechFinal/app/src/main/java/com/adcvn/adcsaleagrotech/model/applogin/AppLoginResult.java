package com.adcvn.adcsaleagrotech.model.applogin;

import android.os.Parcel;
import android.os.Parcelable;
import com.adcvn.adcsaleagrotech.model.applogin.AppLoginDetail;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;


public class AppLoginResult implements Parcelable
{

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("value")
    @Expose
    private List<AppLoginDetail> appLoginDetail = null;
    public final static Creator<AppLoginResult> CREATOR = new Creator<AppLoginResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AppLoginResult createFromParcel(Parcel in) {
            return new AppLoginResult(in);
        }

        public AppLoginResult[] newArray(int size) {
            return (new AppLoginResult[size]);
        }

    }
            ;

    protected AppLoginResult(Parcel in) {
        this.odataContext = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.appLoginDetail, (AppLoginDetail.class.getClassLoader()));
    }

    public AppLoginResult() {
    }

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<AppLoginDetail> getAppLoginDetail() {
        return appLoginDetail;
    }

    public void setAppLoginDetail(List<AppLoginDetail> appLoginDetail) {
        this.appLoginDetail = appLoginDetail;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataContext);
        dest.writeList(appLoginDetail);
    }

    public int describeContents() {
        return 0;
    }

}