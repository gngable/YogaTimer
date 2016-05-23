package com.servantsoftware.yogatimer;

import android.app.*;
import android.os.*;
import android.speech.tts.*;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener
{
	TextToSpeech tts = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		tts = new TextToSpeech(getApplicationContext(), this);
    }

	@Override
	public void onInit(int p1)
	{
		tts.speak("Everything is awesome!", TextToSpeech.QUEUE_ADD, null);
	}

	
	
}
