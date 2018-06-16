package com.alviss.shoesstore.utils;

import android.util.Log;

import com.alviss.shoesstore.models.BaseModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseDatabases {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mStore = firebaseDatabase.getReference("Store");

    public void writeTest() {
        mStore.child("test").setValue(new BaseModel("Bui Thao Nam"));
    }

   public void readTest() {
//        mStore.child("test").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                BaseModel model = dataSnapshot.getValue(BaseModel.class);
//                Log.e("readTest", model.get_id());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });

        mStore.child("test").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                BaseModel model = dataSnapshot.getValue(BaseModel.class);
                Log.e("readTest", model.get_id());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
