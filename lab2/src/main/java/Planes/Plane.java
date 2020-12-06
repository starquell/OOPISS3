package Planes;

public class Plane implements Comparable {

    private String model;
    private String origin;
    private Chars chars = new Chars();
    private Params params = new Params();
    private int price;

    public Plane() {}

    public Plane(String model, String origin, Chars chars, Params params, int price) {
        this.model = model;
        this.origin = origin;
        this.chars = chars;
        this.params = params;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Plane" +
                "{" +
                "model='" + model + "'\n" +
                ", origin='" + origin + "'\n" +
                ", price=" + price + "'\n" +
                '}';
    }
    public void setModel(String model) {
        this.model = model;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setChars(Chars chars) {
        this.chars = chars;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public String getOrigin() {
        return origin;
    }

    public Chars getChars() {
        return chars;
    }

    public Params getParams() {
        return params;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public int compareTo(Object o) {
        return model.compareTo(((Plane) o).getModel());
    }
}
