package com.noname.smatech.model.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class PreferenceHelper {

	private static SharedPreferences app_prefs;
	private static String app_ref = "details";
	private static String hasdata = "hasdata";

	private Context context;

	public PreferenceHelper(Context context) {
		this.context = context;
		try {
			app_prefs = context.getSharedPreferences(app_ref,
					Context.MODE_PRIVATE);
		}
		catch (NullPointerException e)
		{
		}
	}


	public static Boolean checkData() {
		return app_prefs.getBoolean(hasdata,false);
	}

	public static void setcheckData(boolean setdata){
	Editor edit = app_prefs.edit();
	edit.putBoolean(hasdata, setdata);
	edit.apply();
}


}
