package com.ricknotes.gadsleaderboard.Model;

import com.google.gson.annotations.SerializedName;

public class Learner {
    @SerializedName("name")
    private String mName;
    @SerializedName("country")
    private String mCountry;
    @SerializedName("badgeUrl")
    private String mBadgeUrl;
    
    public Learner(String name, String country, String badgeUrl){
        this.mName = name;
        this.mCountry = country;
        this.mBadgeUrl = badgeUrl;
    }
    
    public String getName() {
        return mName;
    }
    
    public void setName(String name) {
        mName = name;
    }
    
    public String getCountry() {
        return mCountry;
    }
    
    public void setCountry(String country) {
        mCountry = country;
    }
    
    public String getBadgeUrl() {
        return mBadgeUrl;
    }
    
    public void setBadgeUrl(String badgeUrl) {
        mBadgeUrl = badgeUrl;
    }
}
