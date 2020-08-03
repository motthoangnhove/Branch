package com.adcvn.adcsaleagrotech.model.arbanklist;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ARBankListResult implements Parcelable
{

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("@odata.count")
    @Expose
    private Integer odataCount;
    @SerializedName("value")
    @Expose
    private List<ARBank> aRBank = new ArrayList<>();
    public final static Parcelable.Creator<ARBankListResult> CREATOR = new Creator<ARBankListResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ARBankListResult createFromParcel(Parcel in) {
            return new ARBankListResult(in);
        }

        public ARBankListResult[] newArray(int size) {
            return (new ARBankListResult[size]);
        }

    }
            ;

    protected ARBankListResult(Parcel in) {
        this.odataContext = ((String) in.readValue((String.class.getClassLoader())));
        this.odataCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.aRBank, (com.adcvn.adcsaleagrotech.model.arbanklist.ARBank.class.getClassLoader()));
    }

    public ARBankListResult() {
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

    public List<ARBank> getARBank() {
        return aRBank;
    }

    public void setARBank(List<ARBank> aRBank) {
        this.aRBank = aRBank;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataContext);
        dest.writeValue(odataCount);
        dest.writeList(aRBank);
    }

    public int describeContents() {
        return 0;
    }

}