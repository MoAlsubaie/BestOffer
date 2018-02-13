package bestoffer.kau.edu.bestoffer;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by user on 10/02/18.
 */

public class signinConn extends AsyncTask<String, Void, String> {
    private Context context ;
    private String email ;
    private String password  ;

    public signinConn(Context context) {
        this.context = context ;
    }

    @Override
    protected String doInBackground(String... args) {

        email= args[0] ;
        password = args[1] ;

        String link ;
        String data ;
        BufferedReader bufferedReader ;
        String result ;

        try {

            data = "?email=" + URLEncoder.encode(email, "UTF-8");
            data += "&password=" + URLEncoder.encode(password, "UTF-8");

            link = "http://bestoffer.gwiddle.co.uk/signin.php" + data;
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = bufferedReader.readLine();
            return result;
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }



    }


    @Override
    protected void onPostExecute(String result) {
        String jsonStr = result;
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                String query_result = jsonObj.getString("query_result");
                if (query_result.equals("SUCCESS")) {
                    Toast.makeText(context, "logIn successful.", Toast.LENGTH_SHORT).show();

                    User user = User.getInstance();
                    System.out.println("aa");
                    user.setEmail(jsonObj.getString("email"));
                    user.setFirstname(jsonObj.getString("firstname"));
                    System.out.println(user.getFirstname());
                    user.setLastname(jsonObj.getString("lastname"));
                    user.setPassword(jsonObj.getString("password"));

                    Intent intent = new Intent(context, browseActivity.class);
                    context.startActivity(intent);

                } else if (query_result.equals("FAILURE")) {
                    Toast.makeText(context, "Eamil or password wrong", Toast.LENGTH_SHORT).show();
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