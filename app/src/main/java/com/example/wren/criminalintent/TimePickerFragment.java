package com.example.wren.criminalintent;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.app.Dialog;
import java.util.Calendar;
import java.util.Date;

import android.widget.TimePicker;
import android.view.View;

/**
 * Created by Wren on 10/11/2016.
 */

public class TimePickerFragment extends DialogFragment {
    public static TimePicker mTimePicker;
    public static final String TIME = "time";
    public static final String EXTRA_TIME = "TIME_EXTRA";
    public static Calendar calendar = Calendar.getInstance();
    public static Date time;

    public static TimePickerFragment newInstance(Date date) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(TIME, date);
        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.setArguments(bundle);
        return timePickerFragment;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        time =(Date)getArguments().getSerializable(TIME);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        mTimePicker.setHour(calendar.get(Calendar.HOUR));
        mTimePicker.setMinute(calendar.get(Calendar.MINUTE));

        return new AlertDialog.Builder(getActivity())
                //.setView(v)
                .setTitle("Time picker")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        int hour = mTimePicker.getHour();
                        int min = mTimePicker.getMinute();
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(time);
                        calendar.set(Calendar.HOUR, hour);
                        calendar.set(Calendar.MINUTE,min);
                        Date time = calendar.getTime();
                        sendIntent(Activity.RESULT_OK,time);

                    }
                })
                .create();


    }

    private void sendIntent(int resultCode,Date time) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME,time);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,intent);

    }



}
