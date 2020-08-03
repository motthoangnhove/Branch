package com.adcvn.adcsaleagrotech.adapter.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.common.SystemDateTime;
import com.adcvn.adcsaleagrotech.model.history.History;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

//adapter danh sách nhật ký
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.RecyclerViewHolder>{
    private Context context;
    private List<History> data;

    public HistoryAdapter(Context context, List<History> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.history_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        History items = data.get(position);
        holder.tvCreatorName.setText(items.getCreatorName());
        holder.tvCreatedTime.setText(SystemDateTime.formatDateTimeZoneMinute(items.getCreatedTime()));
        holder.tvAction.setText(items.getActionName());
        holder.tvComment.setText(items.getComment());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView civAvatar;
        private TextView tvCreatorName, tvCreatedTime, tvAction, tvComment;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            civAvatar = itemView.findViewById(R.id.civAvatar);
            tvCreatorName = itemView.findViewById(R.id.tvCreatorName);
            tvCreatedTime = itemView.findViewById(R.id.tvCreatedTime);
            tvAction = itemView.findViewById(R.id.tvAction);
            tvComment = itemView.findViewById(R.id.tvComment);
        }
    }
}
