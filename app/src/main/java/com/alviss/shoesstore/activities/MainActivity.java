package com.alviss.shoesstore.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alviss.shoesstore.utils.JsonParser;
import com.alviss.shoesstore.utils.MySession;
import com.alviss.shoesstore.R;
import com.alviss.shoesstore.adapter.ShoesListAdapter;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import static com.alviss.shoesstore.utils.Configuration.LIST_SHOES_URL;


public class MainActivity extends AppCompatActivity {


    Toolbar toolbar;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        // Attaching the layout to the toolbar object
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);
        if (MySession.count==-1){
        MySession.count=0;
        MySession.sum=0;
        }
        listView = (ListView) findViewById(R.id.list_view);
        sendRequest();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cart) {
            Intent intent = new Intent(MainActivity.this,CartView.class);
            startActivity(intent);
        }else if (id == R.id.action_infor){
            Intent intent = new Intent(MainActivity.this,Infor.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
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
                        Toast.makeText(MainActivity.this,error.getMessage(), Toast.LENGTH_LONG).show();
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
        final ShoesListAdapter shoesListAdapter = new ShoesListAdapter(MainActivity.this, JsonParser.sIds,JsonParser.sShoenames,JsonParser.sShopnames, JsonParser.sPrices, JsonParser.sSizes, JsonParser.sInfors, JsonParser.sImages);
        listView.setAdapter(shoesListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,ShoesDetail.class);
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

