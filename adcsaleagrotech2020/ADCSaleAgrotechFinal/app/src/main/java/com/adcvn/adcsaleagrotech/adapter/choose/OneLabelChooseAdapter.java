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

import androidx.recyclerview.widget.RecyclerView;

import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectOneLabel;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectOneLabelConfirm;

import java.util.ArrayList;
import java.util.List;

public class OneLabelChooseAdapter extends RecyclerView.Adapter<OneLabelChooseAdapter.RecyclerViewHolder> implements Filterable {
    private Context context;
    private Activity activity;
    private Dialog dialog;
    private String keySelect;
    private List<ISelectOneLabel> listSelectsOneLabel;
    private List<ISelectOneLabel> listSelectsOneLabelFull;

    public OneLabelChooseAdapter(Context context, Activity activity, Dialog dialog, String keySelect, List<ISelectOneLabel> listSelectsOneLabelInput) {
        this.context = context;
        this.activity = activity;
        this.dialog = dialog;
        this.keySelect = keySelect;
        this.listSelectsOneLabelFull = listSelectsOneLabelInput;
        this.listSelectsOneLabel = listSelectsOneLabelInput;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_choose_one_label, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        final ISelectOneLabel item = listSelectsOneLabel.get(position);
        holder.containerChooseOneLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activity instanceof ISelectOneLabelConfirm) {
                    ((ISelectOneLabelConfirm) (activity)).itemSelectOneLabel(keySelect, item);
                    dialog.dismiss();
                }
            }
        });
        holder.tvTitleChooseOneLabel.setText(item.getLabel());
    }

    @Override
    public int getItemCount() {
        return listSelectsOneLabel.size();
    }

    @Override
    public Filter getFilter() {
        return listSelectsOneLabelFilter;
    }

    private Filter listSelectsOneLabelFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ISelectOneLabel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(listSelectsOneLabelFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ISelectOneLabel item : listSelectsOneLabelFull) {
                    if (item.getLabel().toLowerCase().contains(filterPattern)) {
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
            listSelectsOneLabel = new ArrayList<>();
            listSelectsOneLabel.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        LinearLayout containerChooseOneLabel;
        TextView tvTitleChooseOneLabel;
        public RecyclerViewHolder(View itemView) {

            super(itemView);
            containerChooseOneLabel = itemView.findViewById(R.id.containerChooseOneLabel);
            tvTitleChooseOneLabel = itemView.findViewById(R.id.tvTitleChooseOneLabel);
        }
    }
}
