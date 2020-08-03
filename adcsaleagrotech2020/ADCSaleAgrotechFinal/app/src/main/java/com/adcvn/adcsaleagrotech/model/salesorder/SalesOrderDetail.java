package com.adcvn.adcsaleagrotech.model.salesorder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SalesOrderDetail implements Parcelable
{

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Quantity")
    @Expose
    private Integer quantity;
    @SerializedName("UnitName")
    @Expose
    private String unitName;
    @SerializedName("UnitPrice")
    @Expose
    private double unitPrice;
    @SerializedName("Amount")
    @Expose
    private double amount;
    @SerializedName("ProductName")
    @Expose
    private String productName;
    @SerializedName("SalesOrderId")
    @Expose
    private String salesOrderId;
    @SerializedName("SeqNum")
    @Expose
    private Integer seqNum;
    @SerializedName("UnitPriceAfterTax")
    @Expose
    private double unitPriceAfterTax;
    @SerializedName("AmountAfterTax")
    @Expose
    private double amountAfterTax;
    public final static Parcelable.Creator<SalesOrderDetail> CREATOR = new Creator<SalesOrderDetail>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SalesOrderDetail createFromParcel(Parcel in) {
            return new SalesOrderDetail(in);
        }

        public SalesOrderDetail[] newArray(int size) {
            return (new SalesOrderDetail[size]);
        }

    }
            ;

    protected SalesOrderDetail(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.quantity = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.unitName = ((String) in.readValue((String.class.getClassLoader())));
        this.unitPrice = ((double) in.readValue((double.class.getClassLoader())));
        this.amount = ((double) in.readValue((double.class.getClassLoader())));
        this.productName = ((String) in.readValue((String.class.getClassLoader())));
        this.salesOrderId = ((String) in.readValue((String.class.getClassLoader())));
        this.seqNum = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.unitPriceAfterTax = ((double) in.readValue((double.class.getClassLoader())));
        this.amountAfterTax = ((double) in.readValue((double.class.getClassLoader())));
    }

    public SalesOrderDetail() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(String salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public Integer getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
    }

    public double getUnitPriceAfterTax() {
        return unitPriceAfterTax;
    }

    public void setUnitPriceAfterTax(double unitPriceAfterTax) {
        this.unitPriceAfterTax = unitPriceAfterTax;
    }

    public double getAmountAfterTax() {
        return amountAfterTax;
    }

    public void setAmountAfterTax(Float amountAfterTax) {
        this.amountAfterTax = amountAfterTax;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(quantity);
        dest.writeValue(unitName);
        dest.writeValue(unitPrice);
        dest.writeValue(amount);
        dest.writeValue(productName);
        dest.writeValue(salesOrderId);
        dest.writeValue(seqNum);
        dest.writeValue(unitPriceAfterTax);
        dest.writeValue(amountAfterTax);
    }

    public int describeContents() {
        return 0;
    }

}