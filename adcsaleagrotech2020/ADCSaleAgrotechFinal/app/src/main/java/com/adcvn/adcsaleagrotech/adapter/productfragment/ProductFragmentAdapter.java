package com.adcvn.adcsaleagrotech.adapter.productfragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.action.activity.ProductActivity;
import com.adcvn.adcsaleagrotech.common.Common;
import com.adcvn.adcsaleagrotech.model.productfragment.ProductFragmentModel;
import java.util.List;

public class ProductFragmentAdapter extends RecyclerView.Adapter<ProductFragmentAdapter.RecyclerViewHolder> {
    private Context context;
    private List<ProductFragmentModel> productList;

    public ProductFragmentAdapter(Context context, List<ProductFragmentModel> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductFragmentAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        final ProductFragmentModel model = productList.get(position);
        holder.tvProductName.setText(model.getProductName());
        holder.tvProductUses.setText(model.getProductUses());
        holder.tvUnitPrice.setText(Common.formatNumber(model.getPrice()) + "đ / " + model.getUnit());
        holder.lyProductItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ProductActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView tvProductName, tvProductUses, tvUnitPrice;
        private LinearLayout lyProductItem;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            lyProductItem = itemView.findViewById(R.id.lyProductItem);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductUses = itemView.findViewById(R.id.tvProductUses);
            tvUnitPrice = itemView.findViewById(R.id.tvUnitPrice);
        }
    }
}