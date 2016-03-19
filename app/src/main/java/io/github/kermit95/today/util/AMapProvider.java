package io.github.kermit95.today.util;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import io.github.kermit95.today.fluxbase.App;

/**
 * Created by kermit on 16/3/17.
 */
public class AMapProvider {

    private static final String TAG = "AMapProvider";


    //这里希望mode和isNeedAddress为必选设置项
    //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
    private final AMapLocationClientOption.AMapLocationMode mMode;
    //设置是否返回地址信息（默认返回地址信息）
    private final boolean isNeedAddress;


    //设置是否只定位一次,默认为false
    private boolean isOnceLocation;
    //设置是否强制刷新WIFI，默认为强制刷新
    private boolean isWifiActiveScan;
    //设置是否允许模拟位置,默认为false，不允许模拟位置
    private boolean isMockEnable;
    //设置定位间隔,单位毫秒,默认为2000ms
    private int interval;


    private AMapLocationClient mAMapLocationClient;
    private AMapLocationClientOption mClientOption;

    private AMapProvider(Builder builder){

        mMode = builder.mMode;
        isNeedAddress = builder.isNeedAddress;
        isOnceLocation = builder.isOnceLocation;
        isWifiActiveScan = builder.isWifiActiveScan;
        isMockEnable = builder.isMockEnable;
        interval = builder.interval;

        mAMapLocationClient = new AMapLocationClient(App.getInstance());
        mClientOption = new AMapLocationClientOption();
    }

    public void startLocation(AMapLocationListener locationListener){

        mClientOption.setLocationMode(mMode);
        mClientOption.setNeedAddress(isNeedAddress);
        mClientOption.setOnceLocation(isOnceLocation);
        mClientOption.setWifiActiveScan(isWifiActiveScan);
        mClientOption.setMockEnable(isMockEnable);
        mClientOption.setInterval(interval);
        mAMapLocationClient.setLocationOption(mClientOption);

        mAMapLocationClient.setLocationListener(locationListener);
        mAMapLocationClient.startLocation();
    }

    public void stopLocation(AMapLocationListener locationListener){
        mAMapLocationClient.stopLocation();
        mAMapLocationClient.unRegisterLocationListener(locationListener);
        mClientOption = null;
    }

    public static class Builder{

        private final AMapLocationClientOption.AMapLocationMode mMode;
        private final boolean isNeedAddress;


        private boolean isOnceLocation = false;
        private boolean isWifiActiveScan = false;
        private boolean isMockEnable = false;
        private int interval = 200;

        public Builder(AMapLocationClientOption.AMapLocationMode mode, boolean isNeedAddress){
            this.mMode = mode;
            this.isNeedAddress = isNeedAddress;
        }


        public Builder isOnceLocation(boolean isOnceLocation){
            this.isOnceLocation = isOnceLocation;
            return this;
        }

        public Builder isWifiActiveScan(boolean isWifiActiveScan){
            this.isWifiActiveScan = isWifiActiveScan;
            return this;
        }

        public Builder isMockEnable(boolean isMockEnable){
            this.isMockEnable = isMockEnable;
            return this;
        }

        public Builder interval(int interval){
            this.interval = interval;
            return this;
        }

        public AMapProvider build(){
            return new AMapProvider(this);
        }
    }
}
