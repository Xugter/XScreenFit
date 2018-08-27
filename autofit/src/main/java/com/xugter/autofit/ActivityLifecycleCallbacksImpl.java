package com.xugter.autofit;

import android.app.Activity;
import android.app.Application;
import android.content.res.Resources;
import android.os.Bundle;

import com.xugter.autofit.adapt.CancelAdapt;
import com.xugter.autofit.adapt.CustomAdapt;
import com.xugter.autofit.adapt.FitAdapter;
import com.xugter.autofit.utils.Logger;
import com.xugter.autofit.utils.ScreenUtils;

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
    private boolean isReady = false;

    private float verticalScale = 1;
    private float horizontalScale = 1;

    private final static String TAG = "ActivityLifecycleCallbacksImpl";

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        getReady(activity);
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
        if (state != State.NORMAL && activity instanceof CustomAdapt) {
            if (((CustomAdapt) activity).resetOnResume()) {
                resetDensity(activity);
            }
        }
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

    public void configChanged() {
        Logger.i(TAG, "=========configChanged");
        state = State.NORMAL;
        isReady = false;
    }

    public void changeToHorizontalDensity(Activity activity) {
        Logger.d(TAG, "=========changeToHorizontalDensity");
        if (state == State.HORIZONTAL) return;
        state = State.HORIZONTAL;
        activity.getResources().getDisplayMetrics().density = Resources.getSystem().getDisplayMetrics().density * horizontalScale;
        activity.getResources().getDisplayMetrics().scaledDensity = Resources.getSystem().getDisplayMetrics().scaledDensity * horizontalScale;
    }

    public void changeToVerticalDensity(Activity activity) {
        Logger.d(TAG, "=========changeToVerticalDensity");
        if (state == State.VERTICAL) return;
        state = State.VERTICAL;
        activity.getResources().getDisplayMetrics().density = Resources.getSystem().getDisplayMetrics().density * verticalScale;
        activity.getResources().getDisplayMetrics().scaledDensity = Resources.getSystem().getDisplayMetrics().scaledDensity * verticalScale;
    }

    public void resetDensity(Activity activity) {
        Logger.d(TAG, "=========resetDensity");
        if (state == State.NORMAL) return;
        state = State.NORMAL;
        activity.getResources().getDisplayMetrics().density = Resources.getSystem().getDisplayMetrics().density;
        activity.getResources().getDisplayMetrics().scaledDensity = Resources.getSystem().getDisplayMetrics().scaledDensity;
    }

    private void setCustomAdapt(Activity activity) {
        if (((CustomAdapt) activity).isBaseOnWidth()) {
            changeToHorizontalDensity(activity);
        } else {
            changeToVerticalDensity(activity);
        }
    }

    private void getReady(Activity activity) {
        if (!isReady) {
            float horizontalDensity = activity.getResources().getDisplayMetrics().widthPixels / Config.baseWidth;
            horizontalScale = horizontalDensity / Resources.getSystem().getDisplayMetrics().density;
            float verticalDensity = (activity.getResources().getDisplayMetrics().heightPixels - ScreenUtils.getStatusBarHeight()) / Config.baseHeight;
            verticalScale = verticalDensity / Resources.getSystem().getDisplayMetrics().density;
            Logger.i(TAG, "=========horizontalScale=" + horizontalScale + "  verticalScale=" + verticalScale);
            isReady = true;
        }
    }
}
