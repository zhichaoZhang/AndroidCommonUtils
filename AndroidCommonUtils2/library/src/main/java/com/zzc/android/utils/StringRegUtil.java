package com.zzc.android.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串数据类型校验工具类
 *
 * Created by zczhang on 16/1/26.
 */
public class StringRegUtil {
    public static boolean isEmail(String strEmail) {
        String strPattern = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isCellphone(String str) {
        Pattern pattern = Pattern.compile("1[0-9]{10}");
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
