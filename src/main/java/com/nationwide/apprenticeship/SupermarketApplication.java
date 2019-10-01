package com.nationwide.apprenticeship;

import com.nationwide.apprenticeship.entity.Product;
import com.nationwide.apprenticeship.service.ProductService;
import com.nationwide.apprenticeship.supermarket.ShoppingBasket;
import com.nationwide.apprenticeship.supermarket.ShoppingBasketTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

@SpringBootApplication
public class SupermarketApplication extends JFrame {

    @Autowired
    private ProductService productService;
    private List<Product> products;
    private ShoppingBasket shoppingBasket = new ShoppingBasket();
    private Product selectedProduct;
    ShoppingBasketTableModel shoppingBasketTableModel = new ShoppingBasketTableModel(this.shoppingBasket);
    private JTable shoppingBasketTable = new JTable(shoppingBasketTableModel);
    private JLabel itemCountLabel;
    private JLabel totalPriceLabel;

    public static void main(String[] args) {
	    ConfigurableApplicationContext context = new SpringApplicationBuilder(SupermarketApplication.class).headless(false).run(args);

        EventQueue.invokeLater(() -> {
            SupermarketApplication ex = context.getBean(SupermarketApplication.class);
            ex.setVisible(true);
        });
	}

    @PostConstruct
	private void initialiseUI() {
        /* Get the Products from the database */
        products = productService.getProducts();

        Container container = getContentPane();
        container.setBackground(Color.WHITE);

        /* Get the size of the user's screen */
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        /* Setup the UI */
        setTitle("Supermarket");
        setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(initialiseTopPanel(), BorderLayout.NORTH);
        add(initaliseCenterPanel(), BorderLayout.CENTER);
        add(initialiseBottomPanel(), BorderLayout.SOUTH);
    }

    private JPanel initialiseTopPanel() {
        /* Setup the top panel */
        JComboBox productsComboBox = new JComboBox(this.products.toArray());
        selectedProduct = (Product) productsComboBox.getSelectedItem();

        JPanel productsPanel = new JPanel();
        productsPanel.add(new JLabel("Product:"));
        productsPanel.add(productsComboBox);

        JPanel offerPanel = new JPanel();
        offerPanel.add(new JLabel("Offer:"));
        JLabel offerLabel = new JLabel(this.selectedProduct.getOffer().getOfferDescription());
        offerPanel.add(offerLabel);

        JPanel pricePanel = new JPanel();
        pricePanel.add(new JLabel("Price:"));
        JLabel priceLabel = new JLabel(String.valueOf((selectedProduct.getFormattedUnitPrice())));
        pricePanel.add(priceLabel);

        productsComboBox.addActionListener(actionEvent -> {
            /* Get the selected Product from the Combo Box */
            this.selectedProduct = (Product) productsComboBox.getSelectedItem();
            /* Update the price and offer labels */
            priceLabel.setText(String.valueOf((this.selectedProduct.getFormattedUnitPrice())));
            if (this.selectedProduct.getOffer().getOfferDescription() != null) {
                offerLabel.setText(this.selectedProduct.getOffer().getOfferDescription());
            } else {
                offerLabel.setText("NO OFFERS ON THIS PRODUCT");
            }
        });

        JPanel quantityPanel = new JPanel();
        quantityPanel.add(new JLabel("Quantity:"));
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, null, 1);
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        JComponent editor = quantitySpinner.getEditor();
        JFormattedTextField textField = ((JSpinner.DefaultEditor) editor).getTextField();
        textField.setColumns(4);
        quantityPanel.add(quantitySpinner);

        JButton addButton = new JButton("Add");

        addButton.addActionListener(actionEvent -> {
            /* Add a Product to the Shopping Basket, update the Table, and update the total price and item count */
            shoppingBasket.addProduct(selectedProduct.getProductName(), selectedProduct.getUnitPrice(), Integer.parseInt(quantitySpinner.getValue().toString()), selectedProduct.getOffer());
            shoppingBasketTableModel.fireTableRowsInserted(0, 0);
            itemCountLabel.setText(String.valueOf(shoppingBasket.getNumberOfItems()));
            totalPriceLabel.setText(shoppingBasket.getTotalPrice());
        });

        JPanel topPanel = new JPanel();
        topPanel.add(productsPanel);
        topPanel.add(offerPanel);
        topPanel.add(pricePanel);
        topPanel.add(quantityPanel);
        topPanel.add(addButton);

        return topPanel;
    }

    private JPanel initaliseCenterPanel() {
        JTableHeader shoppingBasketHeader = shoppingBasketTable.getTableHeader();
        shoppingBasketHeader.setFont(new Font("Lucida Grande", Font.PLAIN, 13));

        /* Right-align columns that contain prices */
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.RIGHT);
        shoppingBasketTable.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
        shoppingBasketTable.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
        shoppingBasketTable.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);

        JScrollPane scrollPane = new JScrollPane(shoppingBasketTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        shoppingBasketTable.setFillsViewportHeight(true);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new GridLayout(0, 1));
        centerPanel.add(scrollPane);

        return centerPanel;
    }

    private JPanel initialiseBottomPanel() {
        JPanel itemCountPanel = new JPanel();
        itemCountPanel.add(new JLabel("No. Items:"));
        itemCountLabel = new JLabel(String.valueOf(shoppingBasket.getNumberOfItems()));
        itemCountPanel.add(itemCountLabel);

        JButton removeItemButton = new JButton("Remove Item");

        removeItemButton.addActionListener(actionEvent -> {
            int row = shoppingBasketTable.getSelectedRow();
            /* Check that an item is selected */
            if (row >= 0) {
                String productName = shoppingBasketTableModel.getValueAt(row, 0).toString();
                /* Remove the Product from the Shopping Basket, update the Table, and update the total price and item count */
                shoppingBasket.removeProduct(productName);
                shoppingBasketTableModel.fireTableDataChanged();
                itemCountLabel.setText(String.valueOf(shoppingBasket.getNumberOfItems()));
                totalPriceLabel.setText(shoppingBasket.getTotalPrice());
            }
        });

        JButton clearBasketButton = new JButton("Clear Basket");

        clearBasketButton.addActionListener(actionEvent -> {
            /* Clear the Shopping Basket, update the Table, and update the total price and item count */
            shoppingBasket.clearBasket();
            shoppingBasketTableModel.fireTableDataChanged();
            itemCountLabel.setText(String.valueOf(shoppingBasket.getNumberOfItems()));
            totalPriceLabel.setText(shoppingBasket.getTotalPrice());
        });

        JButton exitButton = new JButton("Exit");

        exitButton.addActionListener(actionEvent -> {
            /* Exit the application */
            System.exit(0);
        });

        JPanel totalPricePanel = new JPanel();
        totalPricePanel.add(new JLabel("Total:"));
        totalPriceLabel = new JLabel(shoppingBasket.getTotalPrice());
        totalPricePanel.add(totalPriceLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(removeItemButton);
        buttonPanel.add(clearBasketButton);
        buttonPanel.add(exitButton);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(itemCountPanel, BorderLayout.WEST);
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);
        bottomPanel.add(totalPricePanel, BorderLayout.EAST);

        return bottomPanel;
    }
}
