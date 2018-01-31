package com.cherry_blossom.crypto_tracker.TabView.Tabs.Bids.view;

import com.cherry_blossom.crypto_tracker.Base.view.BasePresenter;
import com.cherry_blossom.crypto_tracker.Base.view.BaseView;
import com.cherry_blossom.crypto_tracker.TabView.Tabs.Bids.model.Bid;

import java.util.ArrayList;

/**
 * Created by matthew on 1/25/18.
 */

public interface BidsTabContract {
    interface View extends BaseView {
        void displayBids(ArrayList<Bid> bidArrayList);
    }

    interface Presenter extends BasePresenter {
        void setupBids();
    }
}
