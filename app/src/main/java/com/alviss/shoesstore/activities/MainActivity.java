package com.alviss.shoesstore.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

import com.alviss.shoesstore.R;
import com.alviss.shoesstore.models.HangHoa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MainActivity extends BaseActivity {

    Toolbar toolbar;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        listView = (ListView) findViewById(R.id.list_view);
        new QueryTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_cart) {
            Intent intent = new Intent(MainActivity.this,CartActivity.class);
            startActivity(intent);
        }else if (id == R.id.action_infor){
            Intent intent = new Intent(MainActivity.this,InfoActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void showJSON(final ArrayList<HangHoa> arr){
        HangHoaAdapter hangHoaAdapter = new HangHoaAdapter(arr, MainActivity.this);
        listView.setAdapter(hangHoaAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {Intent intent = new Intent(MainActivity.this,ShoesDetailActivity.class);
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

