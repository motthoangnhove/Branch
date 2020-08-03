package com.adcvn.adcsaleagrotech.model.payment;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payment implements Parcelable
{

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("AllowTransition")
    @Expose
    private Integer allowTransition;
    @SerializedName("ReporterId")
    @Expose
    private String reporterId;
    @SerializedName("PhotoLink")
    @Expose
    private String photoLink;
    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("SearchString")
    @Expose
    private Object searchString;
    @SerializedName("TransactionTypeName")
    @Expose
    private String transactionTypeName;
    @SerializedName("OrganizationName")
    @Expose
    private String organizationName;
    @SerializedName("CheckPayment")
    @Expose
    private Integer checkPayment;
    @SerializedName("PaymentName")
    @Expose
    private String paymentName;
    @SerializedName("OrganizationId")
    @Expose
    private String organizationId;
    @SerializedName("SeqNum")
    @Expose
    private Integer seqNum;
    @SerializedName("DoneTime")
    @Expose
    private String doneTime;
    @SerializedName("BankAccount")
    @Expose
    private String bankAccount;
    @SerializedName("StatusId")
    @Expose
    private String statusId;
    @SerializedName("PrefixSeq")
    @Expose
    private String prefixSeq;
    @SerializedName("StatusName")
    @Expose
    private String statusName;
    @SerializedName("IsAwaitingApproval")
    @Expose
    private Integer isAwaitingApproval;
    @SerializedName("BankNumber")
    @Expose
    private String bankNumber;
    @SerializedName("PaymentAmount")
    @Expose
    private Integer paymentAmount;
    @SerializedName("AllowChangeAssignee")
    @Expose
    private Integer allowChangeAssignee;
    @SerializedName("CreatorId")
    @Expose
    private String creatorId;
    @SerializedName("ApprovalRate")
    @Expose
    private Float approvalRate;
    @SerializedName("CustomField")
    @Expose
    private Object customField;
    @SerializedName("BankId")
    @Expose
    private String bankId;
    @SerializedName("BankName")
    @Expose
    private String bankName;
    @SerializedName("AssigneeId")
    @Expose
    private String assigneeId;
    @SerializedName("TransactionTypeId")
    @Expose
    private String transactionTypeId;
    @SerializedName("StatusColor")
    @Expose
    private String statusColor;
    @SerializedName("AllowEdit")
    @Expose
    private Integer allowEdit;
    @SerializedName("AllowDelete")
    @Expose
    private Integer allowDelete;
    public final static Creator<Payment> CREATOR = new Creator<Payment>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Payment createFromParcel(Parcel in) {
            return new Payment(in);
        }

        public Payment[] newArray(int size) {
            return (new Payment[size]);
        }

    }
            ;

    protected Payment(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.allowTransition = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.reporterId = ((String) in.readValue((String.class.getClassLoader())));
        this.photoLink = ((String) in.readValue((String.class.getClassLoader())));
        this.statusCode = ((String) in.readValue((String.class.getClassLoader())));
        this.searchString = ((Object) in.readValue((Object.class.getClassLoader())));
        this.transactionTypeName = ((String) in.readValue((String.class.getClassLoader())));
        this.organizationName = ((String) in.readValue((String.class.getClassLoader())));
        this.checkPayment = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.paymentName = ((String) in.readValue((String.class.getClassLoader())));
        this.organizationId = ((String) in.readValue((String.class.getClassLoader())));
        this.seqNum = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.doneTime = ((String) in.readValue((String.class.getClassLoader())));
        this.bankAccount = ((String) in.readValue((String.class.getClassLoader())));
        this.statusId = ((String) in.readValue((String.class.getClassLoader())));
        this.prefixSeq = ((String) in.readValue((String.class.getClassLoader())));
        this.statusName = ((String) in.readValue((String.class.getClassLoader())));
        this.isAwaitingApproval = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.bankNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.paymentAmount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.allowChangeAssignee = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.creatorId = ((String) in.readValue((String.class.getClassLoader())));
        this.approvalRate = ((Float) in.readValue((Float.class.getClassLoader())));
        this.customField = ((Object) in.readValue((Object.class.getClassLoader())));
        this.bankId = ((String) in.readValue((String.class.getClassLoader())));
        this.bankName = ((String) in.readValue((String.class.getClassLoader())));
        this.assigneeId = ((String) in.readValue((String.class.getClassLoader())));
        this.transactionTypeId = ((String) in.readValue((String.class.getClassLoader())));
        this.statusColor = ((String) in.readValue((String.class.getClassLoader())));
        this.allowEdit = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.allowDelete = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Payment() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAllowTransition() {
        return allowTransition;
    }

    public void setAllowTransition(Integer allowTransition) {
        this.allowTransition = allowTransition;
    }

    public String getReporterId() {
        return reporterId;
    }

    public void setReporterId(String reporterId) {
        this.reporterId = reporterId;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Object getSearchString() {
        return searchString;
    }

    public void setSearchString(Object searchString) {
        this.searchString = searchString;
    }

    public String getTransactionTypeName() {
        return transactionTypeName;
    }

    public void setTransactionTypeName(String transactionTypeName) {
        this.transactionTypeName = transactionTypeName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Integer getCheckPayment() {
        return checkPayment;
    }

    public void setCheckPayment(Integer checkPayment) {
        this.checkPayment = checkPayment;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
    }

    public String getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(String doneTime) {
        this.doneTime = doneTime;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getPrefixSeq() {
        return prefixSeq;
    }

    public void setPrefixSeq(String prefixSeq) {
        this.prefixSeq = prefixSeq;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Integer getIsAwaitingApproval() {
        return isAwaitingApproval;
    }

    public void setIsAwaitingApproval(Integer isAwaitingApproval) {
        this.isAwaitingApproval = isAwaitingApproval;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public Integer getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Integer paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Integer getAllowChangeAssignee() {
        return allowChangeAssignee;
    }

    public void setAllowChangeAssignee(Integer allowChangeAssignee) {
        this.allowChangeAssignee = allowChangeAssignee;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public Float getApprovalRate() {
        return approvalRate;
    }

    public void setApprovalRate(Float approvalRate) {
        this.approvalRate = approvalRate;
    }

    public Object getCustomField() {
        return customField;
    }

    public void setCustomField(Object customField) {
        this.customField = customField;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(String transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public String getStatusColor() {
        return statusColor;
    }

    public void setStatusColor(String statusColor) {
        this.statusColor = statusColor;
    }

    public Integer getAllowEdit() {
        return allowEdit;
    }

    public void setAllowEdit(Integer allowEdit) {
        this.allowEdit = allowEdit;
    }

    public Integer getAllowDelete() {
        return allowDelete;
    }

    public void setAllowDelete(Integer allowDelete) {
        this.allowDelete = allowDelete;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(allowTransition);
        dest.writeValue(reporterId);
        dest.writeValue(photoLink);
        dest.writeValue(statusCode);
        dest.writeValue(searchString);
        dest.writeValue(transactionTypeName);
        dest.writeValue(organizationName);
        dest.writeValue(checkPayment);
        dest.writeValue(paymentName);
        dest.writeValue(organizationId);
        dest.writeValue(seqNum);
        dest.writeValue(doneTime);
        dest.writeValue(bankAccount);
        dest.writeValue(statusId);
        dest.writeValue(prefixSeq);
        dest.writeValue(statusName);
        dest.writeValue(isAwaitingApproval);
        dest.writeValue(bankNumber);
        dest.writeValue(paymentAmount);
        dest.writeValue(allowChangeAssignee);
        dest.writeValue(creatorId);
        dest.writeValue(approvalRate);
        dest.writeValue(customField);
        dest.writeValue(bankId);
        dest.writeValue(bankName);
        dest.writeValue(assigneeId);
        dest.writeValue(transactionTypeId);
        dest.writeValue(statusColor);
        dest.writeValue(allowEdit);
        dest.writeValue(allowDelete);
    }

    public int describeContents() {
        return 0;
    }

}