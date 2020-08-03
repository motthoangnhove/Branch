package com.adcvn.adcsaleagrotech.action.fragment.receivablelist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.action.activity.deposit.DepositActivity;
import com.adcvn.adcsaleagrotech.action.fragment.receivablelist.middleinterface.IReceivableListViewModel;
import com.adcvn.adcsaleagrotech.action.fragment.receivablelist.viewmodel.ReceivableListViewModel;
import com.adcvn.adcsaleagrotech.adapter.receivemoney.deposit.DepositAdapter;
import com.adcvn.adcsaleagrotech.adapter.receivemoney.receipt.ReceiptAdapter;
import com.adcvn.adcsaleagrotech.adapter.receivemoney.receivable.BaseAdapter;
import com.adcvn.adcsaleagrotech.adapter.receivemoney.receivable.ReceivableFutureAdapter;
import com.adcvn.adcsaleagrotech.adapter.receivemoney.receivable.ReceivableNowAdapter;
import com.adcvn.adcsaleagrotech.common.Constant;
import com.adcvn.adcsaleagrotech.model.receivemoney.receivablelist.ReceivableDetail;
import com.adcvn.adcsaleagrotech.model.receivemoney.receivablelist.ReceivableListResult;
import com.cruxlab.sectionedrecyclerview.lib.SectionDataManager;
import com.cruxlab.sectionedrecyclerview.lib.SectionHeaderLayout;

import java.util.ArrayList;
import java.util.List;

public class ReceiveMoneyFragment extends Fragment {
    private SwipeRefreshLayout swipeReceivable;
    private TextView tvButtonTabReceivable, tvButtonTabReceipt, tvButtonTabDeposit, tvWalletTotal;
    private ImageButton btnCreateDeposit;
    private RecyclerView rvReceivable, rvReceipt, rvDeposit;
    private TextView tvNow, tvFuture;
    private LinearLayout lyReceipt, lyDeposit;
    private SectionHeaderLayout shlReceivable;
    private NestedScrollView nsDeposit;
    private int skip = 0;
    private int type = 1;
    private int receivableNowDataCount, receivableFutureDataCount;
    private String CREATE_KEY = "Create",
            RECEIVE_MONEY_TAB = "ReceiveMoneyTab",
            RECEIVABLE_SUB_TAB = "ReceivableSubTab",
            RECEIPT_SUB_TAB = "ReceiptSubTab",
            DEPOSIT_SUB_TAB = "DepositSubTab";
    private boolean outOfDataNowReceivabel, outOfDataFutureReceivabel, outOfDataDeposit;
    private ReceiptAdapter receiptAdapter;
    private DepositAdapter depositAdapter;
    private SectionDataManager sectionDataManager;
    private BaseAdapter sectionAdapterNow;
    private BaseAdapter sectionAdapterFuture;
    private List<ReceivableDetail> receivableNowList = new ArrayList<>();
    private List<ReceivableDetail> receivableFutureList = new ArrayList<>();

    private IReceivableListViewModel iViewModel;
    private Context context;
    private FragmentManager fm;

