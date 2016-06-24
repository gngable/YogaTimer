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

/**
 * Created by Nick Gable on 5/3/2016.
 */
public class SinglePickerDialog extends DialogFragment {

    protected String title = "";

    protected int current = 0;

    public void setCurrent(int current) {
        this.current = current;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public interface SinglePickerDialogListener {
        public void onSetClick(int value);
    }

    SinglePickerDialogListener listener;
	
	public void setListener(SinglePickerDialogListener spdlistener){
		listener = spdlistener;
	}

//    @Override
//    public void onAttach(Activity activity){
//        super.onAttach(activity);
//
//        listener = (SinglePickerDialogListener) activity;
//    }

    @Override
    public Dialog onCreateDialog(Bundle savedState){
        String[] nums = new String[100];
        for(int i=0; i<nums.length; i++) nums[i] = Integer.toString(i);

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View pickerview = inflater.inflate(R.layout.dialog_single_picker, null);

        final NumberPicker picker = (NumberPicker)pickerview.findViewById(R.id.single_picker);

        picker.setMinValue(0);
        picker.setMaxValue(99);
        picker.setDisplayedValues(nums);
        picker.setValue(current);
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
                        if (listener != null) listener.onSetClick(picker.getValue());
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
