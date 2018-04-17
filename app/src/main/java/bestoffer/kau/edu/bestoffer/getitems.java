package bestoffer.kau.edu.bestoffer;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by user on 10/02/18.
 */

public class getitems extends AsyncTask<String, Void, String> {
    private Context context ;


    public getitems(Context context) {
        this.context = context ;
    }

    @Override
    protected String doInBackground(String... args) {



        String link ;

        BufferedReader bufferedReader ;
        String result ;

        try {



            link = "http://bestoffer.gwiddle.co.uk/getitems.php";
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            result = bufferedReader.readLine();
            return result;
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }



    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onPostExecute(String result) {
        String jsonStr = result;
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                String query_result = jsonObj.getString("query_result");
                if (query_result.equals("SUCCESS")) {
                    Toast.makeText(context, "items updated", Toast.LENGTH_SHORT).show();
                        int num = jsonObj.getInt("i") ;

                    for (int i = 0; i < num; i++) {

                        items item = new items();
                        item.setIndex(i);
                        item.setId(jsonObj.getLong("id"+i));
                        item.setName(jsonObj.getString("name"+i).trim());
                        item.setType(jsonObj.getString("type"+i).trim());
                        item.setDescription(jsonObj.getString("description"+i).trim());
                        item.setPrice(jsonObj.getDouble("price"+i));
                        item.setPictureLink(jsonObj.getString("picture"+i).trim());
                        new GetImg(i).execute(item.getPictureLink()) ;
                        item.setOffer(jsonObj.getDouble("offer"+i));
                        item.setSupermarket(jsonObj.getString("supermarket"+i).trim());
                        items.ItemList.add(item);

                    }


                    Intent intent = new Intent(context, browseActivity.class);
                    context.startActivity(intent);
                } else if (query_result.equals("FAILURE")) {
                    Toast.makeText(context, "there is something wrong please try again later", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Couldn't connect to remote database.", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, "check your internet connection Please.", Toast.LENGTH_SHORT).show();
                System.out.println(e.getMessage());
            }
        } else {
            Toast.makeText(context, "Somthing went wrong Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
}