    public ReceiveMoneyFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getActivity();
        fm = getActivity().getSupportFragmentManager();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_receive_money, container, false);
        initView(root);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iViewModel = ViewModelProviders.of(getActivity()).get(ReceivableListViewModel.class);
        iViewModel.setInitContext(context, getActivity().getSupportFragmentManager());
        iViewModel.callApiReceivableList(0, 1);
        initEvent();
        initRecyclerView();
        onObserverDataChange(iViewModel);
    }

    private void initView(View root) {

//        text view chọn tab phải thu
        tvButtonTabReceivable = root.findViewById(R.id.tvButtonTabReceivable);
//        text view chọn tab phiếu thu
        tvButtonTabReceipt = root.findViewById(R.id.tvButtonTabReceipt);
//        text view chọn tab phiếu nộp tiền
        tvButtonTabDeposit = root.findViewById(R.id.tvButtonTabDeposit);

//        SwipeRefreshLayout tab danh sách phải thu
        swipeReceivable = root.findViewById(R.id.swipeReceivable);
        swipeReceivable.setColorSchemeResources(R.color.d00A850, R.color.d00A850, R.color.d00A850, R.color.d00A850);
//        SectionHeaderLayout danh sách phải thu
        shlReceivable = root.findViewById(R.id.shlReceivable);
//        recyclerview danh sách phải thu
        rvReceivable = root.findViewById(R.id.rvReceivable);

//        layout tab danh sách phiếu thu
        lyReceipt = root.findViewById(R.id.lyReceipt);
//        recyclerview danh sách phiếu thu
        rvReceipt = root.findViewById(R.id.rvReceipt);

//        NestedScrollView tab danh sách phiếu nộp tiền
        nsDeposit = root.findViewById(R.id.nsDeposit);
//        layout tab danh sách phiếu nộp tiền
        lyDeposit = root.findViewById(R.id.lyDeposit);
//        text view tổng tiền đã thu
        tvWalletTotal = root.findViewById(R.id.tvWalletTotal);
//        button nộp tiền
        btnCreateDeposit = root.findViewById(R.id.btnCreateDeposit);
//        recyclerview danh sách phiếu nộp tiền
        rvDeposit = root.findViewById(R.id.rvDeposit);
    }

    private void initEvent() {
//        chọn tab danh sách phải thu
        tvButtonTabReceivable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeTabFetchData(1);
            }
        });

//        chọn tab danh sách phiếu thu
        tvButtonTabReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeTabFetchData(2);
            }
        });

//        chọn tab danh sách phiếu nộp tiền
        tvButtonTabDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeTabFetchData(3);
            }
        });

        rvReceivable.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!outOfDataNowReceivabel) {
                        skip += Constant.MAX_PAGE_ELEMENT;
                        iViewModel.callApiReceivableList(skip, 1);
                    } else if (!outOfDataFutureReceivabel) {
                        skip += Constant.MAX_PAGE_ELEMENT;
                        iViewModel.callApiReceivableList(skip, 0);
                    }
                }
            }
        });

        //        Lắng nghe sự kiện refresh dữ liệu tab phải thu
        swipeReceivable.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeReceivable.setRefreshing(true);
                activeTabFetchData(1);
                swipeReceivable.setRefreshing(false);
            }
        });

