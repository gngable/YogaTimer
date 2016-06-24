package com.mercangelsoftware.yogatimer;
import android.app.*;
import android.os.*;
import android.view.*;
import android.content.*;
import android.widget.*;

public class EditSessionActivity extends Activity
{
	private String sessionName;
	private TextView repeatLabel;
	private TextView warmupLabel;
	private TextView cooldownLabel;
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_session);
		
		sessionName = GlobalsAndStatics.getActiveSession(getApplicationContext());
		
		((TextView)findViewById(R.id.session_name)).setText(sessionName);
		
		repeatLabel = ((TextView)findViewById(R.id.repeat));
		
		warmupLabel = ((TextView)findViewById(R.id.warmup));
		cooldownLabel = ((TextView)findViewById(R.id.cooldown));
    }
	
	public void backClick(View view){
		Intent intent = new Intent(this, SessionsActivity.class);
		startActivity(intent);
	}
	
	public void forwardClick(View view){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	
	public void addActivityClick(View view){
		AddActivityDialog aad = new AddActivityDialog();
		aad.setListener(new AddActivityDialog.AddActivityDialogListener(){

				@Override
				public void onAddClick(String name, int minutes, int seconds)
				{
					
				}
		});
		
		aad.show(getFragmentManager(), "addactivitydialog");
	}
	
	public void warmupClick(View view){
		MinutesSecondsPickerDialog msp = new MinutesSecondsPickerDialog();
		
		msp.setTitle("Warm up for ");
		
		msp.setListener(new MinutesSecondsPickerDialog.MinutesSecondsDialogListener(){

				@Override
				public void onMSSetClick(int minutes, int seconds)
				{
					String pad = "";
					if (seconds < 10) pad = "0";
					warmupLabel.setText("Warm up for " + minutes + ":" + pad + seconds);
				}
		});
		
		msp.show(getFragmentManager(), "MinutesSecondsPickerDialog");
	}
	
	public void repeatClick(View view){
		SinglePickerDialog spd = new SinglePickerDialog();
		spd.setListener(new SinglePickerDialog.SinglePickerDialogListener(){

				@Override
				public void onSetClick(int value)
				{
					String t = " time";
					
					if (value != 1) t += "s";
					repeatLabel.setText("Repeat " + value + t);
				}

			
		});
		spd.show(getFragmentManager(), "SinglePickerDialog");
	}
	
	public void cooldownClick(View view){
		MinutesSecondsPickerDialog msp = new MinutesSecondsPickerDialog();

		msp.setTitle("Cool down for ");

		msp.setListener(new MinutesSecondsPickerDialog.MinutesSecondsDialogListener(){

				@Override
				public void onMSSetClick(int minutes, int seconds)
				{
					String pad = "";
					if (seconds < 10) pad = "0";
					cooldownLabel.setText("Cool down for " + minutes + ":" + pad + seconds);
				}
			});

		msp.show(getFragmentManager(), "MinutesSecondsPickerDialog");
	}
}
