package com.adcvn.adcsaleagrotech.dialog;

import androidx.fragment.app.DialogFragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.common.Common;
import com.adcvn.adcsaleagrotech.model.misacustomerdebtcheck.MisaCustomerDebtDetail;

public class DebtInfoDialog extends DialogFragment {
    // Button thoát
    private Button btnExit;
    private TextView tvMaximizeDebtAmount, tvDebitAmount, tvOverdueAmount, tvInprocessAmount, tvRemainingAmount, tvDebitStatusNormal, tvDebitStatusDebt;
    private MisaCustomerDebtDetail debtCustomerDetail;
    public DebtInfoDialog() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.debt_info_dialog, container, false);
        debtCustomerDetail = getArguments().getParcelable("misaCustomerDebtDetail");
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            getDialog().setCanceledOnTouchOutside(false);
        }
        initView(view);
        initEvent();
        initData();
        return view;
    }

    // khởi tạo giao diện màn hình
    private void initView(View view) {
        //Button thoát
        btnExit = view.findViewById(R.id.btnExit);
        tvMaximizeDebtAmount = view.findViewById(R.id.tvMaximizeDebtAmount);
        tvDebitAmount = view.findViewById(R.id.tvDebitAmount);
        tvOverdueAmount = view.findViewById(R.id.tvOverdueAmount);
        tvInprocessAmount = view.findViewById(R.id.tvInprocessAmount);
        tvRemainingAmount = view.findViewById(R.id.tvRemainingAmount);
        tvDebitStatusNormal = view.findViewById(R.id.tvDebitStatusNormal);
        tvDebitStatusDebt = view.findViewById(R.id.tvDebitStatusDebt);
    }

    // khởi tạo sự kiện giao diện màn hình
    private void initEvent(){
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
    }

    private void initData(){
          tvMaximizeDebtAmount.setText(Common.formatNumber(debtCustomerDetail.getMaximizeDebtAmount()));
          tvDebitAmount.setText(Common.formatNumber(debtCustomerDetail.getDebitAmount()));
          tvOverdueAmount.setText(Common.formatNumber(debtCustomerDetail.getOverdueAmount()));
          tvInprocessAmount.setText(Common.formatNumber(debtCustomerDetail.getInprocessAmount()));
          tvRemainingAmount.setText(Common.formatNumber(debtCustomerDetail.getRemainingAmount()));
          if(debtCustomerDetail.getDebitFlag() == 0){
              tvDebitStatusNormal.setVisibility(View.VISIBLE);
              tvDebitStatusNormal.setText(debtCustomerDetail.getDebitStatus());
          }else{
              tvDebitStatusDebt.setVisibility(View.VISIBLE);
              tvDebitStatusNormal.setText(debtCustomerDetail.getDebitStatus());
          }
    }
}