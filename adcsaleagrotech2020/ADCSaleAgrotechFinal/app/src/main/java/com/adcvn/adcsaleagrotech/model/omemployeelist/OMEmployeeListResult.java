package com.adcvn.adcsaleagrotech.model.omemployeelist;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import java.util.ArrayList;

public class OMEmployeeListResult implements Parcelable
{

  @SerializedName("@odata.context")
  @Expose
  private String odataContext;
  @SerializedName("@odata.count")
  @Expose
  private Integer odataCount;
  @SerializedName("value")
  @Expose
  private List<OMEmployee> oMEmployee = new ArrayList<OMEmployee>();
  public final static Parcelable.Creator<OMEmployeeListResult> CREATOR = new Creator<OMEmployeeListResult>() {


    @SuppressWarnings({
            "unchecked"
    })
    public OMEmployeeListResult createFromParcel(Parcel in) {
      return new OMEmployeeListResult(in);
    }

    public OMEmployeeListResult[] newArray(int size) {
      return (new OMEmployeeListResult[size]);
    }

  }
          ;

  protected OMEmployeeListResult(Parcel in) {
    this.odataContext = ((String) in.readValue((String.class.getClassLoader())));
    this.odataCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
    in.readList(this.oMEmployee, (com.adcvn.adcsaleagrotech.model.omemployeelist.OMEmployee.class.getClassLoader()));
  }

  public OMEmployeeListResult() {
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

  public List<OMEmployee> getOMEmployee() {
    return oMEmployee;
  }

  public void setOMEmployee(List<OMEmployee> oMEmployee) {
    this.oMEmployee = oMEmployee;
  }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(odataContext);
    dest.writeValue(odataCount);
    dest.writeList(oMEmployee);
  }

  public int describeContents() {
    return 0;
  }

}