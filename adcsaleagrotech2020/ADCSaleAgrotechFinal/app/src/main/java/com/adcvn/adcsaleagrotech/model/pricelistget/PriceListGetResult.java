package com.adcvn.adcsaleagrotech.model.pricelistget;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PriceListGetResult implements Parcelable
{

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("value")
    @Expose
    private List<PriceListGet> priceListGet = new ArrayList<PriceListGet>();
    public final static Parcelable.Creator<PriceListGetResult> CREATOR = new Creator<PriceListGetResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PriceListGetResult createFromParcel(Parcel in) {
            return new PriceListGetResult(in);
        }

        public PriceListGetResult[] newArray(int size) {
            return (new PriceListGetResult[size]);
        }

    }
            ;

    protected PriceListGetResult(Parcel in) {
        this.odataContext = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.priceListGet, (com.adcvn.adcsaleagrotech.model.pricelistget.PriceListGet.class.getClassLoader()));
    }

    public PriceListGetResult() {
    }

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<PriceListGet> getPriceListGet() {
        return priceListGet;
    }

    public void setPriceListGet(List<PriceListGet> priceListGet) {
        this.priceListGet = priceListGet;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataContext);
        dest.writeList(priceListGet);
    }

    public int describeContents() {
        return 0;
    }

}