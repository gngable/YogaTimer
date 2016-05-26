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

public class SessionsActivity extends Activity implements SingleLineDialog.SingleLineDialogListener
{
	
	SingleLineDialog sd = new SingleLineDialog();
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessions);
		loadSessions();
    }
	
	public void addSession(View view){
		sd.setTitle("Please Enter Title");
		sd.setPositiveText("Add");
		sd.show(getFragmentManager(), "SessionNameDialog");
	}

	@Override
	public void onPositiveClick(DialogFragment dialog)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(sd.getText());
		builder.create().show();
		
		
	}
	
	private Set<String> getSessions(){
		SharedPreferences preferences = getApplicationContext().getSharedPreferences(GlobalsAndStatics.PREFNAME, Context.MODE_PRIVATE);
		return preferences.getStringSet("sessions", null);
	}
	
	private void loadSessions(){
		Set<String> sessions = getSessions();
		
		ListView lv = (ListView)findViewById(R.id.sessionslist);
		
		lv.removeAllViewsInLayout();
		
		if (sessions == null) return;
		
		for (String session : sessions){
			lv.addView(getSessionView(session));
		}
	}

	private View getSessionView(String session)
	{
		TextView tv = new TextView(getApplicationContext());
		
		tv.setText(session);
		tv.setLayoutParams(new LayoutParams(
							   LinearLayout.LayoutParams.MATCH_PARENT,
							   LinearLayout.LayoutParams.MATCH_PARENT));
		
		return tv;
	}
}
