package com.coolweather.app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.coolweather.app.app.activity.model.*;
import com.coolweather.app.app.activity.model.City;
import com.coolweather.app.app.activity.model.County;
import com.coolweather.app.db.CoolWeatherOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jixiaoyong on 2016/4/18.
 */
public class CoolWeatherDB {
    /**
     * 数据库名
     */
    public static final String DB_NAME = "cool_weather";
    /**
     * 数据库版本
     */
    public static final int VERSION = 1;
    private static CoolWeatherDB coolWeatherDB;
    private SQLiteDatabase db;
    /**
     * 将构造方法私有化
     */
    private CoolWeatherDB(Context context) {
        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context,
                DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }
    /**
     * 获取CoolWeatherDB的实例。
     */
    public synchronized static CoolWeatherDB getInstance(Context context) {
        if (coolWeatherDB == null) {
            coolWeatherDB = new CoolWeatherDB(context);
        }
        return coolWeatherDB;
    }
    /**
     * 将Province实例存储到数据库。
     */
    public void saveProvince(com.coolweather.app.app.activity.model.Province province) {
        if (province != null) {
            ContentValues values = new ContentValues();
            values.put("province_name", province.getProvinceName());
            values.put("province_code", province.getProvinceCode());
            db.insert("Province", null, values);
        }
    }
    /**
     * 从数据库读取全国所有的省份信息。
     */
    public List<com.coolweather.app.app.activity.model.Province> loadProvinces() {
        List<com.coolweather.app.app.activity.model.Province> list = new ArrayList<com.coolweather.app.app.activity.model.Province>();
        Cursor cursor = db
                .query("Province", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                com.coolweather.app.app.activity.model.Province province = new com.coolweather.app.app.activity.model.Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor
                        .getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            } while (cursor.moveToNext());
        }
        return list;
    }
    /**
     * 将City实例存储到数据库。
     */
    public void saveCity(com.coolweather.app.app.activity.model.City city) {
        if (city != null) {
            ContentValues values = new ContentValues();
            values.put("city_name", city.getCityName());
            values.put("city_code", city.getCityCode());
            values.put("province_id", city.getProvinceId());
            db.insert("City", null, values);
        }
    }
    /**
     * 从数据库读取某省下所有的城市信息。
     */
    public List<com.coolweather.app.app.activity.model.City> loadCities(int provinceId) {
        List<com.coolweather.app.app.activity.model.City> list = new ArrayList<com.coolweather.app.app.activity.model.City>();
        Cursor cursor = db.query("City", null, "province_id = ?",
                new String[] { String.valueOf(provinceId) }, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                com.coolweather.app.app.activity.model.City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor
                        .getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor
                        .getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                list.add(city);
            } while (cursor.moveToNext());
        }
        return list;
    }
    /**
     * 将County实例存储到数据库。
     */
    public void saveCounty(com.coolweather.app.app.activity.model.County county) {
        if (county != null) {
            ContentValues values = new ContentValues();
            values.put("county_name", county.getCountyName());
            values.put("county_code", county.getCountyCode());
            values.put("city_id", county.getCityId());
            db.insert("County", null, values);
        }
    }
/**
 * 从数据库读取某城市下所有的县信息。
 */
public List<com.coolweather.app.app.activity.model.County> loadCounties(int cityId) {
    List<com.coolweather.app.app.activity.model.County> list = new ArrayList<com.coolweather.app.app.activity.model.County>();
    Cursor cursor = db.query("County", null, "city_id = ?",
            new String[] { String.valueOf(cityId) }, null, null, null);
    if (cursor.moveToFirst()) {
        do {
            com.coolweather.app.app.activity.model.County county = new County();
            county.setId(cursor.getInt(cursor.getColumnIndex("id")));
            county.setCountyName(cursor.getString(cursor
                    .getColumnIndex("county_name")));
            county.setCountyCode(cursor.getString(cursor
                    .getColumnIndex("county_code")));
            county.setCityId(cityId);
            list.add(county);
        } while (cursor.moveToNext());
    }
    return list;
}
}
