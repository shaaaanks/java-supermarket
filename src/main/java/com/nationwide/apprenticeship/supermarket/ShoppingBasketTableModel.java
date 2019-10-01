package com.nationwide.apprenticeship.supermarket;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.text.NumberFormat;
import java.util.Locale;

public class ShoppingBasketTableModel extends AbstractTableModel {
    private String[] columnNames = {"Product Name", "Offer", "Quantity", "Price", "Discounted Price", "Total Price"};
    private ShoppingBasket shoppingBasket;

    public ShoppingBasketTableModel(ShoppingBasket shoppingBasket) {
        this.shoppingBasket = shoppingBasket;
    }

    @Override
    public int getRowCount() {
        return shoppingBasket.getItems().size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Object value = null;
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.UK);
        BasketItem item = shoppingBasket.getItems().get(row);

        if (column == 0) {
            value = item.getName();
        } else if (column == 1) {
            /* If the item doesn't have an Offer, print "--" */
            value = (item.getOffer().getOfferDescription() == null ? "--" : item.getOffer().getOfferDescription());
        } else if (column == 2) {
            value = item.getQuantity();
        } else if (column == 3) {
            value = formatter.format(item.getPrice());
        } else if (column == 4) {
            /* If the Offer isn't of type Discount, print "--" */
            value = (!item.getOffer().isDiscount() ? "--" : formatter.format(item.getDiscountedPrice()));
        } else if (column == 5) {
            value = formatter.format(item.getTotalPrice());
        }

        return value;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
