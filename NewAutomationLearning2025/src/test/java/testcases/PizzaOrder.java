package testcases;

import Enums.PizzaSize;

public class PizzaOrder {

    public static void main(String[] args) {
        // Selecting a pizza size
        PizzaSize size = PizzaSize.MEDIUM;

        // Display the selected size and its price
        System.out.println("Selected Pizza Size: " + size);
        System.out.println("Price of " + size + ": " + size.getPrice() + " units");

        // Loop through all sizes
        System.out.println("\nAvailable Sizes and Prices:");
        for (PizzaSize s : PizzaSize.values()) {
            System.out.println(s + ": " + s.getPrice() + " units");
        }
    }
}
