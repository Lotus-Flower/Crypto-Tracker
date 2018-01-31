package com.cherry_blossom.crypto_tracker.Base.callbacks;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by matthew on 1/31/18.
 */

public interface StorageCallback {
    void onStorageSuccess(HashMap<String, String> hashMap);

    void onStorageFailure();
}
