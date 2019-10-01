package com.nationwide.apprenticeship.supermarket.supermarket;

import com.nationwide.apprenticeship.entity.Offer;
import com.nationwide.apprenticeship.supermarket.ShoppingBasket;
import com.nationwide.apprenticeship.supermarket.ShoppingBasketTableModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShoppingBasketTableModelTest {
    ShoppingBasket shoppingBasket = new ShoppingBasket();
    ShoppingBasketTableModel shoppingBasketTableModel = new ShoppingBasketTableModel(shoppingBasket);

    @Before()
    public void addItemsToShoppingBasket() {
        shoppingBasket.addProduct("Cheese", 0.56, 1, new Offer());
        shoppingBasket.addProduct("Milk", 1.12, 2, new Offer(1, "10% Discount", "", false, false, 0, true, 10));
    }

    @Test
    public void canGetColumnCount() {
        Assert.assertEquals(6, shoppingBasketTableModel.getColumnCount());
    }

    @Test
    public void canGetColumnNames() {
        Assert.assertEquals("Product Name", shoppingBasketTableModel.getColumnName(0));
        Assert.assertEquals("Offer", shoppingBasketTableModel.getColumnName(1));
        Assert.assertEquals("Quantity", shoppingBasketTableModel.getColumnName(2));
        Assert.assertEquals("Price", shoppingBasketTableModel.getColumnName(3));
        Assert.assertEquals("Discounted Price", shoppingBasketTableModel.getColumnName(4));
        Assert.assertEquals("Total Price", shoppingBasketTableModel.getColumnName(5));
    }


    @Test
    public void canGetColumnValues() {
        Assert.assertEquals("Milk", shoppingBasketTableModel.getValueAt(0, 0));
        Assert.assertEquals("10% Discount", shoppingBasketTableModel.getValueAt(0, 1));
        Assert.assertEquals(2, shoppingBasketTableModel.getValueAt(0,2));
        Assert.assertEquals("£1.12", shoppingBasketTableModel.getValueAt(0,3));
        Assert.assertEquals("£1.01", shoppingBasketTableModel.getValueAt(0,4));
        Assert.assertEquals("£2.02", shoppingBasketTableModel.getValueAt(0,5));

        Assert.assertEquals("Cheese", shoppingBasketTableModel.getValueAt(1, 0));
        Assert.assertEquals("--", shoppingBasketTableModel.getValueAt(1, 1));
        Assert.assertEquals(1, shoppingBasketTableModel.getValueAt(1,2));
        Assert.assertEquals("£0.56", shoppingBasketTableModel.getValueAt(1,3));
        Assert.assertEquals("--", shoppingBasketTableModel.getValueAt(1,4));
        Assert.assertEquals("£0.56", shoppingBasketTableModel.getValueAt(1,5));
    }

    @Test
    public void canGetRowCount() {
        Assert.assertEquals(shoppingBasketTableModel.getRowCount(), 2);
    }
}
