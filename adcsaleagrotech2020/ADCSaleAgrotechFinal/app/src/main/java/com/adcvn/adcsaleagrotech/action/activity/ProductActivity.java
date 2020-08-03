package com.adcvn.adcsaleagrotech.action.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.adcvn.adcsaleagrotech.R;

// màn hình chi tiết sản phẩm
public class ProductActivity extends BaseActivity {
    //ImageButton thoát
    private ImageButton ibBack;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        initView();
        initEvent();
    }

    // khởi tạo giao diện màn hình
    private void initView(){
        ibBack = findViewById(R.id.ibBack);
    }

    // khởi tạo sự kiện giao diện
    private void initEvent(){
      ibBack.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              finish();
          }
      });
    }
}