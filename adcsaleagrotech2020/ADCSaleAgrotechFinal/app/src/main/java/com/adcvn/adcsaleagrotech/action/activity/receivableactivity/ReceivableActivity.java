package com.adcvn.adcsaleagrotech.action.activity.receivableactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.action.activity.ImageActivity;
import com.adcvn.adcsaleagrotech.action.activity.receivableactivity.middleinterface.IReceivableActivityViewModel;
import com.adcvn.adcsaleagrotech.action.activity.receivableactivity.viewmodel.ReceivableActivityViewModel;
import com.adcvn.adcsaleagrotech.common.Common;
import com.adcvn.adcsaleagrotech.common.Constant;
import com.adcvn.adcsaleagrotech.common.SystemDialog;
import com.adcvn.adcsaleagrotech.databinding.ActivityReceivableBinding;
import com.adcvn.adcsaleagrotech.dialog.ErrorMessageDialog;
import com.adcvn.adcsaleagrotech.model.receiptedtransfercustomeraction.ReceiptedTransferCustomerActionResult;
import com.adcvn.adcsaleagrotech.model.receiptrequestaction.ReceiptRequestActionResult;
import com.adcvn.adcsaleagrotech.model.receivableaction.ReceiptedActionResult;
import com.adcvn.adcsaleagrotech.model.receivemoney.receivablelist.ReceivableDetail;

import br.com.sapereaude.maskedEditText.MaskedEditText;

