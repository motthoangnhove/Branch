package com.adcvn.adcsaleagrotech.action.activity.debtcustomerdetail;

import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.action.activity.BaseActivity;
import com.adcvn.adcsaleagrotech.action.activity.debtcustomerdetail.middleinterface.IDebtCustomerDetail;
import com.adcvn.adcsaleagrotech.action.activity.debtcustomerdetail.viewmodel.DebtCustomerDetail;
import com.adcvn.adcsaleagrotech.databinding.ActivityDebtCustomerDetailBinding;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

// màn hình chi tiết công nợ khách hàng
public class DebtCustomerDetailActivity extends BaseActivity {
    // khởi tạo live data và observer màn hình
    private ActivityDebtCustomerDetailBinding binding;
    private IDebtCustomerDetail iViewModel;
    // khai báo nút thoát màn hình
    private ImageButton ibBack;
    // khai báo các tab
    private TextView tvLabelTabInfoDebtCustomer, tvLabelTabDebtCustomer, tvLabelTabContractDebtCustomer;
    // khai báo các biến tạm
    private int typeTabActive = 1;
    //khởi tạo các thành phần context, fragment manager
    private Context context;
    private FragmentManager fm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        fm = getSupportFragmentManager();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_debt_customer_detail);
        binding.setLifecycleOwner(this);
        iViewModel = ViewModelProviders.of(this).get(DebtCustomerDetail.class);
        iViewModel.setInitApi(context, fm);
        binding.setDebtCustomerDetail(iViewModel);
        initView();
        initEvent();
    }

    // khởi tạo giao diện màn hình
    private void initView(){
        // ImageButton thoát màn hình
        ibBack = findViewById(R.id.ibBack);
        // tab Thông tin
        tvLabelTabInfoDebtCustomer = findViewById(R.id.tvLabelTabInfoDebtCustomer);
        // tab Công nợ
        tvLabelTabDebtCustomer = findViewById(R.id.tvLabelTabDebtCustomer);
        // tab Hợp đồng
        tvLabelTabContractDebtCustomer = findViewById(R.id.tvLabelTabContractDebtCustomer);
    }

    // khởi tạo sự kiện giao diện màn hình
    private void initEvent() {

        // sự kiện nút thoát màn hình
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             finish();
            }
        });

        // sự kiện bấm tab Thông tin
        tvLabelTabInfoDebtCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeTabActive = 1;
                setTabActive(1);
            }
        });

        // sự kiện bấm tab Công nợ
        tvLabelTabDebtCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeTabActive = 2;
                setTabActive(2);
            }
        });

        // sự kiện bấm tab Hợp đồng
        tvLabelTabContractDebtCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeTabActive = 3;
                setTabActive(3);
            }
        });

    }

    // thay đổi màu sắc các tab
    private void setTabActive(int type){
        if (type == 1) {
            tvLabelTabInfoDebtCustomer.setBackground(getResources().getDrawable(R.drawable.ic_active_tab_debt_customer_detail));
            tvLabelTabDebtCustomer.setBackground(getResources().getDrawable(R.drawable.ic_tab_debt_customer_detail));
            tvLabelTabContractDebtCustomer.setBackground(getResources().getDrawable(R.drawable.ic_tab_debt_customer_detail));
        } else if (type == 2) {
            tvLabelTabInfoDebtCustomer.setBackground(getResources().getDrawable(R.drawable.ic_tab_debt_customer_detail));
            tvLabelTabDebtCustomer.setBackground(getResources().getDrawable(R.drawable.ic_active_tab_debt_customer_detail));
            tvLabelTabContractDebtCustomer.setBackground(getResources().getDrawable(R.drawable.ic_tab_debt_customer_detail));
        } else if (type == 3) {
            tvLabelTabInfoDebtCustomer.setBackground(getResources().getDrawable(R.drawable.ic_tab_debt_customer_detail));
            tvLabelTabDebtCustomer.setBackground(getResources().getDrawable(R.drawable.ic_tab_debt_customer_detail));
            tvLabelTabContractDebtCustomer.setBackground(getResources().getDrawable(R.drawable.ic_active_tab_debt_customer_detail));
        }
    }
}