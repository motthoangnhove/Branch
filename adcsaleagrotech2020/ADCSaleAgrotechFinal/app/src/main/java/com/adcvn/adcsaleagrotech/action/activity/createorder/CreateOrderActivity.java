package com.adcvn.adcsaleagrotech.action.activity.createorder;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.action.activity.BaseActivity;
import com.adcvn.adcsaleagrotech.action.activity.createorder.middleinterface.ICreateOrderViewModel;
import com.adcvn.adcsaleagrotech.action.activity.createorder.viewmodel.CreateOrderViewModel;
import com.adcvn.adcsaleagrotech.common.Common;
import com.adcvn.adcsaleagrotech.common.Constant;
import com.adcvn.adcsaleagrotech.common.SystemDatePickerDialog;
import com.adcvn.adcsaleagrotech.common.SystemDateTime;
import com.adcvn.adcsaleagrotech.common.SystemDialog;
import com.adcvn.adcsaleagrotech.databinding.ActivityCreateOrderBinding;
import com.adcvn.adcsaleagrotech.dialog.ChooseCustomerDialog;
import com.adcvn.adcsaleagrotech.dialog.ChooseEmployeeDialog;
import com.adcvn.adcsaleagrotech.adapter.createorder.CreateOrderProductAdapter;
import com.adcvn.adcsaleagrotech.dialog.ChooseProductDialog;
import com.adcvn.adcsaleagrotech.dialog.DebtInfoDialog;
import com.adcvn.adcsaleagrotech.dialog.ErrorMessageDialog;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.IDoubleField;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectThreeLabel;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectThreeLabelConfirm;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectTwoLabel;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectTwoLabelConfirm;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.IStringField;
import com.adcvn.adcsaleagrotech.model.createorder.SalesOrderActionResult;
import com.adcvn.adcsaleagrotech.model.misacustomerdebtcheck.MisaCustomerDebtCheckResult;
import com.adcvn.adcsaleagrotech.model.misacustomerdebtcheck.MisaCustomerDebtDetail;
import com.adcvn.adcsaleagrotech.model.omcustomerlist.OMCustomer;
import com.adcvn.adcsaleagrotech.model.omcustomerlist.OMCustomerListResult;
import com.adcvn.adcsaleagrotech.model.omemployeelist.OMEmployee;
import com.adcvn.adcsaleagrotech.model.omemployeelist.OMEmployeeListResult;
import com.adcvn.adcsaleagrotech.model.ordertypelist.OrderTypeDetail;
import com.adcvn.adcsaleagrotech.model.pricelistget.PriceListGet;
import com.adcvn.adcsaleagrotech.model.pricelistget.PriceListGetResult;
import com.adcvn.adcsaleagrotech.model.pricelistget.ProductDetail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

import br.com.sapereaude.maskedEditText.MaskedEditText;

