package com.adcvn.adcsaleagrotech.action.activity.notification;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.action.activity.BaseActivity;
import com.adcvn.adcsaleagrotech.adapter.notification.NotificationListAdapter;
import com.adcvn.adcsaleagrotech.model.notification.NotificationDetail;
import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends BaseActivity {
    private ImageButton ibExit;
    private TextView tvTotalNotification;
    private RecyclerView rvNotification;
    private NotificationListAdapter rvNotificationAdapter;
    private List<NotificationDetail> notificationList = new ArrayList<NotificationDetail>();
    private Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_notification);
        initView();
        initEvent();
    }

    // khởi tạo giao diện màn hình
    private void initView(){
        ibExit = findViewById(R.id.ibExit);
        NotificationDetail nc1 = new NotificationDetail("Khuyến mãi đầu vụ","Gạo tháng 01/2020 NV Văn Phòng 93 & Khu vực Tây Nguyên","http://www.africau.edu/images/default/sample.pdf",1);
        NotificationDetail nc2 = new NotificationDetail("Thông tin chương trình giảm giá","Gạo tháng 01/2020 NV Văn Phòng 93 & Khu vực ĐBSCL","http://www.pdf995.com/samples/pdf.pdf",0);
        NotificationDetail nc3 = new NotificationDetail("Khuyến mãi đầu vụ","Gạo tháng 01/2020 NV Văn Phòng 93 & Khu vực Tây Nguyên","https://www.adobe.com/support/products/enterprise/knowledgecenter/media/c4611_sample_explain.pdf",0);
        NotificationDetail nc4 = new NotificationDetail("Lịch nghỉ lễ nhân viên","Gạo tháng 01/2020 NV Văn Phòng 93 & Khu vực Bắc Trung Bộ","http://www.africau.edu/images/default/sample.pdf",1);
        NotificationDetail nc5 = new NotificationDetail("Thông tin chương trình giới thiệu sản phẩm mới","Gạo tháng 01/2020 NV Văn Phòng 93 & Khu vực ĐBSCL","http://www.pdf995.com/samples/pdf.pdf",1);
        NotificationDetail nc6 = new NotificationDetail("Thông báo khai chương chi nhánh mới","Gạo tháng 01/2020 NV Văn Phòng 93 & Khu vực Duyên Hải Nam Trung Bộ","https://www.adobe.com/support/products/enterprise/knowledgecenter/media/c4611_sample_explain.pdf",0);
        NotificationDetail nc7 = new NotificationDetail("Chương trình học bổng","Học bổng thắp sáng niềm tin","http://www.africau.edu/images/default/sample.pdf",0);
        NotificationDetail nc8 = new NotificationDetail("Thông tin Chương trình quay số trúng thưởng","Chương trình quay số trúng thưởng tháng 10","http://www.pdf995.com/samples/pdf.pdf",0);
        NotificationDetail nc9 = new NotificationDetail("Chương trình thần tài gõ cửa","Thông tin chương trình Thần tài gõ cửa tháng 08/2020","https://www.adobe.com/support/products/enterprise/knowledgecenter/media/c4611_sample_explain.pdf",1);
        notificationList.add(nc1);
        notificationList.add(nc2);
        notificationList.add(nc3);
        notificationList.add(nc4);
        notificationList.add(nc5);
        notificationList.add(nc6);
        notificationList.add(nc7);
        notificationList.add(nc8);
        notificationList.add(nc9);
        rvNotification =  findViewById(R.id.rvNotification);
        rvNotification.setLayoutManager(new LinearLayoutManager(context));
        rvNotificationAdapter = new NotificationListAdapter(context,notificationList);
        rvNotification.setAdapter(rvNotificationAdapter);
    }

    //khởi tạo sự kiện giao diện
    private void initEvent(){
        ibExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
    }
}
