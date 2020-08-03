package com.adcvn.adcsaleagrotech.model.omsalesorderlist;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OMSalesOrder implements Parcelable
{

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("AssigneeName")
    @Expose
    private String assigneeName;
    @SerializedName("ReporterId")
    @Expose
    private String reporterId;
    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("ReceiptStatusDescription")
    @Expose
    private String receiptStatusDescription;
    @SerializedName("OrganizationName")
    @Expose
    private String organizationName;
    @SerializedName("NumberDebitDay")
    @Expose
    private int numberDebitDay;
    @SerializedName("OrganizationId")
    @Expose
    private String organizationId;
    @SerializedName("ReceiptAmount")
    @Expose
    private Object receiptAmount;
    @SerializedName("StatusColor")
    @Expose
    private String statusColor;
    @SerializedName("SearchString")
    @Expose
    private String searchString;
    @SerializedName("RefNo")
    @Expose
    private String refNo;
    @SerializedName("CustomerName")
    @Expose
    private String customerName;
    @SerializedName("StatusName")
    @Expose
    private String statusName;
    @SerializedName("PaymentTermName")
    @Expose
    private String paymentTermName;
    @SerializedName("AssigneeId")
    @Expose
    private String assigneeId;
    @SerializedName("CreatorId")
    @Expose
    private String creatorId;
    @SerializedName("SeqNum")
    @Expose
    private Integer seqNum;
    @SerializedName("ReceiptStatusCode")
    @Expose
    private String receiptStatusCode;
    @SerializedName("CreatorName")
    @Expose
    private String creatorName;
    @SerializedName("PaymentTermId")
    @Expose
    private String paymentTermId;
    @SerializedName("Amount")
    @Expose
    private Double amount;
    @SerializedName("ReceiptStatusName")
    @Expose
    private String receiptStatusName;
    @SerializedName("ReporterName")
    @Expose
    private String reporterName;
    @SerializedName("ReceiptStatusColor")
    @Expose
    private String receiptStatusColor;
    @SerializedName("PrefixSeq")
    @Expose
    private String prefixSeq;
    @SerializedName("DeliveryDate")
    @Expose
    private String deliveryDate;
    public final static Parcelable.Creator<OMSalesOrder> CREATOR = new Creator<OMSalesOrder>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OMSalesOrder createFromParcel(Parcel in) {
            return new OMSalesOrder(in);
        }

        public OMSalesOrder[] newArray(int size) {
            return (new OMSalesOrder[size]);
        }

    };

    protected OMSalesOrder(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.assigneeName = ((String) in.readValue((String.class.getClassLoader())));
        this.reporterId = ((String) in.readValue((String.class.getClassLoader())));
        this.statusCode = ((String) in.readValue((String.class.getClassLoader())));
        this.receiptStatusDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.organizationName = ((String) in.readValue((String.class.getClassLoader())));
        this.numberDebitDay = ((int) in.readValue((int.class.getClassLoader())));
        this.organizationId = ((String) in.readValue((String.class.getClassLoader())));
        this.receiptAmount = ((Object) in.readValue((Object.class.getClassLoader())));
        this.statusColor = ((String) in.readValue((String.class.getClassLoader())));
        this.searchString = ((String) in.readValue((String.class.getClassLoader())));
        this.refNo = ((String) in.readValue((String.class.getClassLoader())));
        this.customerName = ((String) in.readValue((String.class.getClassLoader())));
        this.statusName = ((String) in.readValue((String.class.getClassLoader())));
        this.paymentTermName = ((String) in.readValue((String.class.getClassLoader())));
        this.assigneeId = ((String) in.readValue((String.class.getClassLoader())));
        this.creatorId = ((String) in.readValue((String.class.getClassLoader())));
        this.seqNum = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.receiptStatusCode = ((String) in.readValue((String.class.getClassLoader())));
        this.creatorName = ((String) in.readValue((String.class.getClassLoader())));
        this.paymentTermId = ((String) in.readValue((String.class.getClassLoader())));
        this.amount = ((Double) in.readValue((Double.class.getClassLoader())));
        this.receiptStatusName = ((String) in.readValue((String.class.getClassLoader())));
        this.reporterName = ((String) in.readValue((String.class.getClassLoader())));
        this.receiptStatusColor = ((String) in.readValue((String.class.getClassLoader())));
        this.prefixSeq = ((String) in.readValue((String.class.getClassLoader())));
        this.deliveryDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public OMSalesOrder() {
    }

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

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getReceiptStatusDescription() {
        return receiptStatusDescription;
    }

    public void setReceiptStatusDescription(String receiptStatusDescription) {
        this.receiptStatusDescription = receiptStatusDescription;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public int getNumberDebitDay() {
        return numberDebitDay;
    }

    public void setNumberDebitDay(int numberDebitDay) {
        this.numberDebitDay = numberDebitDay;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public Object getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(Object receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public String getStatusColor() {
        return statusColor;
    }

    public void setStatusColor(String statusColor) {
        this.statusColor = statusColor;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getPaymentTermName() {
        return paymentTermName;
    }

    public void setPaymentTermName(String paymentTermName) {
        this.paymentTermName = paymentTermName;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
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

    public String getReceiptStatusCode() {
        return receiptStatusCode;
    }

    public void setReceiptStatusCode(String receiptStatusCode) {
        this.receiptStatusCode = receiptStatusCode;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getPaymentTermId() {
        return paymentTermId;
    }

    public void setPaymentTermId(String paymentTermId) {
        this.paymentTermId = paymentTermId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getReceiptStatusName() {
        return receiptStatusName;
    }

    public void setReceiptStatusName(String receiptStatusName) {
        this.receiptStatusName = receiptStatusName;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getReceiptStatusColor() {
        return receiptStatusColor;
    }

    public void setReceiptStatusColor(String receiptStatusColor) {
        this.receiptStatusColor = receiptStatusColor;
    }

    public String getPrefixSeq() {
        return prefixSeq;
    }

    public void setPrefixSeq(String prefixSeq) {
        this.prefixSeq = prefixSeq;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(assigneeName);
        dest.writeValue(reporterId);
        dest.writeValue(statusCode);
        dest.writeValue(receiptStatusDescription);
        dest.writeValue(organizationName);
        dest.writeValue(numberDebitDay);
        dest.writeValue(organizationId);
        dest.writeValue(receiptAmount);
        dest.writeValue(statusColor);
        dest.writeValue(searchString);
        dest.writeValue(refNo);
        dest.writeValue(customerName);
        dest.writeValue(statusName);
        dest.writeValue(paymentTermName);
        dest.writeValue(assigneeId);
        dest.writeValue(creatorId);
        dest.writeValue(seqNum);
        dest.writeValue(receiptStatusCode);
        dest.writeValue(creatorName);
        dest.writeValue(paymentTermId);
        dest.writeValue(amount);
        dest.writeValue(receiptStatusName);
        dest.writeValue(reporterName);
        dest.writeValue(receiptStatusColor);
        dest.writeValue(prefixSeq);
        dest.writeValue(deliveryDate);
    }

    public int describeContents() {
        return 0;
    }

}