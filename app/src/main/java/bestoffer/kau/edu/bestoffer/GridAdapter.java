package bestoffer.kau.edu.bestoffer;



import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {

    private Context mContext;

    public GridAdapter(Context context) {
        mContext = context;

    }

    @Override
    public int getCount() {
        return items.Ar.size();
    }

    @Override
    public Object getItem(int i) {
        return items.Ar.get(i);
    }

    @Override
    public long getItemId(int i) {
        return items.Ar.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View gridViewAndroid;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            gridViewAndroid = new View(mContext);
            gridViewAndroid = inflater.inflate(R.layout.grid_view, null);




        } else {
            gridViewAndroid = (View) convertView;



        }

        TextView textViewAndroid = (TextView) gridViewAndroid.findViewById(R.id.gridview_text);
        TextView textViewAndroidP = (TextView) gridViewAndroid.findViewById(R.id.gridview_price);
        ImageView imageViewAndroid = (ImageView) gridViewAndroid.findViewById(R.id.gridview_image);
        TextView textViewAndroidO = (TextView) gridViewAndroid.findViewById(R.id.gridview_priceold);
        ImageView imageViewAndroidLogo = (ImageView) gridViewAndroid.findViewById(R.id.gridview_logo);

        textViewAndroid.setText(items.Ar.get(i).getName());
        new GetImg(imageViewAndroid , i).execute(items.Ar.get(i).getPictureLink()) ;
        if(items.Ar.get(i).getSupermarket().equalsIgnoreCase("ca")){
            imageViewAndroidLogo.setImageResource(R.drawable.ca);
        }else if(items.Ar.get(i).getSupermarket().equalsIgnoreCase("da")){
            imageViewAndroidLogo.setImageResource(R.drawable.da);
        }else{
            imageViewAndroidLogo.setImageResource(R.drawable.pa);
        }

        if(items.Ar.get(i).getOffer() != 0){
            textViewAndroidO.setText(String.valueOf(items.Ar.get(i).getPrice()));
            textViewAndroidP.setText(String.valueOf(items.Ar.get(i).getOffer()));
            textViewAndroidO.setPaintFlags(textViewAndroidO.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            textViewAndroidP.setText(String.valueOf(items.Ar.get(i).getPrice()));
            textViewAndroidO.setText("");

        }

        return gridViewAndroid;

    }


}


