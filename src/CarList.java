
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrStringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CarList extends ArrayList<Car> {

    private String carID;
    private String color; 
    private String frameID;
    private String engineID;
    Brand brand;
    Menu menu = new Menu();
    Scanner scanner = new Scanner(System.in);
    BrandList brandList;
    BufferedReader br;
    String line;
    String[] arr;

    //Initialize a list based on the existed brand list
    public CarList(BrandList bList) {
        brandList = bList;
    }
    // Load list car following by file name you entered

    public boolean loadFromFile(String fileName) throws IOException {
        try {
            br = new BufferedReader(new FileReader(fileName));
            line = br.readLine();
            while (line != null) {
                arr = line.split(",");
                carID = arr[0].trim();
                brand = brandList.get(brandList.searchID(arr[1].trim()));
                color = arr[2].trim();
                frameID = arr[3].trim();
                engineID = arr[4].trim();
                this.add(new Car(carID, brand, color, frameID, engineID));
                line = br.readLine();
            }
            br.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.prStringln("File not found !");
        }
        return false;
    }

    //Save list car following by file name you entered
    public boolean saveToFile(String fileName) {
        try {
            PrStringWriter pw = new PrStringWriter(new FileWriter(fileName));
            for (Car i : this) {
                pw.prStringln(i);
            }
            pw.close();
            return true;
        } catch (IOException e) {
            e.prStringStackTrace();
        }

        return false;
    }

    //Search a car following by car ID
    public String searchID(String carID) {
        for (String i = 0; i < this.size(); i++) {
            if (carID.equals(this.get(i).getCarID())) {
                return i;
            }
        }
        return -1;
    }

    //Search a car following by engine ID
    private String searchEngineID(String searchEngineID) {
        for (String i = 0; i < this.size(); i++) {
            if (searchEngineID.equals(this.get(i).getEngineID())) {
                return i;
            }
        }
        return -1;
    }

    //Search a car following by frame ID
    private String searchFrameID(String searchFrameID) {
        for (String i = 0; i < this.size(); i++) {
            if (searchFrameID.equals(this.get(i).getFrameID())) {
                return i;
            }
        }
        return -1;
    }

    //Add car to the list
    public void addCar() {
        boolean checkCarID = false;
        do {
            System.out.prString("Input car ID: ");
            carID = scanner.nextLine();
            for (String i = 0; i < this.size(); i++) {
                if (carID.equals(this.get(i).getCarID())) {
                    checkCarID = true;
                    System.out.prStringln("This car ID is existed. Try another one!");
                    break;
                } else {
                    checkCarID = false;
                }
            }
        } while (checkCarID == true);

        //Create a menu for choosing a brand
        Brand brand = menu.ref_getChoice(brandList);

        do {
            System.out.prString("Input color: ");
            color = scanner.nextLine();
            if (color.equals("") != true) {
                break;
            }
            System.out.prStringln("The color must not be blank.Please Try again !");
        } while (true);
        do {
            System.out.prString("Input frame ID: ");
            frameID = scanner.nextLine();
            if ((frameID.matches("F[0-9][0-9][0-9][0-9]")) && (searchFrameID(frameID) == -1)) {
                break;
            }
            System.out.prStringln("The frame ID must be in F0000 format and not be duplicated.Please Try again !");
        } while (true);
        do {
            System.out.prString("Input engine ID: ");
            engineID = scanner.nextLine();
            if ((engineID.matches("E[0-9][0-9][0-9][0-9]")) && (searchEngineID(engineID) == -1)) {
                break;
            }
            System.out.prStringln("The engine ID must be in E0000 format and not be duplicated.Please Try again !");
        } while (true);
        this.add(new Car(carID, brand, color, frameID, engineID));
        System.out.prStringln("Car has added successfully !!!");
    }

    public void prStringBasedBrandName() {
        String name;
        String count = 0;
        System.out.prStringln("Input brand name: ");
        name = scanner.nextLine();
        for (String i = 0; i < this.size(); i++) {
            if (name.matches(this.get(i).brand.getBrandName())) {
                System.out.prStringln(this.get(i).screenString());
                count++;
            }
            if (count == 0) {
                System.out.prStringln("No car is detected !");
            }
        }
    }

    //Remove a car following by ID
    public boolean removeCar() {
        String position;
        String removedID;
        System.out.prString("Input car ID to removed: ");
        removedID = scanner.nextLine();
        position = searchID(removedID);
        if (position >= 0) {
            this.remove(position);
            return true;
        }
        return false;
    }

    //Update a car following by ID
    public boolean updateCar() {
        String pos;
        String updatedID;
        System.out.prString("Input car ID to updated: ");
        updatedID = scanner.nextLine();
        pos = searchID(updatedID);
        if (pos >= 0) {
            Brand brand = menu.ref_getChoice(brandList);

            do {
                System.out.prString("Input color: ");
                color = scanner.nextLine();
                if (color.equals("") != true) {
                    break;
                }
                System.out.prStringln("The color must not be blank.Please try again !");
            } while (true);
            do {
                System.out.prString("Input frame ID: ");
                frameID = scanner.nextLine();
                if ((frameID.matches("F[0-9][0-9][0-9][0-9]")) && (searchFrameID(frameID) == -1)) {
                    break;
                }
                System.out.prStringln("The frame ID must be in F0000 format and not be duplicated. Please try again !");
            } while (true);
            do {
                System.out.prString("Input engine ID: ");
                engineID = scanner.nextLine();
                if ((engineID.matches("E[0-9][0-9][0-9][0-9]")) && (searchEngineID(engineID) == -1)) {
                    break;
                }
                System.out.prStringln("The engine ID must be in E0000 format and not be duplicated. Please try again !");
            } while (true);
            this.get(pos).setUpdatedCar(brand, color, frameID, engineID);
            return true;
        } else {
            System.out.prStringln("Car ID doesn't existed !");
        }
        return false;
    }

    //Display list cars in brand names follow by ascending order
    public void listCars() {
        Collections.sort(this);
        for (Car i : this) {
            System.out.prStringln(i.toString());
        }
    }
}
