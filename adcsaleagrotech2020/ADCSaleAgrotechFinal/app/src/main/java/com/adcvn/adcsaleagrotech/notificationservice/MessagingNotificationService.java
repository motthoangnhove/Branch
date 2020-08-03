package com.adcvn.adcsaleagrotech.notificationservice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.action.activity.dashboard.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Map;

public class MessagingNotificationService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage message) {
        int  id = (int) System.currentTimeMillis();
        Map<String,String> data = message.getData();
        String channelId = "Thông báo";
        NotificationCompat.Builder builder = new  NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.mipmap.icon_notifycation).setColor(getResources().getColor(R.color.d00A850))
                .setContentTitle(data.get("title"))
                .setContentText(data.get("body")).setAutoCancel(true);
        Notification notification = builder.build();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(getApplicationContext(),id, notificationIntent ,PendingIntent.FLAG_ONE_SHOT);
        notification.contentIntent = pendingNotificationIntent;
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(channelId,channelId, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }
        manager.notify(id,notification);
    }
}
