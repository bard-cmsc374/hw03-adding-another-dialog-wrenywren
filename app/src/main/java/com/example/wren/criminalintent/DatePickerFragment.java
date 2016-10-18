package com.example.wren.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import static com.example.wren.criminalintent.TimePickerFragment.EXTRA_TIME;
import static com.example.wren.criminalintent.TimePickerFragment.TIME;
import static com.example.wren.criminalintent.TimePickerFragment.mTimePicker;
import static com.example.wren.criminalintent.TimePickerFragment.time;


/**
 * Created by Wren on 9/27/2016.
 */

public class DatePickerFragment extends DialogFragment {

    public static final String EXTRA_DATE = "com.bignerdranch.android.criminalintent.date";
    private static final String ARG_DATE = "date";
    private DatePicker mDatePicker;


    public static DatePickerFragment newInstance(Date date){
        Bundle args = new Bundle();
        Bundle bundle = new Bundle();
        args.putSerializable(ARG_DATE, date);
        bundle.putSerializable(TIME, date); //Time
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        time =(Date)getArguments().getSerializable(TIME); //Time
        Date date = (Date) getArguments().getSerializable(ARG_DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);
        mDatePicker = (DatePicker) v.findViewById(R.id.dialog_date_date_picker);
        mDatePicker.init(year, month, day, null);


        mTimePicker = (TimePicker)v.findViewById(R.id.dialog_date_time_picker); //View for Time Picker


        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener(){
                            @Override
                            public  void onClick(DialogInterface dialog, int which){

             ///////////////// HOUR and MINUTE from TIMEPICKER FRAGMENT ////////////
                                int hour = mTimePicker.getHour();
                                int min = mTimePicker.getMinute();
                                Calendar calendar = Calendar.getInstance();
                                TimePickerFragment.calendar.set(Calendar.HOUR, hour);
                                TimePickerFragment.calendar.set(Calendar.MINUTE,min);
                                Date time = calendar.getTime();
                                sendResult(Activity.RESULT_OK,time);
            ///////////////////////////////////////////////////////////////////

                                int year = mDatePicker.getYear();
                                int month = mDatePicker.getMonth();
                                int day = mDatePicker.getDayOfMonth();

                                // Added hour and min to G.Calendar
                                Date date = new GregorianCalendar(year, month, day,hour,min).getTime();
                                sendResult(Activity.RESULT_OK, date);
                            }
                        })
                                .create();


    }

    private void sendResult(int resultCode, Date date){
        if (getTargetFragment() == null){
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);
        intent.putExtra(EXTRA_TIME,time);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }



}