package com.alviss.shoesstore.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alviss.shoesstore.R;



public class InfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infor);
        setTitle("About Shoes Store");
    }
}
