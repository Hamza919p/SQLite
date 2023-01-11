package com.kimu.grocerysqlite.models;

import java.io.Serializable;

/**
 * CREATED BY HAMZA-ALI 12-09-2022
 */
public class Products implements Serializable {
    private String productId, productName, productQuantity;

    public Products(String productId, String productName, String productQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.productQuantity = productQuantity;
    }

    public void setProductId(String productId) {this.productId = productId;}
    public void setProductName(String productName) {this.productName = productName;}
    public void setProductQuantity(String productQuantity) {this.productQuantity = productQuantity;}

    public String getProductId() {return this.productId;}
    public String getProductName() {return this.productName;}
    public String getProductQuantity() {return this.productQuantity;}

}
