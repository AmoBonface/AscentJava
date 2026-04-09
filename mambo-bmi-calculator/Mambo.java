
package simpo;

import java.util.Scanner;

public class Mambo {
    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	//getName
	System.out.printf("Your Name:  ");
	String name =  scanner.nextLine();	
	//getUnit
	System.out.printf("Choose the unit (Kg/Lb): ");
	String unit = scanner.nextLine().toLowerCase();
        //getWeight 
	System.out.printf("Your Weight:  ");
	Double weight = scanner.nextDouble();
       //getHeight
	System.out.printf("Your Height:  ");
	Double height = scanner.nextDouble();
	//convert kilograms  to pounds
	if (unit.equals("lb")) {
	    height = height*0.453592;
	}
	// CalculateTheBMIandPrint
	Double bmi = weight/(height*height); 
	//printing out
	System.out.printf("Hi %s your BMI is: %.2f ", 
name, bmi);
	System.out.println("  ");
	System.out.println("Karibu...");
	//close the scanner
	scanner.close ();
    }

}

