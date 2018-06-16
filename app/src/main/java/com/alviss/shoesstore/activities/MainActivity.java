package com.alviss.shoesstore.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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
import android.widget.Toast;

import com.alviss.shoesstore.R;
import com.alviss.shoesstore.models.HangHoa;
import com.alviss.shoesstore.utils.MySession;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MainActivity extends BaseActivity {
    ListView listView;
    AlertDialog.Builder alertDialog;
    Toolbar toolbar;
    QueryTask queryTask;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressDialog progressDialog;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        queryTask.execute();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //MARK:_doing
                if (queryTask.isCancelled()) {
                    progressDialog.show();
                    queryTask.execute();
                } else {
                    swipeRefreshLayout.setRefreshing(false);
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Doing...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void initialize() {
        alertDialog = new AlertDialog.Builder(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (ListView) findViewById(R.id.list_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeToRefresh);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");

        queryTask = new QueryTask();
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (menu != null) {
            menu.findItem(R.id.action_cart).setTitle("Your cart(" + MySession.count + ")");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_cart) {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_infor) {
            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(intent);
        }




        return super.onOptionsItemSelected(item);
    }


    private void bindToView(final ArrayList<HangHoa> arr) {
        HangHoaAdapter hangHoaAdapter = new HangHoaAdapter(arr, MainActivity.this);
        listView.setAdapter(hangHoaAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ShoesDetailActivity.class);
                intent.putExtra("ID", arr.get(position).getCODEM());
                intent.putExtra("NAME", arr.get(position).getMNAME());
                intent.putExtra("SHOP", arr.get(position).getMSHOPNAME());
                intent.putExtra("PRICE", arr.get(position).getMPRICE());
                intent.putExtra("SIZE", arr.get(position).getMSIZE());
                intent.putExtra("INFOR", arr.get(position).getMINFO());
                intent.putExtra("IMAGE", arr.get(position).getMIMAGE());
                startActivity(intent);
            }
        });

        progressDialog.dismiss();
    }

    //MARK:_Bind data to view
    public class QueryTask extends AsyncTask<Void, ArrayList<HangHoa>, ArrayList<HangHoa>> {

        @Override
        protected ArrayList<HangHoa> doInBackground(Void... voids) {
            final ArrayList<HangHoa> arr = new ArrayList<>();

            firebaseDatabase.HangHoa.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        arr.add(postSnapshot.getValue(HangHoa.class));
                    }
                    bindToView(arr);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    alertDialog.setTitle("Load HangHoa error").setMessage(databaseError.getMessage())
                            .show();
                }
            });
            return null;
        }
    }


    //MARK:_Adapter
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
            ImageView iv = (ImageView) listViewItem.findViewById(R.id.img_icon);

            textViewName.setText(arr.get(position).getMNAME());
            textViewPrice.setText(arr.get(position).getMPRICE());
            textViewShop.setText(arr.get(position).getMSHOPNAME());
            textViewSize.setText(arr.get(position).getMSIZE());
            Picasso.with(context).load(arr.get(position).getMIMAGE()).into(iv);

            return listViewItem;
        }
    }

}

