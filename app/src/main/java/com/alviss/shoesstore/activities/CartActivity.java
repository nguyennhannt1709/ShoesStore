package com.alviss.shoesstore.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alviss.shoesstore.utils.MySession;
import com.alviss.shoesstore.R;
import com.alviss.shoesstore.utils.Recheck;
import com.alviss.shoesstore.adapter.CartListAdapter;

/**
 * Created by Alviss on 5/29/2018.
 */

public class CartActivity extends BaseActivity implements Recheck {

    TextView summ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);
        getSupportActionBar().hide();
        summ = (TextView) findViewById(R.id.tv_sum);
        summ.setText(util.formatToCurrency(String.valueOf(MySession.sum))+ " đ");

        ListView listView = (ListView) findViewById(R.id.cart_list);
        CartListAdapter adapter = new CartListAdapter(CartActivity.this,MySession.lid,MySession.lname,MySession.lprice,MySession.lsize,MySession.lpic,CartActivity.this);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Button btnpay = (Button) findViewById(R.id.btn_buy);
        btnpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MySession.count < 1) {
                    //MARK:_ Alert
                    new AlertDialog.Builder(CartActivity.this)
                            .setTitle("Ops!")
                            .setMessage("Please choose something to checkout")
                            .setPositiveButton("Okay", null)
                            .show();

                    return;
                }
                else {
                    Intent intent = new Intent(CartActivity.this, PayBillActivity.class);
                    startActivity(intent);
                    finish();
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
        MySession.sum -= Integer.valueOf(MySession.lprice.get(t));
        summ.setText(String.valueOf(MySession.sum));
        MySession.lprice.remove(t);

    }
}
