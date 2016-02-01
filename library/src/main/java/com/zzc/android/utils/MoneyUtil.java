package com.zzc.android.utils;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 与金额相关处理的工具类
 *
 * Created by zczhang on 16/1/26.
 */
public class MoneyUtil {

    /**
     * 去除价格末尾的零和小数点
     * @param
     * @return
     */
    public static String subZeroAndDot(float f) {
        String result = String.valueOf(f);

        return subZeroAndDot(result);
    }

    public static String subZeroAndDot(String result) {
        if (result.indexOf(".") > 0) {
            result = result.replaceAll("0+?$", "");//去掉多余的0
            result = result.replaceAll("[.]$", "");//如最后一位是.则去掉
        }

        return result;
    }

    /**
     * 添加 ¥ 前缀
     */
    public static String moneyAddPrefix(String money) {
        return "¥ " + money;
    }

    public static String moneyAddPrefix(int money) {
        return moneyAddPrefix(String.valueOf(money));
    }

    public static String moneyAddPrefix(float money) {
        return moneyAddPrefix(String.valueOf(money));
    }

    /**
     * 添加 元 后缀
     */
    public static String moneyAddSuffix(String money) {
        return money + "元";
    }

    /**
     * 当值小于分的钱向上取整
     */
    public static String moneyRoundUp2Point(BigDecimal money) {
        double moneyFen = Math.ceil(money.multiply(new BigDecimal("100")).doubleValue());
        return new BigDecimal(moneyFen).divide(new BigDecimal("100")).toString();
    }

    public static boolean isEmpty(String money) {
        if (TextUtils.isEmpty(money) || "0".equals(money) ||
                "0.".equals(money) || "0.0".equals(money) || "0.00".equals(money)) {
            return true;
        }
        return false;
    }

    /**
     * -1 小于 0 等于 1 大于
     */
    public static int compareMoney(String money1, String money2) {
        BigDecimal moneyDecimal1 = new BigDecimal(money1);
        BigDecimal moneyDecimal2 = new BigDecimal(money2);
        return moneyDecimal1.compareTo(moneyDecimal2);
    }

    /**
     * 格式化 bigdecimal 为两位小数
     * 如果为负数则返回 0.00
     */
    public static String format2Point(BigDecimal decimal) {
        if (decimal == null) {
            return "";
        }
        DecimalFormat df = new DecimalFormat("0.00");
        String decimalStr = df.format(decimal);
        return decimalStr;
    }
}
