package com.adcvn.adcsaleagrotech.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.IDoubleField;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.ILongField;
import com.adcvn.adcsaleagrotech.middleinterface.uimiddleinterface.IStringField;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class Common {
    public static String token;
    //định dạng giá trị phần nghìn 1,000,000
    public static String formatNumber(double number) {
        return NumberFormat.getNumberInstance(Locale.US).format(number);
    }

    // ẩn bàn phím điện thoại
    public static void hideSoftKeyboard(Context context, EditText editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    //hiện bàn phím điện thoại
    public static void showSoftKeyboard(Context context, EditText editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    // format lấy số điện thoại nhập vào 0988-120-201 -> 0988120201
    public static String getNumberPhoneFromMask(String numberMask) {
        String numberPhone = "";
        StringTokenizer maskElements = new StringTokenizer(numberMask, " - ");
        List<String> numberPhoneElements = new ArrayList<String>();
        while (maskElements.hasMoreTokens()) {
            numberPhoneElements.add(maskElements.nextToken());
        }
        for (int i = 0; i < numberPhoneElements.size(); i++) {
            numberPhone += numberPhoneElements.get(i);
        }
        return numberPhone;
    }

    //kiểm tra tình trạng wifi
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    //Format số điện thoại từ 0938234569 -> 0938 234 569
    public static String getPhoneFormat(String number) {
        return number.replaceFirst("(\\d{4})(\\d{3})(\\d+)", "$1 $2 $3");
    }

    // search contain item in list
    public static boolean searchItemInList(String text, List<IStringField> list){
        for(IStringField item : list){
            if(item.getStrValue().equals(text)){
                return true;
            }
        }
        return false;
    }

    // get sum double field
    public static double getTotalLongField(List<IDoubleField> list){
        double sumDouble = 0;
        for(IDoubleField item : list){
            sumDouble+= item.getDoubleValue();
        }
        return sumDouble;
    }

    // xử lý tạo đối tượng danh sách sản phẩm đơn hàng
    public static String getJSONList(java.util.List<Object> list,String classObject,String segNum, String quantity,String amount,String productId, String unitId, String unitPrice) {
        try {
            Object[] args={};
            Class cl = Class.forName(classObject);
            Method getSegNum = cl.getMethod(segNum,null);
            Method getQuantity = cl.getMethod(quantity, null);
            Method getAmount = cl.getMethod(amount, null);
            Method getProductId = cl.getMethod(productId, null);
            Method getUnitId = cl.getMethod(unitId,null);
            Method getUnitPrice = cl.getMethod(unitPrice,null);
            String json="[";
            for (int i = 0; i < list.size(); i++) {
                Object o = list.get(i);
                if(i>0){
                    json+=",";
                }
                json+="{\"SeqNum\":\""+getSegNum.invoke(o,args)+"\",\"Quantity\":\""+getQuantity.invoke(o,args)+"\",\"Amount\":\""+new BigDecimal(String.valueOf(getAmount.invoke(o,args))).toBigInteger()+"\",\"ProductId\":\""+getProductId.invoke(o,args)+"\",\"UnitId\":\""+getUnitId.invoke(o,args)+"\",\"UnitPrice\":\""+getUnitPrice.invoke(o,args)+"\"}";
            }
            json+="]";
            return json;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "[]";
    }
}
