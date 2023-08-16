import java.util.ArrayList;
import java.util.Scanner;

public class Customer {
    String username;
    String password;
    ArrayList<Item> shoppingCart = new ArrayList<Item>();

    public Customer(String u, String p) {
        this.username = u;
        this.password = p;
        this.shoppingCart = new ArrayList<Item>();
    }

    /*
     * Customer adds item to shopping cart if the item is available.
     * If unavailable, the item is removed from Customer's shopping cart.
     */
    void addItem(Item i) {
        this.shoppingCart.add(i);
        if (i.availability == true) {
            i.quantity = i.quantity - 1;

            if (i.quantity == 0) {
                i.availability = false;
            }
        }

        else {
            this.shoppingCart.remove(i);
        }
    }

    /*
     * Before the purchase is confirmed, the customer can remove items from their
     * shopping cart
     * by specifying the number of items to be removed, and then specifying the
     * actual item to be removed
     * in each iteration.
     */
    void removeItems(int numberofItemsRemoved) {
        Scanner scan = new Scanner(System.in);

        for (int i = 0; i < numberofItemsRemoved; i++) {

            System.out.println(
                    "Please enter the number that corresponds to the item that you would like to be removed from your cart.");

            this.displayItems();

            int removedItemNum = scan.nextInt();

            for (int j = 0; j <= shoppingCart.size(); j++) {
                if (removedItemNum == j) {
                    Item removedItem = shoppingCart.get(removedItemNum - 1);
                    shoppingCart.remove(removedItem);
                }
            }

        }
    }

    /*
     * Items that are in the customer's shopping cart are displayed in a list
     * format.
     */
    void displayItems() {
        if (!(shoppingCart.isEmpty())) {
            int j = 1;
            for (Item item : shoppingCart) {
                System.out.println(j + "." + item.getName());
                j = j + 1;
            }
        }

        else {
            System.out.println(this.username + ", you currently have no items in your Shopping Cart.");
        }
    }

    /* Accumulates the total price from all items in the cart and returns it. */
    double getTotalPrice() {
        double priceTotal = 0;
        for (Item item : shoppingCart) {
            priceTotal = priceTotal + item.getPrice();
        }

        return priceTotal;
    }

    /*
     * When the customer confirms the purchase, they will see the total price of the
     * items
     * in their shopping cart.
     * 
     * Then, they must choose their method of payment.
     * 
     * If the chosen method is 'card', the payment will go through immediately.
     * 
     * 
     * If the chosen method is 'cash', an initial payment amount is required.
     * If the initial payment is less than the total price, this method will be
     * recursively called until a valid amount is entered. If a valid amount is
     * entered, the transaction
     * is completed, and the change from the transaction is provided to the
     * customer.
     * 
     * 
     * If the entered method of payment is invalid, then the method will be
     * recursively called
     * until a valid method of payment is specified.
     * 
     * 
     * If the shopping cart is empty, then the Customer won't be able to proceed
     * with a payment.
     */
    void purchaseItems() {
        if (!shoppingCart.isEmpty()) {
            System.out.println(this.username + ", your total price is: $" + (float) (this.getTotalPrice()));
            System.out.println();

            Scanner scan = new Scanner(System.in);

            System.out.println("Please type either 'card' or 'cash' to indicate your method of payment.");

            String paymentMethod = scan.next();

            if (paymentMethod.equals("card")) { // card payment
                System.out.println("$" + (float) this.getTotalPrice() + " has been deducted from your account.");
                System.out.println("THANK YOU for your purchase, have a great day!");
            }

            else if (paymentMethod.equals("cash")) { // cash payment

                System.out.println("Please enter payment amount: ");
                double initialPayment = scan.nextDouble();

                if (initialPayment < this.getTotalPrice()) {
                    System.out.println("Insufficient funds, please enter a valid amount.");
                    System.out.println();
                    purchaseItems();
                }

                else {

                    System.out.println("THANK YOU for your purchase, have a great day!");
                    System.out.println("CHANGE: $" + (float) (initialPayment - this.getTotalPrice()));
                }

            }

            else { // invalid method
                System.out.println("Invalid method of payment. Please try again.");
                purchaseItems();
            }

            scan.close();

        }

        else {
            System.out.println("Nothing to Buy!");
        }
    }

}
