package com.cherry_blossom.crypto_tracker.TabView.Tabs.Graph.view;

import com.cherry_blossom.crypto_tracker.Base.view.BasePresenter;
import com.cherry_blossom.crypto_tracker.Base.view.BaseView;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

/**
 * Created by matthew on 1/25/18.
 */

public interface GraphTabContract {
    interface View extends BaseView{
        void displayGraph(ArrayList<Entry> entries);
    }

    interface Presenter extends BasePresenter{
        void setupGraph();
    }
}
