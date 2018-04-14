//
// Copyright 2016 by Grindr LLC,
// All rights reserved.
//
// This software is confidential and proprietary information of
// Grindr LLC ("Confidential Information").
// You shall not disclose such Confidential Information and shall use
// it only in accordance with the terms of the license agreement
// you entered into with Grindr LLC.
//
package com.screener.ad.screener.network;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ScreenerCallback<T> implements Callback<T> {

    private static final String TAG = "ScreenerCallback";
    // TODO: There are two instances where this may fail in the future
    // 1. On device rotation since isFinishing is false when rotated (which we don't support right now)
    // 2. On Android killing our Activity
    // The reason is that onFinishing() will not be set to true, and then we may execute unsafe code.
    // There are several ways we could solve this:
    // A. If our minimum API has risen to 17, we could use isDestroyed()
    // B. BaseGrindrActivity could keep track of a boolean triggered by onDestroy
    // C. We can use isDestroyed() only for sdk 17+ and leave neglect sdk 15 and 16.
    Activity activity;
    //Alternately, if we pass a fragment, we can also check if getActivity returns null
    Fragment fragment;

    public ScreenerCallback(Activity activity) {
        if (activity == null) {
            throw new RuntimeException("Activity should not be null. If this is a fragment, make sure to instantiate callback in onAttach. Otherwise, consider using the regular Callback class.");
        }
        this.activity = activity;
    }

    public ScreenerCallback(Fragment fragment) {
        if (fragment == null) {
            throw new RuntimeException("Fragment should not be null.");
        }
        this.fragment = fragment;

    }

    private Activity getActivity() {
        if (fragment != null) {
            return fragment.getActivity();
        }
        return activity;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            success(response.body(), response);
        } else {
            failure(null);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        failure(RetrofitError.unexpectedError("", t));
    }

    // These are final to enforce usage
    // if this class is insufficient, use Callback preferentially instead of removing final.
    public final void success(T t, Response response) {
        handleSuccess(t, response);
        if (getActivity() != null && !getActivity().isFinishing()) {
            handleSafeSuccess(t, response);
        }

        if (response != null) {
            Log.d(TAG, "SUCCESS url[" + response.raw().request().url() + "]");
        }
    }

    public final void failure(RetrofitError error) {
        handleFailure(error);
        if (getActivity() != null && !getActivity().isFinishing()) {
            handleSafeFailure(error);
        }

        if (error != null) {
            Log.e(TAG, "FAILURE url[" + error.getUrl() + "] message [" + error.getMessage() + "]");
        }
    }

    public abstract void handleSuccess(T t, Response response);

    public abstract void handleSafeSuccess(T t, Response response);

    public abstract void handleFailure(RetrofitError error);

    public abstract void handleSafeFailure(RetrofitError error);
}