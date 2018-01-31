package com.cherry_blossom.crypto_tracker.TabView.Tabs.Alert.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.cherry_blossom.crypto_tracker.Network.APIClient;
import com.cherry_blossom.crypto_tracker.Network.APIEndpointInterface;
import com.cherry_blossom.crypto_tracker.Network.APIService;
import com.cherry_blossom.crypto_tracker.R;
import com.cherry_blossom.crypto_tracker.Storage.SharedPreferencesService;
import com.cherry_blossom.crypto_tracker.TabView.Tabs.Alert.model.CurrentValue;
import com.cherry_blossom.crypto_tracker.TabView.Tabs.Alert.service.AlertJobService;
import com.cherry_blossom.crypto_tracker.TabView.Tabs.Graph.view.GraphTabPresenter;
import com.cherry_blossom.crypto_tracker.databinding.AlertTabFragmentBinding;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by matthew on 1/25/18.
 */

public class AlertTabFragment extends Fragment implements AlertTabContract.View{

    SimpleDateFormat dateFormat = new SimpleDateFormat("H:mm a", Locale.US);

    private Button button;
    private CheckBox checkBox;
    EditText editText;

    private AlertTabFragmentBinding binding;

    View view;
    private AlertTabPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.alert_tab_fragment, container, false);
        view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new AlertTabPresenter(this, new APIService(APIClient.getClient().create(APIEndpointInterface.class)), new SharedPreferencesService(getActivity().getPreferences(Context.MODE_PRIVATE)), new FirebaseJobDispatcher(new GooglePlayDriver(getContext())));

        setupUI();
        setEventHandlers();
    }

    @Override
    public void setupUI() {
        button = view.findViewById(R.id.button_confirm);
        checkBox = view.findViewById(R.id.check_box_enable);
        editText = view.findViewById(R.id.edit_text_threshold);

        presenter.getPreferences();
        presenter.setupValueInfo();
    }

    @Override
    public void setEventHandlers() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked() && !editText.getText().toString().isEmpty()){
                    presenter.setPreferences(editText.getText().toString(), true);
                    presenter.startAlertJob(Double.parseDouble(editText.getText().toString()));
                } else{
                    presenter.setPreferences("", false);
                    presenter.stopAlertJob();
                }
            }
        });
    }

    @Override
    public void setError() {

    }

    @Override
    public void displayPreferences(HashMap<String, String> hashMap) {
        editText.setText(hashMap.get("threshold"));
        checkBox.setChecked(Boolean.parseBoolean(hashMap.get("check")));
    }

    @Override
    public void displayValueInfo(CurrentValue currentValue) {
        Long epochValue = (Long.parseLong(currentValue.getTimestamp()));
        String dateTime = dateFormat.format(new Date(epochValue * 1000));
        DecimalFormat decimalFormat = new DecimalFormat("0.0##");

        currentValue.setTimestamp(dateTime);

        currentValue.setVolume(decimalFormat.format(Double.parseDouble(currentValue.getVolume())));

        currentValue.setHigh(getString(R.string.value_format, currentValue.getHigh()));
        currentValue.setLast(getString(R.string.value_format, currentValue.getLast()));
        currentValue.setBid(getString(R.string.value_format, currentValue.getBid()));
        currentValue.setVwap(getString(R.string.value_format, currentValue.getVwap()));
        currentValue.setLow(getString(R.string.value_format, currentValue.getLow()));
        currentValue.setAsk(getString(R.string.value_format, currentValue.getAsk()));
        currentValue.setOpen(getString(R.string.value_format, currentValue.getOpen()));

        binding.setCurrentValue(currentValue);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }


}
