package Parser;

import Planes.Plane;

import java.util.ArrayList;
import java.util.List;


public class PlaneBuilder {

    private Plane plane;
    private List<Plane> planes;
    private String rootName;

    public PlaneBuilder() {
        plane = new Plane();
        planes = new ArrayList<>();
        rootName = "Plane";
    }

    public Plane getPlane(){
        return plane;
    }
    public List<Plane> getPlanes(){
        return planes;
    }

    public String getRootName(){
        return rootName;
    }

    public void setTag(String tag, String value) {

        switch (tag.toLowerCase()){
            case "plane" -> plane = new Plane();
            case "model" -> plane.setModel(value);
            case "origin" -> plane.setOrigin(value);
            case "type" -> plane.getChars().setType(value);
            case "nplaces" -> plane.getChars().setnPlaces(Integer.parseInt(value));
            case "hasradar" -> plane.getChars().setHasRadar(Boolean.parseBoolean(value));
            case "length" -> plane.getParams().setLength(Double.parseDouble(value));
            case "width" -> plane.getParams().setWidth(Double.parseDouble(value));
            case "heigth" -> plane.getParams().setHeigth(Double.parseDouble(value));
            default -> {}
        }
    }

    public void saveElement(String element){
        if(element.equalsIgnoreCase("plane")) {
            planes.add(plane);
            plane = new Plane();
        }
    }
}