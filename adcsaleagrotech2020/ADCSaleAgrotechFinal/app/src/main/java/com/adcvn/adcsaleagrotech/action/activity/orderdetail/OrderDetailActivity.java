package com.adcvn.adcsaleagrotech.action.activity.orderdetail;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.action.activity.BaseActivity;
import com.adcvn.adcsaleagrotech.action.activity.history.HistoryActivity;
import com.adcvn.adcsaleagrotech.action.activity.orderdetail.middleinterface.IOrderDetailViewModel;
import com.adcvn.adcsaleagrotech.action.activity.orderdetail.viewmodel.OrderDetailViewModel;
import com.adcvn.adcsaleagrotech.adapter.orderdetail.OrderDetailProductAdapter;
import com.adcvn.adcsaleagrotech.common.Common;
import com.adcvn.adcsaleagrotech.common.SystemDateTime;
import com.adcvn.adcsaleagrotech.common.SystemPopupWindow;
import com.adcvn.adcsaleagrotech.databinding.ActivityOrderDetailBinding;
import com.adcvn.adcsaleagrotech.model.salesorder.SalesOrder;
import com.adcvn.adcsaleagrotech.model.salesorder.SalesOrderDetail;
import java.util.ArrayList;
import java.util.List;
import br.com.sapereaude.maskedEditText.MaskedEditText;

