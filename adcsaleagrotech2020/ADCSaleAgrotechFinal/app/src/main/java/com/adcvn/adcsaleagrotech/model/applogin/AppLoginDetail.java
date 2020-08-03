package com.adcvn.adcsaleagrotech.model.applogin;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppLoginDetail implements Parcelable {

    @SerializedName("EmployeeCode")
    @Expose
    private String employeeCode;
    @SerializedName("FullName")
    @Expose
    private String fullName;
    @SerializedName("ReceiptWallet")
    @Expose
    private Integer receiptWallet;
    @SerializedName("Avatar")
    @Expose
    private String avatar;
    @SerializedName("IsSystem")
    @Expose
    private Integer isSystem;
    @SerializedName("JobRoleCode")
    @Expose
    private String jobRoleCode;
    @SerializedName("OrganizationType")
    @Expose
    private String organizationType;
    @SerializedName("OrganizationId")
    @Expose
    private String organizationId;
    @SerializedName("IsApplicationManager")
    @Expose
    private Integer isApplicationManager;
    @SerializedName("IsApplicationUser")
    @Expose
    private Integer isApplicationUser;
    @SerializedName("errorCode")
    @Expose
    private Integer errorCode;
    @SerializedName("OrganizationHierarchy")
    @Expose
    private String organizationHierarchy;
    @SerializedName("CollectionDeliveryOrder")
    @Expose
    private Integer collectionDeliveryOrder;
    @SerializedName("IsManager")
    @Expose
    private Integer isManager;
    @SerializedName("AwaitingDeliveryOrder")
    @Expose
    private Integer awaitingDeliveryOrder;
    @SerializedName("OrganizationCode")
    @Expose
    private String organizationCode;
    @SerializedName("EmployeeId")
    @Expose
    private String employeeId;
    @SerializedName("OrganizationName")
    @Expose
    private String organizationName;
    @SerializedName("AllowChangeAssignee")
    @Expose
    private Integer allowChangeAssignee;
    @SerializedName("Phone")
    @Expose
    private String phone;
    @SerializedName("JobRoleName")
    @Expose
    private String jobRoleName;
    @SerializedName("JobRoleId")
    @Expose
    private String jobRoleId;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Email")
    @Expose
    private String email;

    public AppLoginDetail() {
    }

    public AppLoginDetail(String employeeCode, String fullName, Integer receiptWallet, String avatar, Integer isSystem, String jobRoleCode, String organizationType, String organizationId, Integer isApplicationManager, Integer isApplicationUser, Integer errorCode, String organizationHierarchy, Integer collectionDeliveryOrder, Integer isManager, Integer awaitingDeliveryOrder, String organizationCode, String employeeId, String organizationName, Integer allowChangeAssignee, String phone, String jobRoleName, String jobRoleId, String message, String email) {
        super();
        this.employeeCode = employeeCode;
        this.fullName = fullName;
        this.receiptWallet = receiptWallet;
        this.avatar = avatar;
        this.isSystem = isSystem;
        this.jobRoleCode = jobRoleCode;
        this.organizationType = organizationType;
        this.organizationId = organizationId;
        this.isApplicationManager = isApplicationManager;
        this.isApplicationUser = isApplicationUser;
        this.errorCode = errorCode;
        this.organizationHierarchy = organizationHierarchy;
        this.collectionDeliveryOrder = collectionDeliveryOrder;
        this.isManager = isManager;
        this.awaitingDeliveryOrder = awaitingDeliveryOrder;
        this.organizationCode = organizationCode;
        this.employeeId = employeeId;
        this.organizationName = organizationName;
        this.allowChangeAssignee = allowChangeAssignee;
        this.phone = phone;
        this.jobRoleName = jobRoleName;
        this.jobRoleId = jobRoleId;
        this.message = message;
        this.email = email;
    }

    protected AppLoginDetail(Parcel in) {
        employeeCode = in.readString();
        fullName = in.readString();
        if (in.readByte() == 0) {
            receiptWallet = null;
        } else {
            receiptWallet = in.readInt();
        }
        avatar = in.readString();
        if (in.readByte() == 0) {
            isSystem = null;
        } else {
            isSystem = in.readInt();
        }
        jobRoleCode = in.readString();
        organizationType = in.readString();
        organizationId = in.readString();
        if (in.readByte() == 0) {
            isApplicationManager = null;
        } else {
            isApplicationManager = in.readInt();
        }
        if (in.readByte() == 0) {
            isApplicationUser = null;
        } else {
            isApplicationUser = in.readInt();
        }
        if (in.readByte() == 0) {
            errorCode = null;
        } else {
            errorCode = in.readInt();
        }
        organizationHierarchy = in.readString();
        if (in.readByte() == 0) {
            collectionDeliveryOrder = null;
        } else {
            collectionDeliveryOrder = in.readInt();
        }
        if (in.readByte() == 0) {
            isManager = null;
        } else {
            isManager = in.readInt();
        }
        if (in.readByte() == 0) {
            awaitingDeliveryOrder = null;
        } else {
            awaitingDeliveryOrder = in.readInt();
        }
        organizationCode = in.readString();
        employeeId = in.readString();
        organizationName = in.readString();
        if (in.readByte() == 0) {
            allowChangeAssignee = null;
        } else {
            allowChangeAssignee = in.readInt();
        }
        phone = in.readString();
        jobRoleName = in.readString();
        jobRoleId = in.readString();
        message = in.readString();
        email = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(employeeCode);
        dest.writeString(fullName);
        if (receiptWallet == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(receiptWallet);
        }
        dest.writeString(avatar);
        if (isSystem == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isSystem);
        }
        dest.writeString(jobRoleCode);
        dest.writeString(organizationType);
        dest.writeString(organizationId);
        if (isApplicationManager == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isApplicationManager);
        }
        if (isApplicationUser == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isApplicationUser);
        }
        if (errorCode == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(errorCode);
        }
        dest.writeString(organizationHierarchy);
        if (collectionDeliveryOrder == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(collectionDeliveryOrder);
        }
        if (isManager == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isManager);
        }
        if (awaitingDeliveryOrder == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(awaitingDeliveryOrder);
        }
        dest.writeString(organizationCode);
        dest.writeString(employeeId);
        dest.writeString(organizationName);
        if (allowChangeAssignee == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(allowChangeAssignee);
        }
        dest.writeString(phone);
        dest.writeString(jobRoleName);
        dest.writeString(jobRoleId);
        dest.writeString(message);
        dest.writeString(email);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AppLoginDetail> CREATOR = new Creator<AppLoginDetail>() {
        @Override
        public AppLoginDetail createFromParcel(Parcel in) {
            return new AppLoginDetail(in);
        }

        @Override
        public AppLoginDetail[] newArray(int size) {
            return new AppLoginDetail[size];
        }
    };

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getReceiptWallet() {
        return receiptWallet;
    }

    public void setReceiptWallet(Integer receiptWallet) {
        this.receiptWallet = receiptWallet;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Integer isSystem) {
        this.isSystem = isSystem;
    }

    public String getJobRoleCode() {
        return jobRoleCode;
    }

    public void setJobRoleCode(String jobRoleCode) {
        this.jobRoleCode = jobRoleCode;
    }

    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getIsApplicationManager() {
        return isApplicationManager;
    }

    public void setIsApplicationManager(Integer isApplicationManager) {
        this.isApplicationManager = isApplicationManager;
    }

    public Integer getIsApplicationUser() {
        return isApplicationUser;
    }

    public void setIsApplicationUser(Integer isApplicationUser) {
        this.isApplicationUser = isApplicationUser;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getOrganizationHierarchy() {
        return organizationHierarchy;
    }

    public void setOrganizationHierarchy(String organizationHierarchy) {
        this.organizationHierarchy = organizationHierarchy;
    }

    public Integer getCollectionDeliveryOrder() {
        return collectionDeliveryOrder;
    }

    public void setCollectionDeliveryOrder(Integer collectionDeliveryOrder) {
        this.collectionDeliveryOrder = collectionDeliveryOrder;
    }

    public Integer getIsManager() {
        return isManager;
    }

    public void setIsManager(Integer isManager) {
        this.isManager = isManager;
    }

    public Integer getAwaitingDeliveryOrder() {
        return awaitingDeliveryOrder;
    }

    public void setAwaitingDeliveryOrder(Integer awaitingDeliveryOrder) {
        this.awaitingDeliveryOrder = awaitingDeliveryOrder;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Integer getAllowChangeAssignee() {
        return allowChangeAssignee;
    }

    public void setAllowChangeAssignee(Integer allowChangeAssignee) {
        this.allowChangeAssignee = allowChangeAssignee;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJobRoleName() {
        return jobRoleName;
    }

    public void setJobRoleName(String jobRoleName) {
        this.jobRoleName = jobRoleName;
    }

    public String getJobRoleId() {
        return jobRoleId;
    }

    public void setJobRoleId(String jobRoleId) {
        this.jobRoleId = jobRoleId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}