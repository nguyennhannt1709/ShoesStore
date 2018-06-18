package com.alviss.shoesstore.activities;



import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alviss.shoesstore.R;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.alviss.shoesstore.utils.Configuration.ADD_SHOES_URL;
import static com.alviss.shoesstore.utils.Configuration.KEY_ACTION;
import static com.alviss.shoesstore.utils.Configuration.KEY_ID;
import static com.alviss.shoesstore.utils.Configuration.KEY_IMAGE;
import static com.alviss.shoesstore.utils.Configuration.KEY_INFOR;
import static com.alviss.shoesstore.utils.Configuration.KEY_PRICE;
import static com.alviss.shoesstore.utils.Configuration.KEY_SHOENAME;
import static com.alviss.shoesstore.utils.Configuration.KEY_SHOPNAME;
import static com.alviss.shoesstore.utils.Configuration.KEY_SIZE;

public class AddShoesActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextShoeName;
    private EditText editTextShopName;
    private EditText editTextPrice;
    private EditText editTextId;
    private EditText editTextSize;
    private EditText editTextInfor;
    private ImageView imageViewShoeImage;
    private Button buttonAddShoes,buttonAddImage;
    String shoesImage;

    private int PICK_IMAGE_REQUEST = 1;

    Bitmap rbitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_shoes);

        editTextId = (EditText) findViewById(R.id.in_id);
        editTextShoeName = (EditText) findViewById(R.id.in_shoesname);
        editTextShopName = (EditText) findViewById(R.id.in_shopname);
        editTextPrice = (EditText) findViewById(R.id.in_price);
        editTextSize = (EditText) findViewById(R.id.in_size);
        editTextInfor = (EditText) findViewById(R.id.in_infor);
        imageViewShoeImage=(ImageView)findViewById(R.id.in_photo);



        buttonAddShoes = (Button) findViewById(R.id.btn_addtocart);
        buttonAddImage = (Button) findViewById(R.id.btn_image);

        buttonAddImage.setOnClickListener(this);
        buttonAddShoes.setOnClickListener(this);
    }


    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);

    }


    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        return encodedImage;
    }

    private void addShoes(){
        final ProgressDialog loading = ProgressDialog.show(this,"Uploading...","Please wait...",false,false);
        final String shoesId = editTextId.getText().toString().trim();
        final String shoesName = editTextShoeName.getText().toString().trim();
        final String shoesShop = editTextShopName.getText().toString().trim();
        final String shoesPrice = editTextPrice.getText().toString().trim();
        final String shoesSize = editTextSize.getText().toString().trim();
        final String shoesInfor = editTextInfor.getText().toString().trim();
       //Bitmap  rbitmap = getResizedBitmap(bitmap,500);

       // Toast.makeText(AddShoes.this,shoesId+shoesName+shoesShop+shoesSize+shoesInfor,Toast.LENGTH_LONG).show();
        Log.e("null","values"+shoesImage);


        StringRequest stringRequest = new StringRequest(Request.Method.POST,ADD_SHOES_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Toast.makeText(AddShoesActivity.this,response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddShoesActivity.this,error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_ACTION,"insert");
                params.put(KEY_ID,shoesId);
                params.put(KEY_SHOENAME,shoesName);
                params.put(KEY_SHOPNAME,shoesShop);
                params.put(KEY_PRICE,shoesPrice);
                params.put(KEY_SIZE,shoesSize);
                params.put(KEY_INFOR,shoesInfor);
                params.put(KEY_IMAGE,shoesImage);

                return params;
            }

        };

        int socketTimeout = 30000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
        editTextId.setText("");
        editTextShoeName.setText("");
        editTextShopName.setText("");
        editTextPrice.setText("");
        editTextSize.setText("");
        editTextInfor.setText("");
    }




    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
               Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                rbitmap = getResizedBitmap(bitmap,250);//Setting the Bitmap to ImageView
                shoesImage = getStringImage(rbitmap);
                imageViewShoeImage.setImageBitmap(rbitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    @Override
    public void onClick(View v) {
        if(v == buttonAddShoes){
            addShoes();
        }
        if(v == buttonAddImage){
            showFileChooser();
        }

    }
}