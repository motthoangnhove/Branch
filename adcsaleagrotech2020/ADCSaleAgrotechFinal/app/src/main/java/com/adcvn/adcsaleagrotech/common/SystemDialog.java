package com.adcvn.adcsaleagrotech.common;

import android.app.Dialog;
import android.content.Context;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.adcvn.adcsaleagrotech.R;

public class SystemDialog {

    // hiển thị dialog xử lý tiến trình request
    public static Dialog showProcessDialog(Context context){
        Dialog progressDialog = new Dialog(context);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.setContentView(R.layout.custom_progressbar_dialog);
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    //show fragment dialog
    public static void showFragmentDialog(FragmentManager fm, DialogFragment dialog, String tag) {
        try {
            dialog.show(fm, tag);
            dialog.setCancelable(true);
        } catch (Exception e) {
        }
    }
}
