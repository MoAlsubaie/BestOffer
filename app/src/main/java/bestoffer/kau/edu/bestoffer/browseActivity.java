package bestoffer.kau.edu.bestoffer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class browseActivity extends AppCompatActivity  {
    GridView androidGridView;
     Context context = this ;
    MaterialSearchView searchView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);


        Toast.makeText(this, "updateing...", Toast.LENGTH_SHORT).show();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar) ;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Best Offer");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        br(items.Ar);

         searchView = (MaterialSearchView)findViewById(R.id.search_view);

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                br(items.Ar);

            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null && !query.isEmpty()) {
                    ArrayList<items> search = new ArrayList<items>();

                    for(items item :items.Ar)
                        if(item.getDescription().contains(query))
                            search.add(item);

                    Collections.sort(search, new Comparator<items>() {
                        @Override
                        public int compare(items c1, items c2) {
                            return Double.compare(c1.getPrice(), c2.getPrice());
                        }
                    });

                    br(search);

                }else{
                    br(items.Ar);
                }
                return true ;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()) {
                    ArrayList<items> search = new ArrayList<items>();

                    for(items item :items.Ar)
                        if(item.getDescription().contains(newText))
                            search.add(item);

                    Collections.sort(search, new Comparator<items>() {
                        @Override
                        public int compare(items c1, items c2) {
                            return Double.compare(c1.getPrice(), c2.getPrice());
                        }
                    });

                    br(search);

                }else{
                    br(items.Ar);
                }
                return true ;
            }
        });

       /* Collections.sort(items.Ar, new Comparator<items>() {
            @Override
            public int compare(items c1, items c2) {
                return Double.compare(c1.getPrice(), c2.getPrice());
            }
        });*/



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu); ;
        MenuItem item = menu.findItem(R.id.action_search) ;
        searchView.setMenuItem(item);
        return true ;
    }

    public void br (ArrayList<items> Ar){


        GridAdapter adapterViewAndroid = new GridAdapter(browseActivity.this,Ar);
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

