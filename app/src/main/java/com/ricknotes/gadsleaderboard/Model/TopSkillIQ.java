package com.ricknotes.gadsleaderboard.Model;

import com.google.gson.annotations.SerializedName;

public class TopSkillIQ extends Learner{
    @SerializedName("score")
    private int mIq;
    public TopSkillIQ(String name, String country, String badgeUrl, int iq) {
        super(name, country, badgeUrl);
        this.mIq = iq;
    }
    
    public int getIq() {
        return mIq;
    }
    
    public void setIq(int iq) {
        mIq = iq;
    }
}
