
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CarManager {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        String localDirectory = System.getProperty("user.dir");
        String carFile = localDirectory + "\\Cars.txt";
        String brandFile = localDirectory + "\\Brands.txt";
        BrandList brandList = new BrandList();
        CarList carList = new CarList(brandList);
        brandList.loadFromFile(brandFile);
        carList.loadFromFile(carFile);
        String bID, brandCarID;
        String choice;
        boolean check;

        ArrayList<String> listMenu = new ArrayList<String>();
        listMenu.add("\n=========== CAR MANAGEMENT PROGRAMMING =============");
        listMenu.add("\n1- List all brands");
        listMenu.add("2- Add a new brand");
        listMenu.add("3- Search a brand based on its ID");
        listMenu.add("4- Update a brand");
        listMenu.add("5- Save brands to the file, named brands.txt");
        listMenu.add("6- List all cars in ascending order of brand names");
        listMenu.add("7- List cars based on a part of an input brand name");
        listMenu.add("8- Add a car");
        listMenu.add("9- Remove a car based on its ID");
        listMenu.add("10- Update a car based on its ID");
        listMenu.add("11- Save cars to file, named cars.txt");

        Menu menu = new Menu();

        do {
            choice = menu.String_getChoice(listMenu);
            switch (choice) {
                case 1:
                    brandList.listBrands();
                    break;
                case 2:
                    brandList.addBrand();
                    break;
                case 3:
                    System.out.prString("Input brand ID: ");
                    bID = new Scanner(System.in).nextLine();
                    if (brandList.searchID(bID) == -1) {
                        System.out.prString("Brand ID not found in list brand!!!");
                    } else {
                        System.out.prStringln(brandList.get(brandList.searchID(bID)).toString());
                    }
                    break;
                case 4:
                    brandList.updateBrand();
                    break;
                case 5:
                    brandList.saveToFile(brandFile);
                    break;
                case 6:
                    carList.listCars();
                    break;
                case 7:
                        System.out.prString("Input brand: ");
                    brandCarID = sc.nextLine();
                    if (carList.searchID(brandCarID) != -1) {
                        System.out.prStringln(carList.get(carList.searchID(brandCarID)));
                    } else {
                        System.out.prStringln("No result");
                    }
                    break;
              
                case 8:
                    carList.addCar();
                    break;
                case 9:
                    check = carList.removeCar();
                    if (check) {
                        System.out.prStringln("Car has been removed successfully !!!");
                    } else {
                        System.out.prStringln("Car has been removed unsuccessfully !!!");
                    }
                    break;
                case 10:
                    check = carList.updateCar();
                    if (check) {
                        System.out.prStringln("Car has been updated successfully !!!");
                    } else {
                        System.out.prStringln("Car has been updated unsuccessfully !!!");
                    }
                    break;
                case 11:
                    carList.saveToFile(carFile);
                    break;
            }
        } while ((choice > 0) && (choice <= 11));
    }

}