public class ReceivableActivity extends AppCompatActivity {
    private ImageButton ibBack, ibPhoto;
    private TextView tvHeader, tvPayer, tvAddress, tvContactName, tvReason, tvAmount, tvStatus;
    private MaskedEditText mEdContactPhone;
    private EditText edtProceeds;
    private Button btnCollected, btnReceiptRequest, btnTransferCustomer;
    private String key;
    private String edtProceedsValueBefore;
    private long receiptedAmount;
    private ReceivableDetail receivableDetailData;
    private String CREATE_KEY = "Create", DETAIL_KEY = "Detail";
    private int isCurrent;
    private ActivityReceivableBinding binding;
    private IReceivableActivityViewModel iReceivableActivityViewModel;
    private Context context;
    private FragmentManager fm;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.activity = this;
        this.fm = getSupportFragmentManager();
        context = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_receivable);
        binding.setLifecycleOwner(this);
        iReceivableActivityViewModel = ViewModelProviders.of(this).get(ReceivableActivityViewModel.class);
        iReceivableActivityViewModel.setInitApi(context, fm);
        binding.setReceivableActivity(iReceivableActivityViewModel);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        key = bundle.getString("key", "");
        if (key.equals(DETAIL_KEY)) {
//            receiptModel = bundle.getParcelable("receiptDetail");
        } else if (key.equals(CREATE_KEY)) {
            isCurrent = bundle.getInt("isCurrent");
            receivableDetailData = bundle.getParcelable("receivableCreate");
            iReceivableActivityViewModel.setReceivableDetail(receivableDetailData);
        }
        initView();
        initEvent();
        onObserverDataChange(binding);
    }

    private void onObserverDataChange(ActivityReceivableBinding binding) {
        binding.getReceivableActivity().getReceivableDetailData().observe(this, new Observer<ReceivableDetail>() {
            @Override
            public void onChanged(ReceivableDetail receivableDetail) {
                receivableDetailData = receivableDetail;
                tvPayer.setText(receivableDetailData.getReceiverName());
                tvAddress.setText(receivableDetailData.getReceiverAddress());
                tvContactName.setText(receivableDetailData.getContactName());
                mEdContactPhone.setText(receivableDetailData.getContactPhone());
                tvReason.setText(receivableDetailData.getReceiptReason());
                tvAmount.setText(Common.formatNumber(receivableDetailData.getReceiptAbleAmount()));
                receiptedAmount = receivableDetailData.getReceiptAbleAmount();
                edtProceeds.setText(Common.formatNumber(receiptedAmount));
            }
        });

        binding.getReceivableActivity().getReceiptedActionResultData().observe(this, new Observer<ReceiptedActionResult>() {
            @Override
            public void onChanged(ReceiptedActionResult receiptedActionResult) {
                if (receiptedActionResult.getOdataContext() != null) {
                    ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                    Bundle args = new Bundle();
                    args.putString("colorTitle", Constant.TITLE_SUCCESS_DIALOG);
                    args.putString("type", Constant.TYPE_REFRESH_TAB);
                    args.putString("tabActive", "ReceiveMoneyTab");
                    args.putString("subTabActive", "ReceivableSubTab");
                    args.putInt("isCurrent", isCurrent);
                    args.putString("title", context.getResources().getString(R.string.title_notification));
                    args.putString("firstMessageContent", receiptedActionResult.getReceiptedActionDetail().get(0).getMessage());
                    errorMessageDialog.setArguments(args);
                    SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_notification_dialog");
                }
            }
        });

        binding.getReceivableActivity().getReceiptRequestActionResultData().observe(this, new Observer<ReceiptRequestActionResult>() {
            @Override
            public void onChanged(ReceiptRequestActionResult receiptRequestActionResult) {
                if (receiptRequestActionResult.getOdataContext() != null) {
                    ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                    Bundle args = new Bundle();
                    args.putString("colorTitle", Constant.TITLE_SUCCESS_DIALOG);
                    args.putString("type", Constant.TYPE_REFRESH_TAB);
                    args.putString("tabActive", "ReceiveMoneyTab");
                    args.putString("subTabActive", "ReceivableSubTab");
                    args.putInt("isCurrent", isCurrent);
                    args.putString("title", context.getResources().getString(R.string.title_notification));
                    args.putString("firstMessageContent", receiptRequestActionResult.getReceiptRequestActionDetail().get(0).getMessage());
                    errorMessageDialog.setArguments(args);
                    SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_notification_dialog");
                }
            }
        });

        binding.getReceivableActivity().getReceiptedTransferCustomerActiontResultData().observe(this, new Observer<ReceiptedTransferCustomerActionResult>() {
            @Override
            public void onChanged(ReceiptedTransferCustomerActionResult receiptedTransferCustomerActionResult) {
                if (receiptedTransferCustomerActionResult.getOdataContext() != null) {
                    ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                    Bundle args = new Bundle();
                    args.putString("colorTitle", Constant.TITLE_SUCCESS_DIALOG);
                    args.putString("type", Constant.TYPE_REFRESH_TAB);
                    args.putString("tabActive", "ReceiveMoneyTab");
                    args.putString("subTabActive", "ReceivableSubTab");
                    args.putInt("isCurrent", isCurrent);
                    args.putString("title", context.getResources().getString(R.string.title_notification));
                    args.putString("firstMessageContent", receiptedTransferCustomerActionResult.getReceiptedTransferCustomerActionDetail().get(0).getMessage());
                    errorMessageDialog.setArguments(args);
                    SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_notification_dialog");
                }
            }
        });
    }

    private void initEvent() {
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        mở màn hình danh sách hình ảnh/chụp ảnh
        ibPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(ReceivableActivity.this, ImageActivity.class));
            }
        });

        //nhập số tiền thu thực tế
        edtProceeds.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (!edtProceedsValueBefore.equals(s.toString()) && s.toString().length() > 0) {
                    receiptedAmount = s.toString().contains(",") ? Long.valueOf(s.toString().replace(",", "")) : Long.valueOf(s.toString());
                    edtProceeds.setText(Common.formatNumber(receiptedAmount));
                }
                edtProceeds.setSelection(edtProceeds.getText().length());
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                edtProceedsValueBefore = s.toString();
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.toString().length() == 0) {
                    edtProceeds.setSelection(edtProceeds.getText().length());
                }
            }
        });

//        hành động thu tiền
        btnCollected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAction("collected");
            }
        });

//        hành động yêu cầu thu hộ
        btnReceiptRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iReceivableActivityViewModel.callApiReceiptRequestAction();
            }
        });

