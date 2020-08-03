package com.adcvn.adcsaleagrotech.adapter.receivemoney.receivable;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.adapter.receivemoney.receivable.view_holders.ReceivableHeaderNowViewHodler;
import com.adcvn.adcsaleagrotech.adapter.receivemoney.receivable.view_holders.ReceivableItemViewHodler;
import com.adcvn.adcsaleagrotech.model.receivemoney.receivablelist.ReceivableDetail;
import com.cruxlab.sectionedrecyclerview.lib.SectionManager;

import java.util.ArrayList;
import java.util.List;

//adapter danh sách phải thu
public class ReceivableNowAdapter extends BaseAdapter<ReceivableHeaderNowViewHodler> {

    public ReceivableNowAdapter(Context context, SectionManager sectionManager, boolean isHeaderVisible, boolean isHeaderPinned, List<ReceivableDetail> receivableList, String header, int count) {
        super(context, sectionManager, isHeaderVisible, isHeaderPinned, receivableList, header, count);
        Log.d("LeCo", "ReceivableNowAdapter: " + receivableList.size());
        this.type = 1;
        this.receivableList = receivableList;
        this.count = count;
        this.header = header;
    }


    @Override
    public ReceivableHeaderNowViewHodler onCreateHeaderViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receivabel_header, parent, false);
        return new ReceivableHeaderNowViewHodler(view);
    }

    @Override
    public ReceivableItemViewHodler onCreateItemViewHolder(ViewGroup parent, short type) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receivable, parent, false);
        return new ReceivableItemViewHodler(view);
    }

    @Override
    public BaseAdapter getCopy() {
        ReceivableNowAdapter copy = new ReceivableNowAdapter(context, sectionManager, isHeaderVisible(), isHeaderPinned(), receivableList, header, count);
        copy.receivableList = new ArrayList<>(receivableList);
        return copy;
    }

    @Override
    public BaseAdapter getNext() {
        return new ReceivableNowAdapter(context, sectionManager, isHeaderVisible(), isHeaderPinned(), receivableList, header, count);
    }


//    private int isCurrent;
//    private Context context;
//    private List<ReceivableDetail> modelList;
//    private String CREATE_KEY = "Create";
//    private FragmentManager fm;
//
//    public ReceivableAdapter(Context context, List<ReceivableDetail> modelList, FragmentManager fm) {
//        this.context = context;
//        this.modelList = modelList;
//        this.fm = fm;
//    }
//
//    @NonNull
//    @Override
//    public ReceivableAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.item_receivable, parent, false);
//        return new RecyclerViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ReceivableAdapter.RecyclerViewHolder holder, int position) {
//        final ReceivableDetail modelItem = modelList.get(position);
//        holder.tvCompanyName.setText(modelItem.getCompany());
//
//        //        if (modelItem.getNumberDebitDayFuture() != null && modelItem.getNumberDebitDayFuture() > 0) {
////            holder.tvStatus.setText("Còn " + modelItem.getNumberDebitDayFuture() + " ngày");
////            holder.imgStatusColor.setImageResource(R.drawable.ic_ovalgreen);
////            holder.tvStatus.setTextColor(Color.parseColor("#00A850"));
////            isCurrent = 0;
////        } else if (modelItem.getNumberDebitDayCurrent() != null) {
////            if (modelItem.getNumberDebitDayCurrent() == 0) {
////                holder.tvStatus.setText("Đến hạn");
////                holder.imgStatusColor.setImageResource(R.drawable.ic_ovalorange);
////                holder.tvStatus.setTextColor(Color.parseColor("#FFAB36"));
////            } else {
////                holder.tvStatus.setText("Quá hạn " + modelItem.getNumberDebitDayCurrent() + " ngày");
////                holder.imgStatusColor.setImageResource(R.drawable.ic_ovalred);
////                holder.tvStatus.setTextColor(Color.parseColor("#EF1C23"));
////            }
////            isCurrent = 1;
////        }
//
//        Calendar c = Calendar.getInstance();
//        String time = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
//        int duration = (int) getDateDiff(new SimpleDateFormat("yyyy-MM-dd"), time, modelItem.getDueDay().substring(0, 10));
//        if (duration > 0) {
//            holder.tvStatus.setText("Còn " + duration + " ngày");
//            holder.imgStatusColor.setImageResource(R.drawable.ic_ovalgreen);
//            holder.tvStatus.setTextColor(Color.parseColor("#00A850"));
//            isCurrent = 0;
//        } else if (duration == 0) {
//            holder.tvStatus.setText("Đến hạn");
//            holder.imgStatusColor.setImageResource(R.drawable.ic_ovalorange);
//            holder.tvStatus.setTextColor(Color.parseColor("#FFAB36"));
//            isCurrent = 1;
//        } else {
//            holder.tvStatus.setText("Quá hạn " + Math.abs(duration) + " ngày");
//            holder.imgStatusColor.setImageResource(R.drawable.ic_ovalred);
//            holder.tvStatus.setTextColor(Color.parseColor("#EF1C23"));
//            isCurrent = 1;
//        }
//
//        holder.tvTotalReceivable.setText(Common.formatNumber(modelItem.getReceiptAbleAmount()));
//
//        holder.lyReceivableChild.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, ReceivableActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("isCurrent", isCurrent);
//                bundle.putString("key", CREATE_KEY);
//                bundle.putParcelable("receivableCreate", modelItem);
//                intent.putExtras(bundle);
//                context.startActivity(intent);
//            }
//        });
//    }
//
//    public static long getDateDiff(SimpleDateFormat format, String oldDate, String newDate) {
//        try {
//            return TimeUnit.DAYS.convert(format.parse(newDate).getTime() - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return modelList.size();
//    }
//
//    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
//        CircleImageView imgStatusColor;
//        TextView tvCompanyName, tvStatus, tvTotalReceivable;
//        LinearLayout lyReceivableChild;
//
//        public RecyclerViewHolder(@NonNull View itemView) {
//            super(itemView);
//            lyReceivableChild = itemView.findViewById(R.id.lyReceivableChild);
//            imgStatusColor = itemView.findViewById(R.id.imgStatusColor);
//            tvCompanyName = itemView.findViewById(R.id.tvCompanyName);
//            tvStatus = itemView.findViewById(R.id.tvStatus);
//            tvTotalReceivable = itemView.findViewById(R.id.tvTotalReceivable);
//        }
//    }
}
