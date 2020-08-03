package com.adcvn.adcsaleagrotech.model.misacustomerdebtcheck;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MisaCustomerDebtDetail implements Parcelable
{

    @SerializedName("DebitAmount")
    @Expose
    private Double debitAmount;
    @SerializedName("OverdueAmount")
    @Expose
    private Double overdueAmount;
    @SerializedName("InprocessAmount")
    @Expose
    private Double inprocessAmount;
    @SerializedName("MaximizeDebtAmount")
    @Expose
    private Double maximizeDebtAmount;
    @SerializedName("RemainingAmount")
    @Expose
    private Double remainingAmount;
    @SerializedName("DebitStatus")
    @Expose
    private String debitStatus;
    @SerializedName("DebitFlag")
    @Expose
    private Integer debitFlag;
    public final static Parcelable.Creator<MisaCustomerDebtDetail> CREATOR = new Creator<MisaCustomerDebtDetail>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MisaCustomerDebtDetail createFromParcel(Parcel in) {
            return new MisaCustomerDebtDetail(in);
        }

        public MisaCustomerDebtDetail[] newArray(int size) {
            return (new MisaCustomerDebtDetail[size]);
        }

    }
            ;

    protected MisaCustomerDebtDetail(Parcel in) {
        this.debitAmount = ((Double) in.readValue((Double.class.getClassLoader())));
        this.overdueAmount = ((Double) in.readValue((Double.class.getClassLoader())));
        this.inprocessAmount = ((Double) in.readValue((Double.class.getClassLoader())));
        this.maximizeDebtAmount = ((Double) in.readValue((Double.class.getClassLoader())));
        this.remainingAmount = ((Double) in.readValue((Double.class.getClassLoader())));
        this.debitStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.debitFlag = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public MisaCustomerDebtDetail() {
    }

    public Double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(Double debitAmount) {
        this.debitAmount = debitAmount;
    }

    public Double getOverdueAmount() {
        return overdueAmount;
    }

    public void setOverdueAmount(Double overdueAmount) {
        this.overdueAmount = overdueAmount;
    }

    public Double getInprocessAmount() {
        return inprocessAmount;
    }

    public void setInprocessAmount(Double inprocessAmount) {
        this.inprocessAmount = inprocessAmount;
    }

    public Double getMaximizeDebtAmount() {
        return maximizeDebtAmount;
    }

    public void setMaximizeDebtAmount(Double maximizeDebtAmount) {
        this.maximizeDebtAmount = maximizeDebtAmount;
    }

    public Double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(Double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public String getDebitStatus() {
        return debitStatus;
    }

    public void setDebitStatus(String debitStatus) {
        this.debitStatus = debitStatus;
    }

    public Integer getDebitFlag() {
        return debitFlag;
    }

    public void setDebitFlag(Integer debitFlag) {
        this.debitFlag = debitFlag;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(debitAmount);
        dest.writeValue(overdueAmount);
        dest.writeValue(inprocessAmount);
        dest.writeValue(maximizeDebtAmount);
        dest.writeValue(remainingAmount);
        dest.writeValue(debitStatus);
        dest.writeValue(debitFlag);
    }

    public int describeContents() {
        return 0;
    }

}