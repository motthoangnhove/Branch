package com.adcvn.adcsaleagrotech.adapter.choose;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectThreeLabel;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectThreeLabelConfirm;
import com.adcvn.adcsaleagrotech.model.receivemoney.deposit.BankAccountModel;

import java.util.List;

public class ThreeLabelChooseAdapter extends RecyclerView.Adapter<ThreeLabelChooseAdapter.RecyclerViewHolder> {
    private Context context;
    private Activity activity;
    private Dialog dialog;
    private String keySelect;
    private List<ISelectThreeLabel> listSelectsThreeLabel;

    public ThreeLabelChooseAdapter(Context context, Activity activity, Dialog dialog, String keySelect, List<ISelectThreeLabel> listSelectsThreeLabel) {
        this.context = context;
        this.activity = activity;
        this.dialog = dialog;
        this.keySelect = keySelect;
        this.listSelectsThreeLabel = listSelectsThreeLabel;
    }

    @Override
    public ThreeLabelChooseAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_choose_three_label, parent, false);
        return new ThreeLabelChooseAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ThreeLabelChooseAdapter.RecyclerViewHolder holder, int position) {
        final ISelectThreeLabel item = listSelectsThreeLabel.get(position);
        holder.containerChooseThreeLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activity instanceof ISelectThreeLabelConfirm) {
                    ((ISelectThreeLabelConfirm) (activity)).itemSelectThreeLabel(keySelect, item);
                    dialog.dismiss();
                }
            }
        });
        holder.tvTitleChooseFirstLabel.setText(item.getFirstLabel());
        holder.tvTitleChooseSecondLabel.setText("Tên TK: " + item.getSecondLabel());
        holder.tvTitleChooseThirdLabel.setText("Số TK: " + item.getThirdLabel());
    }

    @Override
    public int getItemCount() {
        return listSelectsThreeLabel.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        LinearLayout containerChooseThreeLabel;
        TextView tvTitleChooseFirstLabel, tvTitleChooseSecondLabel, tvTitleChooseThirdLabel;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            containerChooseThreeLabel = itemView.findViewById(R.id.containerChooseThreeLabel);
            tvTitleChooseFirstLabel = itemView.findViewById(R.id.tvTitleChooseFirstLabel);
            tvTitleChooseSecondLabel = itemView.findViewById(R.id.tvTitleChooseSecondLabel);
            tvTitleChooseThirdLabel = itemView.findViewById(R.id.tvTitleChooseThirdLabel);
        }
    }
}
