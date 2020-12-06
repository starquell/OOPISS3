package Planes;

import java.util.Collections;
import java.util.Objects;

public class Params {

    private double length;
    private double width;
    private double heigth;

    public Params() {}

    public Params(double length, double width, double heigth) {
        this.length = length;
        this.width = width;
        this.heigth = heigth;
    }
    @Override
    public String toString() {
        return "Params{" +
                "length=" + length +
                ", wigth=" + width +
                ", heigth=" + heigth +
                '}';
    }

    public double getLength() {
        return length;
    }
    public double getWidth() {
        return width;
    }
    public double getHeigth() {
        return heigth;
    }
    public void setLength(double length) {
        this.length = length;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeigth(double heigth) {
        this.heigth = heigth;
    }
}
