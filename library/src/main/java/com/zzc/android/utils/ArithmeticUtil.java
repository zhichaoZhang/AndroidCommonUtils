package com.zzc.android.utils;

import android.text.TextUtils;

import java.math.BigDecimal;

/**
 * 运算相关工具类
 *
 * Created by zczhang on 16/1/26.
 */
public class ArithmeticUtil {
    /**
     * 数字减法
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String subtract(String s1, String s2) {
        if (TextUtils.isEmpty(s1) || TextUtils.isEmpty(s2)) {
            return "params error";
        }

        BigDecimal b1 = new BigDecimal(s1);
        BigDecimal b2 = new BigDecimal(s2);
        return b1.subtract(b2).toString();
    }

    /**
     * 数字乘法
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String multiply(String s1, String s2) {
        if (TextUtils.isEmpty(s1) || TextUtils.isEmpty(s2)) {
            return "params error";
        }
        BigDecimal b1 = new BigDecimal(s1);
        BigDecimal b2 = new BigDecimal(s2);
        return b1.multiply(b2).toString();
    }

    /**
     * 数字除法
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String divide(String s1, String s2) {
        if (TextUtils.isEmpty(s1) || TextUtils.isEmpty(s2)) {
            return "params error";
        }
        BigDecimal b1 = new BigDecimal(s1);
        BigDecimal b2 = new BigDecimal(s2);
        return b1.divide(b2).toString();
    }

    /**
     * 数字加法
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String add(String s1, String s2) {
        if (TextUtils.isEmpty(s1) || TextUtils.isEmpty(s2)) {
            return "params error";
        }
        BigDecimal b1 = new BigDecimal(s1);
        BigDecimal b2 = new BigDecimal(s2);
        return b1.add(b2).toString();
    }
}
