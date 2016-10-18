package com.example.wren.criminalintent;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.ButtonBarLayout;
import android.widget.DatePicker;
import android.widget.TimePicker;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Wren on 10/17/2016.
 */

@RunWith(AndroidJUnit4.class)
public class TestApplication {

    @Rule
    public ActivityTestRule<CrimeListActivity> mCrimeActivityActivityTestRule =
            new ActivityTestRule<CrimeListActivity>(CrimeListActivity.class);

    Activity activity = mCrimeActivityActivityTestRule.getActivity();


    @Test
    public void selectDateandTime() {

        //Date we want
        int year = 1995;
        int month = 4;
        int day = 8;

        //Time we want
        int hour = 9;
        int minutes = 30;

        // Perform a click on first crime in the RecyclerView
        onView(withId(R.id.crime_recycler_view))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition(0, click()));

        // Click in the mDateButton
        onView(withId(R.id.crime_date)).perform(click());

        // Select date and time
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, month + 1, day));
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hour, minutes));
        onView(withId(android.R.id.button1)).perform(click());

        // Go back to list of crimes in RecyclerView
        Espresso.pressBack();

        // Rotates Phone Landscape
        //activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Rotates Phone Portrait
        //activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // returns to first crime
        onView(withId(R.id.crime_recycler_view))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition(0, click()));


    }


}
