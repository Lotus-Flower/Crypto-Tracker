package com.cherry_blossom.crypto_tracker.Base.callbacks;

import com.google.gson.JsonObject;

import io.reactivex.Observable;

/**
 * Created by matthew on 1/26/18.
 */

public interface NetworkCallback {
    void networkSuccess(JsonObject value);

    void networkError(Throwable e);
}
