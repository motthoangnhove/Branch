package com.adcvn.adcsaleagrotech.model.receivemoney.receivablelist;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReceivableListResult implements Parcelable {

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("@odata.count")
    @Expose
    private Integer odataCount;
    @SerializedName("value")
    @Expose
    private List<ReceivableDetail> value = null;

    public ReceivableListResult() {
    }

    protected ReceivableListResult(Parcel in) {
        odataContext = in.readString();
        if (in.readByte() == 0) {
            odataCount = null;
        } else {
            odataCount = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(odataContext);
        if (odataCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(odataCount);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ReceivableListResult> CREATOR = new Creator<ReceivableListResult>() {
        @Override
        public ReceivableListResult createFromParcel(Parcel in) {
            return new ReceivableListResult(in);
        }

        @Override
        public ReceivableListResult[] newArray(int size) {
            return new ReceivableListResult[size];
        }
    };

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

    public List<ReceivableDetail> getReceivableListDetails() {
        return value;
    }

    public void setReceivableListDetail(List<ReceivableDetail> value) {
        this.value = value;
    }

}