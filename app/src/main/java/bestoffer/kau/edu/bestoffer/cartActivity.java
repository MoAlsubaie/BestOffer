package bestoffer.kau.edu.bestoffer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class cartActivity extends AppCompatActivity {
Context context = this ;
    GridView androidGridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_cart) ;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Best Offer");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        GridCartAdapter adapterViewAndroid = new GridCartAdapter(this,cart.cartList);
        androidGridView=(GridView)findViewById(R.id.gridViewCart);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id ) {
                Intent intent = new Intent(context, ShowItem.class);
                intent.putExtra("id" , i ) ;
                intent.putExtra("who" , true) ;
                startActivity(intent);



            }
        });

    }


}
