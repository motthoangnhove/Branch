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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.adapter.choose.OneLabelChooseAdapter;
import com.adcvn.adcsaleagrotech.common.Common;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ISelectOneLabel;

import java.util.ArrayList;
import java.util.List;

public class ChooseOrderTypeDialog extends DialogFragment {
    private String keyChoose;
    private EditText edtSearchItem;
    private ImageButton ibSearchItem;
    private ImageView ivExitChooseDialog;
    private RecyclerView rvListSelectOneLabel;
    private List<ISelectOneLabel> listSelectOneLabel = new ArrayList<>();
    private OneLabelChooseAdapter rvListSelectOneLabelAdapter;
    private boolean statusSearchButton = true;
    private Dialog dialog;
    private Activity activity;
    private Context context;
    public ChooseOrderTypeDialog() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_items_dialog, container, false);
        activity = this.getActivity();
        context = this.getActivity();
        dialog = this.getDialog();
        keyChoose = getArguments().getString("keyChoose", "");
        listSelectOneLabel = getArguments().getParcelableArrayList("listSelectOneLabel");
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            getDialog().setCanceledOnTouchOutside(false);
        }
        initView(view);
        initEvent();
        return view;
    }

    private void initView(View view) {
        ivExitChooseDialog = view.findViewById(R.id.ivExitChooseDialog);
        edtSearchItem = view.findViewById(R.id.edtSearchItem);
        ibSearchItem = view.findViewById(R.id.ibSearchItem);
        rvListSelectOneLabel = view.findViewById(R.id.rvListChoose);
        LinearLayoutManager linearLayoutRecycleView = new LinearLayoutManager(context);
        rvListSelectOneLabel.setLayoutManager(linearLayoutRecycleView);
        rvListSelectOneLabelAdapter = new OneLabelChooseAdapter(context, activity, dialog, keyChoose, listSelectOneLabel);
        rvListSelectOneLabel.setAdapter(rvListSelectOneLabelAdapter);
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
                                String searchText = edtSearchItem.getText().toString();
                                rvListSelectOneLabelAdapter.getFilter().filter(searchText);
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
                String searchText = edtSearchItem.getText().toString();
                rvListSelectOneLabelAdapter.getFilter().filter(searchText);
            }
        });
    }

}
