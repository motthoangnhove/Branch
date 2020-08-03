package com.adcvn.adcsaleagrotech.common;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.adcvn.adcsaleagrotech.R;

public class SystemPopupWindow {
    public static void showPopupWindow(Context context, PopupWindow popup, LinearLayout layout, int type) {
        popup.setBackgroundDrawable(new BitmapDrawable());
        popup.setOutsideTouchable(true);
        if (type == 0) {
            popup.setAnimationStyle(R.style.DialogAnimation);
            popup.showAtLocation(layout, Gravity.BOTTOM, -0, -0);
            View container = popup.getContentView().getRootView();
            if (container != null) {
                WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) container.getLayoutParams();
                layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                layoutParams.dimAmount = 0.6f;
                if (windowManager != null) {
                    windowManager.updateViewLayout(container, layoutParams);
                }
            }
        } else {
            popup.setAnimationStyle(R.style.popup_window_animation_phone);
            popup.showAtLocation(layout, Gravity.TOP | Gravity.END, 0, 0);
        }
    }


}
