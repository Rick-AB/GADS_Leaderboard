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
import com.ricknotes.gadsleaderboard.Model.TopSkillIQ;
import com.ricknotes.gadsleaderboard.R;

import java.util.List;

public class SkillIQAdapter extends RecyclerView.Adapter<SkillIQAdapter.SkillIQViewHolder>{
    
    private List<TopSkillIQ> mIQS;
    private Context mContext;
    
    public SkillIQAdapter(List<TopSkillIQ> IQS, Context context) {
        mIQS = IQS;
        mContext = context;
    }
    
    @NonNull
    @Override
    public SkillIQViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.learning_leaders_items, parent
                , false);
        return new SkillIQViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull SkillIQViewHolder holder, int position) {
        TopSkillIQ skillIQ = mIQS.get(position);
        
        String details = skillIQ.getIq() + " skill IQ Score, " + skillIQ.getCountry();
        
        holder.mName.setText(skillIQ.getName());
        holder.mDetails.setText(details);
    
        Glide.with(mContext)
                .load(skillIQ.getBadgeUrl())
                .into(holder.mBadge);
    }
    
    @Override
    public int getItemCount() {
        return mIQS.size();
    }
    
    static class SkillIQViewHolder extends RecyclerView.ViewHolder{
        private TextView mName, mDetails;
        private ImageView mBadge;
        public SkillIQViewHolder(@NonNull View itemView) {
            super(itemView);
    
            mName = itemView.findViewById(R.id.home_item_name);
            mDetails = itemView.findViewById(R.id.home_item_details);
            mBadge = itemView.findViewById(R.id.home_item_badge);
        }
    }
    
}
