package com.adcvn.adcsaleagrotech.adapter.receivemoney.receipt;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.action.activity.receivableactivity.ReceivableActivity;
import com.adcvn.adcsaleagrotech.common.Common;
import com.adcvn.adcsaleagrotech.model.receivableaction.ReceiptedActionPost;

import java.util.List;
//adapter danh sách phiếu thu
public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.RecyclerViewHolder> {

    private Context context;
    private List<ReceiptedActionPost> modelList;
    private FragmentManager fm;
    private String DETAIL_KEY = "detail";

    public ReceiptAdapter(Context context, List<ReceiptedActionPost> modelList, FragmentManager fm) {
        this.context = context;
        this.modelList = modelList;
        this.fm = fm;
    }

    @NonNull
    @Override
    public ReceiptAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_receipt, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptAdapter.RecyclerViewHolder holder, int position) {
//        final ReceivableDetail modelItem = modelList.get(position);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, ReceivableActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("key", DETAIL_KEY);
//                bundle.putParcelable("receiptDetail", modelItem);
//                intent.putExtras(bundle);
//                context.startActivity(intent);
//            }
//        });
//        holder.tvCompanyName.setText(modelItem.getCompanyName());
//        holder.tvReceipNumber.setText(modelItem.getReceiptNumber());
//        holder.tvStatusReceipt.setText(modelItem.getStatusReceipt());
//        holder.tvDate.setText(modelItem.getDate());
//        holder.tvTotalReceipt.setText(Common.formatNumber(modelItem.getTotalAmount()));
//
//        if (modelItem.getStatusReceipt().equals("Đã thu")) {
//            holder.tvStatusReceipt.setBackgroundResource(R.drawable.ic_bg_status_receipt_green);
//            holder.tvStatusReceipt.setTextColor(Color.parseColor("#00A850"));
//        } else if (modelItem.getStatusReceipt().equals("Chờ thu hộ")) {
//            holder.tvStatusReceipt.setBackgroundResource(R.drawable.ic_bg_status_receipt_yellow);
//            holder.tvStatusReceipt.setTextColor(Color.parseColor("#FFAB36"));
//        }
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tvCompanyName,tvReceipNumber, tvDate, tvStatusReceipt, tvTotalReceipt;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCompanyName = itemView.findViewById(R.id.tvCompanyName);
            tvReceipNumber = itemView.findViewById(R.id.tvReceipNumber);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvStatusReceipt = itemView.findViewById(R.id.tvStatusReceipt);
            tvTotalReceipt = itemView.findViewById(R.id.tvTotalReceipt);
        }
    }
}
