package bestoffer.kau.edu.bestoffer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public class GetImg extends AsyncTask<String, Void, Bitmap> {


    Bitmap img;
    int i;

    public GetImg( int i) {

        this.i = i;
    }

    @Override
    protected Bitmap doInBackground(String... url) {
        img = null;
        try {

            InputStream inputStream = new java.net.URL(url[0]).openStream();
            img = BitmapFactory.decodeStream(inputStream);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }

    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        items.Ar.get(i).setImg(result);



    }
}




