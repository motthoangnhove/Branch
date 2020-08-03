package com.adcvn.adcsaleagrotech.action.activity.history;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.action.activity.BaseActivity;
import com.adcvn.adcsaleagrotech.adapter.history.HistoryAdapter;
import com.adcvn.adcsaleagrotech.model.history.History;
import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends BaseActivity {
    private ImageButton ibClose;
    private RecyclerView rvHistory;
    private List<History> listHistory = new ArrayList<>();
    private HistoryAdapter historyAdapter;
    private Context context;
    private int skip;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        this.context = this;
        initView();
        initEvent();
    }
    private void initView(){
        ibClose = findViewById(R.id.ibClose);
        rvHistory = findViewById(R.id.rvHistory);
        History h1 = new History("Nguyễn Trung Quân","2020-05-30T11:39:18.0000+07:00","Đặt hàng", "Giao hàng trong buổi sáng");
        History h2 = new History("Nguyễn Trung Quân","2020-05-30T11:39:18.0000+07:00","Đặt hàng", "Giao hàng trong buổi sáng");
        History h3 = new History("Nguyễn Trung Quân","2020-05-30T11:39:18.0000+07:00","Đặt hàng", "Giao hàng trong buổi sáng");
        History h4 = new History("Nguyễn Trung Quân","2020-05-30T11:39:18.0000+07:00","Đặt hàng", "Giao hàng trong buổi sáng");
        History h5 = new History("Nguyễn Trung Quân","2020-05-30T11:39:18.0000+07:00","Đặt hàng", "Giao hàng trong buổi sáng");
        listHistory.add(h1);
        listHistory.add(h2);
        listHistory.add(h3);
        listHistory.add(h4);
        listHistory.add(h5);
        LinearLayoutManager linearLayoutRecycleView = new LinearLayoutManager(this);
        rvHistory.setLayoutManager(linearLayoutRecycleView);
        historyAdapter = new HistoryAdapter(context, listHistory);
        rvHistory.setAdapter(historyAdapter);
    }
    private void initEvent() {
        ibClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
