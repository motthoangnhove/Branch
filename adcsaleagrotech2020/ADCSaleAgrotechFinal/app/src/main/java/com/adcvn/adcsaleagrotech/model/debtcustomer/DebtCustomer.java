package com.adcvn.adcsaleagrotech.model.debtcustomer;

public class DebtCustomer {

 private String shortNameDebtCustomer, nameDebtCustomer, addressDebtCustomer;
 private long totalDebtCustomer;

    public DebtCustomer(){

    }

    public DebtCustomer(String shortNameDebtCustomer, String nameDebtCustomer, String addressDebtCustomer, long totalDebtCustomer){
     this.shortNameDebtCustomer = shortNameDebtCustomer;
     this.nameDebtCustomer = nameDebtCustomer;
     this.addressDebtCustomer = addressDebtCustomer;
     this.totalDebtCustomer = totalDebtCustomer;
    }

    public String getShortNameDebtCustomer() {
        return shortNameDebtCustomer;
    }

    public void setShortNameDebtCustomer(String shortNameDebtCustomer) {
        this.shortNameDebtCustomer = shortNameDebtCustomer;
    }

    public String getNameDebtCustomer() {
        return nameDebtCustomer;
    }

    public void setNameDebtCustomer(String nameDebtCustomer) {
        this.nameDebtCustomer = nameDebtCustomer;
    }

    public String getAddressDebtCustomer() {
        return addressDebtCustomer;
    }

    public void setAddressDebtCustomer(String addressDebtCustomer) {
        this.addressDebtCustomer = addressDebtCustomer;
    }

    public long getTotalDebtCustomer() {
        return totalDebtCustomer;
    }

    public void setTotalDebtCustomer(long totalDebtCustomer) {
        this.totalDebtCustomer = totalDebtCustomer;
    }
}
