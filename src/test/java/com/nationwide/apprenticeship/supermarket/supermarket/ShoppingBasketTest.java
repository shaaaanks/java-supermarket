package com.nationwide.apprenticeship.supermarket.supermarket;

import com.nationwide.apprenticeship.entity.Offer;
import com.nationwide.apprenticeship.supermarket.BasketItem;
import com.nationwide.apprenticeship.supermarket.ShoppingBasket;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShoppingBasketTest {
    public ShoppingBasket shoppingBasket;

    @Before
    public void setupShoppingBasket() {
        shoppingBasket = new ShoppingBasket();
    }

    @Test
    public void canAddNewBasketItem() {
        shoppingBasket.addProduct("Beans", 2.12, 1, new Offer());

        ArrayList<BasketItem> actualItems = new ArrayList<>();
        actualItems.add(new BasketItem("Beans", 2.12, 1, new Offer()));

        Assert.assertEquals(actualItems, shoppingBasket.getItems());
    }

    @Test
    public void canAddAndRemoveNewTftpotBasketItem() {
        shoppingBasket.addProduct("Cheese", 1.99, 1, new Offer(1, "", "", true, true, 1, false, 0));
        shoppingBasket.addProduct("Milk", 0.65, 3, new Offer(1, "", "", true, true, 2, false, 0));
        BasketItem cheese = shoppingBasket.getItems().stream().filter(basketItem -> basketItem.getName() == "Cheese").findFirst().get();
        BasketItem milk = shoppingBasket.getItems().stream().filter(basketItem -> basketItem.getName() == "Milk").findFirst().get();

        Assert.assertEquals(1.99, cheese.getTotalPrice(), 0.1);
        Assert.assertEquals(1.30, milk.getTotalPrice(), 0.1);

        shoppingBasket.addProduct("Ham", 2.49, 6, new Offer(1, "", "", true, true, 1, false, 0));
        BasketItem ham = shoppingBasket.getItems().stream().filter(basketItem -> basketItem.getName() == "Ham").findFirst().get();

        Assert.assertEquals(12.45, ham.getTotalPrice(), 0.1);
        Assert.assertEquals(0.00, cheese.getTotalPrice(), 0.1);

        shoppingBasket.removeProduct("Ham");

        Assert.assertEquals(1.99, cheese.getTotalPrice(), 0.1);
    }

    @Test
    public void canUpdateExistingBasketItem() {
        shoppingBasket.addProduct("Beans", 2.12, 1, new Offer());
        shoppingBasket.addProduct("Beans", 2.12, 2, new Offer());

        BasketItem beans = shoppingBasket.getItems().stream().filter(basketItem -> basketItem.getName().equals("Beans")).findFirst().get();

        Assert.assertEquals(3, beans.getQuantity());
    }

    @Test
    public void canRemoveBasketItem() {
        shoppingBasket.addProduct("Beans", 2.12, 1, new Offer());

        shoppingBasket.removeProduct("Beans");

        Assert.assertEquals(new ArrayList<>(), shoppingBasket.getItems());
    }

    @Test
    public void canClearBasket() {
        shoppingBasket.addProduct("Beans", 2.12, 1, new Offer());
        shoppingBasket.addProduct("Corn Flakes", 1.50, 1, new Offer());

        shoppingBasket.clearBasket();

        Assert.assertEquals(new ArrayList<>(), shoppingBasket.getItems());
    }

    @Test
    public void canGetNumberOfItems() {
        shoppingBasket.addProduct("Beans", 2.12, 1, new Offer());
        shoppingBasket.addProduct("Corn Flakes", 1.50, 1, new Offer());

        Assert.assertEquals(2, shoppingBasket.getNumberOfItems());
    }

    @Test
    public void canGetTotalPrice() {
        shoppingBasket.addProduct("Beans", 2.12, 1, new Offer());
        shoppingBasket.addProduct("Corn Flakes", 1.50, 1, new Offer());

        Assert.assertEquals("£3.62", shoppingBasket.getTotalPrice());
    }
}
