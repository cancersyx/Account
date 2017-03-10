package com.zsf.accountbook.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.zsf.accountbook.R;

import java.util.Calendar;

/**
 * Created by zsf on 2017/3/5.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        Toast.makeText(getActivity(),"时间是：" + hourOfDay + ":" + minute,Toast.LENGTH_SHORT).show();
        TextView time = (TextView) getActivity().findViewById(R.id.tv_time);
        time.setText(hourOfDay + ":" + minute);
    }

}
