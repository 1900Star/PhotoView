package photoview.yibao.com.photoview.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import photoview.yibao.com.photoview.MyApplication;

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/8 04:18
 */
public class ConfigUtil {

    private static SharedPreferences sharedPreferences;

    private static SharedPreferences getSharedPreferences() {
        if (sharedPreferences == null) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getIntstance());
        }
        return sharedPreferences;
    }


    /**
     * 写入配置信息，需要最后面进行 commit()
     *
     * @param key
     * @param value
     * @return
     */
    public static void putBoolean(String key, boolean value) {
        SharedPreferences        sharedPref = getSharedPreferences();
        SharedPreferences.Editor editor     = sharedPref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 读取配置信息
     *
     * @param key
     * @return
     */
    public static boolean getBoolean(String key, boolean def) {
        return getSharedPreferences().getBoolean(key, def);
    }
}
