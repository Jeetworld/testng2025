package Enums;

public enum PizzaSize {

    SMALL(200), // Price in currency units
    MEDIUM(300),
    LARGE(400),
    EXTRA_LARGE(500);

    private final int price; // Price associated with the pizza size

    // Constructor to initialize the price
    PizzaSize(int price) {
        this.price = price;
    }

    // Getter to access the price
    public int getPrice() {
        return price;
    }

}
