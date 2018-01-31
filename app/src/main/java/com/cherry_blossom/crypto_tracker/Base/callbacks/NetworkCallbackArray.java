package com.cherry_blossom.crypto_tracker.Base.callbacks;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Created by matthew on 1/30/18.
 */

public interface NetworkCallbackArray {
    void networkSuccess(JsonArray value);

    void networkError(Throwable e);
}
