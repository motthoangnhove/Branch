package com.adcvn.adcsaleagrotech.adapter.ordertypelist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.action.activity.createorder.CreateOrderActivity;
import com.adcvn.adcsaleagrotech.model.ordertypelist.OrderTypeDetail;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class OrderTypeListAdapter extends RecyclerView.Adapter<OrderTypeListAdapter.RecyclerViewHolder> {
    private Context context;
    private List<OrderTypeDetail> orders;
    private PopupWindow popupOrderType;

    public OrderTypeListAdapter(Context context, List<OrderTypeDetail> orders, PopupWindow popupOrderType) {
        this.context = context;
        this.orders = orders;
        this.popupOrderType = popupOrderType;
    }

    @NonNull
    @Override
    public OrderTypeListAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.order_type_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        final OrderTypeDetail item = orders.get(position);
        holder.ivOrderCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CreateOrderActivity.class);
                intent.putExtra("orderType", item);
                context.startActivity(intent);
                popupOrderType.dismiss();
            }
        });
        holder.tvOrderType.setText(item.getOrderTypeName());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout lyOrderTypeItem;
        private CircleImageView civOrderType;
        private TextView tvOrderType;
        private ImageView ivOrderCreate;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            lyOrderTypeItem = itemView.findViewById(R.id.lyOrderTypeItem);
            civOrderType = itemView.findViewById(R.id.civOrderType);
            tvOrderType = itemView.findViewById(R.id.tvOrderType);
            ivOrderCreate = itemView.findViewById(R.id.ivOrderCreate);
        }
    }
}