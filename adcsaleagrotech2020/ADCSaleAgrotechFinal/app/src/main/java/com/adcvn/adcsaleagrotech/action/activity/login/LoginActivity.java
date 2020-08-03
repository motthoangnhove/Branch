package com.adcvn.adcsaleagrotech.action.activity.login;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.action.activity.BaseActivity;
import com.adcvn.adcsaleagrotech.action.activity.dashboard.MainActivity;
import com.adcvn.adcsaleagrotech.action.activity.login.middleinterface.ILoginViewModel;
import com.adcvn.adcsaleagrotech.action.activity.login.model.UserLogin;
import com.adcvn.adcsaleagrotech.action.activity.login.viewmodel.LoginViewModel;
import com.adcvn.adcsaleagrotech.action.activity.verifyotp.VerifyOtpActivity;
import com.adcvn.adcsaleagrotech.application.ADCSaleAgrotechApplication;
import com.adcvn.adcsaleagrotech.checkversionremote.GooglePlayAppVersion;
import com.adcvn.adcsaleagrotech.common.Cache;
import com.adcvn.adcsaleagrotech.common.Common;
import com.adcvn.adcsaleagrotech.common.Constant;
import com.adcvn.adcsaleagrotech.common.SystemDevice;
import com.adcvn.adcsaleagrotech.common.SystemDialog;
import com.adcvn.adcsaleagrotech.databinding.ActivityLoginBinding;
import com.adcvn.adcsaleagrotech.dialog.ErrorMessageDialog;
import com.adcvn.adcsaleagrotech.model.applogin.AppLoginPost;
import com.adcvn.adcsaleagrotech.model.applogin.AppLoginResult;
import com.adcvn.adcsaleagrotech.model.login.LoginDetail;
import com.adcvn.adcsaleagrotech.model.login.LoginResult;
import com.adcvn.adcsaleagrotech.receivesms.AppSignatureHashHelper;
import com.adcvn.adcsaleagrotech.receivesms.SMSReceiver;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

