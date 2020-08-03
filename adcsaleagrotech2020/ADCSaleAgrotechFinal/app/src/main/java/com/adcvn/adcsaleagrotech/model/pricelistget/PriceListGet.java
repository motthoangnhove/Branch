package com.adcvn.adcsaleagrotech.model.pricelistget;

import android.os.Parcel;
import android.os.Parcelable;

import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectThreeLabel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PriceListGet implements Parcelable, ISelectThreeLabel
{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("productList")
    @Expose
    private String productList;
    @SerializedName("pricelistName")
    @Expose
    private String pricelistName;
    @SerializedName("pricelistId")
    @Expose
    private String pricelistId;
    @SerializedName("errorCode")
    @Expose
    private Integer errorCode;
    public final static Parcelable.Creator<PriceListGet> CREATOR = new Creator<PriceListGet>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PriceListGet createFromParcel(Parcel in) {
            return new PriceListGet(in);
        }

        public PriceListGet[] newArray(int size) {
            return (new PriceListGet[size]);
        }

    }
            ;

    protected PriceListGet(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.productList = ((String) in.readValue((String.class.getClassLoader())));
        this.pricelistName = ((String) in.readValue((String.class.getClassLoader())));
        this.pricelistId = ((String) in.readValue((String.class.getClassLoader())));
        this.errorCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public PriceListGet() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProductList() {
        return productList;
    }

    public void setProductList(String productList) {
        this.productList = productList;
    }

    public String getPricelistName() {
        return pricelistName;
    }

    public void setPricelistName(String pricelistName) {
        this.pricelistName = pricelistName;
    }

    public String getPricelistId() {
        return pricelistId;
    }

    public void setPricelistId(String pricelistId) {
        this.pricelistId = pricelistId;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeValue(productList);
        dest.writeValue(pricelistName);
        dest.writeValue(pricelistId);
        dest.writeValue(errorCode);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String getFirstLabel() {
        return null;
    }

    @Override
    public String getSecondLabel() {
        return null;
    }

    @Override
    public String getThirdLabel() {
        return null;
    }
}