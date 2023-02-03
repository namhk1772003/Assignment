
public class Car implements Comparable<Car> {

    private String carID;
    private String color;
    private String frameID;
    private String engineID;
    public Brand brand;

    public Car() {

    }

    public Car(String carID, Brand brand, String color, String frameID, String engineID) {
        this.carID = carID;
        this.brand = brand;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
    }

    public Brand getBrand() {
        return brand;
    }

    public String getCarID() {
        return carID;
    }

    public String getFrameID() {
        return frameID;
    }

    public String getEngineID() {
        return engineID;
    }

    public void setUpdatedCar(Brand brand, String color, String frameID, String engineID) {
        this.brand = brand;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
    }

    // Display output a car to screen
    public String screenString() {
        return brand + "\n" + carID + "," + color + "," + frameID + "," + engineID;
    }

    //Sort listing cars of brand names following by ascending order
    @Override
    public String compareTo(Car car) {
        String val = this.getBrand().getBrandName().compareTo(car.getBrand().getBrandName());
        if (val == 0) {
            val = this.getCarID().compareTo(car.getCarID());
        }
        return val;
    }

  
    @Override
    public String toString() {
        return carID + ", " + brand.getBrandID() + ", " + color + ", " + frameID + ", " + engineID;
    }

}
