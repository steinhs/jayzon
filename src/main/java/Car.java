public class Car {
    // fields
    String model;
    String color;
    Brand brand;

    // constructor
    Car(String model, String color) {
        this.model = model;
        this.color = color;
    }

    public Car(String model, String color, Brand brand) {
        this.model = model;
        this.color = color;
        this.brand = brand;
    }

    public Car() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", brand=" + brand +
                '}';
    }
}
