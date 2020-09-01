package com.ricknotes.gadsleaderboard.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ricknotes.gadsleaderboard.Model.TopLearner;
import com.ricknotes.gadsleaderboard.R;

import java.util.List;

public class TopLearnersAdapter extends RecyclerView.Adapter<TopLearnersAdapter.TopLearnersViewHolder>{
    private List<TopLearner> mLearners;
    private Context mContext;
    
    public TopLearnersAdapter(List<TopLearner> learners, Context context) {
        mLearners = learners;
        mContext = context;
    }
    
    @NonNull
    @Override
    public TopLearnersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.learning_leaders_items, parent
                , false);
        return new TopLearnersViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull TopLearnersViewHolder holder, int position) {
        TopLearner learner = mLearners.get(position);
        
        String details = learner.getHours() + " learning hours, " + learner.getCountry() + ".";
        
        holder.mName.setText(learner.getName());
        holder.mDetails.setText(details);
    
        Glide.with(mContext)
                .load(learner.getBadgeUrl())
                .into(holder.mBadge);
    }
    
    @Override
    public int getItemCount() {
        return mLearners.size();
    }
    
    static class TopLearnersViewHolder extends RecyclerView.ViewHolder{
        private TextView mName, mDetails;
        private ImageView mBadge;
    
        public TopLearnersViewHolder(@NonNull View itemView) {
            super(itemView);
            
            mName = itemView.findViewById(R.id.home_item_name);
            mDetails = itemView.findViewById(R.id.home_item_details);
            mBadge = itemView.findViewById(R.id.home_item_badge);
        }
    }
    
}