//        tạo phiếu nộp tiền
        btnCreateDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DepositActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("key", CREATE_KEY);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    //    khởi tạo các recyclerview
    private void initRecyclerView() {
        initReceivableRecyclerView();
        initReceiptRecyclerView();
        initDepositRecyclerView();
    }

    private void onObserverDataChange(IReceivableListViewModel iReceivableListViewModel) {
        iReceivableListViewModel.getNowReceivableData().observe(this, new Observer<ReceivableListResult>() {
            @Override
            public void onChanged(ReceivableListResult receivableListResult) {
                if (receivableListResult.getOdataContext() != null) {
                    boolean isHeader = false;
                    if (skip == 0) {
                        receivableNowList.clear();
                        receivableFutureList.clear();
                        isHeader = true;
                    }
                    if (receivableListResult.getOdataCount() < Constant.MAX_PAGE_ELEMENT) {
                        outOfDataNowReceivabel = true;
                        skip = 0;
                        iViewModel.callApiReceivableList(skip, 0);
                    }
                    receivableNowList = receivableListResult.getReceivableListDetails();
                    receivableNowDataCount = receivableListResult.getOdataCount();
                    Log.d("LeCo", "ReceiveMoneyFragment ReceivableNowAdapter onObserverDataChange(): " + receivableNowList.size());
                    if (receivableNowList.size() > 0) {
                        sectionAdapterNow = new ReceivableNowAdapter(context, sectionDataManager, isHeader, isHeader, receivableNowList, "Hôm nay ", receivableNowDataCount);
                        sectionDataManager.addSection(sectionAdapterNow, null, sectionAdapterNow.type);
                    }
                }
            }
        });

        iReceivableListViewModel.getFutureReceivableData().observe(this, new Observer<ReceivableListResult>() {
            @Override
            public void onChanged(ReceivableListResult receivableListResult) {
                if (receivableListResult.getOdataContext() != null) {
                    boolean isHeader = false;
                    if (skip == 0) {
                        receivableFutureList.clear();
                        isHeader = true;
                    }
                    if (receivableListResult.getOdataCount() < Constant.MAX_PAGE_ELEMENT) {
                        outOfDataFutureReceivabel = true;
                    }
                    receivableFutureList = receivableListResult.getReceivableListDetails();
                    receivableFutureDataCount = receivableListResult.getOdataCount();
                    Log.d("LeCo", "ReceiveMoneyFragment ReceivableFutureAdapter onObserverDataChange(): " + receivableFutureList.size());
                    if (receivableFutureList.size() > 0) {
                        sectionAdapterFuture = new ReceivableFutureAdapter(context, sectionDataManager, isHeader, isHeader, receivableFutureList, "Sắp tới", receivableFutureDataCount);
                        sectionDataManager.addSection(sectionAdapterFuture, null, sectionAdapterFuture.type);
                    }
                }
            }
        });
    }

    //    khởi tạo recycelerview tab phải thu
    private void initReceivableRecyclerView() {
        LinearLayoutManager linearLayoutManagerReceivable = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvReceivable.setLayoutManager(linearLayoutManagerReceivable);
        rvReceivable.setHasFixedSize(false);
        sectionDataManager = new SectionDataManager();
        RecyclerView.Adapter adapter = sectionDataManager.getAdapter();
        rvReceivable.setAdapter(adapter);
        shlReceivable.attachTo(rvReceivable, sectionDataManager);
    }

    //    khởi tạo recycelerview tab phiếu thu
    private void initReceiptRecyclerView() {
//        LinearLayoutManager linearLayoutManagerReceipt = new LinearLayoutManager(getActivity());
//        rvReceipt.setLayoutManager(linearLayoutManagerReceipt);
//        receiptAdapter = new ReceiptAdapter(context, listReceiptDumi, fm);
//        rvReceipt.setAdapter(receiptAdapter);
    }

    //    khởi tạo recycelerview tab nộp tiền
    private void initDepositRecyclerView() {
//        LinearLayoutManager linearLayoutManagerDeposit = new LinearLayoutManager(getActivity());
//        rvDeposit.setLayoutManager(linearLayoutManagerDeposit);
//        depositAdapter = new DepositAdapter(context, listDepositDumi, fm);
//        rvDeposit.setAdapter(depositAdapter);
    }

    //    cập nhật giao diện và dữ liệu khi chọn tab
    private void activeTabFetchData(int typeInto) {
        type = typeInto;
        if (type == 1) {
            outOfDataNowReceivabel = false;
            outOfDataFutureReceivabel = false;
        } else if (type == 2) {

        } else if (type == 3) {
            outOfDataDeposit = false;
        }
        activeTabReceiveMoney(type);
        refreshDataReceivable(type, 0);
    }

    //    cập nhật lại dữ liệu
    private void refreshDataReceivable(int type, int skip) {
        this.skip = skip;
        this.type = type;
//        cập nhật dữ liệu tab phải thu
        if (type == 1) {
            sectionDataManager.removeSwipeCallback(0);
            initReceivableRecyclerView();
            iViewModel.callApiReceivableList(skip, 1);
//            cập nhật dữ liệu tab phiếu thu
        } else if (type == 2) {

//            cập nhật dữ liệu tab nộp tiền
        } else if (type == 3) {

        }
    }

    //    Đổi màu tab và hiển thị tab đã chọn
    private void activeTabReceiveMoney(int type) {

//        hiển thị tab danh sách phải thu
        if (type == 1) {
            tvButtonTabReceivable.setTextColor(getResources().getColor(R.color.d00A850));
            tvButtonTabReceivable.setBackgroundResource(R.drawable.bg_button_tab_receivable_action);
            tvButtonTabReceipt.setTextColor(getResources().getColor(R.color.d7A7F83));
            tvButtonTabReceipt.setBackgroundResource(R.drawable.bg_button_tab_receivable);
            tvButtonTabDeposit.setTextColor(getResources().getColor(R.color.d7A7F83));
            tvButtonTabDeposit.setBackgroundResource(R.drawable.bg_button_tab_receivable);
            swipeReceivable.setVisibility(View.VISIBLE);
            lyReceipt.setVisibility(View.GONE);
            nsDeposit.setVisibility(View.GONE);

//        hiển thị tab danh sách phiếu thu
        } else if (type == 2) {
            tvButtonTabReceivable.setTextColor(getResources().getColor(R.color.d7A7F83));
            tvButtonTabReceivable.setBackgroundResource(R.drawable.bg_button_tab_receivable);
            tvButtonTabReceipt.setTextColor(getResources().getColor(R.color.d00A850));
            tvButtonTabReceipt.setBackgroundResource(R.drawable.bg_button_tab_receivable_action);
            tvButtonTabDeposit.setTextColor(getResources().getColor(R.color.d7A7F83));
            tvButtonTabDeposit.setBackgroundResource(R.drawable.bg_button_tab_receivable);
            swipeReceivable.setVisibility(View.GONE);
            lyReceipt.setVisibility(View.VISIBLE);
            nsDeposit.setVisibility(View.GONE);
            initReceiptRecyclerView();
//        hiển thị tab danh sách phiếu nộp tiền
        } else {
            tvButtonTabReceivable.setTextColor(getResources().getColor(R.color.d7A7F83));
            tvButtonTabReceivable.setBackgroundResource(R.drawable.bg_button_tab_receivable);
            tvButtonTabReceipt.setTextColor(getResources().getColor(R.color.d7A7F83));
            tvButtonTabReceipt.setBackgroundResource(R.drawable.bg_button_tab_receivable);
            tvButtonTabDeposit.setTextColor(getResources().getColor(R.color.d00A850));
            tvButtonTabDeposit.setBackgroundResource(R.drawable.bg_button_tab_receivable_action);
            swipeReceivable.setVisibility(View.GONE);
            lyReceipt.setVisibility(View.GONE);
            nsDeposit.setVisibility(View.VISIBLE);
            initDepositRecyclerView();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(messageReceiverUpDateOrganization, new IntentFilter("UPDATE_ORGANIZATION_NAME"));
        getActivity().registerReceiver(messageReceiverUpdateDataTab, new IntentFilter("UPDATE_DATA_TAB"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(messageReceiverUpDateOrganization);
        getActivity().unregisterReceiver(messageReceiverUpdateDataTab);
    }

    //xử lý cập nhật dữ liệu sau khi chọn thu tiền / yêu cầu thu hộ / khách hàng chuyển tiền
    private BroadcastReceiver messageReceiverUpdateDataTab = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String tabActive = intent.getStringExtra("tabActive");
            if (tabActive.equals(RECEIVE_MONEY_TAB)) {
                boolean refreshDataReceipt = intent.getBooleanExtra("refreshDataReceivable", false);
                String subTabActive = intent.getStringExtra("subTabActive");
                if (subTabActive.equals(RECEIVABLE_SUB_TAB)) {
                    outOfDataNowReceivabel = refreshDataReceipt;
                    outOfDataFutureReceivabel = refreshDataReceipt;
                    refreshDataReceivable(1, 0);
                } else if (subTabActive.equals(DEPOSIT_SUB_TAB)) {
                    outOfDataDeposit = refreshDataReceipt;
                    refreshDataReceivable(3, 0);
                }
            }
        }
    };

    //xử lý cập nhật dữ liệu sau khi chọn vùng kinh doanh
    private BroadcastReceiver messageReceiverUpDateOrganization = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            activeTabFetchData(type);
        }
    };
}