// màn hình đăng nhập hệ thống
public class LoginActivity extends BaseActivity implements SMSReceiver.OTPReceiveListener {
    private SMSReceiver smsReceiver;
    private ActivityLoginBinding binding;
    private ILoginViewModel iLoginViewModel;
    private UserLogin userLogin;
    private EditText edtVerifyPhone;
    private ImageView ivStatusNumberPhone;
    private Button btnLogin;
    private Activity activity;
    private String androidId;
    private Context context;
    private FragmentManager fm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getSupportFragmentManager();
        activity = this;
        context = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setLifecycleOwner(this);
        iLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        iLoginViewModel.setInitContext(context, fm);
        binding.setLogin(iLoginViewModel);
        fm = getSupportFragmentManager();
        new AppSignatureHashHelper(this);
        startSMSListener();
        initView();
        initEvent();
        onObserverDataChange(binding);
        initDataCacheNumberPhone();
        initCheckVersion();
    }

    private void initView() {
        edtVerifyPhone = findViewById(R.id.edtVerifyPhone);
        ivStatusNumberPhone = findViewById(R.id.ivStatusNumberPhone);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void initEvent() {

        // số điện thoại
        edtVerifyPhone.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                iLoginViewModel.setPhoneNumber(s.toString());
            }
        });


        // số điện thoại
        edtVerifyPhone.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                SystemDevice.hideSoftKeyboard(activity, edtVerifyPhone);
                return false;
            }
        });

        // đăng nhập hệ thống
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userLogin.getNumberPhone() != null) {
                    if (userLogin.getNumberPhone().trim().contains("*")) {
                        ivStatusNumberPhone.setBackground(getResources().getDrawable(R.drawable.ic_warm_input));
                        edtVerifyPhone.requestFocus();
                        return;
                    }
                    requestPermission();
                }
            }
        });
    }

    // cập nhật dữ liệu thay đổi vào view
    private void onObserverDataChange(ActivityLoginBinding binding) {
        //nhận dữ liệu user
        binding.getLogin().getUserLoginData().observe(this, new Observer<UserLogin>() {
            @Override
            public void onChanged(@Nullable UserLogin userLoginData) {
                userLogin = userLoginData;
                checkStatusNumberPhone();
            }
        });

        //nhận dữ liệu đăng nhập
        binding.getLogin().getLoginResultData().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResultData) {
                if (loginResultData.getOdataContext() != null) {
                    ((ADCSaleAgrotechApplication) context.getApplicationContext()).setUserId(loginResultData.getLoginDetail().get(0).getUserId());
                    if (Common.isNetworkConnected(context)) {
                        getTokenPushNotify(loginResultData);
                    } else {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.title_error_connection));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_allow_connection_internet));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    }
                }
            }
        });

        //nhận dữ liệu lấy thông tin nhân viên giao hàng
        binding.getLogin().getAppLoginResultData().observe(this, new Observer<AppLoginResult>() {
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
                        }
                    }, 1500);

                }
            }
        });
    }

    // đăng ký token với fcm
    private void getTokenPushNotify(final LoginResult loginResultData) {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            return;
                        } else {
                            androidId = task.getResult().getToken();
                            LoginDetail loginDetail = loginResultData.getLoginDetail().get(0);
                            if (loginDetail.getRegisterStatus() == 1) {
                                if (loginDetail.getOTPRequire() == 1) {
                                    Intent intent = new Intent(context, VerifyOtpActivity.class);
                                    intent.putExtra("AndroidPushId", androidId);
                                    intent.putExtra("AuthenticationId", loginDetail.getAuthenticationId());
                                    intent.putExtra("PhoneNumber", edtVerifyPhone.getText().toString());
                                    startActivity(intent);
                                } else if (loginDetail.getOTPRequire() == 0) {
                                    AppLoginPost appLoginPost = new AppLoginPost(loginDetail.getUserId(), androidId);
                                    iLoginViewModel.callApiAppLogin(appLoginPost);
                                }
                            } else {
                                ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                                Bundle args = new Bundle();
                                args.putString("type", Constant.TYPE_NORMAL);
                                args.putString("title", context.getResources().getString(R.string.title_error_access_system));
                                args.putString("firstMessageContent", loginDetail.getMessage());
                                errorMessageDialog.setArguments(args);
                                SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_error_access_system_dialog");
                            }
                        }
                    }
                });
    }

    // khởi tạo cache số điện thoại
    private void initDataCacheNumberPhone() {
        if (Cache.hasPreferenceData(context, Constant.SHARE_PRE_CACHE_LOGIN, "phoneNumber")) {
            String phoneNumberCache = Cache.loadPreferenceData(context, Constant.SHARE_PRE_CACHE_LOGIN, "phoneNumber");
            edtVerifyPhone.setText(phoneNumberCache);
            iLoginViewModel.setPhoneNumber(phoneNumberCache);
        }
    }

    // kiểm tra số điện thoại nhập hợp lệ
    private void checkStatusNumberPhone() {
        if (userLogin.getNumberPhone() != null) {
            if (userLogin.getNumberPhone().trim().contains("*")) {
                ivStatusNumberPhone.setBackground(getResources().getDrawable(R.drawable.ic_warm_input));
            } else {
                ivStatusNumberPhone.setBackground(getResources().getDrawable(R.drawable.ic_check));
            }
        }
    }

    // kiểm tra phiên bản ứng dụng và cập nhật ứng dụng mới
    private void initCheckVersion() {
        String packageApp = getPackageName();
        String currentVersion = "";
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(packageApp, 0);
            currentVersion = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException ex) {
            ex.printStackTrace();
        }
        new GooglePlayAppVersion(packageApp, currentVersion, context).execute();
    }

    // yêu cầu quyền lấy imei, camera, truy cập sd card, gọi điện
    private void requestPermission() {
        String[] PERMISSIONS = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE};
        ActivityCompat.requestPermissions(this, PERMISSIONS, 1);
    }

    // kết quả yêu cầu cấp quyền
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                        iLoginViewModel.callApiLogin();
                    } else {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_not_allow_use_permission));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.label_content_not_allow_use_permission));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "error_message_allow_permission_device_dialog");
                    }
                    return;
                }
            }
        }
    }

    private void startSMSListener() {
        try {
            smsReceiver = new SMSReceiver();
            smsReceiver.setOTPListener(this);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION);
            this.registerReceiver(smsReceiver, intentFilter);
            SmsRetrieverClient client = SmsRetriever.getClient(this);
            Task<Void> task = client.startSmsRetriever();
            task.addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    // API successfully started
                    System.out.println("ok");
                }
            });

            task.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Fail to start API
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onOTPReceived(String otp) {
        if (smsReceiver != null) {
            unregisterReceiver(smsReceiver);
            smsReceiver = null;
        }
    }

    @Override
    public void onOTPTimeOut() {

    }

    @Override
    public void onOTPReceivedError(String error) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (smsReceiver != null) {
            unregisterReceiver(smsReceiver);
        }
    }

}
