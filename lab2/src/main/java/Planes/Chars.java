package Planes;

import java.util.Objects;

public class Chars {

    private String type;
    private int nPlaces;
    private boolean hasRadar;

    public Chars() {}

    public Chars(String type, int nPlaces, boolean hasRadar) {
        this.type = type;
        this.nPlaces = nPlaces;
        this.hasRadar = hasRadar;
    }

    public String getType() {
        return type;
    }
    public int getNPlaces() {
        return nPlaces;
    }
    public boolean hasRadar() {
        return hasRadar;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setnPlaces(int nPlaces) {
        this.nPlaces = nPlaces;
    }

    public void setHasRadar(boolean hasRadar) {
        this.hasRadar = hasRadar;
    }

    @Override
    public String toString() {
        return "Chars{" +
                "type='" + type + '\'' +
                ", nPlaces=" + nPlaces +
                ", hasRadar=" + hasRadar +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Chars chars = (Chars) o;
        return nPlaces == chars.nPlaces && hasRadar == chars.hasRadar && type.equals(chars.type);
    }
}
