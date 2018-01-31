package com.cherry_blossom.crypto_tracker.TabView.Tabs.Asks.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cherry_blossom.crypto_tracker.Network.APIClient;
import com.cherry_blossom.crypto_tracker.Network.APIEndpointInterface;
import com.cherry_blossom.crypto_tracker.Network.APIService;
import com.cherry_blossom.crypto_tracker.R;
import com.cherry_blossom.crypto_tracker.TabView.Tabs.Asks.model.Ask;

import java.util.ArrayList;

/**
 * Created by matthew on 1/25/18.
 */

public class AsksTabFragment extends Fragment implements AsksTabContract.View{

    private View view;
    private AsksTabPresenter presenter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.asks_tab_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new AsksTabPresenter(this, new APIService(APIClient.getClient().create(APIEndpointInterface.class)));

        setupUI();
        setEventHandlers();
    }

    @Override
    public void setupUI() {
        recyclerView = view.findViewById(R.id.recycler_view_asks);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);

        presenter.setupAsks();
    }

    @Override
    public void setEventHandlers() {

    }

    @Override
    public void setError() {

    }

    @Override
    public void displayAsks(ArrayList<Ask> askArrayList) {
        AsksRecyclerAdapter adapter = new AsksRecyclerAdapter(askArrayList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }
}
