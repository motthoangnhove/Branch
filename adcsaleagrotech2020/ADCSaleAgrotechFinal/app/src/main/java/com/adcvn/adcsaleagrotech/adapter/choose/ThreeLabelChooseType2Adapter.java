package com.adcvn.adcsaleagrotech.adapter.choose;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectOneLabel;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectThreeLabel;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectThreeLabelConfirm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ThreeLabelChooseType2Adapter extends RecyclerView.Adapter<ThreeLabelChooseType2Adapter.RecyclerViewHolder> implements Filterable {
    private Context context;
    private Activity activity;
    private Dialog dialog;
    private String keySelect;
    private List<ISelectThreeLabel> listSelectsThreeLabel;
    private List<ISelectThreeLabel> listSelectsThreeLabelFull;

    public ThreeLabelChooseType2Adapter(Context context, Activity activity, Dialog dialog, String keySelect, List<ISelectThreeLabel> listSelectsThreeLabelInput) {
        this.context = context;
        this.activity = activity;
        this.dialog = dialog;
        this.keySelect = keySelect;
        this.listSelectsThreeLabel = listSelectsThreeLabelInput;
        this.listSelectsThreeLabelFull = listSelectsThreeLabelInput;
    }

    @NonNull
    @Override
    public ThreeLabelChooseType2Adapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_choose_three_label_type_2, parent, false);
        return new ThreeLabelChooseType2Adapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThreeLabelChooseType2Adapter.RecyclerViewHolder holder, int position) {
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
        holder.tvTitleChooseSecondLabel.setText(item.getSecondLabel().concat("đ") + " / " + item.getThirdLabel());
    }

    @Override
    public int getItemCount() {
        return listSelectsThreeLabel.size();
    }

    @Override
    public Filter getFilter() {
        return listSelectsThreeLabelFilter;
    }

    private Filter listSelectsThreeLabelFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ISelectThreeLabel> filteredList = new ArrayList<>();
            if (constraint == "" || constraint.length() == 0) {
                filteredList = listSelectsThreeLabelFull;
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ISelectThreeLabel item : listSelectsThreeLabelFull) {
                    String strFirst = item.getFirstLabel().toLowerCase();
                    String strSecondt = item.getSecondLabel().replace(",", "");
                    String strThird = item.getThirdLabel().toLowerCase();
                    if (strFirst.contains(filterPattern)
                            || strSecondt.contains(filterPattern.replace(",", ""))
                            || strSecondt.equals((filterPattern.replace(",", ""))
                                                                .replace("đ", ""))
                            || strThird.equals(filterPattern)
                            || (strSecondt + strThird).equals(((filterPattern.replace(",", ""))
                                                                                .replace("đ", ""))
                                                                                .replace("/", ""))) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listSelectsThreeLabel = new ArrayList<>();
            listSelectsThreeLabel.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout containerChooseThreeLabel;
        private TextView tvAvatar, tvTitleChooseFirstLabel, tvTitleChooseSecondLabel;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            containerChooseThreeLabel = itemView.findViewById(R.id.containerChooseThreeLabel);
            tvAvatar = itemView.findViewById(R.id.tvAvatar);
            tvTitleChooseFirstLabel = itemView.findViewById(R.id.tvFirstLabelChoose);
            tvTitleChooseSecondLabel = itemView.findViewById(R.id.tvSecondLabelChoose);
        }
    }
}
