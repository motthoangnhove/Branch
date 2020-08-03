package com.adcvn.adcsaleagrotech.model.paymentcreate;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class PaymentCreateResult implements Parcelable
{

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("value")
    @Expose
    private List<PaymentCreate> paymentCreate = new ArrayList<PaymentCreate>();
    public final static Creator<PaymentCreateResult> CREATOR = new Creator<PaymentCreateResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PaymentCreateResult createFromParcel(Parcel in) {
            return new PaymentCreateResult(in);
        }

        public PaymentCreateResult[] newArray(int size) {
            return (new PaymentCreateResult[size]);
        }

    }
            ;

    protected PaymentCreateResult(Parcel in) {
        this.odataContext = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.paymentCreate, (com.adcvn.adcsaleagrotech.model.paymentcreate.PaymentCreate.class.getClassLoader()));
    }

    public PaymentCreateResult() {
    }

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<PaymentCreate> getPaymentCreate() {
        return paymentCreate;
    }

    public void setPaymentCreate(List<PaymentCreate> paymentCreate) {
        this.paymentCreate = paymentCreate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataContext);
        dest.writeList(paymentCreate);
    }

    public int describeContents() {
        return 0;
    }

}