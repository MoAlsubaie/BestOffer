package bestoffer.kau.edu.bestoffer;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;

public class ShowItem extends AppCompatActivity {
public  items item = null ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        item = items.Ar.get(id);

        TextView name = (TextView) findViewById(R.id.item_name);
        name.setText(item.getName());


        TextView Description = (TextView) findViewById(R.id.Description);
        Description.setText(item.getDescription());

        ImageView imageView = findViewById(R.id.img);
        new GetImg(imageView, id).execute(item.getPictureLink());
        TextView price = (TextView) findViewById(R.id.price);
        if (item.getOffer() == 0) {

            price.setText(String.valueOf(item.getPrice()));

        } else {
            TextView oldp = (TextView) findViewById(R.id.oldprice);
            oldp.setText(String.valueOf(item.getPrice()));
            oldp.setPaintFlags(oldp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            price.setText(String.valueOf(item.getOffer()));


        }
        ImageView logo = (ImageView) findViewById(R.id.logo) ;
        if (item.getSupermarket().equalsIgnoreCase("CA")){
            logo.setImageResource(R.drawable.ca);
    }else if(item.getSupermarket().equalsIgnoreCase("DA")){
            logo.setImageResource(R.drawable.da);
    } else if (item.getSupermarket().equalsIgnoreCase("PA")){
            logo.setImageResource(R.drawable.pa);
        }


        MapView map = (MapView) findViewById(R.id.sumap) ;




    }
}
