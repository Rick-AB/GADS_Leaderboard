package com.ricknotes.gadsleaderboard.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.tabs.TabLayout;
import com.ricknotes.gadsleaderboard.Fragment.LearningLeaders;
import com.ricknotes.gadsleaderboard.Fragment.SkillIQLeaders;
import com.ricknotes.gadsleaderboard.R;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements View.OnClickListener{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleMarginStart(40);
        toolbar.setTitleMarginTop(26);
        toolbar.setTitle("LEADERBOARD");
        setSupportActionBar(toolbar);
        
        initView();
    }
    
    private void initView() {
        Button button = findViewById(R.id.home_submit_btn);
        ViewPager viewPager = findViewById(R.id.home_view_pager);
        TabLayout tabLayout = findViewById(R.id.home_tab_layout);
        
        LearningLeaders learningLeaders = new LearningLeaders();
        SkillIQLeaders skillIQLeaders = new SkillIQLeaders();
        
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        
        viewPagerAdapter.addFragment(learningLeaders, "Learning Leaders");
        viewPagerAdapter.addFragment(skillIQLeaders, "Skill IQ Leaders");
        
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        
        button.setOnClickListener(this);
        
    }
    
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.home_submit_btn){
            Intent intent = new Intent(this, Submit.class);
            startActivity(intent);
            Animatoo.animateSwipeLeft(this);
        }
        
    }
    
    static class MyViewPagerAdapter extends FragmentPagerAdapter{
        List<Fragment> mFragments;
        List<String> mTitles;
    
        public MyViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
            mFragments = new ArrayList<>();
            mTitles = new ArrayList<>();
        }
    
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    
        @Override
        public int getCount() {
            return mFragments.size();
        }
        
        private void addFragment(Fragment fragment, String title){
            mFragments.add(fragment);
            mTitles.add(title);
        }
    
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }
    }
}