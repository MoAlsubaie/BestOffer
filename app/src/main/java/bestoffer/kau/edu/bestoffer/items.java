package bestoffer.kau.edu.bestoffer;

import java.util.ArrayList;

/**
 * Created by user on 24/02/18.
 */

public class items {
   public static ArrayList<items> Ar = new ArrayList<items>() ;
    private long id ;
    private String name ;
    private String type ;
    private String description ;
    private double price ;
    private String pictureLink ;
    private double offer ;
    private String supermarket ;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public double getOffer() {
        return offer;
    }

    public void setOffer(double offer) {
        if(offer ==0) {

            this.offer = offer;
        }else{
            offer/= 100 ;

            this.offer = offer * this.price ;

            this.offer = this.price - this.offer ;

        }

    }

    public String getSupermarket() {
        return supermarket;
    }

    public void setSupermarket(String supermarket) {
        this.supermarket = supermarket;
    }
}
