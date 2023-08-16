import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        // Setting flag
        boolean stillShopping = true;

        // Register Customer Account
        Scanner scan = new Scanner(System.in);

        System.out.println("Please register a username for your account: ");

        String username = scan.next();

        System.out.println("Please register a password for your account: ");

        String password = scan.next();

        Customer c = new Customer(username, password);

        // Login
        System.out.println("Please log into the system by entering your username and password.");

        while (true) {
            System.out.println("Username: ");
            String username_login = scan.next();

            System.out.println("Password: ");
            String password_login = scan.next();

            if (username_login.equals(c.username) && password_login.equals(c.password)) {
                System.out.println("Hello, " + c.username + ", welcome to the app!");
                System.out.println();
                break;
            }

            else {
                System.out.println("Login failed - username or password is incorrect. Please re-enter credentials.");
            }
        }

        // Items
        Item item0 = new Top("NIKE Athletic shirt", true, 13.99, 7, "NIKE", "S", "Shirt", "Regular Fit");
        Item item1 = new Pants("Blue Jeans", true, 55.99, 2, "Levi's", "L", "Jeans");
        Item item2 = new Hat("Under Armour Sunhat", true, 6.99, 20, "Under Armour", "M", "Sun hat");
        Item item3 = new Top("Adidas Hoodie", true, 13.99, 7, "adidas", "XL", "Hoodie", "Regular Fit");
        Item item4 = new Top("Jumpman T-shirt", false, 20.99, 0, "Jordan", "S", "Shirt", "Regular Fit");
        Item item5 = new Hat("NIKE tee", true, 13.99, 7, "NIKE", "S", "Shirt");

        // Creating Item Catalogue that is viewable to the customer
        ArrayList<Item> itemCatalogue = new ArrayList<Item>();
        itemCatalogue.add(item0);
        itemCatalogue.add(item1);
        itemCatalogue.add(item2);
        itemCatalogue.add(item3);
        itemCatalogue.add(item4);
        itemCatalogue.add(item5);

        // Customer selects desired item
        while (stillShopping == true) {
            System.out.println(
                    "This is our catalogue of items. Please select the number that corresponds to the item that you want.");

            if (!(itemCatalogue.isEmpty())) {
                int j = 1;
                for (Item item : itemCatalogue) {
                    System.out.println(j + "." + item.getName() + "..............      $"
                            + (item.getPrice()));
                    j = j + 1;
                }
            }

            int selected = scan.nextInt();
            Item selectedItem = (itemCatalogue.get(selected - 1));

            // Customer chooses the quantity of the desired item
            System.out.println(
                    "Please enter the quantity of '" + selectedItem.getName() + "' that you would like to purchase.");

            int itemQuantity = scan.nextInt();

            for (int i = 0; i < itemQuantity; i++) {
                c.addItem(selectedItem);

                if (selectedItem.availability == false) {
                    System.out.println(selectedItem.getName() + " is now SOLD OUT!");
                    itemCatalogue.remove(selectedItem);
                    break;
                }
            }

            System.out.println(c.username + ", these are the item(s) in your Shopping Cart: ");
            c.displayItems();

            // Gives customer the option to either continue shopping or to purchase items in
            // shopping cart.
            System.out.println("If you would like to purchase these items, type 'Purchase'.");
            System.out.println("If you would like to continue with your shopping, type 'Continue'.");

            String customerDecision = scan.next();

            if (customerDecision.equals("Purchase")) {
                stillShopping = false;
            }

            else {
                continue;
            }

        }

        // Shopping loop is broken - selected items are displayed.
        System.out.println(c.username + ", these are the item(s) in your Shopping Cart: ");
        c.displayItems();

        System.out.println();

        // Option to remove any items from Shopping Cart before purchasing.
        System.out.println(
                "If you would like to remove item(s) from your shopping cart, type 'Remove'. Otherwise, type 'Confirm'.");

        String customerDecision = scan.next();

        if (customerDecision.equals("Remove")) {
            System.out.println("Please enter the amount of items you would like to remove from your cart: ");
            int numofItemsRemoved = scan.nextInt();

            c.removeItems(numofItemsRemoved);

        }

        // Purchase is confirmed!

        System.out.println();

        System.out.println(c.username + ", these are the confirmed item(s) in your Shopping Cart: ");

        c.displayItems();

        c.purchaseItems();

        scan.close();

    }

}
