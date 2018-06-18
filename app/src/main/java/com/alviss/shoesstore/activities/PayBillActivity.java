package com.alviss.shoesstore.activities;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alviss.shoesstore.R;
import com.alviss.shoesstore.models.HangHoa;
import com.alviss.shoesstore.models.HoaDon;
import com.alviss.shoesstore.models.KhachHang;
import com.alviss.shoesstore.utils.MySession;
import com.alviss.shoesstore.utils.Util;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.alviss.shoesstore.activities.CartActivity.summ;




public class PayBillActivity extends BaseActivity {
    private EditText bname;
    private EditText bphone;
    private EditText badd;
    private EditText bmail;
    public static String bName;
    public static String bPhone;
    public static String bAdd;
    public static String bMail;

    String bContent="";
    String bSum;
    String Sum;
    private AlertDialog.Builder alert;
    private List<HangHoa> hangHoas;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payinfor);
        alert = new AlertDialog.Builder(PayBillActivity.this);
        alert.setTitle("Alert!");
        setTitle("Checkout your cart");

        bname = (EditText) findViewById(R.id.tv_bname);
        badd = (EditText) findViewById(R.id.tv_badd);
        bphone = (EditText) findViewById(R.id.tv_bphone);
        bmail = (EditText) findViewById(R.id.tv_bmail);

        Button pay = (Button) findViewById(R.id.btn_accept);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hangHoas = new ArrayList<>();
                bName=bname.getText().toString();
                bPhone = bphone.getText().toString();
                bAdd = badd.getText().toString();
                bMail = bmail.getText().toString();

                for (int i = 0; i< MySession.count; i++){
                    hangHoas.add(new HangHoa(MySession.lid.get(i),MySession.lname.get(i),"Shoes Store",MySession.lprice.get(i),MySession.lsize.get(i),"",MySession.lpic.get(i)));

                    bContent= bContent + MySession.lid.get(i).toString() + " " + MySession.lname.get(i).toString() + " " + MySession.lsize.get(i).toString() + " " +
                            MySession.lprice.get(i).toString()
                            +"\n";
                }
                bSum = String.valueOf(MySession.sum);

                MySession.count=0;
                MySession.sum=0;
                MySession.lid.clear();
                MySession.lname.clear();
                MySession.lsize.clear();
                MySession.lprice.clear();
                MySession.lpic.clear();
                String NgayLapHoaDon;
                SimpleDateFormat format= new SimpleDateFormat("HH:mm dd-MM-yyyy", Locale.getDefault());
                Date currentTime = Calendar.getInstance().getTime();
                NgayLapHoaDon=format.format(currentTime);

                KhachHang khachHang = new KhachHang(bName,bPhone,bAdd,bMail);

                firebaseDatabase.writeKhachHang(khachHang);
                new FirebaseLogBill().execute(new HoaDon(new Date().getTime() + "",MySession.count+1 + "",bSum,"0","0", hangHoas, khachHang));
                sendNotification("Shoes Store","Đơn hàng đã gửi thành công");

                new RequestSendMail().execute(khachHang);

                Intent intent = new Intent(PayBillActivity.this, MainActivity.class);
                startActivity(intent);
                Sum= summ.getText().toString();
                firebaseDatabase.writeKhachHang(khachHang);
            }
        });

    }

    class RequestSendMail extends AsyncTask<KhachHang, Void, String> {

        @Override
        protected String doInBackground(KhachHang... khachHangs) {
            WillBeRequest(khachHangs[0]);
            new RequestSendMail2().execute(new KhachHang(khachHangs[0].getTenKhachHang(),khachHangs[0].getSoDienThoai(),khachHangs[0].getDiaChi(),"ahihi12345678912345678@gmail.com"));
            return null;
        }
    }

    //MARK:_Send mail task
    public void WillBeRequest2(final KhachHang khachHang) {
        RequestQueue queue = Volley.newRequestQueue(PayBillActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Util.sendMailWithModel(Util.ConvertKhachHang2MailModelTwo(khachHang)), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    String status = new JSONObject(response).getString("success");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("onResponse", "onResponse: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("onErrorResponse", "onErrorResponse: " + error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("para1", "value1");
                params.put("para1", "value2");
                return params;
            }
        };
        queue.add(stringRequest);
    }
    class RequestSendMail2 extends AsyncTask<KhachHang, Void, String> {

        @Override
        protected String doInBackground(KhachHang... khachHangs) {
            WillBeRequest2(khachHangs[0]);
            return null;
        }
    }


    //MARK:_Send mail task
    public void WillBeRequest(final KhachHang khachHang) {
        RequestQueue queue = Volley.newRequestQueue(PayBillActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Util.sendMailWithModel(Util.ConvertKhachHang2MailModel(khachHang)), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    String status = new JSONObject(response).getString("success");
                    if (Boolean.parseBoolean(status)) {
                        // Toast.makeText(PayBillActivity.this, "Please check your email:\n "+khachHang.getEmail(), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("onResponse", "onResponse: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("onErrorResponse", "onErrorResponse: " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("para1", "value1");
                params.put("para1", "value2");
                return params;
            }
        };
        queue.add(stringRequest);
    }

    //MARK:_Log bill
    public class FirebaseLogBill extends AsyncTask<HoaDon, Void, Void> {

        @Override
        protected Void doInBackground(final HoaDon... hoaDons) {
            firebaseDatabase.HoaDon.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int count = (int) dataSnapshot.getChildrenCount();
                    firebaseDatabase.HoaDon.child(count + 1 +"")
                            .setValue(hoaDons[0]);
                    Toast.makeText(PayBillActivity.this, "Đơn hàng của bạn đã được lưu\nChúng tôi sẽ liên lạc sớm nhất", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(PayBillActivity.this, "Đơn hàng của bạn đã được lưu\nChúng tôi sẽ liên lạc sớm nhất", Toast.LENGTH_LONG).show();
        }
    }

//    public class PushNotification extends AsyncTask<NotificationItem, Void, Void> {
//
//
//        @Override
//        protected Void doInBackground(NotificationItem... notificationItems) {
//            firebaseMessaging.send(new RemoteMessage.Builder("345793871141" + "@gcm.googleapis.com")
//                    .setMessageId(Integer.toString((int) new Date().getTime()))
//                    .addData("_title", notificationItems[0]._title)
//                    .addData("_content", notificationItems[0]._content)
//                    .addData("_subject", notificationItems[0]._subject)
//                    .build());
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//        }
//    }

    private void sendNotification(String title, String content) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.icon_sneaker_round)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
