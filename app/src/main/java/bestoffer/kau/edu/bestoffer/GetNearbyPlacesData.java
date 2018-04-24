package bestoffer.kau.edu.bestoffer;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 20/03/18.
 */

public class GetNearbyPlacesData extends AsyncTask<Object , String , String> {

    String GooglePlacesData ;
    String myurl ;
    supermarket supermarket ;

    @Override
    protected String doInBackground(Object... objects) {
        supermarket = (supermarket) objects[0] ;
        myurl = (String) objects[1] ;

        InputStream inputStream = null ;
        HttpURLConnection httpURLConnection = null ;

        try {
            URL url = new URL(myurl) ;
            httpURLConnection = (HttpURLConnection) url.openConnection() ;
            httpURLConnection.connect();

            inputStream = httpURLConnection.getInputStream() ;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)) ;
            StringBuffer stringBuffer = new StringBuffer() ;

            String line = "" ;
            while((line = bufferedReader.readLine()) != null)
                stringBuffer.append(line) ;

            GooglePlacesData = stringBuffer.toString() ;
            bufferedReader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            httpURLConnection.disconnect();
        }
        return GooglePlacesData ;

    }

    @Override
    protected void onPostExecute(String s) {
        List<HashMap<String,String>> nearbyPlaces = null ;
        DataParser parser = new DataParser();
        nearbyPlaces = parser.parse(s) ;
        System.err.println("sssssss    :"+s);
        SetnearByPlaces(nearbyPlaces);
    }

    private void SetnearByPlaces (List<HashMap<String , String>> nearByPlaces){
        ArrayList<supermarketLocation> supermarketLocation = new ArrayList<supermarketLocation>();

        for (int i = 0; i < nearByPlaces.size(); i++) {
            HashMap<String , String> googleplace = nearByPlaces.get(i) ;
            supermarketLocation supermarket  = new supermarketLocation();
            supermarket.setName(googleplace.get("name"));
            supermarket.setVicinity(googleplace.get("vicinity"));
            double lat = Double.parseDouble(googleplace.get("lat")) ;
            double lng = Double.parseDouble(googleplace.get("lng")) ;
            supermarket.setLatLng(lat , lng);
            supermarketLocation.add(supermarket) ;

        }
        supermarket.setSupermarketLocations(supermarketLocation);

    }
}
