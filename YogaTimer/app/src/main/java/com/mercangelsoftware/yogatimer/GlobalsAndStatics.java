package com.mercangelsoftware.yogatimer;
import android.content.*;

public class GlobalsAndStatics
{
	public final static String PREFNAME = "yogatimer";
	public final static String SESSION_LIST = "session_list";
	public final static String ACTIVE_SESSION = "active_session";
	
	public static String getActiveSession(Context context)
	{
		SharedPreferences preferences = context.getSharedPreferences(GlobalsAndStatics.PREFNAME, Context.MODE_PRIVATE);
		return preferences.getString(ACTIVE_SESSION, null);
	}
	
	public static int getMinutes(int seconds){
        return seconds / 60;
    }

    public static int getSeconds(int seconds){
        return seconds % 60;
    }

    public static int toSeconds(int minutes, int seconds){
        return (minutes * 60) + seconds;
    }
}