//        hành động xác nhận khách hàng chuyển khoản
        btnTransferCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAction("transferCustomer");
            }
        });
    }

//    thực hiện thu tiền / khách hàng chuyển khoản
    private void callAction(String type) {
        String strReceiptedAmount = edtProceeds.getText().toString();
        if (edtProceeds.getText().toString().trim().length() > 0) {
            receiptedAmount = strReceiptedAmount.contains(",") ? Long.valueOf(strReceiptedAmount.replace(",", "")) : Long.valueOf(strReceiptedAmount);
        }

        if (edtProceeds.getText().toString().trim().length() > 0) {

            if (type.equals("collected")) {
                iReceivableActivityViewModel.callApiReceiptedAction(receiptedAmount);

            } else {
                iReceivableActivityViewModel.callApiReceiptedTransferCustomerAction(receiptedAmount);
            }

        } else {
            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
            Bundle args = new Bundle();
            args.putString("type", Constant.TYPE_NORMAL);
            args.putString("title", context.getResources().getString(R.string.title_notification));

            if (type.equals("collected")) {
                args.putString("firstMessageContent", context.getResources().getString(R.string.label_title_error_receipt_able_amount_not_accept_in_Collected));

            } else {
                args.putString("firstMessageContent", context.getResources().getString(R.string.label_title_error_receipt_able_amount_not_accept_in_TransferCustomer));
            }
            errorMessageDialog.setArguments(args);
            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "error_validate_input_data_dialog");
        }
    }


    private void initView() {
//        image button trở về
        ibBack = findViewById(R.id.ibBack);
//        image button chụp ảnh/ xem ảnh
        ibPhoto = findViewById(R.id.ibPhoto);
//        text view tiêu đề
        tvHeader = findViewById(R.id.tvHeader);
//        text view người nộp tiền
        tvPayer = findViewById(R.id.tvPayer);
//        text view địa chỉ người nộp tiền
        tvAddress = findViewById(R.id.tvAddress);
//        text view tên người liên hệ
        tvContactName = findViewById(R.id.tvContactName);
//        text view số điện thoại người liên  hệ
        mEdContactPhone = findViewById(R.id.mEdContactPhone);
//        text view lý do thu tiền
        tvReason = findViewById(R.id.tvReason);
//        text view tổng tiền cần thu
        tvAmount = findViewById(R.id.tvAmount);
//        text view số tiền thực thu
        edtProceeds = findViewById(R.id.edtProceeds);
//        button thu
        btnCollected = findViewById(R.id.btnCollected);
//        button yêu cầu thu hộ
        btnReceiptRequest = findViewById(R.id.btnReceiptRequest);
//        button xác nhận khách hàng chuyển khoản
        btnTransferCustomer = findViewById(R.id.btnTransferCustomer);
        btnTransferCustomer.getPaint().setUnderlineText(true);
//        text view trạng thái thu
        tvStatus = findViewById(R.id.tvStatus);

//        show màn hình chi tiết phiếu thu
        if (key.equals(DETAIL_KEY)) {
//            tvHeader.setText(receiptModel.getReceiptNumber().replace("#", ""));
            btnCollected.setVisibility(View.GONE);
            btnReceiptRequest.setVisibility(View.GONE);
            btnTransferCustomer.setVisibility(View.GONE);
            edtProceeds.setEnabled(false);
            edtProceeds.setTextColor(getResources().getColor(R.color.d383F45));
            tvStatus.setVisibility(View.VISIBLE);
//        show màn hình tạo phiếu thu
        } else if (key.equals(CREATE_KEY)) {
            tvHeader.setText(getResources().getString(R.string.label_title_receipt_detail));
            btnCollected.setVisibility(View.VISIBLE);
            btnReceiptRequest.setVisibility(View.VISIBLE);
            btnTransferCustomer.setVisibility(View.VISIBLE);
            edtProceeds.setEnabled(true);
            edtProceeds.setTextColor(getResources().getColor(R.color.d00A850));
            tvStatus.setVisibility(View.GONE);
        }
    }
}