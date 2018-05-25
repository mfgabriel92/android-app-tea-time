package com.example.gabriel.teatime;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.example.gabriel.teatime.IdlingResource.SimpleIdlingResource;
import com.example.gabriel.teatime.model.Tea;

import java.util.ArrayList;

class ImageDownloader {
    private static final int DELAY = 3000;

    final static ArrayList<Tea> mTeas = new ArrayList<>();

    public static void downloadImage(Context context, final DelayerCallback callback, @Nullable final SimpleIdlingResource idlingResource) {
        if (idlingResource != null) {
            idlingResource.setIdleState(false);
        }

        mTeas.add(new Tea(context.getString(R.string.black_tea_name), R.drawable.black_tea));
        mTeas.add(new Tea(context.getString(R.string.green_tea_name), R.drawable.green_tea));
        mTeas.add(new Tea(context.getString(R.string.white_tea_name), R.drawable.white_tea));
        mTeas.add(new Tea(context.getString(R.string.oolong_tea_name), R.drawable.oolong_tea));
        mTeas.add(new Tea(context.getString(R.string.honey_lemon_tea_name), R.drawable.honey_lemon_tea));
        mTeas.add(new Tea(context.getString(R.string.chamomile_tea_name), R.drawable.chamomile_tea));

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onDone(mTeas);

                    if (idlingResource != null) {
                        idlingResource.setIdleState(true);
                    }
                }
            }
        }, DELAY);
    }

    interface DelayerCallback {
        void onDone(ArrayList<Tea> teas);
    }
}
