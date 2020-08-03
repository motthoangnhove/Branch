package com.adcvn.adcsaleagrotech.dialog;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.action.activity.login.LoginActivity;
import com.adcvn.adcsaleagrotech.common.Constant;

public class ErrorMessageDialog extends DialogFragment {
    private String type, title, colorTitle, firstMessageContent, secondMessageContent;
    private TextView tvTitleErrorMessageDialog, tvContentFirstErrorMessageDialog, tvContentSecondErrorMessageDialog;
    private Button btnExitDialog;
    private Context context;

    public ErrorMessageDialog() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.error_message_login_dialog, container, false);
        context = getActivity();
        colorTitle = getArguments().getString("colorTitle", "");
        type = getArguments().getString("type", "");
        title = getArguments().getString("title", "");
        firstMessageContent = getArguments().getString("firstMessageContent", "");
        secondMessageContent = getArguments().getString("secondMessageContent", "");
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            getDialog().setCanceledOnTouchOutside(false);
        }
        initView(view);
        initEvent();
        return view;
    }

    private void initView(View view) {
        tvTitleErrorMessageDialog = view.findViewById(R.id.tvTitleErrorMessageDialog);
        tvTitleErrorMessageDialog.setText(title);
        if (colorTitle.equals(Constant.TITLE_SUCCESS_DIALOG)) {
            tvTitleErrorMessageDialog.setTextColor(getResources().getColor(R.color.d00A850));
        }
        tvContentFirstErrorMessageDialog = view.findViewById(R.id.tvContentFirstErrorMessageDialog);
        tvContentFirstErrorMessageDialog.setText(firstMessageContent);
        tvContentSecondErrorMessageDialog = view.findViewById(R.id.tvContentSecondErrorMessageDialog);
        tvContentSecondErrorMessageDialog.setText(secondMessageContent);
        btnExitDialog = view.findViewById(R.id.btnExitMessageErrorDialog);
    }

    private void initEvent() {
        btnExitDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals(Constant.TYPE_NOT_SESSION)) {
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                } else if (type.equals(Constant.TYPE_REFRESH_TAB)) {
                    // gọi sự kiện tải lại dữ liệu danh sách màn hình thu tiền
                    Intent intent = new Intent("UPDATE_DATA_TAB");
                    String tabActive = getArguments().getString("tabActive", "");
                    if (tabActive.equals("ReceiveMoneyTab")) {
                        intent.putExtra("tabActive", tabActive);
                        String subTabActive = getArguments().getString("subTabActive", "");
                        intent.putExtra("subTabActive", subTabActive);
                        if (subTabActive.equals("ReceivableSubTab")) {
                            int isCurrent = getArguments().getInt("isCurrent");
                            intent.putExtra("isCurrent", isCurrent);
                        }
                    }
                    intent.putExtra("refreshDataReceivable", false);
                    context.sendBroadcast(intent);
                    // gọi sự kiện tải lại dữ liệu danh sách màn hình bán hàng
                    Intent intentBroadcastSaleOrderFragment = new Intent("REFRESH_ALL_SALE_ORDER_TAB");
                    tabActive = getArguments().getString("tabActive", "");
                    if (tabActive.equals("SaleOrderTab")) {
                        String refreshSaleOrderList = getArguments().getString("refreshSaleOrderList", "");
                        intent.putExtra("refreshSaleOrderList",refreshSaleOrderList);
                        context.sendBroadcast(intentBroadcastSaleOrderFragment);
                    }
                    getActivity().finish();
                } else {
                    getDialog().dismiss();
                }
            }
        });
    }
}
