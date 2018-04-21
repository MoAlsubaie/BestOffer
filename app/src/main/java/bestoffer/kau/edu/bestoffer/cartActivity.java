package bestoffer.kau.edu.bestoffer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class cartActivity extends AppCompatActivity {
Context context = this ;
    GridView androidGridView;
    GridCartAdapter adapterViewAndroid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_cart) ;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

         adapterViewAndroid = new GridCartAdapter(this,cart.cartList);
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

    public void deleteAll (View v){

        new AlertDialog.Builder(this)
                .setTitle("Delete All")
                .setMessage("Do you really want to Delete all items ?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        new deleteall(cartActivity.this , adapterViewAndroid).execute();
                    }})
                .setNegativeButton(android.R.string.no, null).show();

    }


}
