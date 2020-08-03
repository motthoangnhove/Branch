package com.adcvn.adcsaleagrotech.adapter.debtcustomer;

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
import com.adcvn.adcsaleagrotech.action.activity.debtcustomerdetail.DebtCustomerDetailActivity;
import com.adcvn.adcsaleagrotech.common.TranslateText;
import com.adcvn.adcsaleagrotech.model.debtcustomer.DebtCustomer;
import java.util.List;

public class DebtCustomerAdapter extends RecyclerView.Adapter<DebtCustomerAdapter.RecyclerViewHolder> {
    private Context context;
    private List<DebtCustomer> listDebtCustomer;

    public DebtCustomerAdapter(Context context, List<DebtCustomer> listDebtCustomer) {
        this.context = context;
        this.listDebtCustomer = listDebtCustomer;
    }

    @NonNull
    @Override
    public DebtCustomerAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.debt_customer_item, parent, false);
        return new DebtCustomerAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        DebtCustomer debtCustomer = listDebtCustomer.get(position);
        holder.lyDebtCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, DebtCustomerDetailActivity.class));
            }
        });

        holder.tvAvatar.setText(debtCustomer.getShortNameDebtCustomer());
        holder.tvNameDebtCustomer.setText(debtCustomer.getNameDebtCustomer());
        holder.tvAddressDebtCustomer.setText(debtCustomer.getAddressDebtCustomer());
        holder.tvTotalDebtCustomer.setText(TranslateText.formatNumber(debtCustomer.getTotalDebtCustomer()));
    }

    @Override
    public int getItemCount() {
        return listDebtCustomer.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout lyDebtCustomer;
        private TextView tvAvatar, tvNameDebtCustomer, tvAddressDebtCustomer, tvDetailDebtCustomer, tvTotalDebtCustomer;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            lyDebtCustomer = itemView.findViewById(R.id.lyDebtCustomer);
            tvAvatar = itemView.findViewById(R.id.tvAvatar);
            tvNameDebtCustomer = itemView.findViewById(R.id.tvNameDebtCustomer);
            tvAddressDebtCustomer = itemView.findViewById(R.id.tvAddressDebtCustomer);
            tvDetailDebtCustomer = itemView.findViewById(R.id.tvDetailDebtCustomer);
            tvTotalDebtCustomer = itemView.findViewById(R.id.tvTotalDebtCustomer);
        }
    }
}