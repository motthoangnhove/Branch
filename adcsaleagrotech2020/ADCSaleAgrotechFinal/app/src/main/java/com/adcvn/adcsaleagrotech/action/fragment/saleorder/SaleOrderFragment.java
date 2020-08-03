package com.adcvn.adcsaleagrotech.action.fragment.saleorder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.action.activity.orderdetail.OrderDetailActivity;
import com.adcvn.adcsaleagrotech.action.fragment.saleorder.middleinterface.ISaleOrderViewModel;
import com.adcvn.adcsaleagrotech.action.fragment.saleorder.viewmodel.SaleOrderViewModel;
import com.adcvn.adcsaleagrotech.adapter.saleorder.SaleOrderAdapter;
import com.adcvn.adcsaleagrotech.common.Common;
import com.adcvn.adcsaleagrotech.common.Constant;
import com.adcvn.adcsaleagrotech.common.TranslateText;
import com.adcvn.adcsaleagrotech.model.omsalesorderlist.OMSalesOrder;
import com.adcvn.adcsaleagrotech.model.omsalesorderlist.OMSalesOrderListResult;
import com.adcvn.adcsaleagrotech.model.salesorder.SalesOrder;

import java.util.ArrayList;
import java.util.List;

public class SaleOrderFragment extends Fragment {
    private ISaleOrderViewModel iSaleOrderViewModel;
    private EditText edtSearchItem;
    private ImageButton ibSearchItem;
    private SwipeRefreshLayout swipeToRefresh;
    private RecyclerView rvSaleOrder;
    private SaleOrderAdapter saleOrderAdapter;
    private List<OMSalesOrder> listSaleOrders = new ArrayList<>();
    private boolean outOfData, statusSearchButton;
    private int skip;
    private FragmentManager fm;
    private Context context;
    public SaleOrderFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getActivity();
        fm = getActivity().getSupportFragmentManager();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_order_sale, container, false);
        initView(root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iSaleOrderViewModel = ViewModelProviders.of(getActivity()).get(SaleOrderViewModel.class);
        iSaleOrderViewModel.setInitContext(context, getActivity().getSupportFragmentManager());
        onObserverDataChange(iSaleOrderViewModel);
        refreshSaleOrderList();
        initEvent();
    }

    private void initView(View view) {
        edtSearchItem = view.findViewById(R.id.edtSearchItem);
        ibSearchItem = view.findViewById(R.id.ibSearchItem);
        swipeToRefresh = view.findViewById(R.id.swipeToRefresh);
        swipeToRefresh.setColorSchemeResources(R.color.d00A850, R.color.d00A850, R.color.d00A850, R.color.d00A850);
        rvSaleOrder = view.findViewById(R.id.rvSaleOrder);
        LinearLayoutManager linearLayoutRecycleView = new LinearLayoutManager(context);
        rvSaleOrder.setLayoutManager(linearLayoutRecycleView);
        saleOrderAdapter = new SaleOrderAdapter(context, listSaleOrders);
        rvSaleOrder.setAdapter(saleOrderAdapter);
    }

    private void initEvent(){
        edtSearchItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(final Editable s){
            }
        });
        edtSearchItem.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event != null &&
                                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            if (event == null || !event.isShiftPressed()) {
                                if(edtSearchItem.getText().length() > 0){
                                    statusSearchButton = false;
                                    ibSearchItem.setBackground(getResources().getDrawable(R.drawable.ic_clear));
                                }
                                refreshSaleOrderList();
                                Common.hideSoftKeyboard(getActivity(), edtSearchItem);
                                return true;
                            }
                        }
                        return false;
                    }
                }
        );

        ibSearchItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.hideSoftKeyboard(getActivity(), edtSearchItem);
                if(statusSearchButton){
                    statusSearchButton = false;
                    ibSearchItem.setBackground(getResources().getDrawable(R.drawable.ic_clear));
                }else{
                    statusSearchButton = true;
                    ibSearchItem.setBackground(getResources().getDrawable(R.drawable.ic_search));
                    edtSearchItem.setText("");
                }
                refreshSaleOrderList();
            }
        });

        //lắng nghe sự kiện cuộn trang danh sách
        rvSaleOrder.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!outOfData) {
                        skip += Constant.MAX_PAGE_ELEMENT;
                        String search = TranslateText.deAccent(edtSearchItem.getText().toString());
                        iSaleOrderViewModel.callApiOMSalesOrderList(skip, search);
                    }
                }
            }
        });

        //lắng nghe sự kiện refresh dữ liệu
        swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeToRefresh.setRefreshing(true);
                refreshSaleOrderList();
                swipeToRefresh.setRefreshing(false);
            }
        });
    }

    //cập nhật dữ liệu khi có thay đổi
    private void onObserverDataChange(ISaleOrderViewModel iSaleOrderViewModel) {
        // cập nhật dữ liệu danh sách đơn hàng
        iSaleOrderViewModel.getOMSalesOrderListResultData().observe(this, new Observer<OMSalesOrderListResult>() {
            @Override
            public void onChanged(OMSalesOrderListResult orderDeliveryResult) {
                if (orderDeliveryResult.getOdataContext() != null) {
                    if (skip == 0) {
                        listSaleOrders.clear();
                    }
                    if (orderDeliveryResult.getOdataCount() < Constant.MAX_PAGE_ELEMENT) {
                        outOfData = true;
                    }
                    listSaleOrders.addAll(orderDeliveryResult.getOMSalesOrder());
                    saleOrderAdapter.notifyDataSetChanged();
                }
            }
        });

        iSaleOrderViewModel.getSalesOrderResultData().observe(this, new Observer<SalesOrder>() {
            @Override
            public void onChanged(SalesOrder salesOrder) {
                if (salesOrder.getOdataContext() != null) {
                    Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                    intent.putExtra("salesOrder",salesOrder);
                    getActivity().startActivity(intent);
                }
            }
        });

    }

    // lấy danh sách đơn hàng
    private void refreshSaleOrderList() {
        skip = 0;
        outOfData = false;
        String search = TranslateText.deAccent(edtSearchItem.getText().toString());
        iSaleOrderViewModel.callApiOMSalesOrderList(skip, search);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(messageReceiverUpDateOrganization, new IntentFilter("UPDATE_ORGANIZATION_NAME"));
        getActivity().registerReceiver(messageReceiverSalesOrder, new IntentFilter("GET_SALES_ORDER"));
        getActivity().registerReceiver(messageReceiverRefreshAllDataTab, new IntentFilter("REFRESH_ALL_SALE_ORDER_TAB"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(messageReceiverUpDateOrganization);
        getActivity().unregisterReceiver(messageReceiverSalesOrder);
    }

    //xử lý cập nhật dữ liệu sau khi chọn chi nhánh
    private BroadcastReceiver messageReceiverUpDateOrganization = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            refreshSaleOrderList();
        }
    };

    //xử lý cập nhật dữ liệu chi tiết đơn hàng
    private BroadcastReceiver messageReceiverSalesOrder = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String saleOrderId = intent.getStringExtra("saleOrderId");
            iSaleOrderViewModel.callApiSalesOrder(saleOrderId);
        }
    };

    //xử lý cập nhật dữ liệu sau khi tạo đơn hàng từ màn hình tạo đơn hàng
    private BroadcastReceiver messageReceiverRefreshAllDataTab = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            refreshSaleOrderList();
        }
    };
}
