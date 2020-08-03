package com.adcvn.adcsaleagrotech.common;

import java.text.Normalizer;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class TranslateText {

    // format lấy số điện thoại nhập vào 0988-120-201 -> 0988120201
    public static String  getNumberPhoneFromMask(String numberMask){
        String numberPhone = "";
        StringTokenizer maskElements = new StringTokenizer(numberMask, " - ");
        List<String> numberPhoneElements = new ArrayList<String>();
        while (maskElements.hasMoreTokens()) {
            numberPhoneElements.add(maskElements.nextToken());
        }
        for(int i = 0;i<numberPhoneElements.size();i++){
            numberPhone += numberPhoneElements.get(i);
        }
        return numberPhone;
    }

    // format định dạng xóa dấu chữ việt
    public static String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    // format định dạng số phần ngàn 1,000
    public static String formatNumber(double number){
        return NumberFormat.getNumberInstance(Locale.US).format(number);
    }

}
