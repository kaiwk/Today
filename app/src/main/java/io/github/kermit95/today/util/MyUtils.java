package io.github.kermit95.today.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import io.github.kermit95.today.main.App;

/**
 * Created by kermit on 16/3/24.
 */
public class MyUtils {

    public static boolean isWifi() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) App.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return info != null && info.getType() == 1;
    }

    /**
     * 判断网络是否可用
     * @return
     */
    public static boolean hasNetwork() {
        // 获取手机所有连接管理对象（包括wifi，net等连接的管理）
        ConnectivityManager connectivity=(ConnectivityManager)App.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivity != null) {
            // 获取网络连接管理的对象
            NetworkInfo info=connectivity.getActiveNetworkInfo();
            if(info != null && info.isConnected()) {
                // 判断当前网络是否已经连接
                if(info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

}
