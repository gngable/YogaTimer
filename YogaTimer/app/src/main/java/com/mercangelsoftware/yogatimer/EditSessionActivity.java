package com.mercangelsoftware.yogatimer;
import android.app.*;
import android.os.*;
import android.view.*;
import android.content.*;

public class EditSessionActivity extends Activity
{
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_session);
		
    }
	
	public void backClick(View view){
		Intent intent = new Intent(this, SessionsActivity.class);
		startActivity(intent);
	}
	
	public void forwardClick(View view){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}
