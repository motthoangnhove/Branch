package com.adcvn.adcsaleagrotech.adapter.receivemoney.deposit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.action.activity.deposit.DepositActivity;
import com.adcvn.adcsaleagrotech.common.Common;
import com.adcvn.adcsaleagrotech.model.receivemoney.deposit.DepositModel;

import java.util.List;
//adapter danh sách phiếu nộp tiền
public class DepositAdapter extends RecyclerView.Adapter<DepositAdapter.RecyclerViewHolder> {

    private Context context;
    private List<DepositModel> modelList;
    private FragmentManager fm;
    private String DETAIL_KEY = "detail";

    public DepositAdapter(Context context, List<DepositModel> modelList, FragmentManager fm) {
        this.context = context;
        this.modelList = modelList;
        this.fm = fm;
    }

    @NonNull
    @Override
    public DepositAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_deposit, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DepositAdapter.RecyclerViewHolder holder, int position) {
        final DepositModel modelItem = modelList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DepositActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("key", DETAIL_KEY);
                bundle.putParcelable("depositDetail", modelItem);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.tvBankName.setText(modelItem.getBankName());
        holder.tvDepositNumber.setText(modelItem.getDepositNumber());
        holder.tvDate.setText(modelItem.getDate());
        holder.tvTotalDeposit.setText(Common.formatNumber(modelItem.getDepositAmount()));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tvBankName,tvDepositNumber, tvDate, tvTotalDeposit;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBankName = itemView.findViewById(R.id.tvBankName);
            tvDepositNumber = itemView.findViewById(R.id.tvDepositNumber);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTotalDeposit = itemView.findViewById(R.id.tvTotalDeposit);
        }
    }
}
