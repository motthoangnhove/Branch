package com.adcvn.adcsaleagrotech.action.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.adapter.productfragment.ProductFragmentAdapter;
import com.adcvn.adcsaleagrotech.model.productfragment.ProductFragmentModel;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {

    private RecyclerView rvOrder;
    private ProductFragmentAdapter adapter;
    private List<ProductFragmentModel> productList = new ArrayList<>();
    private Context context;
    private FragmentManager fm;

    public ProductFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getActivity();
        fm = getActivity().getSupportFragmentManager();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_product, container, false);
        initView(root);
        return root;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initEvent();
        initRecyclerview();
    }

//    khởi tạo recyclerview
    private void initRecyclerview() {
        for (int i = 0; i < 8; i++) {
            ProductFragmentModel item = new ProductFragmentModel("", "Help 400SC 250ml", "Thuôc trừ bệnh", "Chai", 3000);
            productList.add(item);
        }
          LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvOrder.setLayoutManager(layoutManager);
        adapter = new ProductFragmentAdapter(context, productList);
        rvOrder.setAdapter(adapter);
    }

    //    khởi tạo giao diện
    private void initView(View root) {
        rvOrder = root.findViewById(R.id.rvOrder);
    }

//    khởi tạo sự kiện
    private void initEvent() {

    }
}