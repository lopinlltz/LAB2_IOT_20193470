package com.example.iot_lab2_20193470;

import com.google.gson.annotations.SerializedName;

public class NumPrimo {
    @SerializedName("number")
    private int number;
    @SerializedName("order")
    private int order;

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public int getOrder() { return order; }
    public void setOrder(int order) { this.order = order; }
}
