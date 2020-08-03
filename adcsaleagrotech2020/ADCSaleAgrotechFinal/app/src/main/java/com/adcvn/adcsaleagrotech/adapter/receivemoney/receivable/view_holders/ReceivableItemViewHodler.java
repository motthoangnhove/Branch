package com.adcvn.adcsaleagrotech.adapter.receivemoney.receivable.view_holders;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.action.activity.receivableactivity.ReceivableActivity;
import com.adcvn.adcsaleagrotech.adapter.receivemoney.receivable.BaseAdapter;
import com.adcvn.adcsaleagrotech.common.Common;
import com.adcvn.adcsaleagrotech.model.receivemoney.receivablelist.ReceivableDetail;
import com.cruxlab.sectionedrecyclerview.lib.BaseSectionAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReceivableItemViewHodler extends BaseSectionAdapter.ItemViewHolder {

    private BaseAdapter adapter;
    private TextView tvCompanyName, tvStatus, tvTotalReceivable;
    private CircleImageView imgStatusColor;
    private LinearLayout lyReceivableChild;
    private int isCurrent;
    private String CREATE_KEY = "Create";

    public ReceivableItemViewHodler(View itemView) {
        super(itemView);
        this.tvCompanyName = itemView.findViewById(R.id.tvCompanyName);
        this.tvStatus = itemView.findViewById(R.id.tvStatus);
        this.tvTotalReceivable = itemView.findViewById(R.id.tvTotalReceivable);
        this.imgStatusColor = itemView.findViewById(R.id.imgStatusColor);
        this.lyReceivableChild = itemView.findViewById(R.id.lyReceivableChild);
    }

    public void bindItem(final ReceivableDetail receivableDetail, final Context context) {

        Calendar c = Calendar.getInstance();
        String time = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        int duration = (int) getDateDiff(new SimpleDateFormat("yyyy-MM-dd"), time, receivableDetail.getDueDay().substring(0, 10));
        tvCompanyName.setText(receivableDetail.getCompany());
        tvStatus.setText(receivableDetail.getStatusName());
        tvTotalReceivable.setText(Common.formatNumber(receivableDetail.getReceiptAbleAmount()));
        if (duration > 0) {
            tvStatus.setText("Còn " + duration + " ngày");
            imgStatusColor.setImageResource(R.drawable.ic_ovalgreen);
            tvStatus.setTextColor(Color.parseColor("#00A850"));
            isCurrent = 0;
        } else if (duration == 0) {
            tvStatus.setText("Đến hạn");
            imgStatusColor.setImageResource(R.drawable.ic_ovalorange);
            tvStatus.setTextColor(Color.parseColor("#FFAB36"));
            isCurrent = 1;
        } else {
            tvStatus.setText("Quá hạn " + Math.abs(duration) + " ngày");
            imgStatusColor.setImageResource(R.drawable.ic_ovalred);
            tvStatus.setTextColor(Color.parseColor("#EF1C23"));
            isCurrent = 1;
        }

        lyReceivableChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ReceivableActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("isCurrent", isCurrent);
                bundle.putString("key", CREATE_KEY);
                bundle.putParcelable("receivableCreate", receivableDetail);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
//        tvCompanyName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(context, ReceivableActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("isCurrent", isCurrent);
//                bundle.putString("key", CREATE_KEY);
//                bundle.putParcelable("receivableCreate", receivableDetail);
//                intent.putExtras(bundle);
//                context.startActivity(intent);
//            }
//        });
//        tvStatus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, ReceivableActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("isCurrent", isCurrent);
//                bundle.putString("key", CREATE_KEY);
//                bundle.putParcelable("receivableCreate", receivableDetail);
//                intent.putExtras(bundle);
//                context.startActivity(intent);
//            }
//        });
//        tvCompanyName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, ReceivableActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("isCurrent", isCurrent);
//                bundle.putString("key", CREATE_KEY);
//                bundle.putParcelable("receivableCreate", receivableDetail);
//                intent.putExtras(bundle);
//                context.startActivity(intent);
//            }
//        });
//        tvTotalReceivable.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, ReceivableActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("isCurrent", isCurrent);
//                bundle.putString("key", CREATE_KEY);
//                bundle.putParcelable("receivableCreate", receivableDetail);
//                intent.putExtras(bundle);
//                context.startActivity(intent);
//            }
//        });
    }

    public static long getDateDiff(SimpleDateFormat format, String oldDate, String newDate) {
        try {
            return TimeUnit.DAYS.convert(format.parse(newDate).getTime() - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}

