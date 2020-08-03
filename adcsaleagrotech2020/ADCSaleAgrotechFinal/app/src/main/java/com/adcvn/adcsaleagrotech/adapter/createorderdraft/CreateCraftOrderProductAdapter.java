package com.adcvn.adcsaleagrotech.adapter.createorderdraft;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.action.activity.createdraftorder.CreateDraftOrderActivity;
import com.adcvn.adcsaleagrotech.common.Common;
import com.adcvn.adcsaleagrotech.model.pricelistget.ProductDetail;

import java.util.List;

public class CreateCraftOrderProductAdapter extends RecyclerView.Adapter<CreateCraftOrderProductAdapter.RecyclerViewHolder> {
    private Context context;
    private List<ProductDetail> productsOrder;
    private CreateDraftOrderActivity createDraftOrderActivity;
    public CreateCraftOrderProductAdapter(CreateDraftOrderActivity createDraftOrderActivity, Context context, List<ProductDetail> productsOrder) {
        this.context = context;
        this.createDraftOrderActivity = createDraftOrderActivity;
        this.productsOrder = productsOrder;
    }

    @NonNull
    @Override
    public CreateCraftOrderProductAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_order_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, final int position) {
        final ProductDetail productOrder = productsOrder.get(position);
        holder.tvNameProduct.setText((position +1)+". "+productOrder.getProductName());
        holder.ivRemoveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productsOrder.remove(position);
                notifyDataSetChanged();
                if(createDraftOrderActivity != null) {
                    createDraftOrderActivity.updateListProduct();
                }
            }
        });
        holder.edtQuantityProduct.setText(Common.formatNumber(productOrder.getQuantity()));
        if(position == productsOrder.size() - 1){
            holder.edtQuantityProduct.requestFocus();
            InputMethodManager imm = (InputMethodManager)holder.edtQuantityProduct.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(holder.edtQuantityProduct, 0);
        }
        holder.edtQuantityProduct.addTextChangedListener(new TextWatcher() {
            String edtActualValueBefore = "";
            @Override
            public void afterTextChanged(Editable s) {
                if(!edtActualValueBefore .equals(s.toString())) {
                    if(s.toString().trim().length() > 0){
                        String strNumberInput = s.toString();
                        long numberInput = strNumberInput.contains(",") ? Long.valueOf(strNumberInput.replace(",", "")) : Long.valueOf(strNumberInput);
                        productOrder.setQuantity(numberInput);
                        productOrder.setTotalSalary(productOrder.getQuantity()*productOrder.getUnitPriceAfterTax());
                        holder.tvTotalMoneyProduct.setText(Common.formatNumber(productOrder.getTotalSalary()));
                        if(createDraftOrderActivity != null){
                           createDraftOrderActivity.updateListProduct();
                        }
                    }else{
                        productOrder.setQuantity(-1);
                        productOrder.setTotalSalary(-1);
                        holder.tvTotalMoneyProduct.setText("N/A");
                        Intent intentTotalMoneyNA = new Intent("UPDATE_TOTAL_MONEY_N/A");
                        context.sendBroadcast(intentTotalMoneyNA);
                    }
                }
                holder.edtQuantityProduct.setSelection(holder.edtQuantityProduct.getText().length());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                edtActualValueBefore = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.toString().length() == 0) {
                    holder.edtQuantityProduct.setSelection(holder.edtQuantityProduct.getText().toString().trim().length());
                }
            }
        });
        holder.tvUnitPrivate.setText(productOrder.getUnitName());
        holder.tvPriceProduct.setText(Common.formatNumber(productOrder.getUnitPriceAfterTax()));
        holder.tvTotalMoneyProduct.setText(Common.formatNumber(productOrder.getTotalSalary()));
    }

    @Override
    public int getItemCount() {
        return productsOrder.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNameProduct, tvUnitPrivate, tvPriceProduct, tvTotalMoneyProduct;
        private ImageView ivRemoveProduct;
        private EditText edtQuantityProduct;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameProduct = itemView.findViewById(R.id.tvNameProduct);
            ivRemoveProduct = itemView.findViewById(R.id.ivRemoveProduct);
            edtQuantityProduct = itemView.findViewById(R.id.edtQuantityProduct);
            tvUnitPrivate = itemView.findViewById(R.id.tvUnitPrivate);
            tvPriceProduct = itemView.findViewById(R.id.tvPriceProduct);
            tvTotalMoneyProduct = itemView.findViewById(R.id.tvTotalMoneyProduct);
        }
    }
}