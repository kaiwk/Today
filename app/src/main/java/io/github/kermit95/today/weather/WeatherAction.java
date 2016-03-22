package io.github.kermit95.today.weather;

/**
 * Created by kermit on 16/3/16.
 */
public final class WeatherAction {

    private WeatherAction(){}

    //Action Type
    public static final String UPDATE_WEATHER = "UPDATE_WEATHER";
    public static final String CHECK_DETAIL = "CHECK_DETAIL";
    public static final String LOAD_LOCALDATA = "LAOD_LOCALDATA";


    //Action Key
    public static final String KEY_WEATHER = "KEY_WEATHER";
    public static final String DETAIL_POSITION = "DETAIL_POSITION";
    public static final String KEY_WEATHERLIST = "KEY_WEATHERLIST ";
}
