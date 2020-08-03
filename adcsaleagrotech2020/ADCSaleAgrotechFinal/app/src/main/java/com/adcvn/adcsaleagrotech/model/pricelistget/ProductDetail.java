package com.adcvn.adcsaleagrotech.model.pricelistget;

import android.os.Parcel;
import android.os.Parcelable;
import com.adcvn.adcsaleagrotech.common.Common;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.IDoubleField;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectThreeLabel;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.IStringField;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetail  implements Parcelable, ISelectThreeLabel, IStringField, IDoubleField
{

    @SerializedName("PriceLineId")
    @Expose
    private String priceLineId;
    @SerializedName("ProductId")
    @Expose
    private String productId;
    @SerializedName("ProductCode")
    @Expose
    private String productCode;
    @SerializedName("ProductName")
    @Expose
    private String productName;
    @SerializedName("SearchString")
    @Expose
    private String searchString;
    @SerializedName("UnitId")
    @Expose
    private String unitId;
    @SerializedName("UnitName")
    @Expose
    private String unitName;
    @SerializedName("TaxId")
    @Expose
    private String taxId;
    @SerializedName("VATRate")
    @Expose
    private double vatRate;
    @SerializedName("TaxName")
    @Expose
    private String taxName;
    @SerializedName("UnitPrice")
    @Expose
    private double unitPrice;
    @SerializedName("UnitPriceAfterTax")
    @Expose
    private double unitPriceAfterTax;
    private int seqNum;
    private long quantity;
    private double totalSalary;
    public final static Parcelable.Creator<ProductDetail> CREATOR = new Creator<ProductDetail>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductDetail createFromParcel(Parcel in) {
            return new ProductDetail(in);
        }

        public ProductDetail[] newArray(int size) {
            return (new ProductDetail[size]);
        }

    }
            ;

    protected ProductDetail(Parcel in) {
        this.priceLineId = ((String) in.readValue((String.class.getClassLoader())));
        this.productId = ((String) in.readValue((String.class.getClassLoader())));
        this.productCode = ((String) in.readValue((String.class.getClassLoader())));
        this.productName = ((String) in.readValue((String.class.getClassLoader())));
        this.searchString = ((String) in.readValue((String.class.getClassLoader())));
        this.unitId = ((String) in.readValue((String.class.getClassLoader())));
        this.unitName = ((String) in.readValue((String.class.getClassLoader())));
        this.taxId = ((String) in.readValue((String.class.getClassLoader())));
        this.vatRate = ((double) in.readValue((Float.class.getClassLoader())));
        this.taxName = ((String) in.readValue((String.class.getClassLoader())));
        this.unitPrice = ((double) in.readValue((Float.class.getClassLoader())));
        this.unitPriceAfterTax = ((double) in.readValue((Float.class.getClassLoader())));
    }

    public ProductDetail() {
    }

    public String getPriceLineId() {
        return priceLineId;
    }

    public void setPriceLineId(String priceLineId) {
        this.priceLineId = priceLineId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public void setVatRate(double vatRate) {
        this.vatRate = vatRate;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getUnitPriceAfterTax() {
        return unitPriceAfterTax;
    }

    public void setUnitPriceAfterTax(Float unitPriceAfterTax) {
        this.unitPriceAfterTax = unitPriceAfterTax;
    }
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setUnitPriceAfterTax(double unitPriceAfterTax) {
        this.unitPriceAfterTax = unitPriceAfterTax;
    }

    public int getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(int seqNum) {
        this.seqNum = seqNum;
    }

    public double getVatRate() {
        return vatRate;
    }

    public void setVatRate(int vatRate) {
        this.vatRate = vatRate;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(priceLineId);
        dest.writeValue(productId);
        dest.writeValue(productCode);
        dest.writeValue(productName);
        dest.writeValue(searchString);
        dest.writeValue(unitId);
        dest.writeValue(unitName);
        dest.writeValue(taxId);
        dest.writeValue(vatRate);
        dest.writeValue(taxName);
        dest.writeValue(unitPrice);
        dest.writeValue(unitPriceAfterTax);
    }

    public int describeContents() {
        return 0;
    }
    @Override
    public String getFirstLabel() {
        return productName;
    }

    @Override
    public String getSecondLabel() {
        return Common.formatNumber(unitPriceAfterTax);
    }

    @Override
    public String getThirdLabel() {
        return unitName;
    }

    @Override
    public String getStrValue() {
        return productId;
    }

    @Override
    public double getDoubleValue() {
        return totalSalary;
    }

}