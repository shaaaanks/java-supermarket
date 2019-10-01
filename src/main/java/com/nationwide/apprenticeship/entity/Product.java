package com.nationwide.apprenticeship.entity;

import javax.persistence.*;
import java.text.NumberFormat;
import java.util.Locale;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;
    private String productName;
    private double unitPrice;
    @OneToOne
    @JoinTable(name = "productoffers", joinColumns = @JoinColumn(name = "productID", referencedColumnName = "productID"), inverseJoinColumns = @JoinColumn(name = "offerID", referencedColumnName = "offerID"))
    private Offer offer;

    public Product() {}

    public Product(long productId, String productName, double unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
    }

    public long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public String getFormattedUnitPrice() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.UK);
        String price = formatter.format(unitPrice);
        return price;
    }

    public Offer getOffer() {
        return (offer != null) ? offer : new Offer();
    }

    @Override
    public String toString() {
        return productName;
    }
}
