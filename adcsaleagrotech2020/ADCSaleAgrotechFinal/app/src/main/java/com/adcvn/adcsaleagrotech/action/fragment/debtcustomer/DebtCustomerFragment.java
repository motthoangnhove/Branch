package com.adcvn.adcsaleagrotech.action.fragment.debtcustomer;

import android.content.Context;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.adapter.debtcustomer.DebtCustomerAdapter;
import com.adcvn.adcsaleagrotech.common.Common;
import com.adcvn.adcsaleagrotech.model.debtcustomer.DebtCustomer;


import java.util.ArrayList;
import java.util.List;

public class DebtCustomerFragment extends Fragment {
    private EditText edtSearchItem;
    private ImageButton ibSearchItem;
    private RecyclerView rvDebtCustomer;
    private DebtCustomerAdapter debtCustomerAdapter;
    private List<DebtCustomer> listDebtCustomer = new ArrayList<>();
    private Context context;
    private boolean outOfData, statusSearchButton;
    private int skip;
    private FragmentManager fm;
    public DebtCustomerFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getActivity();
        fm = getActivity().getSupportFragmentManager();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_debt_customer, container, false);
        initView(root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initEvent();
    }

    // khởi tạo màn hình giao diện
    private void initView(View view) {
        edtSearchItem = view.findViewById(R.id.edtSearchItem);
        ibSearchItem = view.findViewById(R.id.ibSearchItem);
        DebtCustomer d1 = new DebtCustomer("TQ", "Hộ Kinh Doanh Cửa Hàng Vật Tư Nông Nghiệp Thanh Quang","Số nhà 191, ấp Tân Lộc, xã Lâm Tân, huyện Thạnh Trị, tỉnh Sóc Trăng", 12000000);
        DebtCustomer d2 = new DebtCustomer("QT", "Hộ Kinh Doanh Hồ Quang Tùng","Số nhà 200, Ấp Mỹ Đông 2, xã Mỹ Quới, thị xã Ngã Năm, Sóc Trăng", 99000000);
        DebtCustomer d3 = new DebtCustomer("LV", "Hộ Kinh Doanh Cửa Hàng Vật Tư Nông Nghiệp Linh Vân","Ngõ 292, Đường Lê Thái Tổ, Phố Lê Lợi, Phường Nam Thành, Thành phố Ninh Bình, Tỉnh Ninh Bình", 212000000);
        DebtCustomer d4 = new DebtCustomer("HH", "Hộ Kinh Doanh Cửa Hàng Vật Tư Nông Nghiệp Hoàng Hoa","Đường Vườn Đào, tổ 4, Phường Chiềng Cơi, Thành phố Sơn La, Tỉnh Sơn La", 80000000);
        DebtCustomer d5 = new DebtCustomer("BT", "Hộ Kinh Doanh Cửa Hàng Vật Tư Nông Nghiệp Bin Trần","Bản Lĩnh, Xã Mường Pồn, Huyện Điện Biên, Điện Biên", 40000000);
        listDebtCustomer.add(d1);
        listDebtCustomer.add(d2);
        listDebtCustomer.add(d3);
        listDebtCustomer.add(d4);
        listDebtCustomer.add(d5);
        rvDebtCustomer = view.findViewById(R.id.rvDebtCustomer);
        LinearLayoutManager linearLayoutRecycleView = new LinearLayoutManager(context);
        rvDebtCustomer.setLayoutManager(linearLayoutRecycleView);
        debtCustomerAdapter = new DebtCustomerAdapter(context, listDebtCustomer);
        rvDebtCustomer.setAdapter(debtCustomerAdapter);
    }

    // khởi tạo sự kiện giao diện màn hình
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

            }
        });
    }
}
