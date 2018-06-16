package com.alviss.shoesstore;

/**
 * Created by ADJ on 8/8/2017.
 */


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;


public class CartListAdapter extends ArrayAdapter<String> {
    private List<String> sId;
    private List<String> sShoenames;
    private List<String> sSizes;
    private List<String> sPrices;
    private List<String> sImages;
    private Activity context;
    private Recheck layout;

    public String getsId(int poisition){
        return sId.get(poisition);
    }
    public String getsName(int poisition){
        return sShoenames.get(poisition);
    }
    public String getsPrice(int poisition){
        return sPrices.get(poisition);
    }
    public String getsSize(int poisition){
        return sSizes.get(poisition);
    }
    public String getsImage(int poisition){
        return sImages.get(poisition);
    }

    public CartListAdapter(Activity context, List<String> sId, List<String> sShoenames, List<String> sPrices, List<String> sSizes, List<String> sImages, Recheck temp) {
        super(context, R.layout.shoes_list_item, sId);
        this.context = context;
        this.sId = sId;
        this.sShoenames = sShoenames;
        this.sPrices = sPrices;
        this.sSizes = sSizes;
        this.sImages = sImages;
        this.layout = temp;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.cart_list_item, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.tv_shoename);
        TextView textViewPrice = (TextView) listViewItem.findViewById(R.id.tv_price);
        TextView textViewSize = (TextView) listViewItem.findViewById(R.id.tv_size);
        ImageView iv = (ImageView)listViewItem.findViewById(R.id.img_icon);
        Button btndel = (Button) listViewItem.findViewById(R.id.btn_del);

        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st = sId.get(position);
                layout.delincart(st);
                notifyDataSetChanged();
            }
        });


        textViewName.setText(sShoenames.get(position));
        textViewPrice.setText(sPrices.get(position));
        textViewSize.setText(sSizes.get(position));
       // Uri uri = Uri.parse(sImages(position));
        //Uri uri = Uri.parse("https://drive.google.com/uc?id=0B___GhMLUVtOY09SbDU5cDU2T1U");
       // draweeView.setImageURI(uri);

        Picasso.with(context).load(sImages.get(position)).into(iv);

        return listViewItem;
    }
}