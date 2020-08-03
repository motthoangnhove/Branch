package com.adcvn.adcsaleagrotech.action.activity.dashboard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.action.activity.BaseActivity;
import com.adcvn.adcsaleagrotech.action.activity.createorder.CreateOrderActivity;
import com.adcvn.adcsaleagrotech.action.activity.dashboard.middleinterface.IMainViewModel;
import com.adcvn.adcsaleagrotech.action.activity.dashboard.viewmodel.MainViewModel;
import com.adcvn.adcsaleagrotech.action.fragment.DefaultFragment;
import com.adcvn.adcsaleagrotech.action.fragment.debtcustomer.DebtCustomerFragment;
import com.adcvn.adcsaleagrotech.action.fragment.ProductFragment;
import com.adcvn.adcsaleagrotech.action.fragment.saleorder.SaleOrderFragment;
import com.adcvn.adcsaleagrotech.action.fragment.receivablelist.ReceiveMoneyFragment;
import com.adcvn.adcsaleagrotech.action.fragment.HomeFragment;
import com.adcvn.adcsaleagrotech.action.activity.notification.NotificationActivity;
import com.adcvn.adcsaleagrotech.adapter.ordertypelist.OrderTypeListAdapter;
import com.adcvn.adcsaleagrotech.application.ADCSaleAgrotechApplication;
import com.adcvn.adcsaleagrotech.common.SystemDatePickerDialog;
import com.adcvn.adcsaleagrotech.common.SystemDialog;
import com.adcvn.adcsaleagrotech.common.SystemPopupWindow;
import com.adcvn.adcsaleagrotech.databinding.ActivityMainBinding;
import com.adcvn.adcsaleagrotech.dialog.ChooseAddressDialog;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectOneLabel;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectOneLabelConfirm;
import com.adcvn.adcsaleagrotech.model.applogin.AppLoginDetail;
import com.adcvn.adcsaleagrotech.model.applogin.OrganizationHierarchy;
import com.adcvn.adcsaleagrotech.model.applogin.OrganizationHierarchyList;
import com.adcvn.adcsaleagrotech.model.ordertypelist.OMOrderTypeListResult;
import com.adcvn.adcsaleagrotech.model.ordertypelist.OrderTypeDetail;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.jakewharton.threetenabp.AndroidThreeTen;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements ISelectOneLabelConfirm {
    private ActivityMainBinding binding;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private LinearLayout lyContainerIvMenu;
    private View navHeader;
    private LinearLayout lyContainerNumberNotification, lyMain;
    private ImageView ivMenu;
    private TextView tvTitleActionBar;
    private int[] menuIconsActive = {
            R.drawable.ic_home_active,
            R.drawable.ic_sale_active,
            R.drawable.ic_receive_money_active,
            R.drawable.ic_cost_active,
            R.drawable.ic_customer_active,
            R.drawable.ic_service_active,
            R.drawable.ic_polyce_active,
            R.drawable.ic_document_active,
            R.drawable.ic_feed_back_active,
            R.drawable.ic_sign_out_active
    };
    private int[] menuIconsDefault = {
            R.drawable.ic_home_default,
            R.drawable.ic_sale_default,
            R.drawable.ic_receive_money_default,
            R.drawable.ic_cost_default,
            R.drawable.ic_customer_default,
            R.drawable.ic_service_default,
            R.drawable.ic_polyce_default,
            R.drawable.ic_document_default,
            R.drawable.ic_feed_back_default,
            R.drawable.ic_sign_out_default
    };
    private int colorDefaultMenuItem = R.color.d000000, colorActiveMenuItem = R.color.d00A850;
    private FloatingActionButton fab;
    public static int navItemIndex;
    private String TAG_SEASON = "season", TAG_PLANTING_AREA = "planting_area", TAG_SEEDS = "seeds", TAG_WAREHOUSE_SUPPLIES = "warehouse_supplies", TAG_DEVICES = "devices", TAG_CONTRACT = "contract", CURRENT_TAG = TAG_SEASON, userId;
    private String[] titles;
    private Handler mHandler;
    private Context context;
    private Activity activity;
    private boolean statusFilter;
    private Button btnFilterOrder;
    private RelativeLayout rlContainerAvatarUser, rlContainerFilterOrder;
    private ImageView ivChoosePosition, ivMenuItemSeason, ivMenuItemPlatingArea, ivMenuItemSeeds, ivMenuItemWareHouseSupplies, ivMenuItemDevices, ivMenuItemContract, ivMenuItemNotification, ivMenuItemAccount, ivMenuItemFeedBack, ivMenuItemSignOut;
    private TextView tvNamePosition, tvNumberNotification, tvLabelMenuItemSeason, tvLabelMenuItemPlatingArea, tvLabelMenuItemSeeds, tvLabelMenuItemWareHouseSupplies, tvLabelMenuItemDevices, tvLabelMenuItemContract, tvLabelMenuItemNotification, tvLabelMenuItemAccount, tvLabelMenuItemFeedBack, tvLabelMenuItemSignOut;

    //view màn hình chọn loại đơn hàng
    private View orderTypeView;
    private RecyclerView rvOrderTypeList;

    //view màn hình lọc đơn hàng
    private View filterView;
    private TextView tvStartDate, tvEndDate;
    private Button btnFilter, btnCancelFilter;

    private OrderTypeListAdapter orderDetailListAdapter;
    private List<OrderTypeDetail> orderTypeDetails = new ArrayList<>();

    private IMainViewModel iMainViewModel;
    private AppLoginDetail appLoginDetail;
    private FragmentManager fm;

    private PopupWindow popupOrderType;
    private PopupWindow popupFilter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidThreeTen.init(this);
        this.activity = this;
        this.context = this;
        fm = getSupportFragmentManager();
        appLoginDetail = ((ADCSaleAgrotechApplication) context.getApplicationContext()).getAppLoginDetail();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        iMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        iMainViewModel.setInitApi(context, fm);
        binding.setMain(iMainViewModel);
        initView();
        initEvent();
        initData();
        setUpNavigationView();
        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_SEASON;
            loadHomePage();
        }
        onObserverDataChange(binding);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadHomePage();
    }

    // khởi tạo giao diện màn hình
    private void initView() {
        lyMain = findViewById(R.id.lyMain);
        ivMenu = findViewById(R.id.ivMenu);
        tvTitleActionBar = findViewById(R.id.tvTitleActionBar);
        mHandler = new Handler();
        drawer = findViewById(R.id.drawer_layout);
        rlContainerAvatarUser = findViewById(R.id.rlContainerAvatarUser);
        lyContainerNumberNotification = findViewById(R.id.lyContainerNumberNotification);
        tvNumberNotification = findViewById(R.id.tvNumberNotification);
        rlContainerFilterOrder = findViewById(R.id.rlContainerFilterOrder);
        btnFilterOrder = findViewById(R.id.btnFilterOrder);
        lyContainerIvMenu = findViewById(R.id.lyContainerIvMenuLeft);
        navigationView = findViewById(R.id.nav_view);
        fab = findViewById(R.id.fab);
        navHeader = navigationView.getHeaderView(0);
        ivChoosePosition = navHeader.findViewById(R.id.ivChoosePosition);
        tvNamePosition = navHeader.findViewById(R.id.tvNamePosition);
        titles = getResources().getStringArray(R.array.nav_item_dashboard_activity_titles_main);
        transparentStatusAndNavigation();
        // tham chiếu các menu trái
        Menu menu = navigationView.getMenu();
        // trang chính
        ivMenuItemSeason = menu.findItem(R.id.nav_season).getActionView().findViewById(R.id.ivMenuItemSeason);
        tvLabelMenuItemSeason = menu.findItem(R.id.nav_season).getActionView().findViewById(R.id.tvLabelMenuItemSeason);
        // bán hàng
        ivMenuItemPlatingArea = menu.findItem(R.id.nav_planting_area).getActionView().findViewById(R.id.ivMenuItemPlatingArea);
        tvLabelMenuItemPlatingArea = menu.findItem(R.id.nav_planting_area).getActionView().findViewById(R.id.tvLabelMenuItemPlatingArea);
        // thu tiền
        ivMenuItemSeeds = menu.findItem(R.id.nav_seeds).getActionView().findViewById(R.id.ivMenuItemSeeds);
        tvLabelMenuItemSeeds = menu.findItem(R.id.nav_seeds).getActionView().findViewById(R.id.tvLabelMenuItemSeeds);
        // chi phí
        ivMenuItemWareHouseSupplies = menu.findItem(R.id.nav_warehouse_supplies).getActionView().findViewById(R.id.ivMenuItemWareHouseSupplies);
        tvLabelMenuItemWareHouseSupplies = menu.findItem(R.id.nav_warehouse_supplies).getActionView().findViewById(R.id.tvLabelMenuItemWareHouseSupplies);
        // khách hàng
        ivMenuItemDevices = menu.findItem(R.id.nav_devices).getActionView().findViewById(R.id.ivMenuItemDevices);
        tvLabelMenuItemDevices = menu.findItem(R.id.nav_devices).getActionView().findViewById(R.id.tvLabelMenuItemDevices);
        // hàng hóa vs dịch vụ
        ivMenuItemContract = menu.findItem(R.id.nav_contract).getActionView().findViewById(R.id.ivMenuItemContract);
        tvLabelMenuItemContract = menu.findItem(R.id.nav_contract).getActionView().findViewById(R.id.tvLabelMenuItemContract);
        // chính sách
        ivMenuItemNotification = menu.findItem(R.id.nav_notification).getActionView().findViewById(R.id.ivMenuItemNotification);
        tvLabelMenuItemNotification = menu.findItem(R.id.nav_notification).getActionView().findViewById(R.id.tvLabelMenuItemNotification);
        // hồ sơ của tôi
        ivMenuItemAccount = menu.findItem(R.id.nav_account).getActionView().findViewById(R.id.ivMenuItemAccount);
        tvLabelMenuItemAccount = menu.findItem(R.id.nav_account).getActionView().findViewById(R.id.tvLabelMenuItemAccount);
        // phản hồi
        ivMenuItemFeedBack = menu.findItem(R.id.nav_feed_back).getActionView().findViewById(R.id.ivMenuItemFeedBack);
        tvLabelMenuItemFeedBack = menu.findItem(R.id.nav_feed_back).getActionView().findViewById(R.id.tvLabelMenuItemFeedBack);
        // đăng xuất
        ivMenuItemSignOut = menu.findItem(R.id.nav_sign_out).getActionView().findViewById(R.id.ivMenuItemSignOut);
        tvLabelMenuItemSignOut = menu.findItem(R.id.nav_sign_out).getActionView().findViewById(R.id.tvLabelMenuItemSignOut);

        //Khởi tạo view màn hình chọn loại đơn hàng
        orderTypeView = LayoutInflater.from(MainActivity.this).inflate(R.layout.popup_window_order_type_list, null, false);
        rvOrderTypeList = orderTypeView.findViewById(R.id.rvOrderTypeList);
        rvOrderTypeList.setLayoutManager(new LinearLayoutManager(this));

        //Khởi tạo view màn hình lọc
        filterView = LayoutInflater.from(MainActivity.this).inflate(R.layout.popup_window_filter, null, false);
        tvStartDate = filterView.findViewById(R.id.tvStartDate);
        tvEndDate = filterView.findViewById(R.id.tvEndDate);
        btnFilter = filterView.findViewById(R.id.btnFilter);
        btnCancelFilter = filterView.findViewById(R.id.btnCancelFilter);
    }

    private void initData() {
        tvNamePosition.setText(appLoginDetail.getOrganizationName());
    }

    private void onObserverDataChange(ActivityMainBinding binding) {
        binding.getMain().getOrderTypeResultData().observe(this, new Observer<OMOrderTypeListResult>() {
            @Override
            public void onChanged(OMOrderTypeListResult omOrderTypeListResult) {
                if (omOrderTypeListResult.getOdataContext() != null) {
                    popupOrderType = new PopupWindow(orderTypeView,
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            true);
                    SystemPopupWindow.showPopupWindow(MainActivity.this, popupOrderType, lyMain, 0);
                    orderTypeDetails = omOrderTypeListResult.getOrderTypeDetail();
                    if(orderTypeDetails.size()>1) {
                        orderDetailListAdapter = new OrderTypeListAdapter(context, orderTypeDetails, popupOrderType);
                        rvOrderTypeList.setAdapter(orderDetailListAdapter);
                    }else{
                        Intent intent = new Intent(context, CreateOrderActivity.class);
                        intent.putExtra("orderType", orderTypeDetails.get(0));
                        context.startActivity(intent);
                        popupOrderType.dismiss();
                    }
                }
            }
        });
    }

    // thay đổi icon và màu chữ sau khi chọn 1 menu item
    private void setActiveMenuItemChoose(int positionChecked) {
        switch (positionChecked) {
            case 0: {
                ivMenuItemSeason.setBackground(getResources().getDrawable(menuIconsActive[positionChecked]));
                tvLabelMenuItemSeason.setTextColor(getResources().getColor(colorActiveMenuItem));
                setMenuItemPlatingAreaDefault();
                setMenuMenuItemSeedsDefault();
                setMenuItemWareHouseSuppliesDefault();
                setMenuItemDevicesDefault();
                setMenuItemContractDefault();
                setMenuItemNotificationDefault();
                setMenuItemAccountDefault();
                setMenuItemFeedBackDefault();
                setMenuItemSignOutDefault();
                break;
            }
            case 1: {
                ivMenuItemPlatingArea.setBackground(getResources().getDrawable(menuIconsActive[positionChecked]));
                tvLabelMenuItemPlatingArea.setTextColor(getResources().getColor(colorActiveMenuItem));
                setMenuItemSeasonDefault();
                setMenuMenuItemSeedsDefault();
                setMenuItemWareHouseSuppliesDefault();
                setMenuItemDevicesDefault();
                setMenuItemContractDefault();
                setMenuItemNotificationDefault();
                setMenuItemAccountDefault();
                setMenuItemFeedBackDefault();
                setMenuItemSignOutDefault();
                break;
            }
            case 2: {
                ivMenuItemSeeds.setBackground(getResources().getDrawable(menuIconsActive[positionChecked]));
                tvLabelMenuItemSeeds.setTextColor(getResources().getColor(colorActiveMenuItem));
                setMenuItemSeasonDefault();
                setMenuItemPlatingAreaDefault();
                setMenuItemWareHouseSuppliesDefault();
                setMenuItemDevicesDefault();
                setMenuItemContractDefault();
                setMenuItemNotificationDefault();
                setMenuItemAccountDefault();
                setMenuItemFeedBackDefault();
                setMenuItemSignOutDefault();
                break;
            }
            case 3: {
                ivMenuItemWareHouseSupplies.setBackground(getResources().getDrawable(menuIconsActive[positionChecked]));
                tvLabelMenuItemWareHouseSupplies.setTextColor(getResources().getColor(colorActiveMenuItem));
                setMenuItemSeasonDefault();
                setMenuItemPlatingAreaDefault();
                setMenuMenuItemSeedsDefault();
                setMenuItemDevicesDefault();
                setMenuItemContractDefault();
                setMenuItemNotificationDefault();
                setMenuItemAccountDefault();
                setMenuItemFeedBackDefault();
                setMenuItemSignOutDefault();
                break;
            }
            case 4: {
                ivMenuItemDevices.setBackground(getResources().getDrawable(menuIconsActive[positionChecked]));
                tvLabelMenuItemDevices.setTextColor(getResources().getColor(colorActiveMenuItem));
                setMenuItemSeasonDefault();
                setMenuItemPlatingAreaDefault();
                setMenuMenuItemSeedsDefault();
                setMenuItemWareHouseSuppliesDefault();
                setMenuItemContractDefault();
                setMenuItemNotificationDefault();
                setMenuItemAccountDefault();
                setMenuItemFeedBackDefault();
                setMenuItemSignOutDefault();
                break;
            }
            case 5: {
                ivMenuItemContract.setBackground(getResources().getDrawable(menuIconsActive[positionChecked]));
                tvLabelMenuItemContract.setTextColor(getResources().getColor(colorActiveMenuItem));
                setMenuItemSeasonDefault();
                setMenuItemPlatingAreaDefault();
                setMenuMenuItemSeedsDefault();
                setMenuItemWareHouseSuppliesDefault();
                setMenuItemDevicesDefault();
                setMenuItemNotificationDefault();
                setMenuItemAccountDefault();
                setMenuItemFeedBackDefault();
                setMenuItemSignOutDefault();
                break;
            }
            case 6: {
                ivMenuItemNotification.setBackground(getResources().getDrawable(menuIconsActive[positionChecked]));
                tvLabelMenuItemNotification.setTextColor(getResources().getColor(colorActiveMenuItem));
                setMenuItemSeasonDefault();
                setMenuItemPlatingAreaDefault();
                setMenuMenuItemSeedsDefault();
                setMenuItemWareHouseSuppliesDefault();
                setMenuItemDevicesDefault();
                setMenuItemContractDefault();
                setMenuItemAccountDefault();
                setMenuItemFeedBackDefault();
                setMenuItemSignOutDefault();
                break;
            }
            case 7: {
                ivMenuItemAccount.setBackground(getResources().getDrawable(menuIconsActive[positionChecked]));
                tvLabelMenuItemAccount.setTextColor(getResources().getColor(colorActiveMenuItem));
                setMenuItemSeasonDefault();
                setMenuItemPlatingAreaDefault();
                setMenuMenuItemSeedsDefault();
                setMenuItemWareHouseSuppliesDefault();
                setMenuItemDevicesDefault();
                setMenuItemContractDefault();
                setMenuItemNotificationDefault();
                setMenuItemFeedBackDefault();
                setMenuItemSignOutDefault();
                break;
            }
            case 8: {
                ivMenuItemFeedBack.setBackground(getResources().getDrawable(menuIconsActive[positionChecked]));
                tvLabelMenuItemFeedBack.setTextColor(getResources().getColor(colorActiveMenuItem));
                setMenuItemSeasonDefault();
                setMenuItemPlatingAreaDefault();
                setMenuMenuItemSeedsDefault();
                setMenuItemWareHouseSuppliesDefault();
                setMenuItemDevicesDefault();
                setMenuItemContractDefault();
                setMenuItemNotificationDefault();
                setMenuItemAccountDefault();
                setMenuItemSignOutDefault();
                break;
            }
            case 9: {
                ivMenuItemSignOut.setBackground(getResources().getDrawable(menuIconsActive[positionChecked]));
                tvLabelMenuItemSignOut.setTextColor(getResources().getColor(colorActiveMenuItem));
                setMenuItemSeasonDefault();
                setMenuItemPlatingAreaDefault();
                setMenuMenuItemSeedsDefault();
                setMenuItemWareHouseSuppliesDefault();
                setMenuItemDevicesDefault();
                setMenuItemContractDefault();
                setMenuItemNotificationDefault();
                setMenuItemAccountDefault();
                setMenuItemFeedBackDefault();
                break;
            }
        }
    }

    private void initEvent() {
        lyContainerIvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });

        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });

        rlContainerAvatarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
            }
        });

        lyContainerNumberNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
            }
        });

        tvNumberNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
            }
        });

        ivChoosePosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPosition();
            }
        });

        tvNamePosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPosition();
            }
        });

        btnFilterOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!statusFilter) {
                    statusFilter = true;
                    btnFilterOrder.setBackground(getResources().getDrawable(R.drawable.ic_filter_active_icon));
                } else {
                    statusFilter = false;
                    btnFilterOrder.setBackground(getResources().getDrawable(R.drawable.ic_filter_default_icon));
                }

                popupFilter = new PopupWindow(filterView,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        true);
                SystemPopupWindow.showPopupWindow(MainActivity.this, popupFilter, lyMain, 0);
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupFilter.dismiss();
            }
        });

        btnCancelFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupFilter.dismiss();
            }
        });
