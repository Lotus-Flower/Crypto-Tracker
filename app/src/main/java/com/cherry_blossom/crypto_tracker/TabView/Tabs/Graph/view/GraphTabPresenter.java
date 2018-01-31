package com.cherry_blossom.crypto_tracker.TabView.Tabs.Graph.view;

import android.util.Log;

import com.cherry_blossom.crypto_tracker.Base.callbacks.NetworkCallbackArray;
import com.cherry_blossom.crypto_tracker.Network.APIService;
import com.cherry_blossom.crypto_tracker.TabView.Tabs.Graph.model.Point;
import com.github.mikephil.charting.data.Entry;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;

/**
 * Created by matthew on 1/25/18.
 */

public class GraphTabPresenter implements GraphTabContract.Presenter{

    private GraphTabContract.View view;
    private APIService apiService;
    private String TAG = "GraphTabPresenter";

    public GraphTabPresenter(GraphTabContract.View view, APIService apiService){
        this.view = view;
        this.apiService = apiService;
    }

    @Override
    public void setupGraph() {
        apiService.getPoints(new NetworkCallbackArray() {
            @Override
            public void networkSuccess(JsonArray value) {
                Log.d(TAG, "Got the points!");

                ArrayList<Point> pointArrayList = new ArrayList<>();
                for(JsonElement pointElement : value){
                    Gson gson = new Gson();
                    Point point = gson.fromJson(pointElement, Point.class);
                    pointArrayList.add(point);
                }

                getGraphData(pointArrayList);
            }

            @Override
            public void networkError(Throwable e) {

            }
        });
    }

    private void getGraphData(ArrayList<Point> pointArrayList){

        ArrayList<Entry> entries = new ArrayList<>();
        float xEntry;
        float yEntry;

        for(Point point : pointArrayList){
            xEntry = Float.parseFloat(point.getDate());
            yEntry = Float.parseFloat(point.getPrice());
            entries.add(new Entry(xEntry, yEntry));
        }

        if(!entries.isEmpty()){
            view.displayGraph(entries);
        }
    }

    @Override
    public void destroy() {
        this.view = null;
    }


}
