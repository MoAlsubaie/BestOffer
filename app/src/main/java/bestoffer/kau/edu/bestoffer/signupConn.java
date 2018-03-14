package bestoffer.kau.edu.bestoffer;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

class signupConn extends AsyncTask<String, Void, String> {

    private Context context ;
    private String firstname ;
    private String lastname ;
    private String email ;
    private String password  ;

    public signupConn(Context context) {
        this.context = context ;
    }

    @Override
    protected String doInBackground(String... args) {
         firstname = args[0];
         lastname = args[1];
         email= args[2] ;
         password = args[3] ;

        String link ;
        String data ;
        BufferedReader bufferedReader ;
        String result ;

        try {
            data = "?firstname=" + URLEncoder.encode(firstname, "UTF-8");
            data += "&lastname=" + URLEncoder.encode(lastname, "UTF-8");
            data += "&email=" + URLEncoder.encode(email, "UTF-8");
            data += "&password=" + URLEncoder.encode(password, "UTF-8");

            link = "http://bestoffer.gwiddle.co.uk/createuser.php" + data;
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
                    Toast.makeText(context, "Signup successful.", Toast.LENGTH_SHORT).show();

                    User user = User.getInstance();

                    user.setFirstname(firstname);
                    user.setLastname(lastname);
                    user.setEmail(email);
                    user.setPassword(password);

                    new getitems(context).execute() ;

                } else if (query_result.equals("FAILURE")) {
                    Toast.makeText(context, "Eamil already exsists.", Toast.LENGTH_SHORT).show();
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