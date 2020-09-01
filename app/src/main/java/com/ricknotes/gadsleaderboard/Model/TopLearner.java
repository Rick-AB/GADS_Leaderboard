package com.ricknotes.gadsleaderboard.Model;

import com.google.gson.annotations.SerializedName;

public class TopLearner extends Learner {
    @SerializedName("hours")
    private int mHours;
    public TopLearner(String name, String country, String badgeUrl, int hours) {
        super(name, country, badgeUrl);
        this.mHours = hours;
        
    }
    
    public int getHours() {
        return mHours;
    }
    
    public void setHours(int hours) {
        mHours = hours;
    }
}
