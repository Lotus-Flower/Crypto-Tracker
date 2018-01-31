package com.cherry_blossom.crypto_tracker.TabView.Tabs.Asks.view;

import com.cherry_blossom.crypto_tracker.Base.view.BasePresenter;
import com.cherry_blossom.crypto_tracker.Base.view.BaseView;
import com.cherry_blossom.crypto_tracker.TabView.Tabs.Asks.model.Ask;

import java.util.ArrayList;

/**
 * Created by matthew on 1/25/18.
 */

public interface AsksTabContract {
    interface View extends BaseView {
        void displayAsks(ArrayList<Ask> askArrayList);
    }

    interface Presenter extends BasePresenter {
        void setupAsks();
    }
}
