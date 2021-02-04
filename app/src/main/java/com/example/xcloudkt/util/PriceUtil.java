package com.example.xcloudkt.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.example.xcloudkt.R;

import java.text.DecimalFormat;

/**
 * author : zhanghang
 * date : 2020/6/1 14:47
 */
public class PriceUtil {

    @SuppressLint("SetTextI18n")
    public static void setPricePoint(String price, TextView helper, boolean isonePrice, Context context) {
        int dp = isonePrice ? helper.getContext().getResources().getDimensionPixelSize(R.dimen.font_size_16) : helper.getContext().getResources().getDimensionPixelSize(R.dimen.font_size_12);
        int defaultDp = helper.getContext().getResources().getDimensionPixelSize(R.dimen.font_size_12);

        if (!TextUtils.isEmpty(price) && !TextUtils.equals("0.00", price) && price.contains(".")) { // 价格带小数点 ¥123.00
            String[] split = null;
            if (price != null) {
                String bbb = price.replace(".", "a");
                split = bbb.split("a");
            }
            if (split != null) {
                String points = "¥ " + split[0] + "." + split[1];
                SpannableString styledText = new SpannableString(points);
                styledText.setSpan(new AbsoluteSizeSpan(defaultDp, false), 0, 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                styledText.setSpan(new AbsoluteSizeSpan(dp, false), 2, split[0].length() + 2, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                styledText.setSpan(new AbsoluteSizeSpan(defaultDp, false), split[0].length() + 2, split[0].length() + 2 + split[1].length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);

                helper.setText(styledText);
            }
        } else if (!TextUtils.isEmpty(price) && !TextUtils.equals("0.00", price)) {  // 只有价格 ¥123
            if (price != null) {
                String prices = "¥ " + price;
                SpannableString styledText = new SpannableString(prices);
                styledText.setSpan(new AbsoluteSizeSpan(defaultDp, false), 0, 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                styledText.setSpan(new AbsoluteSizeSpan(dp, false), 2, styledText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                helper.setText(styledText);
            } else {
            }
        }
    }


    /**
     * 字符串高亮显示部分文字
     *
     * @param textView 控件
     * @param str1     要高亮显示的文字（输入的关键词）
     * @param str2     包含高亮文字的字符串
     */
    public static void setTextHighLight(TextView textView, String str1, String str2) {
        SpannableString sp = new SpannableString(str2);
        // 遍历要显示的文字
        for (int i = 0; i < str1.length(); i++) {
            // 得到单个文字
            String s1 = str1.charAt(i) + "";
            // 判断字符串是否包含高亮显示的文字
            if (str2.contains(s1)) {
                // 查找文字在字符串的下标
                int index = str2.indexOf(s1);
                // 循环查找字符串中所有该文字并高亮显示
                while (index != -1) {
                    ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#018CD7"));
                    sp.setSpan(colorSpan, index, index + 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    // s1从这个索引往后开始第一个出现的位置
                    index = str2.indexOf(s1, index + 1);
                }
            }
        }
        // 设置控件
        textView.setText(sp);
    }

    public static boolean checkPriceShow(String price) {
        if (TextUtils.isEmpty(price) || ("0").equals(price)) {
            return false;
        }
        return true;
    }

    public static String formatPriceTwo(String price) {
        if (TextUtils.isEmpty(price)) {
            return "0.00";
        }
        try {
            double d = Double.parseDouble(price);
            DecimalFormat formatter = new DecimalFormat("#0.00");
            return formatter.format(d);
        } catch (NumberFormatException ex) {
            return "0.00";
        }
    }
}
