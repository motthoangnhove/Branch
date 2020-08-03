package com.adcvn.adcsaleagrotech.action.activity.deposit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.action.activity.camerasuface.CameraActivity;
import com.adcvn.adcsaleagrotech.action.activity.deposit.middleinterface.IDepositActivityViewModel;
import com.adcvn.adcsaleagrotech.action.activity.deposit.viewmodel.DepositActivityViewModel;
import com.adcvn.adcsaleagrotech.application.ADCSaleAgrotechApplication;
import com.adcvn.adcsaleagrotech.common.Common;
import com.adcvn.adcsaleagrotech.common.Constant;
import com.adcvn.adcsaleagrotech.common.SystemDevice;
import com.adcvn.adcsaleagrotech.common.SystemDialog;
import com.adcvn.adcsaleagrotech.databinding.ActivityDepositBinding;
import com.adcvn.adcsaleagrotech.dialog.ChooseBankDialog;
import com.adcvn.adcsaleagrotech.dialog.ErrorMessageDialog;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectThreeLabel;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectThreeLabelConfirm;
import com.adcvn.adcsaleagrotech.model.arbanklist.ARBank;
import com.adcvn.adcsaleagrotech.model.depositedamountaction.DepositedAmountActionDetail;
import com.adcvn.adcsaleagrotech.model.depositedamountaction.DepositedAmountActionPost;
import com.adcvn.adcsaleagrotech.model.depositedamountaction.DepositedAmountActionResult;
import com.adcvn.adcsaleagrotech.model.receivemoney.deposit.DepositModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.adcvn.adcsaleagrotech.common.SystemImage.getImageUri;
import static com.adcvn.adcsaleagrotech.common.SystemImage.rotateBitmap;

