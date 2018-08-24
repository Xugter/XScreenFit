package com.xugter.autofit;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.xugter.autofit.adapt.CancelAdapt;
import com.xugter.autofit.adapt.CustomAdapt;
import com.xugter.autofit.adapt.FitAdapter;

/**
 * Created by xubo on 2018/8/23.
 */

public class ActivityLifecycleCallbacksImpl implements Application.ActivityLifecycleCallbacks {

    enum State {
        VERTICAL,
        HORIZONTAL,
        NORMAL
    }

    private State state = State.NORMAL;
    private Object lock = new Object();

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (Config.defaultTurnedOn) {
            if (activity instanceof CancelAdapt) {
                resetDensity(activity);
            } else if (activity instanceof CustomAdapt) {
                setCustomAdapt(activity);
            } else {
                if (Config.defaultHorizontal) {
                    changeToHorizontalDensity(activity);
                } else {
                    changeToVerticalDensity(activity);
                }
            }
        } else {
            if (activity instanceof FitAdapter) {
                if (Config.defaultHorizontal) {
                    changeToHorizontalDensity(activity);
                } else {
                    changeToVerticalDensity(activity);
                }
            } else if (activity instanceof CustomAdapt) {
                setCustomAdapt(activity);
            } else {
                resetDensity(activity);
            }
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    private void setCustomAdapt(Activity activity) {
        if (((CustomAdapt) activity).isBaseOnWidth()) {
            changeToHorizontalDensity(activity);
        } else {
            changeToVerticalDensity(activity);
        }
    }

    private void changeToHorizontalDensity(Activity activity) {
        Log.d("bbbb", "=========changeToHorizontalDensity");
        if (state == State.HORIZONTAL) return;
        state = State.HORIZONTAL;
        activity.getResources().getDisplayMetrics().density = 3;
    }

    private void changeToVerticalDensity(Activity activity) {
        Log.d("bbbb", "=========changeToVerticalDensity");
        if (state == State.VERTICAL) return;
        state = State.VERTICAL;
        activity.getResources().getDisplayMetrics().density = 2.7f;
    }

    private void resetDensity(Activity activity) {
        Log.d("bbbb", "=========resetDensity");
        if (state == State.NORMAL) return;
        state = State.NORMAL;
        activity.getResources().getDisplayMetrics().density = 2.625f;
    }
}
