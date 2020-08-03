package com.adcvn.adcsaleagrotech.adapter.orderdetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.common.Common;
import com.adcvn.adcsaleagrotech.model.salesorder.SalesOrderDetail;
import java.util.List;

public class OrderDetailProductAdapter extends RecyclerView.Adapter<OrderDetailProductAdapter.RecyclerViewHolder> {
    private Context context;
    private List<SalesOrderDetail> salesOrderProducts;

    public OrderDetailProductAdapter(Context context, List<SalesOrderDetail> salesOrderProducts) {
        this.context = context;
        this.salesOrderProducts = salesOrderProducts;
    }
    @NonNull
    @Override
    public OrderDetailProductAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_order_detail_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, final int position) {
        final SalesOrderDetail salesOrderDetail = salesOrderProducts.get(position);
        holder.tvNameProduct.setText((position +1)+". "+salesOrderDetail.getProductName());
        holder.tvQuantityProduct.setText(Common.formatNumber(salesOrderDetail.getQuantity()));
        holder.tvUnitPrivate.setText(salesOrderDetail.getUnitName());
        holder.tvPriceProduct.setText(Common.formatNumber(salesOrderDetail.getUnitPriceAfterTax()));
        holder.tvTotalMoneyProduct.setText(Common.formatNumber(salesOrderDetail.getAmountAfterTax()));
    }

    @Override
    public int getItemCount() {
        return salesOrderProducts.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNameProduct, tvUnitPrivate, tvPriceProduct, tvTotalMoneyProduct, tvQuantityProduct;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameProduct = itemView.findViewById(R.id.tvNameProduct);
            tvQuantityProduct = itemView.findViewById(R.id.tvQuantityProduct);
            tvUnitPrivate = itemView.findViewById(R.id.tvUnitPrivate);
            tvPriceProduct = itemView.findViewById(R.id.tvPriceProduct);
            tvTotalMoneyProduct = itemView.findViewById(R.id.tvTotalMoneyProduct);
        }
    }
}