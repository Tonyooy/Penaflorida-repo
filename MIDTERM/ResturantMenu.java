package ResturantMenu;
import java.util.Scanner;
import java.util.ArrayList;

class Menu {
    String name;
    double price;
    int quantity;

    public Menu(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class ResturantMenu {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Menu> orderCount = new ArrayList<>();
        double sumPHP = 0.0; 
        boolean ifOrdered = false;

        while (true) {
            System.out.println("\nMenu: \n");
            System.out.println("1. C1 - Php 100.00");
            System.out.println("2. C2 - Php 150.00");
            System.out.println("3. C3 - Php 200.00\n");
            System.out.println("Add Ons:");
            System.out.println("4. R1 - Php 35.00");
            System.out.println("5. R2 - Php 50.00\n");
            

            System.out.println("Enter order: (enter '0' to end order)");
            System.out.println("Note: Add-ons are only available with C1, C2, or C3 purchases");

            int num = scan.nextInt();
            int quantity = 0;

            if (num == 0) {
                System.out.println("Thank you for using the program.");
                break; 
            } else if (num >= 1 && num <= 5) {
                System.out.println("Enter order quantity:");
                quantity = scan.nextInt();
                if (num >= 1 && num <= 3) {
                    ifOrdered = true;
                }
            } else {
                System.out.println("Invalid input.");
            }

            Menu menu = null;
            switch (num) {
                case 1:
                    menu = new Menu("C1", 100.00, quantity);
                    break;
                case 2:
                    menu = new Menu("C2", 150.00, quantity);
                    break;
                case 3:
                    menu = new Menu("C3", 200.00, quantity);
                    break;
                case 4:
                    if (ifOrdered) {
                        menu = new Menu("R1", 35.00, quantity);
                    } else {
                        System.out.println("Invalid input. You can only order Add-ons if you've ordered C1, C2, or C3.");
                    }
                    break;
                case 5:
                    if (ifOrdered) {
                        menu = new Menu("R2", 50.00, quantity);
                    } else {
                        System.out.println("Invalid input. You can only order Add-ons if you've ordered C1, C2, or C3.");
                    }
                    break;
                default:
                    System.out.println("Invalid input.");
                    break;
            }

            if (menu != null) {
                orderCount.add(menu);
                sumPHP += menu.price * menu.quantity; 
            }
        }

        double exchangeRate = 0.019;

        double sumUSD = sumPHP * exchangeRate;

        System.out.println("Orders in PHP: ");
        for (Menu item : orderCount) {
            System.out.println(item.name + ": amount: " + item.quantity + " || price: " + (item.price * item.quantity) + " PHP");
        }

        String decimalEdit = String.format("%.4f", sumUSD);
        System.out.println("Total price in PHP: " + sumPHP + " PHP");
        System.out.println("Total price in USD: $" + decimalEdit); 
    }
}