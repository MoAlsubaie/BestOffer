package bestoffer.kau.edu.bestoffer;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by user on 25/03/18.
 */

public class deleteall extends AsyncTask<String, Void, String> {
    private Context context ;
    GridCartAdapter adapterViewAndroid;

    public deleteall(Context context ,GridCartAdapter adapterViewAndroid  ) {
        this.context = context ;
        this.adapterViewAndroid = adapterViewAndroid ;
    }

    @Override
    protected String doInBackground(String... args) {


        String link;
        BufferedReader bufferedReader;
        String result="";
    //start conn by Email
        try {

            User user = User.getInstance();

            link = "http://bestoffer.gwiddle.co.uk/delete_all_cart.php?email=" + user.getEmail();
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                       result = bufferedReader.readLine();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

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
                        Toast.makeText(context, "all items deleted", Toast.LENGTH_SHORT).show();

                        cart.cartList.removeAll(cart.cartList);
                        adapterViewAndroid.notifyDataSetChanged();



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


