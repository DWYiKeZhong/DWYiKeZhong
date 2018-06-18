package com.example.yikezhong.ui.shared;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.example.yikezhong.app.MyApplication;

/**
 * SharedPreferences的一个工具类，调用setParam就能保存String, Integer, Boolean, Float, Long类型的参数
 * 同样调用getParam就能获取到保存在手机里面的数据
 * @author xiaanming
 *
 */
public class SharedPreferencesUtils {
	/**
	 * 保存在手机里面的文件名
	 */
	private static final String FILE_NAME = "share_date";
	private static SharedPreferences preferences;

	/**
	 * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
	 * @param context
	 * @param key
	 * @param object 
	 */
	public static void setParam(Context context , String key, Object object){
		
		String type = object.getClass().getSimpleName();
		preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		
		if("String".equals(type)){
			editor.putString(key, (String)object);
		}
		else if("Integer".equals(type)){
			editor.putInt(key, (Integer)object);
		}
		else if("boolean".equals(type)){
			editor.putBoolean(key, (Boolean)object);
		}
		else if("Float".equals(type)){
			editor.putFloat(key, (Float)object);
		}
		else if("Long".equals(type)){
			editor.putLong(key, (Long)object);
		}
		
		editor.commit();
	}
	
	
	/**
	 * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
	 * @param context
	 * @param key
	 * @param defaultObject
	 * @return
	 */
	public static Object getParam(Context context , String key, Object defaultObject){
		String type = defaultObject.getClass().getSimpleName();
		preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
		
		if("String".equals(type)){
			return preferences.getString(key, (String)defaultObject);
		}
		else if("Integer".equals(type)){
			return preferences.getInt(key, (Integer)defaultObject);
		}
		else if("boolean".equals(type)){
			return preferences.getBoolean(key, (Boolean)defaultObject);
		}
		else if("Float".equals(type)){
			return preferences.getFloat(key, (Float)defaultObject);
		}
		else if("Long".equals(type)){
			return preferences.getLong(key, (Long)defaultObject);
		}
		
		return null;
	}

	//清空登录信息
	public static void clear(Context context){
		preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
		preferences.edit().clear().commit();
	}



	//以下为保存获取数据的另一种方法
	public static void saveString(String flag, String str) {
		if (preferences == null) {
			preferences = MyApplication.getAppContext().getSharedPreferences(FILE_NAME, MyApplication.getAppContext().MODE_PRIVATE);
		}
		SharedPreferences.Editor edit = preferences.edit();
		edit.putString(flag, str);
		edit.commit();
	}
	public static String getString(String flag) {
		if (preferences == null) {
			preferences = MyApplication.getAppContext().getSharedPreferences(FILE_NAME, MyApplication.getAppContext().MODE_PRIVATE);
		}
		return preferences.getString(flag, "");
	}

	public static boolean getBoolean(String tag) {
		if (preferences == null) {
			preferences = MyApplication.getAppContext().getSharedPreferences(FILE_NAME, MyApplication.getAppContext().MODE_PRIVATE);
		}
		return preferences.getBoolean(tag, false);
	}

	public static void putBoolean(String tag, boolean content) {
		if (preferences == null) {
			preferences = MyApplication.getAppContext().getSharedPreferences(FILE_NAME, MyApplication.getAppContext().MODE_PRIVATE);
		}
		SharedPreferences.Editor edit = preferences.edit();
		edit.putBoolean(tag, content);
		edit.commit();
	}

	public static void clearSp() {
		if (preferences == null) {
			preferences = MyApplication.getAppContext().getSharedPreferences(FILE_NAME, MyApplication.getAppContext().MODE_PRIVATE);
		}
		boolean isClear = preferences.edit().clear().commit();

		Log.e("AAA","是否清空"+isClear);
	}


}
