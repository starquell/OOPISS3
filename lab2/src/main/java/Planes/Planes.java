package Planes;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Planes {

    private final List<Plane> planes;

    public Planes(List<Plane> planes) {
        this.planes = planes;
    }

    public void sort() {
        Collections.sort(planes);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (var plane : planes) {
            str.append(plane.toString());
        }
        return str.toString();
    }

      public void writeInFile(String fileName){
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(toString());
            writer.append('\n');
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Plane> getPlanes() {
        return planes;
    }
}
