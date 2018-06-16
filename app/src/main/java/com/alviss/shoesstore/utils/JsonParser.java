package com.alviss.shoesstore.utils;

/**
 * Created by ADJ on 8/9/2017.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.alviss.shoesstore.utils.Configuration.KEY_ID;
import static com.alviss.shoesstore.utils.Configuration.KEY_IMAGE;
import static com.alviss.shoesstore.utils.Configuration.KEY_INFOR;
import static com.alviss.shoesstore.utils.Configuration.KEY_PRICE;
import static com.alviss.shoesstore.utils.Configuration.KEY_SHOENAME;
import static com.alviss.shoesstore.utils.Configuration.KEY_SHOES;
import static com.alviss.shoesstore.utils.Configuration.KEY_SHOPNAME;
import static com.alviss.shoesstore.utils.Configuration.KEY_SIZE;


public class JsonParser {
    public static String[] sIds;
    public static String[] sShoenames;
    public static String[] sShopnames;
    public static String[] sPrices;
    public static String[] sSizes;
    public static String[] sInfors;
    public static String[] sImages;


    private JSONArray users = null;

    private String json;

    public JsonParser(String json){
        this.json = json;
    }

    public void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(KEY_SHOES);

            sIds = new String[users.length()];
            sShoenames = new String[users.length()];
            sShopnames = new String[users.length()];
            sPrices = new String[users.length()];
            sSizes = new String[users.length()];
            sInfors = new String[users.length()];
            sImages = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                sIds[i] = jo.getString(KEY_ID);
                sShoenames[i] = jo.getString(KEY_SHOENAME);
                sShopnames[i] = jo.getString(KEY_SHOPNAME);
                sPrices[i] = jo.getString(KEY_PRICE);
                sSizes[i] = jo.getString(KEY_SIZE);
                sInfors[i] = jo.getString(KEY_INFOR);
                sImages[i] = jo.getString(KEY_IMAGE);
            }

           // Log.e("uImage","ser image"+uImages[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