//        chọn ngày bắt đầu lọc
        tvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SystemDatePickerDialog.showCalendar(
                        MainActivity.this, tvStartDate, "Start date");
            }
        });

//        chọn ngày lọc cuối cùng
        tvEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SystemDatePickerDialog.showCalendar(
                        MainActivity.this, tvEndDate, "End date");
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (navItemIndex == 1) {
                    iMainViewModel.callApiOrderType();
                }
            }
        });
    }

    //    hiển thị dialog chọn vùng kinh doanh
    private void showPosition() {
        String organizationHierarchy = "{\"OrganizationHierarchy\" :" + appLoginDetail.getOrganizationHierarchy() + "}";
        Gson gson = new Gson();
        OrganizationHierarchyList organizationHierarchyList = gson.fromJson(organizationHierarchy, OrganizationHierarchyList.class);
        ChooseAddressDialog chooseAddressDialog = new ChooseAddressDialog();
        Bundle args = new Bundle();
        args.putString("keyChoose", "OrganizationHierarchy");
        args.putString("titleChooseDialog", getResources().getString(R.string.label_title_place));
        args.putParcelableArrayList("listSelectOneLabel", (ArrayList<OrganizationHierarchy>) organizationHierarchyList.getOrganizationHierarchy());
        chooseAddressDialog.setArguments(args);
        SystemDialog.showFragmentDialog(fm, chooseAddressDialog, "choose_address_dialog");
    }

    private void loadHomePage() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
        tvTitleActionBar.setText(titles[navItemIndex]);
        if (navItemIndex == 5) {
            tvTitleActionBar.setText(titles[navItemIndex] + " (1.002)");
        }
        if (navItemIndex == 0) {
            tvTitleActionBar.setGravity(Gravity.LEFT);
            tvTitleActionBar.setText(appLoginDetail.getFullName());
            rlContainerAvatarUser.setVisibility(View.VISIBLE);
            rlContainerFilterOrder.setVisibility(View.GONE);
        } else {
            tvTitleActionBar.setGravity(Gravity.CENTER);
            if (navItemIndex == 1) {
                rlContainerAvatarUser.setVisibility(View.GONE);
                rlContainerFilterOrder.setVisibility(View.VISIBLE);
            } else if (navItemIndex == 4) {
                rlContainerAvatarUser.setVisibility(View.GONE);
                rlContainerFilterOrder.setVisibility(View.VISIBLE);
            } else {
                rlContainerAvatarUser.setVisibility(View.GONE);
                rlContainerFilterOrder.setVisibility(View.GONE);
            }
        }
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            toggleFab();
            return;
        }
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
        toggleFab();
        drawer.closeDrawers();
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                HomeFragment homeFragment = new HomeFragment();
//                Bundle args = new Bundle();
//                args.putString("userId",userId);
//                homeFragment.setArguments(args);
                return homeFragment;
            case 1:
                SaleOrderFragment orderFragment = new SaleOrderFragment();
                return orderFragment;
            case 2:
                ReceiveMoneyFragment receiveMoneyFragment = new ReceiveMoneyFragment();
                return receiveMoneyFragment;
            case 4:
                DebtCustomerFragment debtCustomerFragment = new DebtCustomerFragment();
                return debtCustomerFragment;
            case 5:
                ProductFragment productFragment = new ProductFragment();
                return productFragment;
            default:
                return new DefaultFragment();
        }
    }


    private void setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_season:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_SEASON;
                        break;
                    case R.id.nav_planting_area:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_PLANTING_AREA;
                        break;
                    case R.id.nav_seeds:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_SEEDS;
                        break;
                    case R.id.nav_warehouse_supplies:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_WAREHOUSE_SUPPLIES;
                        break;
                    case R.id.nav_devices:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_DEVICES;
                        break;
                    case R.id.nav_contract:
                        navItemIndex = 5;
                        CURRENT_TAG = TAG_CONTRACT;
                        break;
                    case R.id.nav_notification:
                        navItemIndex = 6;
                        setActiveMenuItemChoose(navItemIndex);
