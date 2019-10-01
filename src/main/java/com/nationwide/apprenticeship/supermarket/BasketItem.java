package com.nationwide.apprenticeship.supermarket;

import com.nationwide.apprenticeship.entity.Offer;

import java.util.Objects;

public class BasketItem {
    private String name;
    private double price;
    private double discountedPrice;
    private int quantity;
    private int discountedQuantity;
    private Offer offer;

    public BasketItem(String name, double price, int quantity, Offer offer) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.offer = offer;

        /* If Offer on Basket Item is of type "Discount"
        * work out the discounted price */
        if (offer.isDiscount()) {
            this.discountedPrice = price - (price / 100 * offer.getDiscountPercentage());
        }

        /* If Offer on Basket Item is of type "BOGOF"
        * apply the discount */
        if (offer.isBogof()) {
            applyBogofDiscount();
        }
    }

    public void addItems(int quantity) {
        this.quantity += quantity;

        /* If Offer on Basket Item is of type "BOGOF"
        * apply the discount */
        if (offer.isBogof()) {
            applyBogofDiscount();
        }
    }

    public void applyBogofDiscount() {
        discountedQuantity = quantity / 2;
    }

    /* Pass through and store the quantity of items to discount */
    public void applyTftpotDiscount(int quantityToDiscount) {
        discountedQuantity = quantityToDiscount;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        /* If Offer on Basket Item is of type "Discount"
        * return the discountedPrice * the quantity */
        if (offer.isDiscount()) {
            return discountedPrice * quantity;
        }

        /* If Offer on Basket Item is of type "BOGOF" or "TFTPOT"
        * return the price * the quantity - the number of discounted items */
        if (offer.isBogof() || offer.isTftpot()) {
            return price * (quantity - discountedQuantity);
        }

        /* If the Basket Item has no Offers attached
        * return the price * quantity */
        return price * quantity;
    }

    public Offer getOffer() { return this.offer; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketItem that = (BasketItem) o;
        return Double.compare(that.price, price) == 0 &&
                quantity == that.quantity &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, quantity);
    }
}
