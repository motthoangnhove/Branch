package com.adcvn.adcsaleagrotech.model.receivemoney.receivablelist;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class ReceivableDetail implements Parcelable {
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("AssigneeName")
    @Expose
    private String assigneeName;
    @SerializedName("ReporterId")
    @Expose
    private String reporterId;
    @SerializedName("ReceiverName")
    @Expose
    private String receiverName;
    @SerializedName("ReceiptReason")
    @Expose
    private String receiptReason;
    @SerializedName("ReceiptAbleAmount")
    @Expose
    private Long receiptAbleAmount;
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
    @SerializedName("StatusColor")
    @Expose
    private String statusColor;
    @SerializedName("OrganizationId")
    @Expose
    private String organizationId;
    @SerializedName("RefNo")
    @Expose
    private String refNo;
    @SerializedName("CreatedTime")
    @Expose
    private String createdTime;
    @SerializedName("NumberDebitDayCurrent")
    @Expose
    private Integer numberDebitDayCurrent;
    @SerializedName("StatusName")
    @Expose
    private String statusName;
    @SerializedName("Company")
    @Expose
    private String company;
    @SerializedName("ContactName")
    @Expose
    private String contactName;
    @SerializedName("ModifiedBy")
    @Expose
    private String modifiedBy;
    @SerializedName("DueDay")
    @Expose
    private String dueDay;
    @SerializedName("CreatedBy")
    @Expose
    private String createdBy;
    @SerializedName("AllowChangeAssignee")
    @Expose
    private Integer allowChangeAssignee;
    @SerializedName("CreatorId")
    @Expose
    private String creatorId;
    @SerializedName("SeqNum")
    @Expose
    private Integer seqNum;
    @SerializedName("ContactPhone")
    @Expose
    private String contactPhone;
    @SerializedName("ReceiverCode")
    @Expose
    private String receiverCode;
    @SerializedName("AllowChangeTransition")
    @Expose
    private Integer allowChangeTransition;
    @SerializedName("CreatorName")
    @Expose
    private String creatorName;
    @SerializedName("NumberDebitDayFuture")
    @Expose
    private Integer numberDebitDayFuture;
    @SerializedName("RefNoReference")
    @Expose
    private String refNoReference;
    @SerializedName("StatusId")
    @Expose
    private String statusId;
    @SerializedName("RefIdReference")
    @Expose
    private String refIdReference;
    @SerializedName("ModifiedTime")
    @Expose
    private String modifiedTime;
    @SerializedName("AssigneeId")
    @Expose
    private String assigneeId;
    @SerializedName("TransactionTypeId")
    @Expose
    private String transactionTypeId;
    @SerializedName("ReceiverAddress")
    @Expose
    private String receiverAddress;
    @SerializedName("WorkflowId")
    @Expose
    private Object workflowId;
    @SerializedName("ReporterName")
    @Expose
    private String reporterName;
    @SerializedName("Note")
    @Expose
    private String note;
    @SerializedName("PrefixSeq")
    @Expose
    private String prefixSeq;
    @SerializedName("AllowEdit")
    @Expose
    private Integer allowEdit;
    @SerializedName("AllowDelete")
    @Expose
    private Integer allowDelete;

    public ReceivableDetail() {
    }

    public ReceivableDetail(String id, String assigneeName, String reporterId, String receiverName, String receiptReason, Long receiptAbleAmount, String statusCode, Object searchString, String transactionTypeName, String organizationName, String statusColor, String organizationId, String refNo, String createdTime, Integer numberDebitDayCurrent, String statusName, String company, String contactName, String modifiedBy, String dueDay, String createdBy, Integer allowChangeAssignee, String creatorId, Integer seqNum, String contactPhone, String receiverCode, Integer allowChangeTransition, String creatorName, Integer numberDebitDayFuture, String refNoReference, String statusId, String refIdReference, String modifiedTime, String assigneeId, String transactionTypeId, String receiverAddress, Object workflowId, String reporterName, String note, String prefixSeq, Integer allowEdit, Integer allowDelete) {
        this.id = id;
        this.assigneeName = assigneeName;
        this.reporterId = reporterId;
        this.receiverName = receiverName;
        this.receiptReason = receiptReason;
        this.receiptAbleAmount = receiptAbleAmount;
        this.statusCode = statusCode;
        this.searchString = searchString;
        this.transactionTypeName = transactionTypeName;
        this.organizationName = organizationName;
        this.statusColor = statusColor;
        this.organizationId = organizationId;
        this.refNo = refNo;
        this.createdTime = createdTime;
        this.numberDebitDayCurrent = numberDebitDayCurrent;
        this.statusName = statusName;
        this.company = company;
        this.contactName = contactName;
        this.modifiedBy = modifiedBy;
        this.dueDay = dueDay;
        this.createdBy = createdBy;
        this.allowChangeAssignee = allowChangeAssignee;
        this.creatorId = creatorId;
        this.seqNum = seqNum;
        this.contactPhone = contactPhone;
        this.receiverCode = receiverCode;
        this.allowChangeTransition = allowChangeTransition;
        this.creatorName = creatorName;
        this.numberDebitDayFuture = numberDebitDayFuture;
        this.refNoReference = refNoReference;
        this.statusId = statusId;
        this.refIdReference = refIdReference;
        this.modifiedTime = modifiedTime;
        this.assigneeId = assigneeId;
        this.transactionTypeId = transactionTypeId;
        this.receiverAddress = receiverAddress;
        this.workflowId = workflowId;
        this.reporterName = reporterName;
        this.note = note;
        this.prefixSeq = prefixSeq;
        this.allowEdit = allowEdit;
        this.allowDelete = allowDelete;
    }

    protected ReceivableDetail(Parcel in) {
        id = in.readString();
        assigneeName = in.readString();
        reporterId = in.readString();
        receiverName = in.readString();
        receiptReason = in.readString();
        if (in.readByte() == 0) {
            receiptAbleAmount = null;
        } else {
            receiptAbleAmount = in.readLong();
        }
        statusCode = in.readString();
        transactionTypeName = in.readString();
        organizationName = in.readString();
        statusColor = in.readString();
        organizationId = in.readString();
        refNo = in.readString();
        createdTime = in.readString();
        if (in.readByte() == 0) {
            numberDebitDayCurrent = null;
        } else {
            numberDebitDayCurrent = in.readInt();
        }
        statusName = in.readString();
        company = in.readString();
        contactName = in.readString();
        modifiedBy = in.readString();
        dueDay = in.readString();
        createdBy = in.readString();
        if (in.readByte() == 0) {
            allowChangeAssignee = null;
        } else {
            allowChangeAssignee = in.readInt();
        }
        creatorId = in.readString();
        if (in.readByte() == 0) {
            seqNum = null;
        } else {
            seqNum = in.readInt();
        }
        contactPhone = in.readString();
        receiverCode = in.readString();
        if (in.readByte() == 0) {
            allowChangeTransition = null;
        } else {
            allowChangeTransition = in.readInt();
        }
        creatorName = in.readString();
        if (in.readByte() == 0) {
            numberDebitDayFuture = null;
        } else {
            numberDebitDayFuture = in.readInt();
        }
        refNoReference = in.readString();
        statusId = in.readString();
        refIdReference = in.readString();
        modifiedTime = in.readString();
        assigneeId = in.readString();
        transactionTypeId = in.readString();
        receiverAddress = in.readString();
        reporterName = in.readString();
        note = in.readString();
        prefixSeq = in.readString();
        if (in.readByte() == 0) {
            allowEdit = null;
        } else {
            allowEdit = in.readInt();
        }
        if (in.readByte() == 0) {
            allowDelete = null;
        } else {
            allowDelete = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(assigneeName);
        dest.writeString(reporterId);
        dest.writeString(receiverName);
        dest.writeString(receiptReason);
        if (receiptAbleAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(receiptAbleAmount);
        }
        dest.writeString(statusCode);
        dest.writeString(transactionTypeName);
        dest.writeString(organizationName);
        dest.writeString(statusColor);
        dest.writeString(organizationId);
        dest.writeString(refNo);
        dest.writeString(createdTime);
        if (numberDebitDayCurrent == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(numberDebitDayCurrent);
        }
        dest.writeString(statusName);
        dest.writeString(company);
        dest.writeString(contactName);
        dest.writeString(modifiedBy);
        dest.writeString(dueDay);
        dest.writeString(createdBy);
        if (allowChangeAssignee == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(allowChangeAssignee);
        }
        dest.writeString(creatorId);
        if (seqNum == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(seqNum);
        }
        dest.writeString(contactPhone);
        dest.writeString(receiverCode);
        if (allowChangeTransition == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(allowChangeTransition);
        }
        dest.writeString(creatorName);
        if (numberDebitDayFuture == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(numberDebitDayFuture);
        }
        dest.writeString(refNoReference);
        dest.writeString(statusId);
        dest.writeString(refIdReference);
        dest.writeString(modifiedTime);
        dest.writeString(assigneeId);
        dest.writeString(transactionTypeId);
        dest.writeString(receiverAddress);
        dest.writeString(reporterName);
        dest.writeString(note);
        dest.writeString(prefixSeq);
        if (allowEdit == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(allowEdit);
        }
        if (allowDelete == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(allowDelete);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ReceivableDetail> CREATOR = new Creator<ReceivableDetail>() {
        @Override
        public ReceivableDetail createFromParcel(Parcel in) {
            return new ReceivableDetail(in);
        }

        @Override
        public ReceivableDetail[] newArray(int size) {
            return new ReceivableDetail[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public String getReporterId() {
        return reporterId;
    }

    public void setReporterId(String reporterId) {
        this.reporterId = reporterId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiptReason() {
        return receiptReason;
    }

    public void setReceiptReason(String receiptReason) {
        this.receiptReason = receiptReason;
    }

    public Long getReceiptAbleAmount() {
        return receiptAbleAmount;
    }

    public void setReceiptAbleAmount(Long receiptAbleAmount) {
        this.receiptAbleAmount = receiptAbleAmount;
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

    public String getStatusColor() {
        return statusColor;
    }

    public void setStatusColor(String statusColor) {
        this.statusColor = statusColor;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getNumberDebitDayCurrent() {
        return numberDebitDayCurrent;
    }

    public void setNumberDebitDayCurrent(Integer numberDebitDayCurrent) {
        this.numberDebitDayCurrent = numberDebitDayCurrent;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getDueDay() {
        return dueDay;
    }

    public void setDueDay(String dueDay) {
        this.dueDay = dueDay;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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

    public Integer getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getReceiverCode() {
        return receiverCode;
    }

    public void setReceiverCode(String receiverCode) {
        this.receiverCode = receiverCode;
    }

    public Integer getAllowChangeTransition() {
        return allowChangeTransition;
    }

    public void setAllowChangeTransition(Integer allowChangeTransition) {
        this.allowChangeTransition = allowChangeTransition;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Integer getNumberDebitDayFuture() {
        return numberDebitDayFuture;
    }

    public void setNumberDebitDayFuture(Integer numberDebitDayFuture) {
        this.numberDebitDayFuture = numberDebitDayFuture;
    }

    public String getRefNoReference() {
        return refNoReference;
    }

    public void setRefNoReference(String refNoReference) {
        this.refNoReference = refNoReference;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getRefIdReference() {
        return refIdReference;
    }

    public void setRefIdReference(String refIdReference) {
        this.refIdReference = refIdReference;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
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

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public Object getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Object workflowId) {
        this.workflowId = workflowId;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPrefixSeq() {
        return prefixSeq;
    }

    public void setPrefixSeq(String prefixSeq) {
        this.prefixSeq = prefixSeq;
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
}
