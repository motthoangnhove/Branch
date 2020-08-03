package com.adcvn.adcsaleagrotech.adapter.saleorder;

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
import com.adcvn.adcsaleagrotech.common.Common;
import com.adcvn.adcsaleagrotech.common.SystemDateTime;
import com.adcvn.adcsaleagrotech.model.omsalesorderlist.OMSalesOrder;
import java.util.List;

public class SaleOrderAdapter extends RecyclerView.Adapter<SaleOrderAdapter.RecyclerViewHolder> {
    private Context context;
    private List<OMSalesOrder> saleOrders;
    private final String DEMO = "Demo", AWAITING_STOCK = "Awaiting Stock", AWAITING_SHIP = "Awaiting Ship", DONE = "Done";
    private final String AWAITING_PAYMENT = "Awaiting Payment", PAYMENT_TED = "Paymented", PAYMENT = "Payment";
    public SaleOrderAdapter(Context context, List<OMSalesOrder> saleOrders) {
        this.context = context;
        this.saleOrders = saleOrders;
    }

    @NonNull
    @Override
    public SaleOrderAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.order_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        final OMSalesOrder saleOrder = saleOrders.get(position);
        holder.lyContainerOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("GET_SALES_ORDER");
                intent.putExtra("saleOrderId",saleOrder.getId());
                context.sendBroadcast(intent);
            }
        });
        holder.tvCompanyName.setText(saleOrder.getCustomerName());
        holder.tvOrderNumber.setText("#"+saleOrder.getRefNo());
        holder.tvDate.setText(SystemDateTime.formatDateTimeZone(saleOrder.getDeliveryDate()));
        holder.tvTotalSalary.setText(Common.formatNumber(saleOrder.getAmount()));
        // trạng thái đơn hàng
        if(saleOrder.getStatusCode() != null ) {
            holder.tvStatusOrder.setVisibility(View.VISIBLE);
            if (saleOrder.getStatusCode().equals(DEMO)) {
                holder.tvStatusOrder.setTextColor(context.getResources().getColor(R.color.d5856D6));
                holder.tvStatusOrder.setText(saleOrder.getStatusName());
                holder.tvStatusOrder.setBackground(context.getResources().getDrawable(R.drawable.ic_bg_status_receipt_purple));
            } else if (saleOrder.getStatusCode().equals(AWAITING_STOCK)) {
                holder.tvStatusOrder.setTextColor(context.getResources().getColor(R.color.dFFAB36));
                holder.tvStatusOrder.setText(saleOrder.getStatusName());
                holder.tvStatusOrder.setBackground(context.getResources().getDrawable(R.drawable.ic_bg_status_receipt_orange));
            } else if (saleOrder.getStatusCode().equals(AWAITING_SHIP)) {
                holder.tvStatusOrder.setTextColor(context.getResources().getColor(R.color.dEF1C23));
                holder.tvStatusOrder.setText(saleOrder.getStatusName());
                holder.tvStatusOrder.setBackground(context.getResources().getDrawable(R.drawable.ic_bg_status_receipt_red));
            } else if (saleOrder.getStatusCode().equals(DONE)) {
                holder.tvStatusOrder.setTextColor(context.getResources().getColor(R.color.d00A850));
                holder.tvStatusOrder.setText(saleOrder.getStatusName());
                holder.tvStatusOrder.setBackground(context.getResources().getDrawable(R.drawable.ic_bg_status_receipt_green));
            }
        }
        // trạng thái thanh toán
        if(saleOrder.getReceiptStatusCode() != null) {
            holder.tvStatusOrderReceipt.setVisibility(View.VISIBLE);
            holder.tvStatusOrderReceipt.setText(saleOrder.getReceiptStatusDescription());
            if (saleOrder.getReceiptStatusCode().equals(AWAITING_PAYMENT)) {
                holder.tvStatusOrderReceipt.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_wait,0);
                holder.tvStatusOrderReceipt.setTextColor(context.getResources().getColor(R.color.dEF1C23));
            } else if (saleOrder.getReceiptStatusCode().equals(PAYMENT_TED)) {
                holder.tvStatusOrderReceipt.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.ic_done,0);
                holder.tvStatusOrderReceipt.setTextColor(context.getResources().getColor(R.color.d00A850));
            }else if (saleOrder.getReceiptStatusCode().equals(PAYMENT)) {
                holder.tvStatusOrderReceipt.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.ic_currency,0);
                holder.tvStatusOrderReceipt.setTextColor(context.getResources().getColor(R.color.dFFAB36));
            }
        }
    }

    @Override
    public int getItemCount() {
        return saleOrders.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout lyContainerOrder;
        private TextView tvCompanyName, tvOrderNumber, tvDate, tvTotalSalary, tvStatusOrder, tvStatusOrderReceipt;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            lyContainerOrder = itemView.findViewById(R.id.lyContainerOrder);
            tvCompanyName = itemView.findViewById(R.id.tvCompanyName);
            tvOrderNumber = itemView.findViewById(R.id.tvOrderNumber);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTotalSalary =  itemView.findViewById(R.id.tvTotalSalary);
            tvStatusOrder = itemView.findViewById(R.id.tvStatusOrder);
            tvStatusOrderReceipt = itemView.findViewById(R.id.tvStatusOrderReceipt);
        }
    }
}