package com.zzc.android.utils;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;


/**
 * 手机设备相关工具类
 * <p/>
 * Created by zczhang on 16/1/26.
 */
public class DeviceUtil {
    private static final String UNIQUEID = "UNIQUEID";
    private static String sID = null;


    /**
     * 是否有SDCard
     *
     * @return 是否有SDCard
     */
    public static boolean hasSDCard() {

        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取应用运行的最大内存
     *
     * @return 最大内存
     */
    public static long getMaxMemory() {

        return Runtime.getRuntime().maxMemory();
    }

    /**
     * 获取应用运行的可用内存
     * @return
     */
    public static long getFreeMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    /**
     * 获取当前总共的堆内存
     * @return
     */
    public static long getTotalMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    /**
     * 获取系统版本号
     *
     * @return
     */
    public static String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    public static String getDeviceName() {
        return Build.MODEL;
    }

    /**
     * 获取手机系统SDK版本
     *
     * @return 如API 17 则返回 17
     */
    public static int getSDKVersion() {
        return android.os.Build.VERSION.SDK_INT;
    }


    /**
     * 设置媒体音量
     *
     * @param context
     *            上下文
     * @return 媒体音量，取值范围为0-15
     */
    public static void setMediaVolume(Context context, int mediaVolume) {
        AudioManager audio = (AudioManager) context.getSystemService(Service.AUDIO_SERVICE);
        int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        if (mediaVolume < 0) {
            mediaVolume = 0;
        } else if (mediaVolume > maxVolume) {
            mediaVolume = mediaVolume % maxVolume;
            if (mediaVolume == 0) {
                mediaVolume = maxVolume;
            }
        }
        audio.setStreamVolume(AudioManager.STREAM_MUSIC, mediaVolume, AudioManager.FLAG_PLAY_SOUND);
    }

    public static String getDeviceId(Context context) {
        if (context == null) {
            return "";
        }
        String deviceId = null;
        if (Build.VERSION.SDK_INT >= 23) {
            int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                deviceId = telephonyManager.getDeviceId();
                if (TextUtils.isEmpty(deviceId)) {
                    deviceId = telephonyManager.getSimSerialNumber();
                }
                if (TextUtils.isEmpty(deviceId)) {
                    WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                    String macAddress = wifiManager.getConnectionInfo().getMacAddress();
                    deviceId = EnDecodeUtil.md5(macAddress);
                }
            } else {
                if (TextUtils.isEmpty(deviceId)) {
                    deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                }
                if (TextUtils.isEmpty(deviceId)) {
                    deviceId = EnDecodeUtil.md5(uniqueId(context));
                }
            }
        } else {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = telephonyManager.getDeviceId();
            if (TextUtils.isEmpty(deviceId)) {
                deviceId = telephonyManager.getSimSerialNumber();
            }
            if (TextUtils.isEmpty(deviceId)) {
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                String macAddress = wifiManager.getConnectionInfo().getMacAddress();
                deviceId = EnDecodeUtil.md5(macAddress);
            }
            if (TextUtils.isEmpty(deviceId)) {
                deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            }
            if (TextUtils.isEmpty(deviceId)) {
                deviceId = EnDecodeUtil.md5(uniqueId(context));
            }
        }
        return deviceId;
    }

    public synchronized static String uniqueId(Context context) {
        if (sID == null) {
            File uniqueid = new File(context.getFilesDir(), UNIQUEID);
            try {
                if (!uniqueid.exists())
                    writeUniqueidFile(uniqueid);
                sID = readUniqueidFile(uniqueid);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return sID;
    }

    private static String readUniqueidFile(File installation) throws IOException {
        RandomAccessFile f = new RandomAccessFile(installation, "r");
        byte[] bytes = new byte[(int) f.length()];
        f.readFully(bytes);
        f.close();
        return new String(bytes);
    }

    private static void writeUniqueidFile(File installation) throws IOException {
        FileOutputStream out = new FileOutputStream(installation);
        String id = UUID.randomUUID().toString();
        out.write(id.getBytes());
        out.close();
    }
}
