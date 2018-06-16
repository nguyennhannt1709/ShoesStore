package com.alviss.shoesstore.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alviss.shoesstore.R;
import com.alviss.shoesstore.adapter.HangHoaAdapter;
import com.alviss.shoesstore.models.HangHoa;
import com.alviss.shoesstore.utils.MySession;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

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


        //MARK:_Pull to refresh
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //MARK_4fun
                progressDialog.show();
                swipeRefreshLayout.setRefreshing(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                },1000);
            }
        });
    }

    //MARK:_initialize view
    public void initialize() {
        alertDialog = new AlertDialog.Builder(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (ListView) findViewById(R.id.list_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeToRefresh);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Syncing data...");

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
        //MARK:_Update Your cart(0) when choose a item
        if (menu != null) {
            menu.findItem(R.id.action_cart).setTitle("Your cart(" + MySession.count + ")");
        }
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

}

