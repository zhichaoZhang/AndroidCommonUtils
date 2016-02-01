package com.zzc.android.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 自定义Toast提示
 *
 * Created by zcZhang on 15/3/26.
 */
public class Toaster {
    private static Toast toast;

    public static void showLongToast(Context context, String msg) {
        if (context == null) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_LONG);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    public static void showShortToast(Context context, String msg) {
        if (context == null) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    public static void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
    }
}