public class DepositActivity extends AppCompatActivity implements ISelectThreeLabelConfirm {
    private ActivityDepositBinding binding;
    private ImageButton ibBack;
    private ImageView ivChooseBank;
    private TextView tvHeader, tvNameUser, tvBankName, tvAccountName, tvAccountNumber, tvStatusPaymentComplete;
    private EditText edtAmountPayment;
    private RoundedImageView rvImage;
    private Button btnPayment;
    private RelativeLayout lyContainerBank;
    private String key;
    private String edtAmountPaymentValueBefore;
    private DepositModel depositModel;
    private String CREATE_KEY = "Create", DETAIL_KEY = "Detail";
    private IDepositActivityViewModel iViewModel;
    private DepositedAmountActionPost depositedAmountActionPost;
    private DepositedAmountActionResult depositedAmountActionResult;
    private DepositedAmountActionDetail depositedAmountActionDetail;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private final int LAUNCH_CAMERA_ACTIVITY = 1;
    private Context context;
    private FragmentManager fm;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.activity = this;
        this.fm = getSupportFragmentManager();
        this.context = this;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        key = bundle.getString("key", "");
        if (key.equals(DETAIL_KEY)) {
            depositModel = bundle.getParcelable("depositDetail");
        }
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_deposit);
        binding.setLifecycleOwner(this);
        iViewModel = ViewModelProviders.of(this).get(DepositActivityViewModel.class);
        iViewModel.setInitApi(context, fm);
        binding.setDeposit(iViewModel);
        initView();
        initEvent();
        onObserverDataChange(binding);
        if (key.equals(CREATE_KEY)) {
            iViewModel.callApiBank(0, 0, "");
        }
    }

    private void initView() {
//        image button trở về
        ibBack = findViewById(R.id.ibBack);
//        image button chọn ngân hàng
        ivChooseBank = findViewById(R.id.ivChooseBank);
//        textview tiêu đề
        tvHeader = findViewById(R.id.tvHeader);
//        textview tên người nộp tiền
        tvNameUser = findViewById(R.id.tvNameUser);
//        textview tên ngân hàng
        tvBankName = findViewById(R.id.tvBankName);
//        textview tên tài khoản
        tvAccountName = findViewById(R.id.tvAccountName);
//        textview số tài khoản
        tvAccountNumber = findViewById(R.id.tvAccountNumber);
//        textview trạng thái nộp tiền
        tvStatusPaymentComplete = findViewById(R.id.tvStatusPaymentComplete);
//        edittext nhập số tiền nộp
        edtAmountPayment = findViewById(R.id.edtAmountPayment);
//        RoundedImageView chụp ảnh
        rvImage = findViewById(R.id.rvImage);
//        button thực hiện thanh toán
        btnPayment = findViewById(R.id.btnPayment);
//        RelativeLayout tên ngân hàng
        lyContainerBank = findViewById(R.id.lyContainerBank);

//        show màn hình tạo phiếu nộp tiền
        if (key.equals(CREATE_KEY)) {
            ivChooseBank.setVisibility(View.VISIBLE);
            tvHeader.setText(getResources().getString(R.string.label_title_payment_slip));
            edtAmountPayment.setEnabled(true);
            edtAmountPayment.setTextColor(getResources().getColor(R.color.d00A850));
            btnPayment.setVisibility(View.VISIBLE);
            tvStatusPaymentComplete.setVisibility(View.GONE);
            rvImage.setBackground(getResources().getDrawable(R.drawable.ic_image_default));

//        show màn hình chi tiết phiếu nộp tiền
        } else if (key.equals(DETAIL_KEY)) {
            ivChooseBank.setVisibility(View.GONE);
            tvHeader.setText(depositModel.getDepositNumber().replace("#", ""));
            edtAmountPayment.setEnabled(false);
            edtAmountPayment.setTextColor(getResources().getColor(R.color.d383F45));
            btnPayment.setVisibility(View.GONE);
            tvStatusPaymentComplete.setVisibility(View.VISIBLE);
            rvImage.setBackground(getResources().getDrawable(R.drawable.ic_image_empty));
        }
    }

    private void initEvent() {

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        chọn ngân hàng
        lyContainerBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (key.equals(CREATE_KEY)) {
                    iViewModel.callApiBank(1, 0, "");
                }
            }
        });
        ivChooseBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (key.equals(CREATE_KEY)) {
                    iViewModel.callApiBank(1, 0, "");
                }
            }
        });
        tvBankName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (key.equals(CREATE_KEY)) {
                    iViewModel.callApiBank(1, 0, "");
                }
            }
        });

        rvImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DepositActivity.this, CameraActivity.class);
                startActivityForResult(intent, LAUNCH_CAMERA_ACTIVITY);
            }});
        //nhập số tiền nộp
        edtAmountPayment.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (!edtAmountPaymentValueBefore.equals(s.toString()) && s.toString().length() > 0) {
                    double amount = s.toString().contains(",") ? Double.valueOf(s.toString().replace(",", "")) : Double.valueOf(s.toString());
                    edtAmountPayment.setText(Common.formatNumber(amount));
                } else
                edtAmountPayment.setSelection(edtAmountPayment.getText().length());
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                edtAmountPaymentValueBefore = s.toString();
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.toString().length() == 0) {
                    edtAmountPayment.setSelection(edtAmountPayment.getText().length());
                } else {
                    String strReceiptAbleAmount = s.toString();
                    long amount = strReceiptAbleAmount.contains(",") ? Long.valueOf(strReceiptAbleAmount.replace(",", "")) : Long.valueOf(strReceiptAbleAmount);
                    iViewModel.setDepositedAmount(amount);
                }
            }
        });

        edtAmountPayment.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                SystemDevice.hideSoftKeyboard(activity, edtAmountPayment);
                return false;
            }
        });

        edtAmountPayment.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                SystemDevice.hideSoftKeyboard(activity, edtAmountPayment);
                return false;
            }
        });

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (depositedAmountActionPost.getBankId() == null) {
                    ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                    Bundle args = new Bundle();
                    args.putString("type", Constant.TYPE_NORMAL);
                    args.putString("title", context.getResources().getString(R.string.title_notification));
                    args.putString("firstMessageContent", context.getResources().getString(R.string.label_title_error_choose_bank));
                    errorMessageDialog.setArguments(args);
                    SystemDialog.showFragmentDialog(fm, errorMessageDialog, "error_validate_input_data_dialog");
                    return;
                }
                if (edtAmountPayment.getText().toString().trim().length() == 0) {
                    ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                    Bundle args = new Bundle();
                    args.putString("type", Constant.TYPE_NORMAL);
                    args.putString("title", context.getResources().getString(R.string.title_notification));
                    args.putString("firstMessageContent", context.getResources().getString(R.string.label_title_error_amount_payment));
                    errorMessageDialog.setArguments(args);
                    SystemDialog.showFragmentDialog(fm, errorMessageDialog, "error_validate_input_data_dialog");
                    return;
                }
                iViewModel.callApiDepositedAmountAction();
            }
        });
    }

    private void onObserverDataChange(ActivityDepositBinding binding) {
        binding.getDeposit().getDepositCreatePostData().observe(this, new Observer<DepositedAmountActionPost>() {
            @Override
            public void onChanged(DepositedAmountActionPost depositedAmountActionPostData) {
                depositedAmountActionPost = depositedAmountActionPostData;
            }
        });

        binding.getDeposit().getDepositCreateResultData().observe(this, new Observer<DepositedAmountActionResult>() {
            @Override
            public void onChanged(DepositedAmountActionResult depositedAmountActionResult) {
                if (depositedAmountActionResult.getOdataContext() != null) {
                    ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                    Bundle args = new Bundle();
                    args.putString("colorTitle", Constant.TITLE_SUCCESS_DIALOG);
                    args.putString("type", Constant.TYPE_REFRESH_TAB);
                    args.putString("tabActive", "ReceiveMoneyTab");
                    args.putString("subTabActive", "DepositSubTab");
                    args.putString("title", context.getResources().getString(R.string.title_notification));
                    args.putString("firstMessageContent", depositedAmountActionResult.getDepositedAmountActionDetail().get(0).getMessage());
                    errorMessageDialog.setArguments(args);
                    SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_notification_dialog");
                }
            }
        });

        binding.getDeposit().getInitBankData().observe(this, new Observer<List<ARBank>>() {
            @Override
            public void onChanged(List<ARBank> arBanks) {
                if (arBanks.size() > 0) {
                    ARBank bank = arBanks.get(0);
                    iViewModel.setBank(bank);
                    if (bank.getId() != null) {
                        tvBankName.setText(bank.getName());
                        tvAccountName.setText("Tên TK: " + bank.getChangeAccount());
                        tvAccountNumber.setText("Số TK: " + bank.getChangeNumber());
                    }
                }
            }
        });

        binding.getDeposit().getBankDataChoose().observe(this, new Observer<List<ARBank>>() {
            @Override
            public void onChanged(List<ARBank> arBanks) {
                if (arBanks.size() > 0) {
                    ChooseBankDialog chooseAddressDialog = new ChooseBankDialog();
                    Bundle args = new Bundle();
                    args.putString("keyChoose", "Bank");
                    args.putString("titleChooseDialog", getResources().getString(R.string.label_title_bank));
                    args.putParcelableArrayList("listSelectThreeLabel", (ArrayList<? extends Parcelable>) arBanks);
                    chooseAddressDialog.setArguments(args);
                    SystemDialog.showFragmentDialog(fm, chooseAddressDialog, "choose_bank_dialog");
                }
            }
        });

        binding.getDeposit().getBankChooseData().observe(this, new Observer<ARBank>() {
            @Override
            public void onChanged(ARBank arBank) {
                ADCSaleAgrotechApplication adcSaleAgrotechApplication = (ADCSaleAgrotechApplication) context.getApplicationContext();
                tvNameUser.setText(adcSaleAgrotechApplication.getAppLoginDetail().getFullName());
//                edtAmountPayment.setText(TranslateText.formatNumber(adcSaleAgrotechApplication.getDashboardResultDetail().getWalletAmount()));
                edtAmountPayment.setText("0");
                ivChooseBank.setVisibility(View.VISIBLE);
                tvHeader.setText(getResources().getString(R.string.label_title_payment_slip));
                edtAmountPayment.setEnabled(true);
                edtAmountPayment.setTextColor(getResources().getColor(R.color.d00A850));
                btnPayment.setVisibility(View.VISIBLE);
                tvStatusPaymentComplete.setVisibility(View.GONE);
                rvImage.setBackground(getResources().getDrawable(R.drawable.ic_image_default));

                if (arBank.getId() != null) {
                    lyContainerBank.setVisibility(View.VISIBLE);
                    tvBankName.setText(arBank.getName());
                    tvAccountName.setText("Tên TK: " + arBank.getChangeAccount());
                    tvAccountNumber.setText("STK: " + arBank.getChangeNumber());
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAUNCH_CAMERA_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK){
                String pathImage = data.getStringExtra("pathImage");
                Uri uri = Uri.fromFile(new File(pathImage));
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bitmap = rotateBitmap(bitmap,90);
                uri = getImageUri(context,bitmap);
                uploadImageCapture(uri);
            }
        }
    }

    //Xử lý cập nhật hình ảnh
    private void uploadImageCapture(Uri uriPhotoFile) {
        SystemDialog.showProcessDialog(context);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();
        final StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
        ref.putFile(uriPhotoFile)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String photoLink = uri.toString();
                                iViewModel.setPhotoLink(photoLink);
                                Glide.with(context).load(photoLink).diskCacheStrategy(DiskCacheStrategy.NONE)
                                        .skipMemoryCache(true).centerCrop().into(rvImage);
                                progressDialog.dismiss();
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                .getTotalByteCount());
                        progressDialog.setMessage("" + (int) progress + "%");
                    }
                });
    }

    //    kết quả ngân hàng trả về
    @Override
    public void itemSelectThreeLabel(String key, ISelectThreeLabel iSelectThreeLabel) {
        if (key.equals("Bank")) {
            ARBank bankChoose = (ARBank) iSelectThreeLabel;
            tvBankName.setText(bankChoose.getName());
            tvAccountName.setText("Tên TK: " + bankChoose.getChangeAccount());
            tvAccountNumber.setText("Số TK: " + bankChoose.getChangeNumber());
        }
    }
}