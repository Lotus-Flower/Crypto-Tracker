package com.cherry_blossom.crypto_tracker.TabView.Dashboard;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cherry_blossom.crypto_tracker.Base.view.BaseView;
import com.cherry_blossom.crypto_tracker.R;

public class DashboardActivity extends AppCompatActivity implements BaseView{

    private TabLayout tabLayout;
    private DashboardPagerAdapter adapter;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);
        setupUI();
        setEventHandlers();
    }

    @Override
    public void setupUI() {
        //Set up the table!
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Graph"));
        tabLayout.addTab(tabLayout.newTab().setText("Live Order Book - Bid"));
        tabLayout.addTab(tabLayout.newTab().setText("Live Order Book - Ask"));
        tabLayout.addTab(tabLayout.newTab().setText("Alert"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = findViewById(R.id.pager);
        adapter = new DashboardPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        //Event Handling!
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public void setEventHandlers() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void setError() {

    }
}
