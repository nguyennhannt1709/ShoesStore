package com.alviss.shoesstore.utils;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;

import com.alviss.shoesstore.models.BaseModel;
import com.alviss.shoesstore.models.KhachHang;
import com.alviss.shoesstore.models.SendMailItem;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.regex.Pattern;



public class Util {

    public Util() { }

    public static SendMailItem ConvertKhachHang2MailModel(KhachHang khachHang) {

        SendMailItem item = new SendMailItem(
                "Xác nhận đơn hàng Shoes Store "+ new Date().getTime(),
                "admin@shoesstore.vn",
                "Shoes Store",
                    khachHang.getEmail(),
                "<body>\n" +
                        "<h1>Confirm your infomation</h1>\n" +
                        "<b>Dear "+khachHang.getTenKhachHang()+",</b><br>\n" +
                        "<p>Your phone number is "+khachHang.getSoDienThoai()+"<br>\n" +
                        "Your address: " + khachHang.getDiaChi()+ "</p><br>"+
                        "<a href=\"http://demo8257742.mockable.io/activate_success\">Activate</a><br><br>\n" +
                        "<img width=\"200\" height=\"200\"  src=\"http://channel.mediacdn.vn/thumb_w/640/prupload/164/2017/08/img20170815150115496.jpg\"/><br>\n" +
                        "<br><br>\n" +
                        "<i>Please don't reply this email.</i>\n" +
                        "\n" +
                        "</body>");
        return item;
    }
    public static SendMailItem ConvertKhachHang2MailModelTwo(KhachHang khachHang) {

        SendMailItem item = new SendMailItem(
                "Xác nhận đơn hàng Shoes Store "+ new Date().getTime(),
                "admin@shoesstore.vn",
                "Shoes Store",
                khachHang.getEmail(),
                "<body>\n" +
                        "<h1>Ban co don hang can xu ly</h1>\n" +
                        "Ten khach hang: " + khachHang.getTenKhachHang()+ "</p><br>"+
                        "<b>Dia chi khach hang "+khachHang.getDiaChi()+",</b><br>\n" +
                        "<p>So dien thoai khach hang la: "+khachHang.getSoDienThoai()+"<br>\n" +
                        "<img width=\"200\" height=\"200\"  src=\"http://channel.mediacdn.vn/thumb_w/640/prupload/164/2017/08/img20170815150115496.jpg\"/><br>\n" +
                        "<br><br>\n" +
                        "<i>Please don't reply this email.</i>\n" +
                        "\n" +
                        "</body>");
        return item;
    }

    public static SendMailItem _this_is_example = new SendMailItem(
            "Xác nhận đơn hàng Gongcha 17062018",
            "admin@shoesstore.vn",
            "Shoes Store",
            "nguyennhan20@outlook.com\n",
            "<body>\n" +
                    "<h1>Confirm your infomation</h1>\n" +
                    "<b>Dear Ms. Nam,</b><br>\n" +
                    "<p>Your phone number is 0123456789</p><br>\n" +
                    "<a href=\"https://google.com.vn\">Activate</a><br><br>\n" +
                    "<img width=\"200\" height=\"200\"  src=\"http://channel.mediacdn.vn/thumb_w/640/prupload/164/2017/08/img20170815150115496.jpg\"/><br>\n" +
                    "<br><br>\n" +
                    "<i>Please don't reply this email.</i>\n" +
                    "\n" +
                    "</body>");


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

    public static String sendMailWithModel(SendMailItem item) {
        Bundle bundle = new Bundle();
        bundle.putString("_subject", item.get_subject());
        bundle.putString("_fromMail", item.get_fromMail());
        bundle.putString("_fromName", item.get_fromName());
        bundle.putString("_mailTo", item.get_mailTo());
        bundle.putString("_html", item.get_html());
        return sendMailWithBundle(bundle);
    }

    public static String sendMailWithBundle(Bundle bundle) {

        return "https://api.elasticemail.com/v2/email/send?apikey=31cafc2f-b111-4a38-a934-9751dae468df&subject="+ bundle.getString("_subject", "") +"&from="+ bundle.getString("_fromMail", "frommail@empty.com") +"&fromName=" + bundle.getString("_fromName", "Empty Company") +"&to="+ bundle.getString("_mailTo", "") +"\n" +
                "&bodyHtml="+ bundle.getString("_html", "") +"&charset=utf-8\n" +
                "&charsetBodyHtml=utf-8\n" +
                "&encodingType=0&isTransactional=false&trackOpens=True&trackClicks=True";
    }
}
