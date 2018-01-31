package com.cherry_blossom.crypto_tracker.TabView.Tabs.Asks.view;

import android.util.Log;

import com.cherry_blossom.crypto_tracker.Base.callbacks.NetworkCallback;
import com.cherry_blossom.crypto_tracker.Network.APIService;
import com.cherry_blossom.crypto_tracker.TabView.Tabs.Asks.model.Ask;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

/**
 * Created by matthew on 1/25/18.
 */

public class AsksTabPresenter implements AsksTabContract.Presenter{

    private AsksTabContract.View view;
    private APIService apiService;
    private String TAG = "AsksTabPresenter";

    public AsksTabPresenter(AsksTabContract.View view, APIService apiService){
        this.view = view;
        this.apiService = apiService;
    }

    @Override
    public void setupAsks() {
        apiService.getBidsAsks(new NetworkCallback() {
            @Override
            public void networkSuccess(JsonObject value) {
                Log.d(TAG, "Got the Asks!");

                JsonArray jsonArray = value.get("asks").getAsJsonArray();

                ArrayList<Ask> askArrayList = new ArrayList<>();

                for(JsonElement askElement : jsonArray){
                    JsonArray askArray = askElement.getAsJsonArray();

                    Ask ask = new Ask();
                    ask.setValue(askArray.get(0).getAsString());
                    ask.setAmount(askArray.get(1).getAsString());

                    askArrayList.add(ask);
                }

                displayAsks(askArrayList);
            }

            @Override
            public void networkError(Throwable e) {

            }
        });
    }

    private void displayAsks(ArrayList<Ask> askArrayList){
        view.displayAsks(askArrayList);
    }

    @Override
    public void destroy() {
        this.view = null;
    }

}
