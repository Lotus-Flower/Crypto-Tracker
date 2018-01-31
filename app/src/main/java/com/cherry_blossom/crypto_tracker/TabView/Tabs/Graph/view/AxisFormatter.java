package com.cherry_blossom.crypto_tracker.TabView.Tabs.Graph.view;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by matthew on 1/26/18.
 */

public class AxisFormatter implements IAxisValueFormatter {

    //Puts epoch data into lovely date time format
    private SimpleDateFormat dateFormat = new SimpleDateFormat("H:mm a", Locale.US);

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        long epochValue = (long) (value * 1000);
        return dateFormat.format(new Date(epochValue));
    }
}