public class OrderDetailActivity extends BaseActivity {
    private ActivityOrderDetailBinding activityOrderDetailBinding;
    private IOrderDetailViewModel iOrderDetailViewModel;
    private TextView tvTitleOrderDetail, tvNameEmployee, tvCustomerName, tvAddressCustomer, tvContactName, tvDeliveryDate, tvPaymentTerName, tvIsReceiptDelivery, tvTotalAmountSalesOrder, tvDueDateOrderDetail, tvStatusOrderDetail;
    private MaskedEditText mEdContactPhone;
    private LinearLayout lyMenu;
    private ImageButton ibBack, ibMenu;
    private View viewMenu;
    private PopupWindow menu;
    private Button btnDiary, btnCancel, btnDelete;
    private RecyclerView rvProductOrder;
    private OrderDetailProductAdapter orderDetailProductAdapter;
    private List<SalesOrderDetail> productOrderList = new ArrayList<SalesOrderDetail >();
    private SalesOrder salesOrder;
    private final String AWAITING_PAYMENT = "Awaiting Payment", PAYMENT_TED = "Paymented", PAYMENT = "Payment";
    private Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        salesOrder = getIntent().getParcelableExtra("salesOrder");
        activityOrderDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_order_detail);
        activityOrderDetailBinding.setLifecycleOwner(this);
        iOrderDetailViewModel = ViewModelProviders.of(this).get(OrderDetailViewModel.class);
        iOrderDetailViewModel.setSalesOrder(salesOrder);
        activityOrderDetailBinding.setOrderDetail(iOrderDetailViewModel);
        initView();
        initEvent();
        onObserverDataChange(activityOrderDetailBinding);
    }

    // khởi tạo giao diện màn hình
    private void initView() {
        ibBack = findViewById(R.id.ibBack);
        tvTitleOrderDetail = findViewById(R.id.tvTitleOrderDetail);
        lyMenu = findViewById(R.id.lyMenu);
        ibMenu = findViewById(R.id.ibMenu);
        viewMenu = LayoutInflater.from(OrderDetailActivity.this).inflate(R.layout.popup_window_menu_more, null, false);
        btnDiary = viewMenu.findViewById(R.id.btnDiary);
        btnCancel = viewMenu.findViewById(R.id.btnCancel);
        btnDelete = viewMenu.findViewById(R.id.btnDelete);
        tvNameEmployee = findViewById(R.id.tvNameEmployee);
        tvCustomerName = findViewById(R.id.tvCustomerName);
        tvAddressCustomer = findViewById(R.id.tvAddressCustomer);
        tvContactName = findViewById(R.id.tvContactName);
        mEdContactPhone = findViewById(R.id.mEdContactPhone);
        tvDeliveryDate = findViewById(R.id.tvDeliveryDate);
        tvPaymentTerName = findViewById(R.id.tvPaymentTermName);
        tvIsReceiptDelivery = findViewById(R.id.tvIsReceiptDelivery);
        rvProductOrder = findViewById(R.id.rvProductOrder);
        rvProductOrder.setLayoutManager(new LinearLayoutManager(this));
        tvTotalAmountSalesOrder = findViewById(R.id.tvTotalAmountSalesOrder);
        tvDueDateOrderDetail = findViewById(R.id.tvDueDateOrderDetail);
        tvStatusOrderDetail = findViewById(R.id.tvStatusOrderDetail);
    }

    // khởi tạo sự kiện giao diện màn hình
    private void initEvent() {

        // ImageButton thoát
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //ImageButton menu phải
        ibMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu = new PopupWindow(viewMenu,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        true);
                SystemPopupWindow.showPopupWindow(OrderDetailActivity.this, menu, lyMenu, 1);
            }
        });

        btnDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, HistoryActivity.class));
                menu.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.dismiss();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.dismiss();
            }
        });

    }
    // cập nhật dữ liệu thay đổi vào view
    private void onObserverDataChange(ActivityOrderDetailBinding binding) {
        //nhận dữ liệu thông tin chi tiết đơn hàng
        binding.getOrderDetail().salesOrderData().observe(this, new Observer<SalesOrder>() {
            @Override
            public void onChanged(SalesOrder salesOrder) {
                tvTitleOrderDetail.setText(salesOrder.getRefNo());
                tvNameEmployee.setText(salesOrder.getAssigneeName());
                tvCustomerName.setText(salesOrder.getCustomerName());
                tvAddressCustomer.setText(salesOrder.getDeliveryAddress());
                tvContactName.setText(salesOrder.getContactName());
                mEdContactPhone.setText(salesOrder.getContactPhone());
                tvDeliveryDate.setText(SystemDateTime.formatDateTimeZone(salesOrder.getDeliveryDate()));
                tvPaymentTerName.setText(salesOrder.getPaymentTermName());
                setStatusReceiptDelivery(salesOrder.getIsReceiptDelivery());
                productOrderList.addAll(salesOrder.getSalesOrderDetail());
                orderDetailProductAdapter = new OrderDetailProductAdapter(context,productOrderList);
                rvProductOrder.setAdapter(orderDetailProductAdapter);
                tvTotalAmountSalesOrder.setText(Common.formatNumber(salesOrder.getAmount()));
                if(salesOrder.getDueDate() != null) {
                    tvDueDateOrderDetail.setText(SystemDateTime.formatDateToClient(salesOrder.getDueDate()));
                }
                if(salesOrder.getReceiptStatusCode() != null) {
                    if (salesOrder.getReceiptStatusCode().equals(AWAITING_PAYMENT)) {
                        tvStatusOrderDetail.setTextColor(getResources().getColor(R.color.dEF1C23));
                        tvStatusOrderDetail.setVisibility(View.VISIBLE);
                        tvStatusOrderDetail.setText(salesOrder.getReceiptStatusName());
                    } else if (salesOrder.getReceiptStatusCode().equals(PAYMENT_TED)) {
                        tvStatusOrderDetail.setTextColor(getResources().getColor(R.color.d00A850));
                        tvStatusOrderDetail.setVisibility(View.VISIBLE);
                        tvStatusOrderDetail.setText(salesOrder.getReceiptStatusName());
                    } else if (salesOrder.getReceiptStatusCode().equals(PAYMENT)) {
                        tvStatusOrderDetail.setTextColor(getResources().getColor(R.color.dFFAB36));
                        tvStatusOrderDetail.setVisibility(View.VISIBLE);
                        tvStatusOrderDetail.setText(salesOrder.getReceiptStatusName());
                    }
                }
            }
        });
     }

    // set trạng thái giao hàng thu tiền
    private void setStatusReceiptDelivery(int isReceiptDelivery){
        if(isReceiptDelivery == 1) {
            tvIsReceiptDelivery.setBackground(getResources().getDrawable(R.drawable.ic_check_box_active));
        }else if(isReceiptDelivery == 0){
            tvIsReceiptDelivery.setBackground(getResources().getDrawable(R.drawable.ic_check_box));
        }
    }

}