public class CreateOrderActivity extends BaseActivity implements ISelectTwoLabelConfirm, ISelectThreeLabelConfirm {
    // khởi tạo live data và observer màn hình
    private ActivityCreateOrderBinding binding;
    private ICreateOrderViewModel iViewModel;
    // ImageButton thoát, chọn nhân viên, chọn khách hàng, công nợ phải trả
    private LinearLayout lyContainerChooseEmployee, lyContainerChooseCustomer;
    private ImageButton ibBlack, ibChooseEmployee, ibLiabilities, ibChooseCustomer;
    // Text View tên nhân viên,
    private TextView tvNameEmployee, tvCustomer, tvAddressCustomer, tvContactName, tvContactPhone, tvChooseDateDeliveryOrder, tvReceiveMoney, tvPaymentTermName, tvTotalAmount;
    // MaskedEditText số điện thoại liên hệ
    private MaskedEditText mEdPhone;
    // Edit Text ghi chú đơn hàng
    private EditText edtNoteOrder;
    // LinearLayout thêm sản phẩm
    private LinearLayout lyAddProduct;
    // RecyclerView danh sách sản phẩm
    private RecyclerView rvProductOrder;
    private CreateOrderProductAdapter productOrderAdapter;
    private ArrayList<ProductDetail> productChoose = new ArrayList<>();
    // Button tạo đơn hàng
    private Button btnCreateOrder;
    // khai báo biến tạm
    private OrderTypeDetail orderTypeDetail;
    private OMEmployee omEmployeeChoose;
    private OMCustomer omCustomerChoose;
    private double totalAmount;
    private boolean receiveMoney;
    // khai báo các thành phần android
    private Context context;
    private FragmentManager fm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        fm = getSupportFragmentManager();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_order);
        Intent intent = getIntent();
        orderTypeDetail = intent.getParcelableExtra("orderType");
        binding.setLifecycleOwner(this);
        iViewModel = ViewModelProviders.of(this).get(CreateOrderViewModel.class);
        iViewModel.setInitContext(context, fm);
        iViewModel.setOrderTypeDetail(orderTypeDetail);
        binding.setCreateOrder(iViewModel);
        initView();
        initEvent();
        initData();
        onObserverDataChange(binding);
    }

    // khởi tạo giao diện màn hình
    private void initView(){
        ibBlack = findViewById(R.id.ibBack);
        tvNameEmployee = findViewById(R.id.tvNameEmployee);
        lyContainerChooseEmployee = findViewById(R.id.lyContainerChooseEmployee);
        ibChooseEmployee = findViewById(R.id.ibChooseEmployee);
        ibLiabilities = findViewById(R.id.ibLiabilities);
        tvCustomer = findViewById(R.id.tvCustomer);
        lyContainerChooseCustomer = findViewById(R.id.lyContainerChooseCustomer);
        ibChooseCustomer = findViewById(R.id.ibChooseCustomer);
        tvAddressCustomer = findViewById(R.id.tvAddressCustomer);
        tvContactName = findViewById(R.id.tvContactName);
        mEdPhone = findViewById(R.id.mEdContactPhone);
        tvChooseDateDeliveryOrder = findViewById(R.id.tvChooseDateDeliveryOrder);
        tvReceiveMoney = findViewById(R.id.tvReiceveMoney);
        tvPaymentTermName = findViewById(R.id.tvPaymentTermName);
        lyAddProduct = findViewById(R.id.lyAddProduct);
        rvProductOrder= findViewById(R.id.rvProductOrder);
        rvProductOrder.setLayoutManager(new LinearLayoutManager(this));
        productOrderAdapter = new CreateOrderProductAdapter(this, context, productChoose);
        rvProductOrder.setAdapter(productOrderAdapter);
        btnCreateOrder = findViewById(R.id.btnCreateOrder);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        edtNoteOrder = findViewById(R.id.edtNoteOrder);
    }

    // khởi tạo sự kiện giao diện màn hình
    private void initEvent(){

        //ImageButton thoát
        ibBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //LinearLayout chọn nhân viên
        lyContainerChooseEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iViewModel.callApiOMEmployeeList(0,"");
            }
        });

        // ImageButton chọn nhân viên
        ibChooseEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               iViewModel.callApiOMEmployeeList(0,"");
            }
        });

        // ImageButton công nợ phải trả
        ibLiabilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(omCustomerChoose != null){
                  iViewModel.callApiMisaCustomerDebtCheck();
              }
            }
        });

        // TextView chọn khách hàng
        lyContainerChooseCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(omEmployeeChoose != null) {
                    iViewModel.callApiOMCustomerList(0, "");
                }
            }
        });

        // ImageButton chọn khách hàng
        ibChooseCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(omEmployeeChoose != null) {
                    iViewModel.callApiOMCustomerList(0, "");
                }
            }
        });

        //TextView chọn ngày giao hàng
        tvChooseDateDeliveryOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SystemDatePickerDialog.showCalendar(CreateOrderActivity.this, tvChooseDateDeliveryOrder, "Ngày giao hàng");
            }
        });

        // Text View Giao hàng thu tiền
        tvReceiveMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               receiveMoneyOrder();
            }
        });

        // LinearLayout thêm sản phẩm
        lyAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(omCustomerChoose != null){
                    iViewModel.callApiPriceListGet();
                }
            }
        });

        // Button tạo đơn hàng
        btnCreateOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(omEmployeeChoose == null){
                   ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                   Bundle args = new Bundle();
                   args.putString("type", Constant.TYPE_NORMAL);
                   args.putString("title", context.getResources().getString(R.string.label_title_notification));
                   args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_empty_choose_employee));
                   errorMessageDialog.setArguments(args);
                   SystemDialog.showFragmentDialog(fm, errorMessageDialog, "validate_input_data_dialog");
               } else if(omCustomerChoose == null){
                    ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                    Bundle args = new Bundle();
                    args.putString("type", Constant.TYPE_NORMAL);
                    args.putString("title", context.getResources().getString(R.string.label_title_notification));
                    args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_empty_choose_customer));
                    errorMessageDialog.setArguments(args);
                    SystemDialog.showFragmentDialog(fm, errorMessageDialog, "validate_input_data_dialog");
                } else if(productChoose.size() == 0){
                    ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                    Bundle args = new Bundle();
                    args.putString("type", Constant.TYPE_NORMAL);
                    args.putString("title", context.getResources().getString(R.string.label_title_notification));
                    args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_empty_choose_product));
                    errorMessageDialog.setArguments(args);
                    SystemDialog.showFragmentDialog(fm, errorMessageDialog, "validate_input_data_dialog");
                }else if(tvTotalAmount.getText().toString().equals("N/A")){
                   ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                   Bundle args = new Bundle();
                   args.putString("type", Constant.TYPE_NORMAL);
                   args.putString("title", context.getResources().getString(R.string.label_title_notification));
                   args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_input_quantity_choose_product_invalid));
                   errorMessageDialog.setArguments(args);
                   SystemDialog.showFragmentDialog(fm, errorMessageDialog, "validate_input_data_dialog");
                } else {
                   iViewModel.callApiSalesOrderAction(receiveMoney, tvChooseDateDeliveryOrder.getText().toString(), totalAmount, edtNoteOrder.getText().toString() );
               }
            }
        });
    }

    // cập nhật dữ liệu thay đổi vào view
    private void onObserverDataChange(ActivityCreateOrderBinding binding) {

        //nhận dữ liệu loại đơn hàng
        binding.getCreateOrder().orderTypeDetailData().observe(this, new Observer<OrderTypeDetail>() {
            @Override
            public void onChanged(OrderTypeDetail orderTypeDetail) {
                // thông tin loại đơn hàng đã chọn từ nút tạo đơn hàng
            }
        });

        //nhận dữ liệu danh sách nhân viên
        binding.getCreateOrder().oMEmployeeListResultData().observe(this, new Observer<OMEmployeeListResult>() {
            @Override
            public void onChanged(OMEmployeeListResult omEmployeeListResultData) {
                if (omEmployeeListResultData.getOdataContext() != null) {
                    ArrayList<ISelectTwoLabel> lEmployees = new ArrayList<>();
                    lEmployees.addAll(omEmployeeListResultData.getOMEmployee());
                    ChooseEmployeeDialog chooseEmployeeDialog = new ChooseEmployeeDialog();
                    Bundle args = new Bundle();
                    args.putString("keyChoose", "chooseEmployee");
                    args.putParcelableArrayList("listSelectTwoLabel", lEmployees);
                    chooseEmployeeDialog.setArguments(args);
                    SystemDialog.showFragmentDialog(fm, chooseEmployeeDialog, "choose_employee_dialog");
                }
            }
        });

        //nhận dữ liệu nhân viên đã chọn
        binding.getCreateOrder().employeeChooseData().observe(this, new Observer<OMEmployee>() {
            @Override
            public void onChanged(OMEmployee omEmployeeData) {
                if (omEmployeeData != null) {
                    omEmployeeChoose = omEmployeeData;
                    iViewModel.setCustomerChoose(null);
                    clearDataCusomer();
                    tvNameEmployee.setText(omEmployeeChoose.getEmployeeName());
                    tvNameEmployee.setTextColor(getResources().getColor(R.color.d383F45));
                    lyContainerChooseCustomer.setVisibility(View.VISIBLE);
                }
            }
        });

        //nhận dữ liệu danh sách khách hàng
        binding.getCreateOrder().oMCustomerListResultData().observe(this, new Observer<OMCustomerListResult>() {
            @Override
            public void onChanged(OMCustomerListResult customerListResultData) {
                if (customerListResultData.getOdataContext() != null) {
                    ArrayList<ISelectThreeLabel> lCustomer = new ArrayList<>();
                    lCustomer.addAll(customerListResultData.getOMCustomer());
                    ChooseCustomerDialog chooseCustomerDialog = new ChooseCustomerDialog();
                    Bundle args = new Bundle();
                    args.putString("salesPersonId", omEmployeeChoose.getEmployeeId());
                    args.putString("keyChoose", "chooseCustomer");
                    args.putParcelableArrayList("listSelectThreeLabel", lCustomer);
                    chooseCustomerDialog.setArguments(args);
                    SystemDialog.showFragmentDialog(fm, chooseCustomerDialog, "choose_customer_dialog");
                }
            }
        });

        //nhận dữ liệu khách hàng đã chọn
        binding.getCreateOrder().customerChooseData().observe(this, new Observer<OMCustomer>() {
            @Override
            public void onChanged(OMCustomer omCustomerData) {
                omCustomerChoose = omCustomerData;
                if (omCustomerData != null) {
                    tvCustomer.setText(omCustomerChoose.getCustomerName());
                    tvCustomer.setTextColor(getResources().getColor(R.color.d383F45));
                    tvAddressCustomer.setText(omCustomerChoose.getAddress());
                    tvPaymentTermName.setText(omCustomerChoose.getPaymentTermName());
                    ibLiabilities.setVisibility(View.VISIBLE);
                    clearDataProductChoose();
                }else{
                    tvCustomer.setText(getResources().getString(R.string.label_title_empty_choose_customer));
                    tvCustomer.setTextColor(getResources().getColor(R.color.d7A7F83));
                    ibLiabilities.setVisibility(View.GONE);
                }
            }
        });

        //nhận dữ liệu công nợ khách hàng
        binding.getCreateOrder().misaCustomerDebtCheckResultData().observe(this, new Observer<MisaCustomerDebtCheckResult>() {
            @Override
            public void onChanged(MisaCustomerDebtCheckResult misaCustomerDebtCheckResultData) {
                if (misaCustomerDebtCheckResultData.getOdataContext() != null) {
                    String strMisaCustomerDebtData = misaCustomerDebtCheckResultData.getMisaCustomerDebtCheckDetail().get(0).getResult();
                    Gson gson = new Gson();
                    TypeToken<List<MisaCustomerDebtDetail>> token = new TypeToken<List<MisaCustomerDebtDetail>>() {};
                    List<MisaCustomerDebtDetail> listMisaCustomerDebtData = gson.fromJson(strMisaCustomerDebtData, token.getType());
                    DebtInfoDialog debtInfoDialog = new DebtInfoDialog();
                    Bundle args = new Bundle();
                    args.putParcelable("misaCustomerDebtDetail",listMisaCustomerDebtData.get(0));
                    debtInfoDialog.setArguments(args);
                    SystemDialog.showFragmentDialog(fm, debtInfoDialog, "debt_info_dialog");
                }
            }
        });

        //nhận dữ liệu danh sách sản phẩm
        binding.getCreateOrder().priceListGetResultData().observe(this, new Observer<PriceListGetResult>() {
            @Override
            public void onChanged(PriceListGetResult priceListGetResultData) {
                if (priceListGetResultData.getOdataContext() != null) {
                    PriceListGet priceListGet = priceListGetResultData.getPriceListGet().get(0);
                    String strProductListData = priceListGet.getProductList();
                    Gson gson = new Gson();
                    TypeToken<List<ProductDetail>> token = new TypeToken<List<ProductDetail>>() {};
                    List<ProductDetail> listProductDetailData = gson.fromJson(strProductListData, token.getType());
                    ArrayList<ISelectThreeLabel> listProductChoose = new ArrayList<>();
                    listProductChoose.addAll(listProductDetailData);
                    ChooseProductDialog chooseProductDialog = new ChooseProductDialog();
                    Bundle args = new Bundle();
                    args.putString("keyChoose", "chooseProduct");
                    args.putParcelableArrayList("listSelectThreeLabel", listProductChoose);
                    chooseProductDialog.setArguments(args);
                    SystemDialog.showFragmentDialog(fm, chooseProductDialog, "choose_product_dialog");
                }
            }
        });

        binding.getCreateOrder().productChooseData().observe(this, new Observer<ArrayList<ProductDetail>>() {
            @Override
            public void onChanged(ArrayList<ProductDetail> productChooseData) {
            }
        });

        binding.getCreateOrder().salesOrderActionResultData().observe(this, new Observer<SalesOrderActionResult>() {
            @Override
            public void onChanged(SalesOrderActionResult salesOrderActionResult) {
                if (salesOrderActionResult.getOdataContext() != null) {
                    ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                    Bundle args = new Bundle();
                    args.putString("colorTitle", Constant.TITLE_SUCCESS_DIALOG);
                    args.putString("type", Constant.TYPE_REFRESH_TAB);
                    args.putBoolean("refreshSaleOrderList",true);
                    args.putString("tabActive", "SaleOrderTab");
                    args.putString("title", context.getResources().getString(R.string.title_notification));
                    args.putString("firstMessageContent", salesOrderActionResult.getSalesOrderActionDetail().get(0).getMessage());
                    errorMessageDialog.setArguments(args);
                    SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_notification_dialog");
                }
            }
        });

    }

    // khởi tạo dữ liệu ban đầu cho giao diện màn hình
    private void initData(){
        tvChooseDateDeliveryOrder.setText(SystemDateTime.formatDateToClient(SystemDateTime.getDateTimeCurrent()));
    }

    // trạng thái giao hàng thu tiền
    private void receiveMoneyOrder(){
        if(!receiveMoney) {
            tvReceiveMoney.setBackground(getResources().getDrawable(R.drawable.ic_check_box_active));
        }else{
            tvReceiveMoney.setBackground(getResources().getDrawable(R.drawable.ic_check_box));
        }
        receiveMoney = !receiveMoney;
    }

    // xóa toàn bộ dữ liệu chọn khách hàng
    private void clearDataCusomer(){
        tvAddressCustomer.setText(null);
        tvPaymentTermName.setText(null);
        clearDataProductChoose();
    }

    // xóa toàn bộ dữ liệu chọn sản phẩm
    private void clearDataProductChoose(){
        productChoose.clear();
        productOrderAdapter.notifyDataSetChanged();
        updateListProduct();
        iViewModel.setProducts(productChoose);
    }

    // cập nhật danh sách sản phẩm và tổng tiền đơn hàng
    public void updateListProduct(){
        List<IDoubleField> listDoubleField = new ArrayList<>();
        listDoubleField.addAll(productChoose);
        totalAmount = Common.getTotalLongField(listDoubleField);
        tvTotalAmount.setText(Common.formatNumber(totalAmount));
        iViewModel.setProducts(productChoose);
    }


    // call back chọn nhân viên
    @Override
    public void itemSelectTwoLabel(String key, ISelectTwoLabel iSelectTwoLabel) {
        if(key.equals("chooseEmployee")){
            iViewModel.setEmployeeChoose((OMEmployee)iSelectTwoLabel);
        }
    }

    // call back chọn khách hàng và sản phẩm
    @Override
    public void itemSelectThreeLabel(String key, ISelectThreeLabel iSelectThreeLabel) {
        if(key.equals("chooseCustomer")){
            iViewModel.setCustomerChoose((OMCustomer)iSelectThreeLabel);
        }else if(key.equals("chooseProduct")){
            ProductDetail productDetail = (ProductDetail)iSelectThreeLabel;
            List<IStringField>  listStringField = new ArrayList<>();
            listStringField.addAll(productChoose);
            boolean isChoose = Common.searchItemInList(productDetail.getProductId(),listStringField);
            if(!isChoose) {
                productChoose.add(productDetail);
                updateListProduct();
                productOrderAdapter = new CreateOrderProductAdapter(this, context, productChoose);
                rvProductOrder.setAdapter(productOrderAdapter);
            }
            else{
                ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                Bundle args = new Bundle();
                args.putString("type", Constant.TYPE_NORMAL);
                args.putString("title", context.getResources().getString(R.string.label_title_notification));
                args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_choose_product_exist));
                errorMessageDialog.setArguments(args);
                SystemDialog.showFragmentDialog(fm, errorMessageDialog, "validate_input_data_dialog");
            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(messageReceiverUPDATETOTALMONEYNA, new IntentFilter("UPDATE_TOTAL_MONEY_N/A"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(messageReceiverUPDATETOTALMONEYNA);
    }

    //xử lý cập nhật tổng tiền không hợp lệ
    private BroadcastReceiver messageReceiverUPDATETOTALMONEYNA = new BroadcastReceiver() {
        @Override
            public void onReceive(Context context, Intent intent) {
                totalAmount = 0;
                tvTotalAmount.setText("N/A");
            }
        };
}
