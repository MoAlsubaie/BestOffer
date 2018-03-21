package bestoffer.kau.edu.bestoffer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DataParser {
    private HashMap<String , String> getPlace (JSONObject googlePlaceJson){
        HashMap<String , String > googlePlacesMap = new HashMap<>() ;
        String placename = "-NA-" ;
        String vicinity = "-NA" ;
        String lat ;
        String lng ;
        String refernce ;

        try {
            if(!googlePlaceJson.isNull("name")){

                    placename = googlePlaceJson.getString("name") ;

            }
            if(!googlePlaceJson.isNull("vicinity")){
                vicinity = googlePlaceJson.getString("vicinity") ;
            }

            lat = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lat") ;
            lng =  googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lng") ;

            refernce = googlePlaceJson.getString("reference") ;

            googlePlacesMap.put("name" , placename) ;
            googlePlacesMap.put("vicinity" , vicinity) ;
            googlePlacesMap.put("lat" , lat) ;
            googlePlacesMap.put("lng" , lng) ;
            googlePlacesMap.put("reference" , refernce) ;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return googlePlacesMap ;

    }
    private List<HashMap<String , String >> getplaces(JSONArray jsonArray){
        List<HashMap<String ,String>> placeslist=new ArrayList<>();
        HashMap<String , String> placemap=null;

        for (int i =0; i<jsonArray.length();i++){
            try {
                placemap= getPlace((JSONObject) jsonArray.get(i));
                placeslist.add(placemap);


            }catch (JSONException ex){
                ex.printStackTrace();
            }

        }
        return placeslist;

    }

    public List<HashMap<String , String >> parse (String jsonData){
        JSONArray jsonArray = null ;
        JSONObject jsonObject ;

        try {
            jsonObject = new JSONObject(jsonData) ;
            jsonArray = jsonObject.getJSONArray("results") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getplaces(jsonArray) ;
    }
}
