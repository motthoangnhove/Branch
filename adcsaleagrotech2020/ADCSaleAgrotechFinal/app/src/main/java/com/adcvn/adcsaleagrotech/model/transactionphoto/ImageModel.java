package com.adcvn.adcsaleagrotech.model.transactionphoto;

public class ImageModel {
    private String user;
    private String date;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ImageModel() {
    }

    public ImageModel(String user, String date) {
        this.user = user;
        this.date = date;
    }
}
