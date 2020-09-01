package com.ricknotes.gadsleaderboard.Network;

import com.ricknotes.gadsleaderboard.Model.TopLearner;
import com.ricknotes.gadsleaderboard.Model.TopSkillIQ;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiInterface {
    
    @GET("hours")
    Call<List<TopLearner>> getTopLearners();
    
    @GET("skilliq")
    Call<List<TopSkillIQ>> getTopSkillIQs();
    
    @FormUrlEncoded
    @POST
    Call<Void> submit(@Url String url,
                      @Field(" entry.1824927963")String email,
                      @Field(" entry.1877115667")String firstName,
                      @Field(" entry.2006916086")String lastName,
                      @Field("entry.284483984")String projectLink);
    
}
