package bestoffer.kau.edu.bestoffer;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 27/03/18.
 */

public class GridCartAdapter  extends BaseAdapter {
    private Context mContext;
    private ArrayList<items> Ar ;
    private int i;
    public GridCartAdapter(Context mContext, ArrayList<items> ar) {
        this.mContext = mContext;
        Ar = ar;
    }

    @Override
    public int getCount() {
        return Ar.size();
    }

    @Override
    public Object getItem(int i) {
        return Ar.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Ar.get(i).getId();
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        View gridViewAndroid;
        this.i = i ;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            gridViewAndroid = new View(mContext);
            gridViewAndroid = inflater.inflate(R.layout.grid_view_cart, null);




        } else {
            gridViewAndroid = (View) convertView;



        }

        TextView textViewAndroid = (TextView) gridViewAndroid.findViewById(R.id.gridview_text);
        TextView textViewAndroidP = (TextView) gridViewAndroid.findViewById(R.id.gridview_price);
        ImageView imageViewAndroid = (ImageView) gridViewAndroid.findViewById(R.id.gridview_image);
        TextView textViewAndroidO = (TextView) gridViewAndroid.findViewById(R.id.gridview_priceold);
        ImageView imageViewAndroidLogo = (ImageView) gridViewAndroid.findViewById(R.id.gridview_logo);
        System.out.println(Ar.toString());
        System.out.println(Ar.size());
        System.out.println("dsdsdsdsd  "+i);
        textViewAndroid.setText(Ar.get(i).getName());
        imageViewAndroid.setImageBitmap(Ar.get(i).getImg());
        if(Ar.get(i).getSupermarket().equalsIgnoreCase("ca")){
            imageViewAndroidLogo.setImageResource(R.drawable.ca);
        }else if(Ar.get(i).getSupermarket().equalsIgnoreCase("da")){
            imageViewAndroidLogo.setImageResource(R.drawable.da);
        }else{
            imageViewAndroidLogo.setImageResource(R.drawable.pa);
        }

        if(Ar.get(i).getOffer() != 0){
            textViewAndroidO.setText(String.valueOf(Ar.get(i).getOffer()));
            textViewAndroidP.setText(String.valueOf(Ar.get(i).getPrice()));
            textViewAndroidO.setPaintFlags(textViewAndroidO.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            textViewAndroidP.setText(String.valueOf(Ar.get(i).getPrice()));
            textViewAndroidO.setText("");

        }



        return gridViewAndroid;


    }
}
