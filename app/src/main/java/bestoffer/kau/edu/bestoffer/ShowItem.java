package bestoffer.kau.edu.bestoffer;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ShowItem extends AppCompatActivity  implements OnMapReadyCallback {
public  items item = null ;
    private GoogleMap mMap;
    int src = 1 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        item = items.Ar.get(id);

        TextView name = (TextView) findViewById(R.id.item_name);
        name.setText(item.getName());


        TextView Description = (TextView) findViewById(R.id.Description);
        Description.setText(item.getDescription());

        ImageView imageView = findViewById(R.id.img);
        imageView.setImageBitmap(item.getImg());
        TextView price = (TextView) findViewById(R.id.price);
        if (item.getOffer() == 0) {

            price.setText(String.valueOf(item.getPrice()));

        } else {
            TextView oldp = (TextView) findViewById(R.id.oldprice);
            oldp.setText(String.valueOf(item.getOffer()));
            oldp.setPaintFlags(oldp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            price.setText(String.valueOf(item.getPrice()));


        }
        ImageView logo = (ImageView) findViewById(R.id.logo) ;
        if (item.getSupermarket().equalsIgnoreCase("CA")){
            logo.setImageResource(R.drawable.ca);
    }else if(item.getSupermarket().equalsIgnoreCase("DA")){
            logo.setImageResource(R.drawable.da);
    } else if (item.getSupermarket().equalsIgnoreCase("PA")){
            logo.setImageResource(R.drawable.pa);
        }
try {
    if (item.getSupermarket().equalsIgnoreCase(theNearest())) {
        TextView TheNearest = (TextView) findViewById(R.id.the_nearest);
        TheNearest.setVisibility(View.VISIBLE);

    }
}catch(Exception e ){
        e.printStackTrace();
}
        ImageView cartImg = (ImageView) findViewById(R.id.cart) ;
        int index = cart.cartList.indexOf(item) ;
        if (index == -1){
            cartImg.setImageResource(R.drawable.blackcart);
            src = 0 ;
        }else{
            cartImg.setImageResource(R.drawable.greencart);

        }



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        supermarket mysupermarket = null ;

        System.out.println("dddd"+supermarket.supermarkets.size());
        if(item.getSupermarket().equalsIgnoreCase("pa")){
           mysupermarket = supermarket.supermarkets.get(0);
        } else if(item.getSupermarket().equalsIgnoreCase("da")){
            mysupermarket = supermarket.supermarkets.get(1);
        }else {
            mysupermarket = supermarket.supermarkets.get(2);
        }
        try {
            // Add a marker in Sydney and move the camera
            supermarketLocation supermarketLocation = mysupermarket.getSupermarketLocations().get(0);
            LatLng supermarket = supermarketLocation.getLatLng();
            mMap.addMarker(new MarkerOptions().position(supermarket).title(supermarketLocation.getName() + " : " + supermarketLocation.getVicinity()));
        }catch (Exception e){
            e.printStackTrace();
        }

        LatLng user = new LatLng(User.getInstance().getLatLng().latitude , User.getInstance().getLatLng().longitude) ;
        MarkerOptions markerOptions = new MarkerOptions() ;
        markerOptions.position(user);
        markerOptions.title("YOU");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(user));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
        }

        public void click(View v) {
            ImageView cartimg = (ImageView) findViewById(R.id.cart);

            if(src == 0){
                cart.cartList.add(this.item);
                cartimg.setImageResource(R.drawable.greencart);
                src = 1 ;
                new mangecart(this , item , 1).execute();
            }else{
                cart.cartList.remove(this.item);
                cartimg.setImageResource(R.drawable.blackcart);
                src = 0 ;
                new mangecart(this , item , 2).execute();
            }


        }


        public String theNearest (){
try {
    double pa = supermarket.supermarkets.get(0).getSupermarketLocations().get(0).getDistance();
    double da = supermarket.supermarkets.get(1).getSupermarketLocations().get(0).getDistance();
    double ca = supermarket.supermarkets.get(2).getSupermarketLocations().get(0).getDistance();
    if (pa < da && pa < ca)
        return "PA";
    else if (da < pa && da < ca)
        return "DA";
    else
        return "CA";
}catch (Exception e){
    e.printStackTrace();
}

return null ;
        }

    }

