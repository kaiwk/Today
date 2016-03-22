package io.github.kermit95.today.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import io.github.kermit95.today.fluxbase.App;

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
}
