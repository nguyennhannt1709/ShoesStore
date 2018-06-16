package com.alviss.shoesstore.utils;

import android.util.Log;

import com.alviss.shoesstore.models.BaseModel;
import com.alviss.shoesstore.models.HangHoa;
import com.alviss.shoesstore.models.HoaDon;
import com.alviss.shoesstore.models.KhachHang;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.alviss.shoesstore.activities.PayBill.bName;

public class FirebaseDatabases {
    public FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    public DatabaseReference mStore = firebaseDatabase.getReference("Store");
    public DatabaseReference KhachHang = firebaseDatabase.getReference("KhachHang");
    public DatabaseReference HangHoa = firebaseDatabase.getReference("HangHoa");
    public DatabaseReference HoaDon = firebaseDatabase.getReference("HoaDon");


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


    //MARK:To Nam_ please mapping this function
    public void writeHoaDon(final HoaDon item) {
        HoaDon.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HoaDon.child((dataSnapshot.getChildrenCount() + 1)+"").setValue(new KhachHang((dataSnapshot.getChildrenCount() + 1)+"",
                        "","","",""));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void writeKhachHang(final KhachHang item) {
        KhachHang.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                KhachHang.child((dataSnapshot.getChildrenCount() + 1)+"").setValue(new KhachHang((dataSnapshot.getChildrenCount() + 1)+"",
                        item.getTenKhachHang(),
                        item.getSoDienThoai(),
                        item.getDiaChi(),
                        item.getEmail()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //KhachHang.child("Kh01").setValue(item);
    }
    public void writeHangHoa(final com.alviss.shoesstore.models.HangHoa item) {
        HangHoa.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HangHoa.child((dataSnapshot.getChildrenCount() + 1)+"").setValue(new HangHoa((dataSnapshot.getChildrenCount() + 1)+"",
                        item.getMNAME(),
                        item.getMSHOPNAME(),
                        item.getMPRICE(),
                        item.getMSIZE(),
                        item.getMINFO(),
                        item.getMIMAGE()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //KhachHang.child("Kh01").setValue(item);
    }

    public void writeArrayHangHoa(ArrayList<HangHoa> arr) {
        HangHoa.setValue(arr);
    }


    public void getCustomer(String _id) {
        HangHoa.child(_id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                KhachHang model = dataSnapshot.getValue(com.alviss.shoesstore.models.KhachHang.class);
                Log.e("Model", model.getTenKhachHang());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
