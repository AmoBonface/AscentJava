package simpo;

import java.util.Scanner;

public class Mambo {
    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	//getName
	System.out.println("Your Name:  ");
	String jina =  scanner.nextLine();	
        //getWeight 
	System.out.println("Your Weight:  ");
	Double uzani = scanner.nextDouble();
       //getHeight
	System.out.println("Your Height:  ");
	Double urefu = scanner.nextDouble();
	//CalculateTheBMI
	Double bMi = uzani/urefu; 
        //print out the result
	System.out.println(jina+ " your BMI is:  " + bMi +" \n Ahsante \n 
Karibu!...");
	//close the Scanner
	scanner.close ();
    }

}

