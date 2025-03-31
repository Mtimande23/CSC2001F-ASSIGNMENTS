
import java.util.Scanner;

/**
 * Program acts as user interface, allowing the user to choose which data structure to use(array or binary search tree).
 *@author Themba Shongwe
 *17 March 2025
 */

public class mainInterface {

/**
 * allows user to choose data structure to use.
 * @param args ...
 */

    public static void main(String []args){
        Scanner user = new Scanner(System.in);
        System.out.println("Welcome to your General Knowledge Box :)"); 
        System.out.println("------------------------------------------");
        System.out.println("Choosebetween using an Array and using a Binary Search Tree.");
        System.out.println("1.Use Array.");
        System.out.println("2.Use Binary Search Tree.");
        System.out.print("Choice: ");
        int option= user.nextInt();

        switch(option){
            case 1:
                System.out.println("You chose an Array. :)");
                GenericsKbArrayApp.main();
                break;

            case 2:
                System.out.println("You chose an The Binary Search Tree. :)");
                GenericsKbBSTApp.main();
                break;
          
        }
    }
}
