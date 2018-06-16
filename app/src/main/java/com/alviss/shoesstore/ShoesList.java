package com.alviss.shoesstore;

/**
 * Created by ADJ on 8/8/2017.
 */


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import static com.alviss.shoesstore.Configuration.LIST_SHOES_URL;

public class ShoesList extends AppCompatActivity {





    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoes_list);



        listView = (ListView) findViewById(R.id.list_view);
        sendRequest();

    }

    private void sendRequest(){
        final ProgressDialog loading = ProgressDialog.show(this,"Uploading...","Please wait...",false,false);

        StringRequest stringRequest = new StringRequest(LIST_SHOES_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       // Log.e("null","ser image"+response);
                        showJSON(response);

                        loading.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ShoesList.this,error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        int socketTimeout = 30000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        JsonParser pj = new JsonParser(json);
        pj.parseJSON();
        //Log.e("uImage","ser image"+JsonParser.uImages[1]);
        final ShoesListAdapter shoesListAdapter = new ShoesListAdapter(this, JsonParser.sIds,JsonParser.sShoenames,JsonParser.sShopnames, JsonParser.sPrices, JsonParser.sSizes, JsonParser.sInfors, JsonParser.sImages);
        listView.setAdapter(shoesListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShoesList.this,ShoesDetail.class);
                intent.putExtra("ID",shoesListAdapter.getsId(position));
                intent.putExtra("NAME",shoesListAdapter.getsName(position));
                intent.putExtra("SHOP",shoesListAdapter.getsShop(position));
                intent.putExtra("PRICE",shoesListAdapter.getsPrice(position));
                intent.putExtra("SIZE",shoesListAdapter.getsSize(position));
                intent.putExtra("INFOR",shoesListAdapter.getsInfor(position));
                intent.putExtra("IMAGE",shoesListAdapter.getsImage(position));
                startActivity(intent);
            }
        });
    }



    }
