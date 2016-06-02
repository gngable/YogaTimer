/*
 MIT License

 Copyright (c) 2016 Nick Gable (Mercangel Software)

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */

package com.mercangelsoftware.yogatimer;

import android.app.*;
import android.os.*;
import android.view.*;
import android.content.*;
import java.util.*;
import android.widget.*;
import android.widget.LinearLayout.*;
import android.widget.AdapterView.*;

public class SessionsActivity extends Activity implements SingleLineDialog.SingleLineDialogListener
{

	SingleLineDialog sd = new SingleLineDialog();

	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessions);
		loadSessions();
		setActiveSession(null);
    }

	public void addSession(View view)
	{
		sd.setTitle("Please Enter Title");
		sd.setPositiveText("Add");
		sd.show(getFragmentManager(), "SessionNameDialog");
	}

	public void clearSessions(View view)
	{
		saveSessions(new HashSet<String>());
		loadSessions();
	}

	@Override
	public void onPositiveClick(DialogFragment dialog)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(sd.getText());
		builder.create().show();

		Set<String> sessions = getSessions();

		HashSet<String> hs = new HashSet<>(sessions);

		hs.add(sd.getText());

		saveSessions(hs);

		loadSessions();
	}
	
	public void deleteSession(String session){
		Set<String> sessions = getSessions();
		
		HashSet<String> hs = new HashSet<>();
		
		for(String s : sessions){
			if (!s.equals(session)){
				hs.add(s);
			}
		}
		
		saveSessions(hs);

		loadSessions();
	}

	private Set<String> getSessions()
	{
		SharedPreferences preferences = getApplicationContext().getSharedPreferences(GlobalsAndStatics.PREFNAME, Context.MODE_PRIVATE);
		return preferences.getStringSet(GlobalsAndStatics.SESSION_LIST, new HashSet<String>());
	}

	private void saveSessions(Set<String> sessions)
	{
		SharedPreferences preferences = getApplicationContext().getSharedPreferences(GlobalsAndStatics.PREFNAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor ed = preferences.edit();

		ed.putStringSet(GlobalsAndStatics.SESSION_LIST, sessions);

		ed.commit();
	}
	
	private void setActiveSession(String session)
	{
		SharedPreferences preferences = getApplicationContext().getSharedPreferences(GlobalsAndStatics.PREFNAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor ed = preferences.edit();

		ed.putString(GlobalsAndStatics.ACTIVE_SESSION, session);

		ed.commit();
	}

	private void loadSessions()
	{
		Set<String> sessions = getSessions();

		ListView lv = (ListView)findViewById(R.id.sessionslist);

		lv.removeAllViewsInLayout();

		//InfoPop("" + sessions.size());

		if (sessions.isEmpty()) return;

		ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this, R.layout.list_item_session, R.id.list_item_sessionLabel, sessions.toArray(new String[sessions.size()]));
		lv.setAdapter(modeAdapter);
	}

	public void InfoPop(String text)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(text);
		builder.create().show();
	}

	private View getSessionView(String session)
	{
		TextView tv = new TextView(getApplicationContext());

		tv.setText(session);
		tv.setLayoutParams(new LayoutParams(
							   LinearLayout.LayoutParams.WRAP_CONTENT,
							   LinearLayout.LayoutParams.WRAP_CONTENT));

		return tv;
	}
	
	public void deleteClick(View view){
		///InfoPop("delete");
		ViewGroup vg = (ViewGroup)view.getParent();
		
		for (int i = 0; i < vg.getChildCount(); i++){
			if (vg.getChildAt(i) instanceof TextView && !(vg.getChildAt(i) instanceof Button)){
				InfoPop(((TextView)vg.getChildAt(i)).getText().toString());
				deleteSession(((TextView)vg.getChildAt(i)).getText().toString());
			}
		}
	}
	
	public void editClick(View view){
		///InfoPop("delete");
		ViewGroup vg = (ViewGroup)view.getParent();

		for (int i = 0; i < vg.getChildCount(); i++){
			if (vg.getChildAt(i) instanceof TextView && !(vg.getChildAt(i) instanceof Button)){
				setActiveSession(((TextView)vg.getChildAt(i)).getText().toString());
				Intent intent = new Intent(this, EditSessionActivity.class);
				startActivity(intent);
				break;
			}
		}
	}
	
	public void startClick(View view){
		ViewGroup vg = (ViewGroup)view.getParent();

		for (int i = 0; i < vg.getChildCount(); i++){
			if (vg.getChildAt(i) instanceof TextView && !(vg.getChildAt(i) instanceof Button)){
				setActiveSession(((TextView)vg.getChildAt(i)).getText().toString());
				Intent intent = new Intent(this, MainActivity.class);
				startActivity(intent);
				break;
			}
		}
	}
}
