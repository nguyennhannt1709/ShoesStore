package com.alviss.shoesstore;

/**
 * Created by ADJ on 8/8/2017.
 */


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class ShoesListAdapter extends ArrayAdapter<String> {
    private String[] sId;
    private String[] sShoenames;
    private String[] sShopnames;
    private String[] sSizes;
    private String[] sPrices;
    private String[] sInfors;
    private String[] sImages;
    private Activity context;

    public String getsId(int poisition){
        return sId[poisition];
    }
    public String getsName(int poisition){
        return sShoenames[poisition];
    }
    public String getsShop(int poisition){
        return sShopnames[poisition];
    }
    public String getsPrice(int poisition){
        return sPrices[poisition];
    }
    public String getsSize(int poisition){
        return sSizes[poisition];
    }
    public String getsInfor(int poisition){
        return sInfors[poisition];
    }
    public String getsImage(int poisition){
        return sImages[poisition];
    }

    public ShoesListAdapter(Activity context, String[] sId, String[] sShoenames, String[] sShopnames, String[] sPrices, String[] sSizes, String[] sInfors, String[] sImages) {
        super(context, R.layout.shoes_list_item, sId);
        this.context = context;
        this.sId = sId;
        this.sShoenames = sShoenames;
        this.sShopnames = sShopnames;
        this.sPrices = sPrices;
        this.sSizes = sSizes;
        this.sInfors = sInfors;
        this.sImages = sImages;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.shoes_list_item, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.tv_shoename);
        TextView textViewShop = (TextView) listViewItem.findViewById(R.id.tv_shopname);
        TextView textViewPrice = (TextView) listViewItem.findViewById(R.id.tv_price);
        TextView textViewSize = (TextView) listViewItem.findViewById(R.id.tv_size);
        ImageView iv = (ImageView)listViewItem.findViewById(R.id.img_icon);


        textViewName.setText(sShoenames[position]);
        textViewShop.setText(sShopnames[position]);
        textViewPrice.setText(sPrices[position]);
        textViewSize.setText(sSizes[position]);
       // Uri uri = Uri.parse(sImages[position]);
        //Uri uri = Uri.parse("https://drive.google.com/uc?id=0B___GhMLUVtOY09SbDU5cDU2T1U");
       // draweeView.setImageURI(uri);

        Picasso.with(context).load(sImages[position]).into(iv);

        return listViewItem;
    }
}