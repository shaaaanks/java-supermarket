package com.nationwide.apprenticeship.supermarket;

import com.nationwide.apprenticeship.entity.Offer;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ShoppingBasket {
    private ArrayList<BasketItem> items = new ArrayList<BasketItem>();

    public void addProduct(String name, double price, int quantity, Offer offer) {
        /* Find the Basket Item if it already exists in the Basket */
        BasketItem item = items.stream().filter(basketItem -> basketItem.getName().equals(name)).findFirst().orElse(null);

        if (item != null) {
            /* If the Basket Item has already been added, call addItems() and pass
            * the additional quantity */
            item.addItems(quantity);
        } else {
            /* If the Item isn't in the basket
            * create a new Basket Item and add it to the top of the array */
            item = new BasketItem(name, price, quantity, offer);
            items.add(0, item);
        }

        /* If Offer is of type "TFTPOT"
        * call applyTftpotDiscount() passing through the TFTPOT Group */
        if (offer.isTftpot()) {
            applyTftpotDiscount(item.getOffer().getTftpotGroup());
        }
    }

    public void removeProduct(String name) {
        /* Get the Basket Item and remove it from the array */
        BasketItem itemToRemove = items.stream().filter(basketItem -> basketItem.getName().equals(name)).findFirst().get();

        items.remove(itemToRemove);

        /* If Offer on the Basket Item is of type "TFTPOT"
        * call applyTftpotDiscount() passing through the TFTPOT Group */
        if (itemToRemove.getOffer().isTftpot()) {
            applyTftpotDiscount(itemToRemove.getOffer().getTftpotGroup());
        }
    }

    public void applyTftpotDiscount(int group) {
        /* Get all of the Basket Items with the TFTPOT Offer
        * and in the passed-in Group */
        List<BasketItem> tftpotItems = items.stream().filter(basketItem -> basketItem.getOffer().isTftpot()).filter(basketItem -> basketItem.getOffer().getTftpotGroup() == group).collect(Collectors.toList());
        /* Get the total quantity of all of the Items in the Group */
        int itemsQuantity = tftpotItems.stream().reduce(0, (quantity, basketItem) -> quantity + basketItem.getQuantity(), Integer::sum);

        /* If the total quantity is 3 or more
        * (TFTPOT = Thee For The Price of Two) */
        if (itemsQuantity >= 3) {
            /* Divide the total quantity by 3 to get the number of free products */
            AtomicInteger freeProductsCount = new AtomicInteger(itemsQuantity / 3);
            /* Sort the array so that it's ordered by the price of the items - cheapest first */
            tftpotItems.sort(Comparator.comparing(BasketItem::getPrice));

            tftpotItems.forEach(basketItem -> {
                /* Get the number of free products left after subtracting the quantity of the Basket Item */
                int freeProductsLeft = freeProductsCount.get() - basketItem.getQuantity();
                if (freeProductsLeft > 0) {
                    /* If there are still free products left after discounting the quantity of the Basket Item
                    * subtract the quantity of Basket Item from the number of free products
                    * and apply the discount */
                    int discountQuantity = freeProductsCount.get() - freeProductsLeft;
                    basketItem.applyTftpotDiscount(discountQuantity);
                    freeProductsCount.set(freeProductsLeft);
                } else {
                    /* If there are no free products left
                    * apply the discount and set free products to 0 */
                    basketItem.applyTftpotDiscount(freeProductsCount.get());
                    freeProductsCount.set(0);
                }
            });
        } else {
            /* If the total quantity is less than 3
            * remove the discount from all Items within the Group */
            tftpotItems.forEach(basketItem -> basketItem.applyTftpotDiscount(0));
        }
    }

    public void clearBasket() {
        items.clear();
    }

    public ArrayList<BasketItem> getItems() {
        return items;
    }

    public int getNumberOfItems() {
        return items.size();
    }

    public String getTotalPrice() {
        /* Get the total price of every Basket Item
        * and format it as GPB */
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.UK);
        Double totalPrice = items.stream().reduce(0.0, (total, basketItem) -> (total + basketItem.getTotalPrice()), Double::sum);

        return formatter.format(totalPrice);
    }
}
