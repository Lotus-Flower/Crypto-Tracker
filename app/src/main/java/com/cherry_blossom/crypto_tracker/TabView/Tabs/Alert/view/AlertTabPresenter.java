package com.cherry_blossom.crypto_tracker.TabView.Tabs.Alert.view;

import android.os.Bundle;
import android.util.Log;

import com.cherry_blossom.crypto_tracker.Base.callbacks.NetworkCallback;
import com.cherry_blossom.crypto_tracker.Base.callbacks.StorageCallback;
import com.cherry_blossom.crypto_tracker.Network.APIService;
import com.cherry_blossom.crypto_tracker.Storage.SharedPreferencesService;
import com.cherry_blossom.crypto_tracker.TabView.Tabs.Alert.model.CurrentValue;
import com.cherry_blossom.crypto_tracker.TabView.Tabs.Alert.service.AlertJobService;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;

/**
 * Created by matthew on 1/25/18.
 */

public class AlertTabPresenter implements AlertTabContract.Presenter{

    private AlertTabContract.View view;
    private APIService apiService;
    private SharedPreferencesService sharedPreferencesService;
    private String TAG = "AlertTabPresenter";
    private FirebaseJobDispatcher jobDispatcher;

    public AlertTabPresenter(AlertTabContract.View view, APIService apiService, SharedPreferencesService sharedPreferencesService, FirebaseJobDispatcher jobDispatcher){
        this.view = view;
        this.apiService = apiService;
        this.sharedPreferencesService = sharedPreferencesService;
    }

    @Override
    public void getPreferences() {
        sharedPreferencesService.getAlertPreferences(new StorageCallback() {
            @Override
            public void onStorageSuccess(HashMap<String, String> hashMap) {
                view.displayPreferences(hashMap);
            }

            @Override
            public void onStorageFailure() {

            }
        });
    }

    @Override
    public void setPreferences(String threshold, Boolean check) {
        sharedPreferencesService.setAlertPreferences(threshold, check, new StorageCallback() {
            @Override
            public void onStorageSuccess(HashMap<String, String> hashMap) {

            }

            @Override
            public void onStorageFailure() {

            }
        });
    }

    @Override
    public void setupValueInfo() {
        apiService.getValue(new NetworkCallback() {
            @Override
            public void networkSuccess(JsonObject value) {
                Gson gson = new Gson();
                CurrentValue currentValue = gson.fromJson(value, CurrentValue.class);
                displayValueInfo(currentValue);
            }

            @Override
            public void networkError(Throwable e) {

            }
        });
    }

    private void displayValueInfo(CurrentValue currentValue){
        view.displayValueInfo(currentValue);
    }

    @Override
    public void startAlertJob(Double threshold) {
        stopAlertJob();

        Bundle bundle = new Bundle();
        bundle.putDouble("threshold", threshold);

        Job job = createJob(jobDispatcher, bundle);
        jobDispatcher.mustSchedule(job);
    }

    @Override
    public void stopAlertJob() {
        jobDispatcher.cancelAll();
    }

    public static Job createJob(FirebaseJobDispatcher dispatcher, Bundle bundle){
        //Create the job to alert users every hour
        return dispatcher.newJobBuilder()
                .setLifetime(Lifetime.FOREVER)
                .setService(AlertJobService.class)
                .setTag("Alert Job")
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(3599, 3600))
                .setExtras(bundle)
                .build();
    }

    @Override
    public void destroy() {
        this.view = null;
    }


}
