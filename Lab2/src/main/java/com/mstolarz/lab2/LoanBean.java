package com.mstolarz.lab2;

import java.io.Serializable;
import java.text.DecimalFormat;

public class LoanBean implements Serializable {
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getInstalments() {
        return instalments;
    }

    public void setInstalments(int instalments) {
        this.instalments = instalments;
    }

    public String getLoan() {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format((amount * rate / 1200.0) / (1 - (1 / Math.pow(1 + (rate / 1200.0), instalments))));
    }

    private double amount = 1000;
    private double rate = 10;
    private int instalments = 10;
}
