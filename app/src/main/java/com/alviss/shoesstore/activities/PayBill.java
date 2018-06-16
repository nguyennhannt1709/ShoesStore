package com.alviss.shoesstore.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alviss.shoesstore.R;
import com.alviss.shoesstore.utils.MySession;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import static com.alviss.shoesstore.utils.Configuration2.KEY_BNAME;
import static com.alviss.shoesstore.utils.Configuration2.KEY_BPHONE;
import static com.alviss.shoesstore.utils.Configuration2.KEY_BADD;
import static com.alviss.shoesstore.utils.Configuration2.KEY_BMAIL;
import static com.alviss.shoesstore.utils.Configuration2.KEY_BCONTENT;
import static com.alviss.shoesstore.utils.Configuration2.KEY_BSUM;

/**
 * Created by Alviss on 5/29/2018.
 */

public class PayBill extends AppCompatActivity {
    private EditText bname;
    private EditText bphone;
    private EditText badd;
    private EditText bmail;
    String bName;
    String bPhone;
    String bAdd;
    String bMail;
    String bContent="";
    String bSum;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payinfor);

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
                    bContent= bContent+MySession.lid.get(i).toString()+" "+MySession.lname.get(i).toString()+" "+MySession.lsize.get(i).toString()+" "+MySession.lprice.get(i).toString()+"\n";
                }
                bSum = String.valueOf(MySession.sum);
                new SendRequest().execute();

                MySession.count=0;
                MySession.sum=0;
                MySession.lid.clear();
                MySession.lname.clear();
                MySession.lsize.clear();
                MySession.lprice.clear();
                MySession.lpic.clear();

                Toast.makeText(PayBill.this, "Đơn hàng của bạn đã được lưu\nChúng tôi sẽ liên lạc sớm nhất", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(PayBill.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    public class SendRequest extends AsyncTask<String, Void, String> {


        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try{
                //Change your web app deployed URL or u can use this for attributes (name, country)
                URL url = new URL("https://script.google.com/macros/s/AKfycby_Feb9KWTHSIqCjWVDjuvsUnLOHcxTAdUltHkIo5Mk81LlPDO9/exec");

                JSONObject postDataParams = new JSONObject();

                //int i;
                //for(i=1;i<=70;i++)


                //    String usn = Integer.toString(i);

                String id= "137x-j-SrRJ1QvfFgpDxvB4Z3ayevcfRwz3QbCt_jm3k";

                postDataParams.put(KEY_BNAME,bName);
                postDataParams.put(KEY_BPHONE,bPhone);
                postDataParams.put(KEY_BADD,bAdd);
                postDataParams.put(KEY_BMAIL,bMail);
                postDataParams.put(KEY_BCONTENT,bContent);
                postDataParams.put(KEY_BSUM,bSum);
                postDataParams.put("id",id);


                Log.e("params",postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                }
                else {
                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
           // Toast.makeText(getApplicationContext(), result,Toast.LENGTH_LONG).show();

        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }
}
