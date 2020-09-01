package com.ricknotes.gadsleaderboard.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ricknotes.gadsleaderboard.Adapter.SkillIQAdapter;
import com.ricknotes.gadsleaderboard.Model.TopSkillIQ;
import com.ricknotes.gadsleaderboard.Network.ApiInterface;
import com.ricknotes.gadsleaderboard.Network.RetrofitBuilder;
import com.ricknotes.gadsleaderboard.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillIQLeaders extends Fragment {
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private ApiInterface mApi;
    private View mView;
    private List<TopSkillIQ> mList;
    
    
    public SkillIQLeaders() {
        // Required empty public constructor
    }
    
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_skill_i_q_leaders, container, false);
        
        mApi = RetrofitBuilder.buildApi(ApiInterface.class);
        
        mRecyclerView = mView.findViewById(R.id.fragment_skill_iq_recycler_view);
        mProgressBar = mView.findViewById(R.id.fragment_skill_iq_progressBar);
        mProgressBar.setVisibility(View.VISIBLE);
        
        getTopSkillIQ();
        
        
        return mView;
    }
    
    private void setUpRecyclerView() {
        SkillIQAdapter adapter = new SkillIQAdapter(mList, getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL, false);
        
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
    }
    
    private void getTopSkillIQ() {
        Call<List<TopSkillIQ>> call = mApi.getTopSkillIQs();
        call.enqueue(new Callback<List<TopSkillIQ>>() {
            @Override
            public void onResponse(Call<List<TopSkillIQ>> call, Response<List<TopSkillIQ>> response) {
                mProgressBar.setVisibility(View.GONE);
                if (response.isSuccessful()){
                    mList = response.body();
                    setUpRecyclerView();
                }
            }
    
            @Override
            public void onFailure(Call<List<TopSkillIQ>> call, Throwable t) {
                mRecyclerView.setVisibility(View.GONE);
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "An error occurred.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}