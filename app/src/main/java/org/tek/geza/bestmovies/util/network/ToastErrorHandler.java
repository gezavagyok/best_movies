package org.tek.geza.bestmovies.util.network;

import android.content.Context;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by gezacsorba on 25/11/2016.
 */

public class ToastErrorHandler implements ErrorHandler {

    WeakReference<Context> contextWeakReference;

    public ToastErrorHandler(Context context) {
        contextWeakReference = new WeakReference<>(context);
    }

    @Override
    public void onError(Throwable t) {
        if (contextWeakReference.get() == null) return;
        Context c = contextWeakReference.get();
        // Crashlytics.logException()
        if (t instanceof RetrofitException) {
            if (((RetrofitException) t).isNetworkError()) {
                Toast.makeText(c, "No internet connection!", Toast.LENGTH_SHORT).show();
            } else if (((RetrofitException) t).getCode() != 0) {
                Toast.makeText(c, "Oops, server is on fire!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(c, "Oops, we are working on this issue!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(c, "Oops, we are working on this issue!", Toast.LENGTH_SHORT).show();
        }
    }
}
