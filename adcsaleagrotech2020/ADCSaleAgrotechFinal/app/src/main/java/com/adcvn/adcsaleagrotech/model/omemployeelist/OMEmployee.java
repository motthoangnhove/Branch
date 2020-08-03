package com.adcvn.adcsaleagrotech.model.omemployeelist;

import android.os.Parcel;
import android.os.Parcelable;

import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectTwoLabel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OMEmployee implements Parcelable, ISelectTwoLabel
{

  @SerializedName("Avatar")
  @Expose
  private Object avatar;
  @SerializedName("EmployeeName")
  @Expose
  private String employeeName;
  @SerializedName("AvatarText")
  @Expose
  private String avatarText;
  @SerializedName("EmployeeCode")
  @Expose
  private String employeeCode;
  @SerializedName("OrganizationName")
  @Expose
  private String organizationName;
  @SerializedName("OrganizationId")
  @Expose
  private String organizationId;
  @SerializedName("SearchString")
  @Expose
  private String searchString;
  @SerializedName("EmployeeId")
  @Expose
  private String employeeId;
  public final static Parcelable.Creator<OMEmployee> CREATOR = new Creator<OMEmployee>() {


    @SuppressWarnings({
            "unchecked"
    })
    public OMEmployee createFromParcel(Parcel in) {
      return new OMEmployee(in);
    }

    public OMEmployee[] newArray(int size) {
      return (new OMEmployee[size]);
    }

  }
          ;

  protected OMEmployee(Parcel in) {
    this.avatar = ((String) in.readValue((Object.class.getClassLoader())));
    this.employeeName = ((String) in.readValue((String.class.getClassLoader())));
    this.avatarText = ((String) in.readValue((Object.class.getClassLoader())));
    this.employeeCode = ((String) in.readValue((String.class.getClassLoader())));
    this.organizationName = ((String) in.readValue((String.class.getClassLoader())));
    this.organizationId = ((String) in.readValue((String.class.getClassLoader())));
    this.searchString = ((String) in.readValue((String.class.getClassLoader())));
    this.employeeId = ((String) in.readValue((String.class.getClassLoader())));
  }

  public OMEmployee() {
  }

  public Object getAvatar() {
    return avatar;
  }

  public void setAvatar(Object avatar) {
    this.avatar = avatar;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  public String getAvatarText() {
    return avatarText;
  }

  public void setAvatarText(String avatarText) {
    this.avatarText = avatarText;
  }

  public String getEmployeeCode() {
    return employeeCode;
  }

  public void setEmployeeCode(String employeeCode) {
    this.employeeCode = employeeCode;
  }

  public String getOrganizationName() {
    return organizationName;
  }

  public void setOrganizationName(String organizationName) {
    this.organizationName = organizationName;
  }

  public String getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(String organizationId) {
    this.organizationId = organizationId;
  }

  public String getSearchString() {
    return searchString;
  }

  public void setSearchString(String searchString) {
    this.searchString = searchString;
  }

  public String getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
  }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(avatar);
    dest.writeValue(employeeName);
    dest.writeValue(avatarText);
    dest.writeValue(employeeCode);
    dest.writeValue(organizationName);
    dest.writeValue(organizationId);
    dest.writeValue(searchString);
    dest.writeValue(employeeId);
  }

  public int describeContents() {
    return 0;
  }

  @Override
  public String getFirstLabel() {
    return avatarText;
  }

  @Override
  public String getSecondLabel() {
    return employeeName;
  }
}