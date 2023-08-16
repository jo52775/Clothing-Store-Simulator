public class Item {
    String name;
    boolean availability;
    double price;
    int quantity;
    String brand;
    String size;
    String type;

    public Item(String itemName, boolean s, double p, int q, String b, String clothingSize, String t) {
        this.name = itemName;
        this.availability = s;
        this.price = p;
        this.quantity = q;
        this.brand = b;
        this.size = clothingSize;
        this.type = t;

        if (quantity == 0) {
            availability = false;
        }
    }

    String getName() {
        return this.name;
    }

    double getPrice() {
        return this.price;
    }

    int getQuantity() {
        return this.quantity;
    }

}