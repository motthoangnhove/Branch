package com.adcvn.adcsaleagrotech.action.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.adcvn.adcsaleagrotech.R;

public class DeleteNotificationActivity extends BaseActivity {
    private ImageButton ibBlack;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_notification);
        initView();
        initEvent();
    }

    // khởi tạo giao diện
    private void initView(){
        ibBlack = findViewById(R.id.ibBack);
    }

    // khởi tạo sự kiện giao diện
    private void initEvent(){
        ibBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              finish();
            }
        });

    }
}