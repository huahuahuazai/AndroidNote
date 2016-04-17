package com.coolweather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CoolWeatherOpenHelper extends SQLiteOpenHelper {
	/*
	 * Province表建表语句
	 * */
	public static final String CREATE_PROVINCE ="create table Province("
			+"id integer primary key autoincrement,"
			+"province_name text,"
			+"provice_code text)";
	/*
	 * City表建表语句
	 */
	public static final String CREATE_CITY = "create table City("
			+"id integer primary key autoincement"
			+"city_name text,"
			+"city_code text"
			+"province_id integer)";
	/*
	 * Country表建表语句
	 */
	public static final String CERATE_COUNTY = "create table country("
			+"id integer primary key autoincrement"
			+"country_name text,"
			+"country_code text,"
			+"ctiy_id integer)";
	
	public CoolWeatherOpenHelper(Context context,String name,CursorFactory factory,int version){
		super(context,name,factory,version);
	}
	
	//下面是分别创建对应的三个表
	@Override
	public void onCreate(SQLiteDatabase db){
		db.execSQL(CREATE_PROVINCE);
		db.execSQL(CREATE_CITY);
		db.execSQL(CERATE_COUNTY);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){}

}
