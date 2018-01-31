package com.cherry_blossom.crypto_tracker.Storage;

import android.content.SharedPreferences;
import android.util.Log;

import com.cherry_blossom.crypto_tracker.Base.callbacks.StorageCallback;

import java.util.HashMap;

/**
 * Created by matthew on 1/31/18.
 */

public class SharedPreferencesService {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String TAG = "TagSharedPrefServ";

    public SharedPreferencesService(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
    }

    public void getAlertPreferences(StorageCallback callback){

        Log.d(TAG, "Getting alert preferences!");

        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("threshold", sharedPreferences.getString("threshold", ""));
        hashMap.put("check", Boolean.toString(sharedPreferences.getBoolean("check", false)));

        callback.onStorageSuccess(hashMap);
    }

    public void setAlertPreferences(String threshold, Boolean check, StorageCallback callback){
        Log.d(TAG, "Storing alert preferences!");

        editor.putString("threshold", threshold);
        editor.putBoolean("check", check);
        editor.apply();
    }
}
