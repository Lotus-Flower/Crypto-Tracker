package com.cherry_blossom.crypto_tracker.Network;

import com.cherry_blossom.crypto_tracker.TabView.Tabs.Alert.model.CurrentValue;
import com.cherry_blossom.crypto_tracker.TabView.Tabs.Graph.model.Point;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by matthew on 1/26/18.
 */

public interface APIEndpointInterface {
    //Uses hour parameter for points
    @GET("transactions/btcusd/?time=hour")
    Observable<JsonArray> getPoints();

    @GET("order_book/btcusd/")
    Observable<JsonObject> getBidAsk();

    @GET("ticker_hour/btcusd/")
    Observable<JsonObject> getValue();
}
