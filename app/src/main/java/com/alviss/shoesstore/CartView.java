package com.alviss.shoesstore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.id;

/**
 * Created by Alviss on 5/29/2018.
 */

public class CartView extends Activity implements Recheck {

    TextView summ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        summ = (TextView) findViewById(R.id.tv_sum);
        summ.setText(String.valueOf(MySession.sum));

        ListView listView = (ListView) findViewById(R.id.cart_list);
        CartListAdapter adapter = new CartListAdapter(CartView.this,MySession.lid,MySession.lname,MySession.lprice,MySession.lsize,MySession.lpic,CartView.this);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Button btnpay = (Button) findViewById(R.id.btn_buy);
        btnpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MySession.count==0) {
                    Toast.makeText(CartView.this,"Giỏ hàng của bạn chưa có sản phẩm",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(CartView.this, PayBill.class);
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    public void delincart(String ids){
        int t = MySession.lid.indexOf(ids);


        MySession.lid.remove(t);
        MySession.lsize.remove(t);
        MySession.lpic.remove(t);
        MySession.lname.remove(t);
        MySession.count--;
        MySession.sum-= Integer.valueOf(MySession.lprice.get(t));
        summ.setText(String.valueOf(MySession.sum));
        MySession.lprice.remove(t);

    }
}
