package com.mercangelsoftware.yogatimer;

import android.app.*;
import android.os.*;
import android.view.*;
import android.content.*;
import android.widget.*;
import android.widget.LinearLayout.*;
import android.text.*;
import android.view.inputmethod.*;

public class SingleLineDialog extends DialogFragment
{
	
	protected String text = "";
	protected String title = "";
	protected String positiveText = "Ok";
	protected String negativeText = "Cancel";

	public void setNegativeText(String negativeText)
	{
		this.negativeText = negativeText;
	}

	public String getNegativeText()
	{
		return negativeText;
	}

	public void setPositiveText(String positiveText)
	{
		this.positiveText = positiveText;
	}

	public String getPositiveText()
	{
		return positiveText;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getTitle()
	{
		return title;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getText()
	{
		return text;
	}
	
	public interface SingleLineDialogListener {
        public void onPositiveClick(DialogFragment dialog);
    }

    SingleLineDialogListener listener;

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        listener = (SingleLineDialogListener) activity;
    }
	
	@Override
    public Dialog onCreateDialog(Bundle savedState){
        EditText et = new EditText(getContext());
		et.setSingleLine();
		et.setLayoutParams(new LayoutParams(
								LinearLayout.LayoutParams.MATCH_PARENT,
							   	LinearLayout.LayoutParams.MATCH_PARENT));
								
		et.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
								
		et.addTextChangedListener(new TextWatcher(){
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
				
				public void onTextChanged(CharSequence s, int start, int before, int count) {}
				
				public void afterTextChanged(Editable s) {
					text = s.toString();
				}
		});

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(title)
			.setView(et)
			.setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					if (listener != null) listener.onPositiveClick(SingleLineDialog.this);
					
					dismiss();
				}
			})
			.setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dismiss();
				}
			});

        return builder.create();
    }
}
