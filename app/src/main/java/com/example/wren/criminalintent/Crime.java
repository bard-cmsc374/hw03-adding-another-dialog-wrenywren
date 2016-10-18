package com.example.wren.criminalintent;

import android.text.format.Time;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Wren on 9/12/2016.
 */
public class Crime {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    //private String mSuspect;




    public Crime() {
        // Generate unique identifier
       this(UUID.randomUUID());
    }

    public Crime(UUID id) {
        mId = id;
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }
    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String title) {
        mTitle = title;
    }
    public Date getDate(){
        return mDate;
    }
    public void setDate(Date date) {
        mDate = date;
    }
    public boolean isSolved(){
        return mSolved;
    }
    public void setSolved(boolean solved){
        mSolved = solved;
    }
    //public String getSuspect() { return mSuspect; }
    //public void setSuspect(String suspect) { mSuspect = suspect; }
    public String getPhotoFilename() { return "IMG_" + getId().toString() + ".jpg";}

}
