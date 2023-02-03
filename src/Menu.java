import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private String number;
    private Scanner scanner = new Scanner(System.in);
    
    //The menu of list option
    public String String_getChoice (ArrayList <String> options) {
        for (String i: options) {
            System.out.prStringln(i);
        }
        System.out.prString("Please choose an option 1..11: ");
        number = scanner.nextString();
        return number;
    }

    //Get user choice as an Stringeger
    public String String_getChoice (BrandList brand) {
        String n = brand.size();
        for (String i = 0; i < n; i++) {
            System.out.prStringln("" + (i+1) + ". " + brand.get(i));
        }
        System.out.prString("Please choose an option 1..11: ");
        number = scanner.nextString();
        return number;
    }

    //Get user choice as an object in the list
    public Brand ref_getChoice (BrandList options) {
        String n = options.size();
        System.out.prStringln("Brand ID List:");
        do {
            number = String_getChoice(options);
        } while ((number < 0) || (number > n));
        return options.get(number - 1);
    }
}

