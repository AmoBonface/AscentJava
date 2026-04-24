ackage simpo;

import java.util.Scanner;
public class MamboContinua{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // getUserName
        System.out.print("Hi enter your Name: ");
        String name = scanner.nextLine();
        
        // LoopHeightUntilHeightGiven
        double height = 0;
        while (height <= 0) {
            System.out.print("Enter your height: ");
            try {
                height = scanner.nextDouble();
                scanner.nextLine(); // clear buffer
                if (height <= 0) {
                    System.out.println("❌ Must be positive! Try 
again.");
                }
            } catch (Exception e) {
                System.out.println("❌ Numbers only! Try again.");
                scanner.nextLine(); // clear bad input
            }
        }
        
        // Force correct unit
        String hUnit = "";
        while (!hUnit.equals("ft") && !hUnit.equals("m")) {
            System.out.print("Enter units (ft/m): ");
            hUnit = scanner.next().toLowerCase();
            if (!hUnit.equals("ft") && !hUnit.equals("m")) {
                System.out.println("❌ Only 'ft' or 'm' allowed! Try 
again.");
            }
        }
        
        // Convert
        if (hUnit.equals("m")) {
            double inFeet = height * 3.28;
            System.out.printf("Height in feet: %.2f ft%n", inFeet);
        } else if (hUnit.equals("ft")) {
            double inMeters = height / 3.28;
            System.out.printf("Height in meters: %.2f m%n", inMeters);
        }
        
        System.out.printf("We value our customers @%s!%n", name);
        scanner.close();
    }
}
