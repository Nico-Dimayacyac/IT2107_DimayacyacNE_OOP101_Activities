import java.util.Scanner;

public class Canteen {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // ===== Menu Setup (individual variables instead of arrays) =====
        String item1Name = "Sinigang na Balut";  double item1Price = 80.00;
        String item2Name = "Sizzling Crocodile"; double item2Price = 120.00;
        String item3Name = "Adobong Kambing";    double item3Price = 100.00;
        String item4Name = "Unli-Wings Tinola";  double item4Price = 70.00;
        String item5Name = "Halo-halo";          double item5Price = 90.00;

        // ===== Running Totals =====
        int totalQuantity = 0;
        double totalBeforeDiscount = 0.0;
        double totalDiscount = 0.0;
        double finalTotal = 0.0;

        // ===== Display Menu (once, at start) =====
        System.out.println("=====  M E N U  =====");
        System.out.printf("1. %-20s - $%.2f%n", item1Name, item1Price);
        System.out.printf("2. %-20s - $%.2f%n", item2Name, item2Price);
        System.out.printf("3. %-20s - $%.2f%n", item3Name, item3Price);
        System.out.printf("4. %-20s - $%.2f%n", item4Name, item4Price);
        System.out.printf("5. %-20s - $%.2f%n", item5Name, item5Price);

        boolean orderAgain = true;

        while (orderAgain) {
            System.out.println();
            System.out.print("Enter item number: ");
            int itemNumber = readInt(scanner);

            System.out.print("Enter quantity: ");
            int quantity = readInt(scanner);

            // ===== Validate item number and quantity =====
            boolean validItem = (itemNumber >= 1 && itemNumber <= 5);
            boolean validQuantity = (quantity >= 1 && quantity <= 10);

            if (!validItem || !validQuantity) {
                System.out.println();
                System.out.println("Invalid order! Please enter a valid item and quantity.");
            } else {
                // Only ask for student status if the order itself is valid
                System.out.print("Are you a student? (Y/N): ");
                String studentInput = scanner.next().trim().toUpperCase();
                boolean isStudent = studentInput.equals("Y");

                // ===== Look up the price for the chosen item number =====
                double pricePerItem;
                if (itemNumber == 1) {
                    pricePerItem = item1Price;
                } else if (itemNumber == 2) {
                    pricePerItem = item2Price;
                } else if (itemNumber == 3) {
                    pricePerItem = item3Price;
                } else if (itemNumber == 4) {
                    pricePerItem = item4Price;
                } else {
                    pricePerItem = item5Price;
                }

                // ===== Compute subtotal =====
                double subtotal = pricePerItem * quantity;

                // ===== Determine discount rate =====
                double discountRate;
                if (isStudent && subtotal >= 500.0) {
                    discountRate = 0.15;   // student AND purchase >= $500
                } else if (isStudent) {
                    discountRate = 0.10;   // student only
                } else if (subtotal >= 500.0) {
                    discountRate = 0.05;   // purchase >= $500 only
                } else {
                    discountRate = 0.0;    // no discount
                }

                double discount = subtotal * discountRate;
                double orderTotal = subtotal - discount;

                // ===== Display order results =====
                System.out.println();
                System.out.printf("Subtotal: $%.2f%n", subtotal);
                System.out.printf("Discount: $%.2f%n", discount);
                System.out.printf("Order total: $%.2f%n", orderTotal);

                // ===== Update running totals =====
                totalQuantity += quantity;
                totalBeforeDiscount += subtotal;
                totalDiscount += discount;
                finalTotal += orderTotal;
            }

            // ===== Ask to order again =====
            System.out.println();
            System.out.print("Do you want to order again? (Y/N): ");
            String again = scanner.next().trim().toUpperCase();
            orderAgain = again.equals("Y");
        }

        // ===== Final Order Summary =====
        System.out.println();
        System.out.println("===== ORDER SUMMARY =====");
        System.out.println("Total items: " + totalQuantity);
        System.out.printf("Total before discount: $%.2f%n", totalBeforeDiscount);
        System.out.printf("Total discount: $%.2f%n", totalDiscount);
        System.out.printf("Final amount: $%.2f%n", finalTotal);
        System.out.println("Thank you for ordering!");

        scanner.close();
    }

    // ===== Helper: safely read an integer, treating non-numeric input as invalid (-1) =====
    private static int readInt(Scanner scanner) {
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            String junk = scanner.next(); // consume invalid token so we don't loop forever
            return -1;
        }
    }
}