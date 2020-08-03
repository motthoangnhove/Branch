package com.adcvn.adcsaleagrotech.model.salesorder;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SalesOrder implements Parcelable
{

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("SalesOrderDetail")
    @Expose
    private List<SalesOrderDetail> salesOrderDetail = new ArrayList<>();
    @SerializedName("DeliveryDate")
    @Expose
    private String deliveryDate;
    @SerializedName("PrefixSeq")
    @Expose
    private String prefixSeq;
    @SerializedName("RefNo")
    @Expose
    private String refNo;
    @SerializedName("ContactPhone")
    @Expose
    private String contactPhone;
    @SerializedName("ReporterName")
    @Expose
    private String reporterName;
    @SerializedName("Amount")
    @Expose
    private Float amount;
    @SerializedName("ContactName")
    @Expose
    private String contactName;
    @SerializedName("NumberDebitDay")
    @Expose
    private Integer numberDebitDay;
    @SerializedName("CustomerName")
    @Expose
    private String customerName;
    @SerializedName("ReceiptAmount")
    @Expose
    private Float receiptAmount;
    @SerializedName("StatusName")
    @Expose
    private String statusName;
    @SerializedName("DueDate")
    @Expose
    private String dueDate;
    @SerializedName("ReceiptStatusId")
    @Expose
    private String receiptStatusId;
    @SerializedName("ReceiptStatusName")
    @Expose
    private String receiptStatusName;
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("AssigneeName")
    @Expose
    private String assigneeName;
    @SerializedName("ReceiptStatusCode")
    @Expose
    private String receiptStatusCode;
    @SerializedName("ReceiptStatusColor")
    @Expose
    private String receiptStatusColor;
    @SerializedName("PaymentTermName")
    @Expose
    private String paymentTermName;
    @SerializedName("IsReceiptDelivery")
    @Expose
    private Integer isReceiptDelivery;
    @SerializedName("DeliveryAddress")
    @Expose
    private String deliveryAddress;
    public final static Parcelable.Creator<SalesOrder> CREATOR = new Creator<SalesOrder>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SalesOrder createFromParcel(Parcel in) {
            return new SalesOrder(in);
        }

        public SalesOrder[] newArray(int size) {
            return (new SalesOrder[size]);
        }

    }
            ;

    protected SalesOrder(Parcel in) {
        this.odataContext = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.salesOrderDetail, (com.adcvn.adcsaleagrotech.model.salesorder.SalesOrderDetail.class.getClassLoader()));
        this.deliveryDate = ((String) in.readValue((String.class.getClassLoader())));
        this.prefixSeq = ((String) in.readValue((String.class.getClassLoader())));
        this.refNo = ((String) in.readValue((String.class.getClassLoader())));
        this.contactPhone = ((String) in.readValue((String.class.getClassLoader())));
        this.reporterName = ((String) in.readValue((String.class.getClassLoader())));
        this.amount = ((Float) in.readValue((Float.class.getClassLoader())));
        this.contactName = ((String) in.readValue((String.class.getClassLoader())));
        this.numberDebitDay = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.customerName = ((String) in.readValue((String.class.getClassLoader())));
        this.receiptAmount = ((Float) in.readValue((Float.class.getClassLoader())));
        this.statusName = ((String) in.readValue((String.class.getClassLoader())));
        this.dueDate = ((String) in.readValue((String.class.getClassLoader())));
        this.receiptStatusId = ((String) in.readValue((String.class.getClassLoader())));
        this.receiptStatusName = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.assigneeName = ((String) in.readValue((String.class.getClassLoader())));
        this.receiptStatusCode = ((String) in.readValue((String.class.getClassLoader())));
        this.receiptStatusColor = ((String) in.readValue((String.class.getClassLoader())));
        this.paymentTermName = ((String) in.readValue((String.class.getClassLoader())));
        this.isReceiptDelivery = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.deliveryAddress = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SalesOrder() {
    }

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<SalesOrderDetail> getSalesOrderDetail() {
        return salesOrderDetail;
    }

    public void setSalesOrderDetail(List<SalesOrderDetail> salesOrderDetail) {
        this.salesOrderDetail = salesOrderDetail;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getPrefixSeq() {
        return prefixSeq;
    }

    public void setPrefixSeq(String prefixSeq) {
        this.prefixSeq = prefixSeq;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Integer getNumberDebitDay() {
        return numberDebitDay;
    }

    public void setNumberDebitDay(Integer numberDebitDay) {
        this.numberDebitDay = numberDebitDay;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Float getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(Float receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getReceiptStatusId() {
        return receiptStatusId;
    }

    public void setReceiptStatusId(String receiptStatusId) {
        this.receiptStatusId = receiptStatusId;
    }

    public String getReceiptStatusName() {
        return receiptStatusName;
    }

    public void setReceiptStatusName(String receiptStatusName) {
        this.receiptStatusName = receiptStatusName;
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

    public String getReceiptStatusCode() {
        return receiptStatusCode;
    }

    public void setReceiptStatusCode(String receiptStatusCode) {
        this.receiptStatusCode = receiptStatusCode;
    }

    public String getReceiptStatusColor() {
        return receiptStatusColor;
    }

    public void setReceiptStatusColor(String receiptStatusColor) {
        this.receiptStatusColor = receiptStatusColor;
    }

    public String getPaymentTermName() {
        return paymentTermName;
    }

    public void setPaymentTermName(String paymentTermName) {
        this.paymentTermName = paymentTermName;
    }

    public Integer getIsReceiptDelivery() {
        return isReceiptDelivery;
    }

    public void setIsReceiptDelivery(Integer isReceiptDelivery) {
        this.isReceiptDelivery = isReceiptDelivery;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataContext);
        dest.writeList(salesOrderDetail);
        dest.writeValue(deliveryDate);
        dest.writeValue(prefixSeq);
        dest.writeValue(refNo);
        dest.writeValue(contactPhone);
        dest.writeValue(reporterName);
        dest.writeValue(amount);
        dest.writeValue(contactName);
        dest.writeValue(numberDebitDay);
        dest.writeValue(customerName);
        dest.writeValue(receiptAmount);
        dest.writeValue(statusName);
        dest.writeValue(dueDate);
        dest.writeValue(receiptStatusId);
        dest.writeValue(receiptStatusName);
        dest.writeValue(id);
        dest.writeValue(assigneeName);
        dest.writeValue(receiptStatusCode);
        dest.writeValue(receiptStatusColor);
        dest.writeValue(paymentTermName);
        dest.writeValue(isReceiptDelivery);
        dest.writeValue(deliveryAddress);
    }

    public int describeContents() {
        return 0;
    }

}