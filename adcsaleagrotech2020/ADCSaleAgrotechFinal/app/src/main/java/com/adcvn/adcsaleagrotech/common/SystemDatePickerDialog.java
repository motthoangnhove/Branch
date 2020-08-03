package com.adcvn.adcsaleagrotech.common;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.adcvn.adcsaleagrotech.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SystemDatePickerDialog {
    //    hiển thị dialog chọn ngày tháng
    @SuppressLint("WrongConstant")
    public static void showCalendar(Context context, final TextView textView, String title) {
        //        Khai báo view lịch
        final View view = LayoutInflater.from(context).inflate(R.layout.calendar_dialog, null, false);
        final TextView tvYear = view.findViewById(R.id.tvYear);
        final TextView tvDate = view.findViewById(R.id.tvDate);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        CalendarView calenderView = view.findViewById(R.id.calendarView);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            Log.d("SDK VERSION", "LOLLIPOP");
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) calenderView.getLayoutParams();
            lp.height = (int) context.getResources().getDimension(R.dimen.d320dp);
            lp.setMargins(25,0,25,0);
            calenderView.setLayoutParams(lp);
            calenderView.setShownWeekCount(6);
            calenderView.setShowWeekNumber(false);
            calenderView.setSelectedDateVerticalBar(context.getResources().getDrawable(R.drawable.bg_button_action));
            calenderView.setSelectedWeekBackgroundColor(context.getResources().getColor(android.R.color.transparent));
            calenderView.setFocusedMonthDateColor(context.getResources().getColor(R.color.d7A7F83));
            calenderView.setUnfocusedMonthDateColor(context.getResources().getColor(R.color.d4D9E9E9E));
            calenderView.setWeekDayTextAppearance(R.style.TextRobotoMediumBasic);
            calenderView.setWeekSeparatorLineColor(context.getResources().getColor(android.R.color.transparent));
        }

        //        khởi tạo ngày tháng ban đầu
        int initYear, initMonth, initDay;
        final Calendar cal = Calendar.getInstance();
        initYear = cal.get(Calendar.YEAR);
        initMonth = cal.get(Calendar.MONTH);
        initDay = cal.get(Calendar.DAY_OF_MONTH);
        if (!textView.getText().toString().equals("")) {
            String selectedDate = textView.getText().toString();
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date date = sdf.parse(selectedDate);
                cal.setTime(date);
                initYear = cal.get(Calendar.YEAR);
                initMonth = cal.get(Calendar.MONTH);
                initDay = cal.get(Calendar.DAY_OF_MONTH);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        calenderView.setDate(cal.getTimeInMillis(), false, true);

        //        Khởi tạo calendar
        final DatePickerDialog dialog = new DatePickerDialog(context,
                R.style.DatePickerDialog,
                new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker datePicker, int year,
                                          int monthOfYear, int dayOfMonth) {//trả về ngày tháng đã chọn
                        String day, month;
                        day = String.valueOf(dayOfMonth);
                        month = String.valueOf(monthOfYear + 1);
                        if (dayOfMonth < 10) {
                            day = "0" + day;
                        }
                        if (monthOfYear < 9) {
                            month = "0" + month;
                        }
                        textView.setText(day + "/" + month + "/" + year);
                    }
                }, initYear, initMonth, initDay);

        //        set button, background lịch và hiển thị lên màn hình
        dialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, context.getResources().getString(R.string.label_exit), dialog);
        dialog.setButton(DatePickerDialog.BUTTON_POSITIVE, context.getResources().getString(R.string.label_text_choose), dialog);
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();
        //        Khởi tạo dữ liệu bang đầu
        tvTitle.setText(title);
        tvDate.setText(SystemDateTime.convertDateToShowCalender(cal));
        tvYear.setText(String.valueOf(cal.get(Calendar.YEAR)));

        //        Thay đổi các dữ liệu khi chọn vào một ngày
        calenderView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                tvDate.setText(SystemDateTime.convertDateToShowCalender(cal));
                tvYear.setText(year + "");
                dialog.onDateChanged(new DatePicker(dialog.getContext()), year, month, dayOfMonth);
            }
        });
    }
}
