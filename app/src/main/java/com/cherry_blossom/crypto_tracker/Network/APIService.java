package com.cherry_blossom.crypto_tracker.Network;

import android.util.Log;

import com.cherry_blossom.crypto_tracker.Base.callbacks.NetworkCallback;
import com.cherry_blossom.crypto_tracker.Base.callbacks.NetworkCallbackArray;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by matthew on 1/26/18.
 */

public class APIService {
    //Logging Tag
    private String TAG = "APIService";
    private APIEndpointInterface apiEndpointInterface;

    public APIService(APIEndpointInterface apiEndpointInterface) {
        this.apiEndpointInterface = apiEndpointInterface;
    }

    public void getPoints(final NetworkCallbackArray callback){
        apiEndpointInterface.getPoints()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonArray>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonArray jsonArray) {
                        Log.d(TAG, "Got the points!");
                        callback.networkSuccess(jsonArray);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, e.toString());
                        callback.networkError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getBidsAsks(final NetworkCallback callback){
        apiEndpointInterface.getBidAsk()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonObject value) {
                        Log.d(TAG, "Got bids/asks!");
                        callback.networkSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, e.toString());
                        callback.networkError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getValue(final NetworkCallback callback){
        apiEndpointInterface.getValue()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonObject value) {
                        Log.d(TAG, "Got the Value!");
                        callback.networkSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, e.toString());
                        callback.networkError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
