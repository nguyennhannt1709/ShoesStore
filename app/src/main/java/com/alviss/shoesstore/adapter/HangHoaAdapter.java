package com.alviss.shoesstore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alviss.shoesstore.R;
import com.alviss.shoesstore.models.HangHoa;
import com.alviss.shoesstore.utils.Util;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by nguyennhan on 6/17/18.
 */

public class HangHoaAdapter extends BaseAdapter {
    private ArrayList<HangHoa> arr = new ArrayList<>();
    private Context context;
    private Util util = new Util();

    public HangHoaAdapter(ArrayList<HangHoa> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return arr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View listViewItem = inflater.inflate(R.layout.shoes_list_item, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.tv_shoename);
        TextView textViewShop = (TextView) listViewItem.findViewById(R.id.tv_shopname);
        TextView textViewPrice = (TextView) listViewItem.findViewById(R.id.tv_price);
        TextView textViewSize = (TextView) listViewItem.findViewById(R.id.tv_size);
        final ImageView iv = (ImageView) listViewItem.findViewById(R.id.img_icon);

        final HangHoa hangHoa = arr.get(position);

        textViewName.setText(hangHoa.getMNAME());
        textViewPrice.setText(util.formatToCurrency(hangHoa.getMPRICE()));
        textViewShop.setText(hangHoa.getMSHOPNAME());
        textViewSize.setText(hangHoa.getMSIZE());


        //MARK:_Cache image
        if (hangHoa.cachedImage == null) {
            Picasso.with(context).load(hangHoa.getMIMAGE()).into(iv,
                    new Callback() {
                        @Override
                        public void onSuccess() {
                            hangHoa.imagesToBase64(iv);
                        }

                        @Override
                        public void onError() {

                        }
                    });
        } else {
            iv.setImageBitmap(hangHoa.cachedImage);
        }

        return listViewItem;
    }
}