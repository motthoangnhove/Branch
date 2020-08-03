package com.adcvn.adcsaleagrotech.model.productfragment;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductFragmentModel implements Parcelable {
    private String avatar, productName, productUses, unit;
    private int price;

    protected ProductFragmentModel(Parcel in) {
        avatar = in.readString();
        productName = in.readString();
        productUses = in.readString();
        unit = in.readString();
        price = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(avatar);
        dest.writeString(productName);
        dest.writeString(productUses);
        dest.writeString(unit);
        dest.writeInt(price);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductFragmentModel> CREATOR = new Creator<ProductFragmentModel>() {
        @Override
        public ProductFragmentModel createFromParcel(Parcel in) {
            return new ProductFragmentModel(in);
        }

        @Override
        public ProductFragmentModel[] newArray(int size) {
            return new ProductFragmentModel[size];
        }
    };

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductUses() {
        return productUses;
    }

    public void setProductUses(String productUses) {
        this.productUses = productUses;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ProductFragmentModel() {
    }

    public ProductFragmentModel(String avatar, String productName, String productUses, String unit, int price) {
        this.avatar = avatar;
        this.productName = productName;
        this.productUses = productUses;
        this.unit = unit;
        this.price = price;
    }
}
