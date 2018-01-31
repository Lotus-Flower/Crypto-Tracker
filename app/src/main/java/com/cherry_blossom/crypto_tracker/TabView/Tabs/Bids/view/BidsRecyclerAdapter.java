package com.cherry_blossom.crypto_tracker.TabView.Tabs.Bids.view;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cherry_blossom.crypto_tracker.R;
import com.cherry_blossom.crypto_tracker.TabView.Tabs.Asks.model.Ask;
import com.cherry_blossom.crypto_tracker.TabView.Tabs.Asks.view.AsksRecyclerAdapter;
import com.cherry_blossom.crypto_tracker.TabView.Tabs.Bids.model.Bid;

import java.util.ArrayList;

/**
 * Created by matthew on 1/26/18.
 */

public class BidsRecyclerAdapter extends RecyclerView.Adapter<BidsRecyclerAdapter.TableViewHolder>{

    private ArrayList<Bid> bidsArrayList;

    public BidsRecyclerAdapter(ArrayList<Bid> bidsArrayList){
        this.bidsArrayList = bidsArrayList;
    }

    @Override
    public TableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new TableViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TableViewHolder holder, int position) {
        holder.ValueTextView.setText(bidsArrayList.get(position).getValue());
        holder.AmountTextView.setText(bidsArrayList.get(position).getAmount());
    }

    @Override
    public int getItemCount() {
        return bidsArrayList.size();
    }

    //Constructs the ViewHolder with CardViews
    public class TableViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView ValueTextView;
        TextView AmountTextView;

        TableViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.card_view);
            ValueTextView = view.findViewById(R.id.value_text_view);
            AmountTextView = view.findViewById(R.id.amount_text_view);
        }
    }
}
