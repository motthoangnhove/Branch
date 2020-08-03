package com.adcvn.adcsaleagrotech.common;
import java.lang.reflect.Method;
import java.math.BigDecimal;

public class ToJsonString {
    // list order to string json
    public static String getJSONList(java.util.List<Object> list, String classObject, String segNum, String productCode, String quantity, String unitPrice, String unitPriceAfterTax, String vatRate) {
        try {
            Object[] args={};
            Class cl = Class.forName(classObject);
            Method getSegNum = cl.getMethod(segNum,null);
            Method getProductCode = cl.getMethod(productCode, null);
            Method getQuantity = cl.getMethod(quantity, null);
            Method getUnitPrice = cl.getMethod(unitPrice,null);
            Method getUnitPriceAfterTax = cl.getMethod(unitPriceAfterTax, null);
            Method getVATRate = cl.getMethod(vatRate, null);
            String json="[";
            for (int i = 0; i < list.size(); i++) {
                Object o = list.get(i);
                if(i>0){
                    json+=",";
                }
                json+="{\"SeqNum\":\""+getSegNum.invoke(o,args)+"\",\"ProductCode\":\""+getProductCode.invoke(o,args)+"\",\"Quantity\":\""+getQuantity.invoke(o,args)+"\",\"UnitPrice\":\""+getUnitPrice.invoke(o,args)+"\",\"UnitPriceAfterTax\":\""+new BigDecimal(String.valueOf(getUnitPriceAfterTax.invoke(o,args))).toBigInteger()+"\",\"VATRate\":\""+getVATRate.invoke(o,args)+"\"}";
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
