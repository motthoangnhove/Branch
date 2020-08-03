package com.adcvn.adcsaleagrotech.adapter.choose;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectThreeLabel;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectThreeLabelConfirm;

import java.util.List;

public class ThreeLabelChooseType1Adapter extends RecyclerView.Adapter<ThreeLabelChooseType1Adapter.RecyclerViewHolder> {
    private Context context;
    private Activity activity;
    private Dialog dialog;
    private String keySelect;
    private List<ISelectThreeLabel> listSelectsThreeLabel;
    public ThreeLabelChooseType1Adapter(Context context, Activity activity, Dialog dialog, String keySelect, List<ISelectThreeLabel> listSelectsThreeLabelInput) {
        this.context = context;
        this.activity = activity;
        this.dialog = dialog;
        this.keySelect = keySelect;
        this.listSelectsThreeLabel = listSelectsThreeLabelInput;
    }

    @NonNull
    @Override
    public ThreeLabelChooseType1Adapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_choose_three_label_type_1, parent, false);
        return new ThreeLabelChooseType1Adapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThreeLabelChooseType1Adapter.RecyclerViewHolder holder, int position) {
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
        holder.tvAvatar.setText(item.getFirstLabel());
        holder.tvTitleChooseFirstLabel.setText(item.getSecondLabel());
        holder.tvTitleChooseSecondLabel.setText(item.getThirdLabel());
    }

    @Override
    public int getItemCount() {
        return listSelectsThreeLabel.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout containerChooseThreeLabel;
        private TextView tvAvatar, tvTitleChooseFirstLabel, tvTitleChooseSecondLabel;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            containerChooseThreeLabel  = itemView.findViewById(R.id.containerChooseThreeLabel);
            tvAvatar = itemView.findViewById(R.id.tvAvatar);
            tvTitleChooseFirstLabel = itemView.findViewById(R.id.tvFirstLabelChoose);
            tvTitleChooseSecondLabel = itemView.findViewById(R.id.tvSecondLabelChoose);
        }
    }
}