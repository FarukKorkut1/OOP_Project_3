package application.managerscreens;

public class RefundItem {
    private String itemName;
    private double price;

    public RefundItem(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }
}
