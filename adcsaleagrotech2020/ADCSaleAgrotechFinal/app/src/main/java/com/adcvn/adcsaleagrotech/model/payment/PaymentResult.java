package com.adcvn.adcsaleagrotech.model.payment;

import android.os.Parcel;
import android.os.Parcelable;
import com.adcvn.adcsaleagrotech.model.payment.Payment;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PaymentResult implements Parcelable
{

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("@odata.count")
    @Expose
    private Integer odataCount;
    @SerializedName("value")
    @Expose
    private List<Payment> payment = null;
    public final static Creator<PaymentResult> CREATOR = new Creator<PaymentResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PaymentResult createFromParcel(Parcel in) {
            return new PaymentResult(in);
        }

        public PaymentResult[] newArray(int size) {
            return (new PaymentResult[size]);
        }

    }
            ;

    protected PaymentResult(Parcel in) {
        this.odataContext = ((String) in.readValue((String.class.getClassLoader())));
        this.odataCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.payment, (com.adcvn.adcsaleagrotech.model.payment.Payment.class.getClassLoader()));
    }

    public PaymentResult() {
    }

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public Integer getOdataCount() {
        return odataCount;
    }

    public void setOdataCount(Integer odataCount) {
        this.odataCount = odataCount;
    }

    public List<Payment> getPayment() {
        return payment;
    }

    public void setPayment(List<Payment> payment) {
        this.payment = payment;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataContext);
        dest.writeValue(odataCount);
        dest.writeList(payment);
    }

    public int describeContents() {
        return 0;
    }

}