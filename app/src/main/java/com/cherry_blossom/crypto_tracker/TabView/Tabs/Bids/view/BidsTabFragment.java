package com.cherry_blossom.crypto_tracker.TabView.Tabs.Bids.view;

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
import com.cherry_blossom.crypto_tracker.TabView.Tabs.Bids.model.Bid;

import java.util.ArrayList;

/**
 * Created by matthew on 1/25/18.
 */

public class BidsTabFragment extends Fragment implements BidsTabContract.View{

    private View view;
    private BidsTabPresenter presenter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bids_tab_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new BidsTabPresenter(this, new APIService(APIClient.getClient().create(APIEndpointInterface.class)));

        setupUI();
        setEventHandlers();
    }

    @Override
    public void setupUI() {
        recyclerView = view.findViewById(R.id.recycler_view_bids);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);

        presenter.setupBids();
    }

    @Override
    public void setEventHandlers() {

    }

    @Override
    public void setError() {

    }

    @Override
    public void displayBids(ArrayList<Bid> bidArrayList) {
        BidsRecyclerAdapter adapter = new BidsRecyclerAdapter(bidArrayList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }


}
