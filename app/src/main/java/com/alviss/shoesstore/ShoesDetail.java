package com.alviss.shoesstore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alviss on 5/27/2018.
 */

public class ShoesDetail extends AppCompatActivity {
    Spinner size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoes_detail);
        TextView name = (TextView) findViewById(R.id.out_shoesname);
        TextView shop = (TextView) findViewById(R.id.out_shopname);
        TextView price = (TextView) findViewById(R.id.out_price);
        TextView infor = (TextView) findViewById(R.id.out_infor);
        ImageView iv = (ImageView) findViewById(R.id.out_photo);
        size = (Spinner) findViewById(R.id.out_size);
        String sizetemp = new String();
        sizetemp = getIntent().getStringExtra("SIZE");
        List<String> sizes = new ArrayList<String>();
        sizes.add(" ");
        List<String> temp = Arrays.asList(sizetemp.split("\\s+"));
        sizes.addAll(temp);
        ArrayAdapter<String> sizeadapter = new ArrayAdapter<String>(this,R.layout.size_item,sizes);
        sizeadapter.setDropDownViewResource(R.layout.size_item);
        size.setAdapter(sizeadapter);


        name.setText(getIntent().getStringExtra("NAME"));
        shop.setText(getIntent().getStringExtra("SHOP"));
        price.setText(getIntent().getStringExtra("PRICE"));
        infor.setText(getIntent().getStringExtra("INFOR"));
        Picasso.with(ShoesDetail.this).load(getIntent().getStringExtra("IMAGE")).into(iv);

        Button addcart =(Button) findViewById(R.id.btn_addtocart);
        addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (size.getSelectedItem().toString().equals(" ")){
                    Toast.makeText(ShoesDetail.this,"Chưa chọn size cần mua",Toast.LENGTH_SHORT).show();
                }
                else{
                    MySession.count++;
                    MySession.lid.add(getIntent().getStringExtra("ID"));
                    MySession.lname.add(getIntent().getStringExtra("NAME"));
                    MySession.lsize.add(size.getSelectedItem().toString());
                    MySession.lprice.add(getIntent().getStringExtra("PRICE"));
                    MySession.lpic.add(getIntent().getStringExtra("IMAGE"));
                    MySession.sum+=Integer.valueOf(getIntent().getStringExtra("PRICE").toString());
                    Toast.makeText(ShoesDetail.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
