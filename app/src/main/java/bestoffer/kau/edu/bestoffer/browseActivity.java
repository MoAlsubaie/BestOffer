package bestoffer.kau.edu.bestoffer;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class browseActivity extends AppCompatActivity  {
    GridView androidGridView;
     Context context = this ;
    MaterialSearchView searchView ;
    static ArrayList<items> search ;
    private FusedLocationProviderClient mFusedLocationClient ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        supermarket panda = new supermarket();
        panda.setName("Hyper Panda");
        supermarket danube = new supermarket();
        danube.setName("Danube");
        supermarket carrefour = new supermarket();
        carrefour.setName("Carrefour");
        supermarket.supermarkets.add(panda) ;
        supermarket.supermarkets.add(danube) ;
        supermarket.supermarkets.add(carrefour) ;
        System.out.println("dddd"+supermarket.supermarkets.size());

        UserLocation();
        AskForLocations();


        Toast.makeText(this, "updateing...", Toast.LENGTH_SHORT).show();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar) ;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Best Offer");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        if(search == null)
        BrowseItems(items.Ar);
        else
        BrowseItems(search);

         searchView = (MaterialSearchView)findViewById(R.id.search_view);

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {


            }

            @Override
            public void onSearchViewClosed() {
                BrowseItems(items.Ar);

            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null && !query.isEmpty()) {

                 Intent intent = new Intent(context , browseActivity.class ) ;
                    startActivity(intent);

                }else{
                    BrowseItems(items.Ar);
                    search = null ;
                }
                return true ;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()) {
                     search = new ArrayList<items>();
                    for(items item :items.Ar)
                        if(item.getDescription().toLowerCase().contains(newText.toLowerCase()))
                            search.add(item);
                            BrowseItems(search);
                }else{
                    search = null ;
                    BrowseItems(items.Ar);
                }
                return true ;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu); ;
        MenuItem item = menu.findItem(R.id.action_search) ;
        searchView.setMenuItem(item);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.sort_by_price:
              SortByPrice();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void BrowseItems (ArrayList<items> Ar){


        GridAdapter adapterViewAndroid = new GridAdapter(browseActivity.this,Ar);
        androidGridView=(GridView)findViewById(R.id.gridView);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id ) {
                Intent intent = new Intent(context, ShowItem.class);
                if(search == null)
                intent.putExtra("id" , i ) ;
                else
                    intent.putExtra("id" , search.get(i).getIndex() ) ;
                startActivity(intent);



            }
        });


    }

    public void SortByPrice (){
        if(search==null){
            Collections.sort(items.Ar, new Comparator<items>() {
                @Override
                public int compare(items c1, items c2) {
                    return Double.compare(c1.getPrice(), c2.getPrice());
                }
            });
            BrowseItems(items.Ar);
        }else{
            Collections.sort(search, new Comparator<items>() {
                @Override
                public int compare(items c1, items c2) {
                    return Double.compare(c1.getPrice(), c2.getPrice());
                }
            });
            BrowseItems(search);
        }


    }


    public void AskForLocations(){
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("PERMISSION").setMessage("sorry we need this premission to get you the best location for you");
                builder.setPositiveButton("OK",null);
                builder.create().show();
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        0);
            } else {

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        0);


            }
        } else {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                for (supermarket su:supermarket.supermarkets) {
                                    String url = getUrl(location.getLatitude() , location.getLongitude() , su.getName()) ;
                                    System.out.println(su.getName());
                                    Object data[] = new Object[2] ;
                                    data[0] = su ;
                                    data[1] = url ;

                                    GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData() ;
                                    getNearbyPlacesData.execute(data) ;
                                }
                            }
                        }
                    });
        }
        /*for (supermarket su:supermarket.supermarkets) {
            String url = getUrl(User.getInstance().getLatLng().latitude , User.getInstance().getLatLng().longitude , su.getName()) ;
            Object data[] = new Object[2] ;
            data[0] = su ;
            data[1] = url ;

            GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData() ;
            getNearbyPlacesData.execute(data) ;
        }*/


    }

    public String getUrl (double lat , double lng , String NP){
        StringBuilder googlePlace = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?") ;
        googlePlace.append("location="+lat+","+lng) ;
        googlePlace.append("&radius=10000");
        googlePlace.append("&keyword="+NP+"+supermarket") ;
        googlePlace.append("&sensor=true") ;
        googlePlace.append("&key="+"AIzaSyCxAvaQRHD6XqC8tzr3ZQ_uUjEPgcbqsu8");

        return googlePlace.toString() ;
    }

    public void UserLocation (){

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("PERMISSION").setMessage("sorry we need this premission to get you the best location for you");
                builder.setPositiveButton("OK",null);
                builder.create().show();
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        0);
            } else {

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        0);


            }
        } else {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                LatLng latLng = new LatLng(location.getLatitude() , location.getLongitude());
                              User.getInstance().setLatLng(latLng);

                            }
                        }
                    });
        }
    }
}

