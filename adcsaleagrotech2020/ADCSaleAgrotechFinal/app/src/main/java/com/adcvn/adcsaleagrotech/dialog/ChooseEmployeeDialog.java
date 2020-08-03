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
import com.adcvn.adcsaleagrotech.adapter.choose.TwoLabelChooseAdapter;
import com.adcvn.adcsaleagrotech.application.ADCSaleAgrotechApplication;
import com.adcvn.adcsaleagrotech.common.Common;
import com.adcvn.adcsaleagrotech.common.Constant;
import com.adcvn.adcsaleagrotech.common.TranslateText;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IOMEmployeeList;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectTwoLabel;
import com.adcvn.adcsaleagrotech.model.applogin.AppLoginDetail;
import com.adcvn.adcsaleagrotech.model.omemployeelist.OMEmployeeListResult;
import com.adcvn.adcsaleagrotech.server.CenterCallApi;
import java.util.ArrayList;
import java.util.List;

public class ChooseEmployeeDialog  extends DialogFragment implements IOMEmployeeList {
    private String keyChoose;
    private EditText edtSearchItem;
    private ImageButton ibSearchItem;
    private ImageView ivExitChooseDialog;
    private RecyclerView rvListSelectTwoLabel;
    private List<ISelectTwoLabel> listSelectTwoLabel = new ArrayList<>();
    private TwoLabelChooseAdapter rvListSelectTwoLabelAdapter;
    private boolean statusSearchButton = true, outOfData;
    private int skip;
    private AppLoginDetail appLoginDetail;
    private Dialog dialog;
    private Activity activity;
    private Context context;
    private CenterCallApi centerCallApi;
    private FragmentManager fm;

    public ChooseEmployeeDialog() {
        setStyle(STYLE_NO_FRAME, R.style.dialog_full_theme);
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
        appLoginDetail =((ADCSaleAgrotechApplication) context.getApplicationContext()).getAppLoginDetail();
        keyChoose = getArguments().getString("keyChoose", "");
        listSelectTwoLabel = getArguments().getParcelableArrayList("listSelectTwoLabel");
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
        rvListSelectTwoLabelAdapter = new TwoLabelChooseAdapter(context, getActivity(),dialog, keyChoose, listSelectTwoLabel);
        rvListSelectTwoLabel.setAdapter(rvListSelectTwoLabelAdapter);
    }

    private void initEvent(){

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
                                searchEmployeeList();
                                Common.hideSoftKeyboard(activity,edtSearchItem);
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
                if(statusSearchButton){
                    statusSearchButton = false;
                    ibSearchItem.setBackground(getResources().getDrawable(R.drawable.ic_clear));
                }else{
                    statusSearchButton = true;
                    ibSearchItem.setBackground(getResources().getDrawable(R.drawable.ic_search));
                    edtSearchItem.setText("");
                }
                searchEmployeeList();
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
        if(listSelectTwoLabel.size() <= Constant.MAX_PAGE_ELEMENT){
            outOfData = true;
        }
    }

    // refresh lại trạng thái tìm kiếm dữ liệu mới
    private void searchEmployeeList(){
        skip = 0;
        outOfData = false;
        listSelectTwoLabel.clear();
        rvListSelectTwoLabelAdapter.notifyDataSetChanged();
        refreshNewDataBySkip();
    }


    //lấy danh sách nhân viên theo trang
    private void refreshNewDataBySkip(){
        String filter = getEmployeeListFilter();
        String searchText = TranslateText.deAccent(edtSearchItem.getText().toString());
        centerCallApi.oMEmployeeList(this, skip, filter, searchText, fm);
    }


    //lấy filter danh sách đầu vào danh sách nhân viên
    private String getEmployeeListFilter() {
        String strFilter ="OrganizationId eq '"+appLoginDetail.getOrganizationId()+"'";
        return strFilter;
    }


    @Override
    public void getOMEmployeeList(OMEmployeeListResult omEmployeeListResult) {
        if (omEmployeeListResult.getOdataCount() < Constant.MAX_PAGE_ELEMENT) {
            this.outOfData = true;
        }
        listSelectTwoLabel.addAll(omEmployeeListResult.getOMEmployee());
        rvListSelectTwoLabelAdapter.notifyDataSetChanged();
    }
}
