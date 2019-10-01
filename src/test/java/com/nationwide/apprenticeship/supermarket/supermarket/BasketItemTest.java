package com.nationwide.apprenticeship.supermarket.supermarket;

import com.nationwide.apprenticeship.entity.Offer;
import com.nationwide.apprenticeship.supermarket.BasketItem;
import org.junit.Assert;
import org.junit.Test;

public class BasketItemTest {
    @Test
    public void canGetBasketItemName() {
        BasketItem basketItem = new BasketItem("Beans", 2.12, 1, new Offer());

        Assert.assertEquals("Beans", basketItem.getName());
    }

    @Test
    public void canGetBasketItemPrice() {
        BasketItem basketItem = new BasketItem("Beans", 2.12, 1, new Offer());

        Assert.assertEquals(2.12, basketItem.getPrice(), 0.1);
    }

    @Test
    public void canGetBasketItemQuantity() {
        BasketItem basketItem = new BasketItem("Beans", 2.12, 1, new Offer());

        Assert.assertEquals(1, basketItem.getQuantity());
    }

    @Test
    public void canGetBasketItemTotalPrice() {
        BasketItem basketItem = new BasketItem("Beans", 2.12, 3, new Offer());

        Assert.assertEquals(6.36, basketItem.getTotalPrice(), 0.1);
    }

    @Test
    public void canAddItemsToBasketItem() {
        BasketItem basketItem = new BasketItem("Beans", 2.12, 1, new Offer());
        basketItem.addItems(2);

        Assert.assertEquals(3, basketItem.getQuantity());
    }

    @Test
    public void canAddItemWithDiscountOfferToBasketAndSetDiscountPrice() {
        BasketItem basketItem = new BasketItem("Margarine", 2.12, 1, new Offer(1, "", "", false, false, 0, true, 10));

        Assert.assertEquals(1, basketItem.getQuantity());
        Assert.assertEquals(1.91, basketItem.getDiscountedPrice(), 0.1);
        Assert.assertEquals(1.91, basketItem.getTotalPrice(), 0.1);
    }

    @Test
    public void canAddItemWithBogofOfferToBasketAndSetDiscountQuantity() {
        BasketItem basketItem = new BasketItem("Beans", 0.56, 1, new Offer(1, "", "", true, false, 0, false, 0));

        Assert.assertEquals(1, basketItem.getQuantity());
        Assert.assertEquals(0.56, basketItem.getTotalPrice(), 0.1);

        basketItem.addItems(1);

        Assert.assertEquals(2, basketItem.getQuantity());
        Assert.assertEquals(0.56, basketItem.getTotalPrice(), 0.1);
    }

    @Test
    public void canDetermineBasketItemEquality() {
        BasketItem one = new BasketItem("Cheese", 0.56, 1, new Offer());
        BasketItem two = new BasketItem("Cheese", 0.56, 1, new Offer());

        Assert.assertTrue(one.equals(two) && two.equals(one));
        Assert.assertTrue(one.hashCode() == two.hashCode());
    }
}
