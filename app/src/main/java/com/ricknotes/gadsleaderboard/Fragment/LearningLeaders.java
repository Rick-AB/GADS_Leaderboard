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

import com.ricknotes.gadsleaderboard.Adapter.TopLearnersAdapter;
import com.ricknotes.gadsleaderboard.Model.TopLearner;
import com.ricknotes.gadsleaderboard.Network.ApiInterface;
import com.ricknotes.gadsleaderboard.Network.RetrofitBuilder;
import com.ricknotes.gadsleaderboard.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningLeaders extends Fragment {
    
    private List<TopLearner> mList;
    private View mView;
    private ApiInterface mApi;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    
    public LearningLeaders() {
        // Required empty public constructor
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_learning_leaders, container, false);
        
        mApi = RetrofitBuilder.buildApi(ApiInterface.class);
    
        mRecyclerView = mView.findViewById(R.id.fragment_learning_leaders_recycler_view);
        mProgressBar = mView.findViewById(R.id.fragment_learning_leaders_progressBar);
        mProgressBar.setVisibility(View.VISIBLE);
        
        getTopLearners();
        
        
        return mView;
    }
    
    private void setUpRecyclerView() {
        TopLearnersAdapter adapter = new TopLearnersAdapter(mList, getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL, false);
        
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
    }
    
    private void getTopLearners() {
        Call<List<TopLearner>> call = mApi.getTopLearners();
        call.enqueue(new Callback<List<TopLearner>>() {
            @Override
            public void onResponse(Call<List<TopLearner>> call, Response<List<TopLearner>> response) {
                mProgressBar.setVisibility(View.GONE);
                if (response.isSuccessful()){
                    mList = response.body();
                    setUpRecyclerView();
                }
            }
    
            @Override
            public void onFailure(Call<List<TopLearner>> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.GONE);
                Toast.makeText(getContext(), "An error occurred.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    
}