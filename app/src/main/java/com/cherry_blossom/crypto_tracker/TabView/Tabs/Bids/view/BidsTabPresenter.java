package com.cherry_blossom.crypto_tracker.TabView.Tabs.Bids.view;

import android.util.Log;

import com.cherry_blossom.crypto_tracker.Base.callbacks.NetworkCallback;
import com.cherry_blossom.crypto_tracker.Network.APIService;
import com.cherry_blossom.crypto_tracker.TabView.Tabs.Bids.model.Bid;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

/**
 * Created by matthew on 1/25/18.
 */

public class BidsTabPresenter implements BidsTabContract.Presenter{

    private BidsTabContract.View view;
    private APIService apiService;
    private String TAG = "BidsTabPresenter";

    public BidsTabPresenter(BidsTabContract.View view, APIService apiService){
        this.view = view;
        this.apiService = apiService;
    }

    @Override
    public void setupBids() {
        apiService.getBidsAsks(new NetworkCallback() {
            @Override
            public void networkSuccess(JsonObject value) {
                Log.d(TAG, "Got the Bids!");

                JsonArray jsonArray = value.get("bids").getAsJsonArray();

                ArrayList<Bid> askArrayList = new ArrayList<>();

                for(JsonElement askElement : jsonArray){
                    JsonArray askArray = askElement.getAsJsonArray();

                    Bid bid = new Bid();
                    bid.setValue(askArray.get(0).getAsString());
                    bid.setAmount(askArray.get(1).getAsString());

                    askArrayList.add(bid);
                }

                displayBids(askArrayList);
            }

            @Override
            public void networkError(Throwable e) {

            }
        });
    }

    private void displayBids(ArrayList<Bid> bidArrayList){
        view.displayBids(bidArrayList);
    }

    @Override
    public void destroy() {
        this.view = null;
    }


}
