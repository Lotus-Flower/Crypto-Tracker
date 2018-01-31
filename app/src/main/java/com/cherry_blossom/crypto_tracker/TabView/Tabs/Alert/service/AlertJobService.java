package com.cherry_blossom.crypto_tracker.TabView.Tabs.Alert.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.cherry_blossom.crypto_tracker.Base.callbacks.NetworkCallback;
import com.cherry_blossom.crypto_tracker.Network.APIClient;
import com.cherry_blossom.crypto_tracker.Network.APIEndpointInterface;
import com.cherry_blossom.crypto_tracker.Network.APIService;
import com.cherry_blossom.crypto_tracker.R;
import com.cherry_blossom.crypto_tracker.TabView.Tabs.Alert.model.CurrentValue;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Created by matthew on 1/26/18.
 */

public class AlertJobService extends JobService{

    private Double threshold;
    private NotificationManager notificationManager;

    @Override
    public boolean onStartJob(JobParameters job) {

        //Gets threshold from parameters
        threshold = job.getExtras().getDouble("threshold");

        //Sets up API Client
        APIService apiService;
        apiService = new APIService(APIClient.getClient().create(APIEndpointInterface.class));

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        apiService.getValue(new NetworkCallback() {
            @Override
            public void networkSuccess(JsonObject value) {
                Gson gson = new Gson();
                CurrentValue currentValue = gson.fromJson(value, CurrentValue.class);

                if(threshold < Double.parseDouble(currentValue.getLast())){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        oreoNotify();
                    }else{
                        compatNotify();
                    }
                }
            }

            @Override
            public void networkError(Throwable e) {

            }
        });

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }

    private void compatNotify(){
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Price Alert")
                        .setContentText("The BTC price has fallen below the specified threshold.")
                        .setChannelId("channel1");

        notificationManager.notify(1, builder.build());
    }

    private void oreoNotify(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel("channel1", "channel1", NotificationManager.IMPORTANCE_HIGH);

            // Create a notification and set the notification channel.
            Notification notification = new Notification.Builder(getApplicationContext(), "channel1")
                    .setContentTitle("Price Alert")
                    .setContentText("The BTC price has fallen below the specified threshold.")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setChannelId("channel1")
                    .build();

            notificationManager.createNotificationChannel(mChannel);
            notificationManager.notify(1, notification);
        }
    }
}
