package zhiwenyan.cmccaifu.com.android2017.Jackson;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Description:
 * Data：2018/9/23
 * Author:Steven
 */
public class Car {
    @JsonProperty("brand")
    private String brand1;
    @JsonProperty
    private int doors;

    public void setBrand(String brand) {
        this.brand1 = brand;
    }

    public String getBrand() {
        return brand1;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public int getDoors() {
        return doors;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand1 + '\'' +
                ", doors='" + doors + '\'' +
                '}';
    }
}
