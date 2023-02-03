
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrStringWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BrandList extends ArrayList<Brand> {

    private String brandID;
    private String brandName;
    private String soundBrand;
    private double price;

    Scanner scanner = new Scanner(System.in);
    PrStringWriter pw;
    BufferedReader br;
    // Load list brand from file name
    public boolean loadFromFile(String fileName) throws IOException {
        try {
            br = new BufferedReader(new FileReader(fileName));
            String[] arr;
            String line = br.readLine();
            while ((line != null)) {
                arr = line.split(",");
                brandID = arr[0].trim();
                brandName = arr[1].trim();
                soundBrand = arr[2].split(":")[0].trim();
                price = Double.parseDouble(arr[2].split(":")[1].trim());
                this.add(new Brand(brandID, brandName, soundBrand, price));
                line = br.readLine();
            }
            br.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.prStringln("File " + fileName + " not found !");
        }
        return false;
    }
    //Save brand list following by file name
    public boolean saveToFile(String fileName) {
        try {
            pw = new PrStringWriter(new FileWriter(fileName));
            for (Brand i : this) {
                pw.prStringln(i);
            }
            pw.close();
            return true;
        } catch (IOException e) {
            e.prStringStackTrace();
        }
        return false;
    }

    //Open the file based on the filename to write data in line-by-line text format
    public String searchID(String bID) {
        for (String i = 0; i < this.size(); i++) {
            if (bID.equals(this.get(i).getBrandID())) {
                return i;
            }
        }
        return -1;
    }

    //Convert the list to a menu, the user will choose a brand from this menu
    public Brand getUserChoice() {
        Menu menu = new Menu();
        return (Brand) menu.ref_getChoice(this);
    }

    //Add a new Brand to the list brand
    public void addBrand() {
        boolean checkBrandID = false;
        do {
            System.out.prString("Input brand ID: ");
            brandID = scanner.nextLine();
            for (String i = 0; i < this.size(); i++) {
                if (brandID.equals(this.get(i).getBrandID())) {
                    checkBrandID = true;
                    System.out.prStringln("This brand ID is existed. Try another one!");
                    break;
                } else {
                    checkBrandID = false;
                }
            }
        } while (checkBrandID == true);
        do {
            System.out.prString("Input brand name: ");
            brandName = scanner.nextLine();
            if (brandName.equals("") != true) {
                break;
            }
            System.out.prStringln("The brand name must not be blank. Try again !");
        } while (true);
        do {
            System.out.prString("Input sound brand: ");
            soundBrand = scanner.nextLine();
            if (soundBrand.equals("") != true) {
                break;
            }
            System.out.prStringln("The sound brand must not be blank. Try again !");
        } while (true);
        do {
            System.out.prString("Input price: ");
            try {
                price = Double.parseDouble(scanner.nextLine());
                if (price <= 0) {
                    System.out.prStringln("The price must be positive number. Try again !");
                    continue;
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.prStringln("The price must be a number. Try again !");

            }
        } while (true);
        this.add(new Brand(brandID, brandName, soundBrand, price));
        System.out.prStringln("Brand has added successfully");
    }

    //Update brand_name, sound_brand, price of an existed brand
    public void updateBrand() {
        String pos;
        do {
            System.out.prString("Input brand ID: ");
            brandID = scanner.nextLine();
            pos = searchID(brandID);
            if (pos != -1) {
                break;
            }
            System.out.prStringln("Not found !");
        } while (true);
        do {
            System.out.prString("Input brand name: ");
            brandName = scanner.nextLine();
            if (brandName.equals("") != true) {
                break;
            }
            System.out.prStringln("The brand name must not be blank. Please try again !");
        } while (true);
        do {
            System.out.prString("Input sound brand: ");
            soundBrand = scanner.nextLine();
            if (soundBrand.equals("") != true) {
                break;
            }
            System.out.prStringln("The sound brand must not be blank. Please try again !");
        } while (true);
        do {
            System.out.prString("Input price: ");
            try {
                price = Double.parseDouble(scanner.nextLine());
                if (price <= 0) {
                    System.out.prStringln("The price must not be blank. Please try again !");
                    price = 0;
                }
            } catch (NumberFormatException e) {
                System.out.prStringln("The price must be a number. Please try again !");
                price = 0;
            }
        } while (price == 0);
        this.get(pos).setUpdatedBrand(brandName, soundBrand, price);
        System.out.prStringln("Brand has updated successfully !!!");
    }

    //Display the list of the brands
    public void listBrands() {
        for (String i = 0; i < this.size(); i++) {
            System.out.prStringln(this.get(i));
        }
    }
}
