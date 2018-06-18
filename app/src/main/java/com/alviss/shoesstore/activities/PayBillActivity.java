package com.alviss.shoesstore.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alviss.shoesstore.R;
import com.alviss.shoesstore.models.ChiTietHoaDon;
import com.alviss.shoesstore.models.HoaDon;
import com.alviss.shoesstore.models.KhachHang;
import com.alviss.shoesstore.models.UserItem;
import com.alviss.shoesstore.utils.MySession;
import com.alviss.shoesstore.utils.Util;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.alviss.shoesstore.activities.CartActivity.summ;
import static com.alviss.shoesstore.activities.ShoesDetailActivity.arrChiTietHoaDon;



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

                bName=bname.getText().toString();
                bPhone = bphone.getText().toString();
                bAdd = badd.getText().toString();
                bMail = bmail.getText().toString();

                for (int i = 0; i< MySession.count; i++){
                    bContent= bContent+MySession.lid.get(i).toString()+" "+MySession.lname.get(i).toString()+" "+MySession.lsize.get(i).toString()+" "+
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


                firebaseDatabase.writeKhachHang(new KhachHang(bName,bPhone,bAdd,bMail));
               // firebaseDatabase.writeHoaDon(new HoaDon("","","","","","","","","","","",""));


                KhachHang khachHang = new KhachHang(bName,bPhone,bAdd,bMail);
                new RequestSendMail().execute(khachHang);
                new RequestSendMail2().execute();

                Toast.makeText(PayBillActivity.this, "Đơn hàng của bạn đã được lưu\nChúng tôi sẽ liên lạc sớm nhất", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(PayBillActivity.this, MainActivity.class);
                startActivity(intent);
                Sum= summ.getText().toString();
                firebaseDatabase.writeKhachHang(new KhachHang(bName,bPhone,bAdd,bMail));
                firebaseDatabase.writeHoaDon(new HoaDon( NgayLapHoaDon ,Sum, bPhone));
                for(ChiTietHoaDon cthd:arrChiTietHoaDon){

                    firebaseDatabase.writeChiTietHoaDon(new ChiTietHoaDon("", cthd.getMaSP(), cthd.getSoLuong(), cthd.getGiaBan(), bPhone));
                }
            }
        });

    }

    class RequestSendMail extends AsyncTask<KhachHang, Void, String> {

        @Override
        protected String doInBackground(KhachHang... khachHangs) {
            WillBeRequest(khachHangs[0]);
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
                    if (Boolean.parseBoolean(status)) {
                        Toast.makeText(PayBillActivity.this, "Please check your email:\n "+khachHang.getEmail(), Toast.LENGTH_SHORT).show();
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
            WillBeRequest2(new KhachHang("", "","", "ahihi12345678912345678@gmail.com"));
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
}
