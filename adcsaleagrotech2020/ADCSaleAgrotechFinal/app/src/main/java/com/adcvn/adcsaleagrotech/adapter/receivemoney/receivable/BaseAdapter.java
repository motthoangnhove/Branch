/*
 * MIT License
 *
 * Copyright (c) 2017 Cruxlab, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.adcvn.adcsaleagrotech.adapter.receivemoney.receivable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.adapter.receivemoney.receivable.view_holders.ReceivableHeaderNowViewHodler;
import com.adcvn.adcsaleagrotech.adapter.receivemoney.receivable.view_holders.ReceivableItemViewHodler;
import com.adcvn.adcsaleagrotech.common.Common;
import com.adcvn.adcsaleagrotech.model.receivemoney.receivablelist.ReceivableDetail;
import com.cruxlab.sectionedrecyclerview.lib.SectionAdapter;
import com.cruxlab.sectionedrecyclerview.lib.SectionManager;
import java.util.List;

public abstract class BaseAdapter<HVH extends ReceivableHeaderNowViewHodler> extends SectionAdapter<ReceivableItemViewHodler, HVH> {

    public List<ReceivableDetail> receivableList;
    public SectionManager sectionManager;
    public short type = 0;
    public String header;
    public int count;
    public Context context;


    public BaseAdapter(Context context, SectionManager sectionManager, boolean isHeaderVisible, boolean isHeaderPinned, List<ReceivableDetail> receivableList, String header, int count) {
        super(isHeaderVisible, isHeaderPinned);
        this.sectionManager = sectionManager;
        this.receivableList = receivableList;
        this.header = header;
        this.count = count;
        this.context = context;
    }

    @Override
    public void onBindHeaderViewHolder(HVH holder) {
        holder.bindHeader(header, count);
    }

    @Override
    public ReceivableItemViewHodler onCreateItemViewHolder(ViewGroup parent, short type) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receivable, parent, false);
        return new ReceivableItemViewHodler(view);
    }

    @Override
    public void onBindItemViewHolder(ReceivableItemViewHodler holder, int position) {
            holder.bindItem(receivableList.get(position), context);
    }
    public abstract BaseAdapter getCopy();

    public abstract BaseAdapter getNext();

    @Override
    public int getItemCount() {
        return receivableList.size();
    }
}