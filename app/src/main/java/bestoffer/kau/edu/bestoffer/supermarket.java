package bestoffer.kau.edu.bestoffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class supermarket {
    private String name ;
    private ArrayList<supermarketLocation> supermarketLocations  ;
    public static ArrayList<supermarket> supermarkets = new ArrayList<supermarket>() ;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<supermarketLocation> getSupermarketLocations() {
        return supermarketLocations;
    }

    public void setSupermarketLocations(ArrayList<supermarketLocation> supermarketLocations) {

        this.supermarketLocations = supermarketLocations;

        Collections.sort(supermarketLocations, new Comparator<supermarketLocation>() {
            @Override
            public int compare(supermarketLocation c1, supermarketLocation c2) {
                return Float.compare(c1.getDistance(), c2.getDistance());
            }
    });


}

}
