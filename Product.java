package application.managerscreens;

import javafx.beans.property.*;

public class Product {
    private final StringProperty productName;
    private final IntegerProperty stock;

    public Product(String productName, int stock) {
        this.productName = new SimpleStringProperty(productName);
        this.stock = new SimpleIntegerProperty(stock);
    }

    public String getProductName() {
        return productName.get();
    }

    public StringProperty productNameProperty() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public int getStock() {
        return stock.get();
    }

    public IntegerProperty stockProperty() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }
}

