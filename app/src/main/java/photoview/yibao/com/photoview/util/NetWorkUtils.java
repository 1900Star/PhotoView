package photoview.yibao.com.photoview.util;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

import photoview.yibao.com.photoview.MyApplication;

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/2 05:08
 */
public class NetWorkUtils {
    private NetWorkUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }



    /**
     * 打开网络设置界面
     * <p>3.0以下打开设置界面</p>
     */
    public static void openWirelessSettings() {
        if (android.os.Build.VERSION.SDK_INT > 10) {
            MyApplication.getIntstance()
                 .startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS).setFlags(
                         Intent.FLAG_ACTIVITY_NEW_TASK));
        } else {
            MyApplication.getIntstance()
                 .startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS).setFlags(
                         Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }

    /**
     * 获取活动网络信息
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @return NetworkInfo
     */
    private static NetworkInfo getActiveNetworkInfo() {
        return ((ConnectivityManager)  MyApplication.getIntstance()
                                           .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
    }

    /**
     * 判断网络是否连接
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isConnected() {
        NetworkInfo info = getActiveNetworkInfo();
        return info != null && info.isConnected();
    }



    /**
     * 判断wifi是否打开
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>}</p>
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean getWifiEnabled() {
        WifiManager wifiManager = (WifiManager) MyApplication.getIntstance()
                                                     .getSystemService(Context.WIFI_SERVICE);
        return wifiManager.isWifiEnabled();
    }

    /**
     * 打开或关闭wifi
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.CHANGE_WIFI_STATE"/>}</p>
     *
     * @param enabled {@code true}: 打开<br>{@code false}: 关闭
     */
    public static void setWifiEnabled(boolean enabled) {
        WifiManager wifiManager = (WifiManager)  MyApplication.getIntstance()
                                                     .getSystemService(Context.WIFI_SERVICE);
        if (enabled) {
            if (!wifiManager.isWifiEnabled()) {
                wifiManager.setWifiEnabled(true);
            }
        } else {
            if (wifiManager.isWifiEnabled()) {
                wifiManager.setWifiEnabled(false);
            }
        }
    }

    /**
     * 判断wifi是否连接状态
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @return {@code true}: 连接<br>{@code false}: 未连接
     */
    public static boolean isWifiConnected() {
        ConnectivityManager cm = (ConnectivityManager)  MyApplication.getIntstance()
                                                            .getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo()
                                                                    .getType() == ConnectivityManager.TYPE_WIFI;
    }






}