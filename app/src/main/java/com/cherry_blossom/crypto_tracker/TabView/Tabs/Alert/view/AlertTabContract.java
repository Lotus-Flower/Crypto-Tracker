package com.cherry_blossom.crypto_tracker.TabView.Tabs.Alert.view;

import com.cherry_blossom.crypto_tracker.Base.view.BasePresenter;
import com.cherry_blossom.crypto_tracker.Base.view.BaseView;
import com.cherry_blossom.crypto_tracker.TabView.Tabs.Alert.model.CurrentValue;

import java.util.HashMap;

/**
 * Created by matthew on 1/25/18.
 */

public interface AlertTabContract {
    interface View extends BaseView {
        void displayPreferences(HashMap<String, String> hashMap);

        void displayValueInfo(CurrentValue currentValue);
    }

    interface Presenter extends BasePresenter {

        void getPreferences();

        void setPreferences(String threshold, Boolean check);

        void setupValueInfo();

        void startAlertJob(Double threshold);

        void stopAlertJob();
    }
}
