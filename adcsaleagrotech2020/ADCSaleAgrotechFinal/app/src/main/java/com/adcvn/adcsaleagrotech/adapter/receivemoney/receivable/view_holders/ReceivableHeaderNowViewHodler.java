package com.adcvn.adcsaleagrotech.adapter.receivemoney.receivable.view_holders;

import android.view.View;
import android.widget.TextView;

import com.adcvn.adcsaleagrotech.R;
import com.cruxlab.sectionedrecyclerview.lib.BaseSectionAdapter;

public class ReceivableHeaderNowViewHodler extends BaseSectionAdapter.HeaderViewHolder {

    public TextView tvReceivableListHeader;

    public ReceivableHeaderNowViewHodler(View itemView) {
        super(itemView);
        this.tvReceivableListHeader = itemView.findViewById(R.id.tvReceivableListHeader);
    }

    public void bindHeader(String header, int count) {
        tvReceivableListHeader.setText(header + " (" + count + ")");
    }

}
