package com.sam.shoppingcart;

/**
 * Created by SAM on 6/6/2016.
 */
public class Model {

    private String name, rate;

    public Model(String name, String rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }


}
