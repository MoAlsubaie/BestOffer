package bestoffer.kau.edu.bestoffer;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by user on 20/03/18.
 */

public class supermarketLocation {
    private String name ;
    private String vicinity ;
    private LatLng latLng ;
    private float distance  ;

    public float getDistance() {
        return distance;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(double lat , double lng   ) {
        this.latLng = new LatLng(lat , lng);
        this.distance = distance(latLng) ;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//

    private static float distance( LatLng last){
        LatLng current = User.getInstance().getLatLng();
        if(last==null)
            return 0;
        Location cL = new Location("");
        cL.setLatitude(current.latitude);
        cL.setLongitude(current.longitude);

        Location lL = new Location("");
        lL.setLatitude(last.latitude);
        lL.setLongitude(last.longitude);

        return lL.distanceTo(cL);
    }

}