//                        startActivity(new Intent(DashboardActivity.this, NotificationActivity.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_account:
                        navItemIndex = 7;
                        setActiveMenuItemChoose(navItemIndex);
//                        startActivity(new Intent(DashboardActivity.this, AccountActivity.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_feed_back:
                        navItemIndex = 8;
                        setActiveMenuItemChoose(navItemIndex);
//                        startActivity(new Intent(DashboardActivity.this, FeedBackActivity.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_sign_out:
                        navItemIndex = 9;
                        setActiveMenuItemChoose(navItemIndex);
//                        Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
                        drawer.closeDrawers();
                        return true;
                    default:
                        navItemIndex = 0;
                }
                setActiveMenuItemChoose(navItemIndex);
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);
                loadHomePage();
                return true;
            }
        });
    }

    private void setMenuItemSeasonDefault() {
        ivMenuItemSeason.setBackground(getResources().getDrawable(menuIconsDefault[0]));
        tvLabelMenuItemSeason.setTextColor(getResources().getColor(colorDefaultMenuItem));
    }

    private void setMenuItemPlatingAreaDefault() {
        ivMenuItemPlatingArea.setBackground(getResources().getDrawable(menuIconsDefault[1]));
        tvLabelMenuItemPlatingArea.setTextColor(getResources().getColor(colorDefaultMenuItem));
    }

    private void setMenuMenuItemSeedsDefault() {
        ivMenuItemSeeds.setBackground(getResources().getDrawable(menuIconsDefault[2]));
        tvLabelMenuItemSeeds.setTextColor(getResources().getColor(colorDefaultMenuItem));
    }

    private void setMenuItemWareHouseSuppliesDefault() {
        ivMenuItemWareHouseSupplies.setBackground(getResources().getDrawable(menuIconsDefault[3]));
        tvLabelMenuItemWareHouseSupplies.setTextColor(getResources().getColor(colorDefaultMenuItem));

    }

    private void setMenuItemDevicesDefault() {
        ivMenuItemDevices.setBackground(getResources().getDrawable(menuIconsDefault[4]));
        tvLabelMenuItemDevices.setTextColor(getResources().getColor(colorDefaultMenuItem));
    }

    private void setMenuItemContractDefault() {
        ivMenuItemContract.setBackground(getResources().getDrawable(menuIconsDefault[5]));
        tvLabelMenuItemContract.setTextColor(getResources().getColor(colorDefaultMenuItem));
    }

    private void setMenuItemNotificationDefault() {
        ivMenuItemNotification.setBackground(getResources().getDrawable(menuIconsDefault[6]));
        tvLabelMenuItemNotification.setTextColor(getResources().getColor(colorDefaultMenuItem));
    }

    private void setMenuItemAccountDefault() {
        ivMenuItemAccount.setBackground(getResources().getDrawable(menuIconsDefault[7]));
        tvLabelMenuItemAccount.setTextColor(getResources().getColor(colorDefaultMenuItem));
    }

    private void setMenuItemFeedBackDefault() {
        ivMenuItemFeedBack.setBackground(getResources().getDrawable(menuIconsDefault[8]));
        tvLabelMenuItemFeedBack.setTextColor(getResources().getColor(colorDefaultMenuItem));
    }

    private void setMenuItemSignOutDefault() {
        ivMenuItemSignOut.setBackground(getResources().getDrawable(menuIconsDefault[9]));
        tvLabelMenuItemSignOut.setTextColor(getResources().getColor(colorDefaultMenuItem));
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }
        super.onBackPressed();
    }

    private void toggleFab() {
        if (navItemIndex == 1)
            fab.show();
        else
            fab.hide();
    }

    private void transparentStatusAndNavigation() {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setTranslucentStatusFlag(true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setTranslucentStatusFlag(false);
            getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.dFFFFFF));
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        navigationView.setPadding(0, getStatusBarHeight(), 0, 0);
    }

    private void setTranslucentStatusFlag(boolean on) {
        if (Build.VERSION.SDK_INT >= 19) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }

    //lấy chiều cao status bar
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public void itemSelectOneLabel(String key, ISelectOneLabel iSelectOneLabel) {
        // cập nhật thông tin và tải lại dữ liệu các màn hình sau khi chọn vùng kinh doanh
        if (key.equals("OrganizationHierarchy")) {
            OrganizationHierarchy organizationHierarchy = (OrganizationHierarchy) iSelectOneLabel;
            ADCSaleAgrotechApplication adcTransportApplication = (ADCSaleAgrotechApplication) context.getApplicationContext();
            AppLoginDetail appLoginDetail = adcTransportApplication.getAppLoginDetail();
            appLoginDetail.setOrganizationId(organizationHierarchy.getOrganizationId());
            appLoginDetail.setOrganizationName(organizationHierarchy.getOrganizationName());
            adcTransportApplication.setAppLoginDetail(appLoginDetail);
            Intent intentSMS = new Intent("UPDATE_ORGANIZATION_NAME");
            context.sendBroadcast(intentSMS);
            initData();
        }
    }
}
