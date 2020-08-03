package com.adcvn.adcsaleagrotech.model.notification;


import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class NotificationResult  implements Parcelable
{
    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("value")
    @Expose
    private List<NotificationDetail> notificationDetail;
    public final static Parcelable.Creator<NotificationResult> CREATOR = new Creator<NotificationResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public NotificationResult createFromParcel(Parcel in) {
            return new NotificationResult(in);
        }

        public NotificationResult[] newArray(int size) {
            return (new NotificationResult[size]);
        }

    }
            ;

    protected NotificationResult(Parcel in) {
        this.odataContext = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.notificationDetail, (com.adcvn.adcsaleagrotech.model.notification.NotificationDetail.class.getClassLoader()));
    }

    public NotificationResult() {
    }

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<NotificationDetail> getNotificationDetail() {
        return notificationDetail;
    }

    public void setNotificationDetail(List<NotificationDetail> notificationDetail) {
        this.notificationDetail = notificationDetail;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataContext);
        dest.writeList(notificationDetail);
    }

    public int describeContents() {
        return 0;
    }

}