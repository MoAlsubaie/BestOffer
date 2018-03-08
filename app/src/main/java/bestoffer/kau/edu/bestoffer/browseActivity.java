package bestoffer.kau.edu.bestoffer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class browseActivity extends AppCompatActivity  {
    GridView androidGridView;
     Context context = this ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);


        Toast.makeText(this, "updateing...", Toast.LENGTH_SHORT).show();


       /* Collections.sort(items.Ar, new Comparator<items>() {
            @Override
            public int compare(items c1, items c2) {
                return Double.compare(c1.getPrice(), c2.getPrice());
            }
        });*/



        GridAdapter adapterViewAndroid = new GridAdapter(browseActivity.this);
        androidGridView=(GridView)findViewById(R.id.gridView);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id ) {
                Intent intent = new Intent(context, ShowItem.class);
                intent.putExtra("id" , i ) ;
                startActivity(intent);



            }
        });


    }




    }

