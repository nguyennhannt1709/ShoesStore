package com.alviss.shoesstore.utils;

import android.util.Log;
import android.util.Patterns;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Pattern;

/**
 * Created by nguyennhan on 6/17/18.
 */

public class Util {

    public Util() { }

    public String formatToCurrency(String value) {
        try {
            NumberFormat formatter = new DecimalFormat("#,###");
            String formattedNumber = formatter.format(Long.parseLong(value));
            return formattedNumber;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ERROR FORMAT", e.getMessage());
        }
        return "0";
    }
}
