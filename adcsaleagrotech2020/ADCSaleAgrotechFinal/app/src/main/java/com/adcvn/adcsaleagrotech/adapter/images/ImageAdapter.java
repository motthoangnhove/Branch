package com.adcvn.adcsaleagrotech.adapter.images;

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
import com.adcvn.adcsaleagrotech.model.transactionphoto.ImageModel;
import com.adcvn.adcsaleagrotech.model.transactionphoto.TransactionPhoto;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class ImageAdapter  extends RecyclerView.Adapter<ImageAdapter.RecyclerViewHolder>{

    private Context context;
    private List<ImageModel> transactionPhotos;

    public ImageAdapter(Context context, List<ImageModel> transactionPhotos) {
        this.context = context;
        this.transactionPhotos = transactionPhotos;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_image, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        ImageModel transactionPhoto = transactionPhotos.get(position);
//        Glide.with(context).load(transactionPhoto.getPhotoLink()).diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true).centerCrop().into(holder.ivImage);
        holder.tvUserName.setText(transactionPhoto.getUser());
//        holder.tvCreatorDate.setText(SystemDateTime.formatDateTimeZoneMinute(transactionPhoto.getDate()));
        holder.tvCreatorDate.setText(transactionPhoto.getDate());
    }

    @Override
    public int getItemCount() {
        return transactionPhotos.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivImage;
        private TextView tvAvatar, tvUserName, tvCreatorDate;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAvatar = itemView.findViewById(R.id.tvAvatar);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvCreatorDate = itemView.findViewById(R.id.tvCreatorDate);
        }
    }
}
