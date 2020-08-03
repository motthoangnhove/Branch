package com.adcvn.adcsaleagrotech.action.activity.verifyotp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.action.activity.BaseActivity;
import com.adcvn.adcsaleagrotech.action.activity.dashboard.MainActivity;
import com.adcvn.adcsaleagrotech.action.activity.verifyotp.model.VerifyOtpModel;
import com.adcvn.adcsaleagrotech.action.activity.verifyotp.viewmodel.VerifyOtpViewModel;
import com.adcvn.adcsaleagrotech.action.activity.verifyotp.middleinterface.IVerifyOtpViewModel;
import com.adcvn.adcsaleagrotech.application.ADCSaleAgrotechApplication;
import com.adcvn.adcsaleagrotech.common.Constant;
import com.adcvn.adcsaleagrotech.common.SystemDialog;
import com.adcvn.adcsaleagrotech.databinding.ActivityVerifyOtpBinding;
import com.adcvn.adcsaleagrotech.dialog.ErrorMessageDialog;
import com.adcvn.adcsaleagrotech.model.applogin.AppLoginPost;
import com.adcvn.adcsaleagrotech.model.applogin.AppLoginResult;
import com.adcvn.adcsaleagrotech.model.verifyopt.VerifyOtpResult;
import com.adcvn.adcsaleagrotech.model.verifyopt.VerifyOtpResultDetail;

public class VerifyOtpActivity extends BaseActivity {
    private Activity activity;
    private Context context;
    private FragmentManager fm;
    private ActivityVerifyOtpBinding binding;
    private IVerifyOtpViewModel iVerifyOtpViewModel;
    private VerifyOtpModel verifyOtpModel;
    private String androidPushId, phoneNumber, authenticationId;
    private ImageView ivClose;
    private TextView tvPhoneNumber;
    private EditText editTextCode;
    private Button btnVerifyCode;

    public VerifyOtpActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getSupportFragmentManager();
        activity = this;
        context = this;
        Intent intent = getIntent();
        androidPushId = intent.getStringExtra("AndroidPushId");
        authenticationId = intent.getStringExtra("AuthenticationId");
        phoneNumber = intent.getStringExtra("PhoneNumber");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_verify_otp);
        binding.setLifecycleOwner(this);
        iVerifyOtpViewModel = ViewModelProviders.of(this).get(VerifyOtpViewModel.class);
        iVerifyOtpViewModel.setInitContext(context, fm);
        iVerifyOtpViewModel.setAuthenticationId(authenticationId);
        binding.setVerifyOtp(iVerifyOtpViewModel);
        initView();
        initEvent();
        onObserverDataChange(binding);
    }

    private void onObserverDataChange(ActivityVerifyOtpBinding binding) {

        binding.getVerifyOtp().getVerifyOtpModelMutableLiveData().observe(this, new Observer<VerifyOtpModel>() {
            @Override
            public void onChanged(VerifyOtpModel model) {
                verifyOtpModel = model;
            }
        });

        binding.getVerifyOtp().getVerifyOtpResultMutableLiveData().observe(this, new Observer<VerifyOtpResult>() {
            @Override
            public void onChanged(VerifyOtpResult verifyOtpResult) {
                if (verifyOtpResult != null && verifyOtpResult.getValue().size() > 0) {
                    ((ADCSaleAgrotechApplication) context.getApplicationContext()).setUserId(verifyOtpResult.getValue().get(0).getUserId());
                    VerifyOtpResultDetail resultDetail = verifyOtpResult.getValue().get(0);
                    if (resultDetail.getUserId() != null && resultDetail.getIsExpire() == 0) {
                        AppLoginPost appLoginPost = new AppLoginPost(resultDetail.getUserId(), androidPushId);
                        iVerifyOtpViewModel.callApiAppLogin(appLoginPost);
                    } else if (resultDetail.getUserId() == null && resultDetail.getIsExpire() == 1) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NOT_SESSION);
                        args.putString("title", context.getResources().getString(R.string.title_not_session));
                        args.putString("firstMessageContent", resultDetail.getMessage());
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_error_access_system_dialog");
                    } else if (resultDetail.getUserId() == null && resultDetail.getIsExpire() == 0) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.title_wrong_otp));
                        args.putString("firstMessageContent", resultDetail.getMessage());
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_error_access_system_dialog");
                    }
                }
            }
        });

        //nhận dữ liệu lấy thông tin nhân viên giao hàng
        binding.getVerifyOtp().getAppLoginResultData().observe(this, new Observer<AppLoginResult>() {
            @Override
            public void onChanged(@Nullable AppLoginResult appLoginResultData) {
                if (appLoginResultData.getOdataContext() != null) {
                    ADCSaleAgrotechApplication adcTransportApplication = (ADCSaleAgrotechApplication) context.getApplicationContext();
                    adcTransportApplication.setAppLoginDetail(appLoginResultData.getAppLoginDetail().get(0));
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(context, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 1500);
                }
            }
        });
    }

    private void initView() {
        ivClose = findViewById(R.id.ivClose);
        tvPhoneNumber = findViewById(R.id.tvPhoneNumber);
        editTextCode = findViewById(R.id.editTextCode);
        btnVerifyCode = findViewById(R.id.btnVerifyCode);
        tvPhoneNumber.setText("Đã gửi đến " + phoneNumber);
    }

    private void initEvent() {
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iVerifyOtpViewModel.setVerifyCode(editTextCode.getText().toString());
                iVerifyOtpViewModel.callApiVerifyOtp();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(mMessageReceiver, new IntentFilter("SMS_INTENT"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mMessageReceiver);
    }

    // xử lý nhân tin nhắn sms
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String smsContent = intent.getStringExtra("SMS_CONTENT");
            editTextCode.setText(smsContent);
        }
    };
}

