package com.adcvn.adcsaleagrotech.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.adapter.choose.ThreeLabelChooseType1Adapter;
import com.adcvn.adcsaleagrotech.common.Common;
import com.adcvn.adcsaleagrotech.common.Constant;
import com.adcvn.adcsaleagrotech.common.TranslateText;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IOMCustomerList;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectThreeLabel;
import com.adcvn.adcsaleagrotech.model.omcustomerlist.OMCustomerListResult;
import com.adcvn.adcsaleagrotech.server.CenterCallApi;

import java.util.ArrayList;
import java.util.List;

public class ChooseCustomerDialog extends DialogFragment implements IOMCustomerList {
    private String salesPersonId, keyChoose;
    private EditText edtSearchItem;
    private ImageButton ibSearchItem;
    private ImageView ivExitChooseDialog;
    private RecyclerView rvListSelectTwoLabel;
    private List<ISelectThreeLabel> listSelectThreeLabel = new ArrayList<>();
    private ThreeLabelChooseType1Adapter rvListSelectThreeLabelAdapter;
    private boolean statusSearchButton = true, outOfData;
    private int skip;
    private Dialog dialog;
    private Activity activity;
    private Context context;
    private CenterCallApi centerCallApi;
    private FragmentManager fm;

    public ChooseCustomerDialog() {
        setStyle(STYLE_NO_FRAME, R.style.dialog_full_theme);
    }

    @Override
    public int getTheme() {
        return super.getTheme();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_items_dialog, container, false);
        activity = this.getActivity();
        context = this.getActivity();
        dialog = this.getDialog();
        fm = getActivity().getSupportFragmentManager();
        centerCallApi = new CenterCallApi(context);
        salesPersonId = getArguments().getString("salesPersonId", "");
        keyChoose = getArguments().getString("keyChoose", "");
        listSelectThreeLabel = getArguments().getParcelableArrayList("listSelectThreeLabel");
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

    private void initView(View view) {
        ivExitChooseDialog = view.findViewById(R.id.ivExitChooseDialog);
        edtSearchItem = view.findViewById(R.id.edtSearchItem);
        ibSearchItem = view.findViewById(R.id.ibSearchItem);
        rvListSelectTwoLabel = view.findViewById(R.id.rvListChoose);
        LinearLayoutManager linearLayoutRecycleView = new LinearLayoutManager(context);
        rvListSelectTwoLabel.setLayoutManager(linearLayoutRecycleView);
        rvListSelectThreeLabelAdapter = new ThreeLabelChooseType1Adapter(context, activity, dialog, keyChoose, listSelectThreeLabel);
        rvListSelectTwoLabel.setAdapter(rvListSelectThreeLabelAdapter);
    }

    private void initEvent() {

        ivExitChooseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        edtSearchItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(final Editable s) {
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
                                if (edtSearchItem.getText().length() > 0) {
                                    statusSearchButton = false;
                                    ibSearchItem.setBackground(getResources().getDrawable(R.drawable.ic_clear));
                                }
                                searchCustomerList();
                                Common.hideSoftKeyboard(activity, edtSearchItem);
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
                Common.hideSoftKeyboard(activity, edtSearchItem);
                if (statusSearchButton) {
                    statusSearchButton = false;
                    ibSearchItem.setBackground(getResources().getDrawable(R.drawable.ic_clear));
                } else {
                    statusSearchButton = true;
                    ibSearchItem.setBackground(getResources().getDrawable(R.drawable.ic_search));
                    edtSearchItem.setText("");
                }
                searchCustomerList();
            }
        });

        rvListSelectTwoLabel.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!outOfData) {
                        skip += Constant.MAX_PAGE_ELEMENT;
                        refreshNewDataBySkip();
                    }
                }
            }
        });
    }

    // khởi tạo dữ liệu ban đầu cho các thành phần giao diện
    private void initData(){
       if(listSelectThreeLabel.size() <= Constant.MAX_PAGE_ELEMENT){
           outOfData = true;
       }
    }

    // refresh lại trạng thái tìm kiếm dữ liệu mới
    private void searchCustomerList() {
        skip = 0;
        outOfData = false;
        listSelectThreeLabel.clear();
        rvListSelectThreeLabelAdapter.notifyDataSetChanged();
        refreshNewDataBySkip();
    }


    //lấy danh sách khách hàng theo trang
    private void refreshNewDataBySkip() {
        String searchText = TranslateText.deAccent(edtSearchItem.getText().toString());
        centerCallApi.oMCustomerList(this, salesPersonId, skip, searchText, fm);
    }


    @Override
    public void getOMCustomerList(OMCustomerListResult omCustomerListResult) {
        if (omCustomerListResult.getOdataCount() < Constant.MAX_PAGE_ELEMENT) {
            this.outOfData = true;
        }
        listSelectThreeLabel.addAll(omCustomerListResult.getOMCustomer());
        rvListSelectThreeLabelAdapter.notifyDataSetChanged();
    }
}