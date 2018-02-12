package com.finitydev.jewishrideshare;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by finit on 12/4/2017.
 */

public class TimePickerFragment  extends DialogFragment
         {

   // private static int hour;
   // private static int minute;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker


            final Calendar c = Calendar.getInstance();
            final int hour = c.get(Calendar.HOUR_OF_DAY);
            final int minute = c.get(Calendar.MINUTE);

        String tag = getArguments().getString("tag");




        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(),
                (TimePickerDialog.OnTimeSetListener)getActivity().getSupportFragmentManager().findFragmentByTag(tag),
                hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }


}