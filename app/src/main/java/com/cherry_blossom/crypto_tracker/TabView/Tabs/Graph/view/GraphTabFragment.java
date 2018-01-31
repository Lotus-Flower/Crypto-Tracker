package com.cherry_blossom.crypto_tracker.TabView.Tabs.Graph.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cherry_blossom.crypto_tracker.Network.APIClient;
import com.cherry_blossom.crypto_tracker.Network.APIEndpointInterface;
import com.cherry_blossom.crypto_tracker.Network.APIService;
import com.cherry_blossom.crypto_tracker.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

/**
 * Created by matthew on 1/25/18.
 */

public class GraphTabFragment extends Fragment implements GraphTabContract.View {

    private View view;
    private GraphTabPresenter presenter;

    private LineChart chart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.graph_tab_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new GraphTabPresenter(this, new APIService(APIClient.getClient().create(APIEndpointInterface.class)));

        setupUI();
        setEventHandlers();
    }

    @Override
    public void setupUI() {
        chart = view.findViewById(R.id.chart);

        setupGraph();
    }

    private void setupGraph(){
        presenter.setupGraph();
    }

    @Override
    public void displayGraph(ArrayList<Entry> entries) {
        int color = ContextCompat.getColor(view.getContext(), R.color.darkGreen);

        //Graph Setup
        LineDataSet dataSet = new LineDataSet(entries, "Bitcoin Value");
        dataSet.setColor(color);
        dataSet.setCircleColor(color);
        LineData lineData = new LineData(dataSet);

        chart.setData(lineData);
        chart.setScaleXEnabled(true);
        chart.getXAxis().setValueFormatter(new AxisFormatter());

        Description description = new Description();
        description.setText("Value vs. Time");
        chart.setDescription(description);
        chart.animateX(3000);
        chart.invalidate();
    }

    @Override
    public void setEventHandlers() {

    }

    @Override
    public void setError() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }
}
