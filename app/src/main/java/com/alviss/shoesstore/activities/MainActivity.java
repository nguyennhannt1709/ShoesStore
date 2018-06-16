package com.alviss.shoesstore.activities;

import android.app.ProgressDialog;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alviss.shoesstore.R;
import com.alviss.shoesstore.adapter.ShoesListAdapter;
import com.alviss.shoesstore.models.HangHoa;
import com.alviss.shoesstore.models.KhachHang;
import com.alviss.shoesstore.utils.JsonParser;
import com.alviss.shoesstore.utils.MySession;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.alviss.shoesstore.utils.Configuration.LIST_SHOES_URL;


public class MainActivity extends BaseActivity {

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
        //sendRequest();
        new QueryTask().execute();
     //   firebaseDatabase.readTest();
       // firebaseDatabase.getCustomer("123");
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
                       // showJSON();

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

    private void showJSON(final ArrayList<HangHoa> arr){
        HangHoaAdapter hangHoaAdapter = new HangHoaAdapter(arr, MainActivity.this);
        listView.setAdapter(hangHoaAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {Intent intent = new Intent(MainActivity.this,ShoesDetail.class);
            intent.putExtra("ID",arr.get(position).getCODEM());
            intent.putExtra("NAME",arr.get(position).getMNAME());
            intent.putExtra("SHOP",arr.get(position).getMSHOPNAME());
            intent.putExtra("PRICE",arr.get(position).getMPRICE());
            intent.putExtra("SIZE",arr.get(position).getMSIZE());
            intent.putExtra("INFOR",arr.get(position).getMINFO());
            intent.putExtra("IMAGE",arr.get(position).getMIMAGE());
            startActivity(intent);
            }
        });
    }

    public class QueryTask extends AsyncTask<Void, ArrayList<HangHoa>, ArrayList<HangHoa>> {


        @Override
        protected ArrayList<HangHoa> doInBackground(Void... voids) {
            final ArrayList<HangHoa> arr = new ArrayList<>();

            firebaseDatabase.HangHoa.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                        arr.add(postSnapshot.getValue(HangHoa.class));
                    }
                    showJSON(arr);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<HangHoa> hangHoas) {
            super.onPostExecute(hangHoas);
        }
    }

    public class HangHoaAdapter extends BaseAdapter {
        private ArrayList<HangHoa> arr = new ArrayList<>();
        private Context context;

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
            ImageView iv = (ImageView)listViewItem.findViewById(R.id.img_icon);

            textViewName.setText(arr.get(position).getMNAME());
            textViewPrice.setText(arr.get(position).getMPRICE());
            textViewShop.setText(arr.get(position).getMSHOPNAME());
            textViewSize.setText(arr.get(position).getMSIZE());
            Picasso.with(context).load(arr.get(position).getMIMAGE()).into(iv);

            return listViewItem;
        }
    }

}

