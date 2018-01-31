package com.cherry_blossom.crypto_tracker.TabView.Dashboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.cherry_blossom.crypto_tracker.TabView.Tabs.Alert.view.AlertTabFragment;
import com.cherry_blossom.crypto_tracker.TabView.Tabs.Asks.view.AsksTabFragment;
import com.cherry_blossom.crypto_tracker.TabView.Tabs.Bids.view.BidsTabFragment;
import com.cherry_blossom.crypto_tracker.TabView.Tabs.Graph.view.GraphTabFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by matthew on 1/25/18.
 */

public class DashboardPagerAdapter extends FragmentStatePagerAdapter {
    static final int GRAPH = 0;
    static final int BIDS = 1;
    static final int ASKS = 2;
    static final int INPUT = 3;

    int mNumOfTabs;

    //Constructor
    public DashboardPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        //Determines what tab we're on, if data exists for that tab, pass it as parameters
        switch (position) {
            case GRAPH:
                Log.d("MMMM", "graph");
                GraphTabFragment graphTabFragment = new GraphTabFragment();
                return graphTabFragment;
            case BIDS:
                Log.d("MMMM", "bids");
                BidsTabFragment bidsTabFragment = new BidsTabFragment();
                return bidsTabFragment;
            case ASKS:
                Log.d("MMMM", "asks");
                AsksTabFragment asksTabFragment = new AsksTabFragment();
                return asksTabFragment;
            case INPUT:
                Log.d("MMMM", "alert");
                AlertTabFragment alertTabFragment = new AlertTabFragment();
                return alertTabFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
