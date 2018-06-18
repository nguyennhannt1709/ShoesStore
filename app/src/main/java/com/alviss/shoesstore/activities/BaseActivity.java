package com.alviss.shoesstore.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alviss.shoesstore.utils.FirebaseDatabases;
import com.alviss.shoesstore.utils.Util;
import com.google.firebase.messaging.FirebaseMessaging;


public class BaseActivity extends AppCompatActivity {
    public static final FirebaseDatabases firebaseDatabase = new FirebaseDatabases();
    public static final FirebaseMessaging firebaseMessaging = FirebaseMessaging.getInstance();
    public static final Util util = new Util();
}
