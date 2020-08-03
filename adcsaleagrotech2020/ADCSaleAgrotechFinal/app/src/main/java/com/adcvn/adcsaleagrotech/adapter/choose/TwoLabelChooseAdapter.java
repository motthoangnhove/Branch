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
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectOneLabelConfirm;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectThreeLabelConfirm;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectTwoLabel;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectTwoLabelConfirm;

import java.util.List;

//adapter danh sách nhân viên bán hàng
public class TwoLabelChooseAdapter extends RecyclerView.Adapter<TwoLabelChooseAdapter.RecyclerViewHolder>{
    private Context context;
    private Activity activity;
    private Dialog dialog;
    private List<ISelectTwoLabel> listSelectsTwoLabel;
    private String keySelect;
    public TwoLabelChooseAdapter(Context context, Activity activity, Dialog dialog, String keySelect, List<ISelectTwoLabel> listSelectsTwoLabelInput) {
        this.context = context;
        this.activity = activity;
        this.dialog = dialog;
        this.keySelect = keySelect;
        this.listSelectsTwoLabel = listSelectsTwoLabelInput;
    }

    @NonNull
    @Override
    public TwoLabelChooseAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_choose_two_label, parent, false);
        return new TwoLabelChooseAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TwoLabelChooseAdapter.RecyclerViewHolder holder, int position) {
        final ISelectTwoLabel item = listSelectsTwoLabel.get(position);
        holder.containerChooseThreeLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activity instanceof ISelectTwoLabelConfirm) {
                    ((ISelectTwoLabelConfirm) (activity)).itemSelectTwoLabel(keySelect, item);
                    dialog.dismiss();
                }
            }
        });
        holder.tvTitleChooseFirstLabel.setText(item.getFirstLabel());
        holder.tvTitleChooseSecondLabel.setText(item.getSecondLabel());
    }

    @Override
    public int getItemCount() {
        return listSelectsTwoLabel.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout containerChooseThreeLabel;
        private TextView tvTitleChooseFirstLabel, tvTitleChooseSecondLabel;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            containerChooseThreeLabel = itemView.findViewById(R.id.containerChooseTwoLabel);
            tvTitleChooseFirstLabel = itemView.findViewById(R.id.tvTitleChooseFirstLabel);
            tvTitleChooseSecondLabel = itemView.findViewById(R.id.tvTitleChooseSecondLabel);
        }
    }
}
