package com.adcvn.adcsaleagrotech.adapter.notification;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.action.activity.DeleteNotificationActivity;
import com.adcvn.adcsaleagrotech.model.notification.NotificationDetail;
import java.util.List;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.RecyclerViewHolder>{
    private Context context;
    private List<NotificationDetail> notificationDetails;

    public NotificationListAdapter(Context context, List<NotificationDetail> notificationDetails) {
        this.context = context;
        this.notificationDetails = notificationDetails;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.notification_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        final NotificationDetail notificationDetail = notificationDetails.get(position);
        holder.lyContainerNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(context, DeleteNotificationActivity.class);
               context.startActivity(intent);
            }
        });
        if(notificationDetail.getNewFlag() == 1) {
            holder.vMainStatusNotificationDot.setVisibility(View.VISIBLE);
        }
        holder.tvTitleNotification.setText(notificationDetail.getName());
        holder.tvDescriptionNotification.setText(notificationDetail.getDescription());
    }

    @Override
    public int getItemCount() {
        return notificationDetails.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        LinearLayout lyContainerNotification;
        View vMainStatusNotificationDot;
        TextView tvTitleNotification, tvDescriptionNotification;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            lyContainerNotification = itemView.findViewById(R.id.lyContainerNotification);
            vMainStatusNotificationDot = itemView.findViewById(R.id.vMainStatusNotificationDot);
            tvTitleNotification = itemView.findViewById(R.id.tvTitleNotification);
            tvDescriptionNotification = itemView.findViewById(R.id.tvDescriptionNotification);
        }
    }
}