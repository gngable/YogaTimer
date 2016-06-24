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

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.*;
import android.graphics.*;

/**
 * Created by Nick Gable on 5/3/2016.
 */
public class MinutesSecondsPickerDialog extends DialogFragment {
    protected String title = "";

    public void setTitle(String title) {
        this.title = title;
    }

    public interface MinutesSecondsDialogListener {
        public void onMSSetClick(int minutes, int seconds);
    }

    MinutesSecondsDialogListener listener;
	
	public void setListener(MinutesSecondsDialogListener msdl){
		listener = msdl;
	}

//    @Override
//    public void onAttach(Activity activity){
//        super.onAttach(activity);
//
//        listener = (MinutesSecondsDialogListener) activity;
//    }

    @Override
    public Dialog onCreateDialog(Bundle savedState){
        String[] secondnums = new String[60];
        for(int i = 0; i < 10; i++) secondnums[i] = "0" + Integer.toString(i);

        for(int i = 10; i < secondnums.length; i++) secondnums[i] = Integer.toString(i);

        String[] minutenums = new String[60];

        for(int i=0; i < minutenums.length; i++) minutenums[i] = Integer.toString(i);

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View pickerview = inflater.inflate(R.layout.dialog_minutes_seconds_picker, null);

        final NumberPicker minutepicker = (NumberPicker)pickerview.findViewById(R.id.minutes_picker);

        minutepicker.setMinValue(0);
        minutepicker.setMaxValue(59);
        minutepicker.setDisplayedValues(minutenums);
        //minutepicker.setValue(getMinutes());
		
		//minutepicker.addOnLayoutChangeListener(new LayoutChangeListener());
		//for (int i = 0; i < minutepicker.getChildCount(); i++){
			//if (minutepicker.getChildAt(i) instanceof EditText){
				//((EditText) minutepicker.getChildAt(i)).setTextSize(25);
				//((EditText) minutepicker.getChildAt(i)).setTextColor(Color.parseColor("#333333"));
			//}
		//}
		//((TextView)minutepicker.getChildAt(1)).setTextSize(10);

        final NumberPicker secondpicker = (NumberPicker)pickerview.findViewById(R.id.seconds_picker);

        secondpicker.setMinValue(0);
        secondpicker.setMaxValue(59);
        secondpicker.setDisplayedValues(secondnums);
        //secondpicker.setValue(getSeconds());
//        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//            @Override
//            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//                TimerState.intervalMax = newVal;
//            }
//        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(title)
                .setView(pickerview)
                .setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onMSSetClick(minutepicker.getValue(), secondpicker.getValue());
                        dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                    }
                });

        return builder.create();

    }
}
