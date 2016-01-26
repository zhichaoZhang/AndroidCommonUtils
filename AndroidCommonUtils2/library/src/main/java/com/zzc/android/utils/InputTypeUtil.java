package com.zzc.android.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 *  输入法工具类,如:打开关闭软键盘,复制,粘帖文字等
 * Created by yamlee on 15/8/6.
 */
public class InputTypeUtil {
    /**
     * 保存数据到剪切板
     *
     * @param context
     * @param str
     */
    public static void saveClipBoard(Context context, String str) {
        if (context == null) {
            return;
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {

            android.text.ClipboardManager cmb = (android.text.ClipboardManager) context
                    .getSystemService(Activity.CLIPBOARD_SERVICE);
            cmb.setText(str);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context
                    .getSystemService(Activity.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("honey", str);
            clipboard.setPrimaryClip(clip);
        }
    }

    /**
     * 打开软件盘
     *
     * @param context
     * @param view
     */
    public static void openSoftKeyBoard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (view != null) {
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        }
    }

    /**
     * 关闭软键盘
     *
     * @param context
     * @param view
     */
    public static void closeSoftKeyBoard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (view != null) {
            if (view.getWindowToken() != null) {
                imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
            }
        }
    }

}
