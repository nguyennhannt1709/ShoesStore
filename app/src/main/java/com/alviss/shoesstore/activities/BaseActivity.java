package com.alviss.shoesstore.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alviss.shoesstore.utils.FirebaseDatabases;


public class BaseActivity extends AppCompatActivity {
    public static final FirebaseDatabases firebaseDatabase = new FirebaseDatabases();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
}
