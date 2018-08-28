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

    /**
     * 当前屏幕适配的状态
     */
    enum State {
        VERTICAL,
        HORIZONTAL,
        NORMAL
    }

    private State state = State.NORMAL;
    /**
     * 缩放因子是否计算完成
     */
    private boolean isReady = false;
    /**
     * 竖向缩放因子
     */
    private float verticalScale = 1;
    /**
     * 横向缩放因子
     */
    private float horizontalScale = 1;

    private final static String TAG = "ActivityLifecycleCallbacksImpl";

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        getReady(activity);
        //处理 activity 的适配行为
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
                //根据配置恢复在 resume 恢复 density
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

    /**
     * 配置改变，需要重新计算适配参数
     */
    public void configChanged() {
        Logger.i(TAG, "=========configChanged");
        state = State.NORMAL;
        isReady = false;
    }

    /**
     * 横向适配
     *
     * @param activity
     */
    public void changeToHorizontalDensity(Activity activity) {
        Logger.d(TAG, "=========changeToHorizontalDensity");
        if (state == State.HORIZONTAL) return;
        state = State.HORIZONTAL;
        activity.getResources().getDisplayMetrics().density = Resources.getSystem().getDisplayMetrics().density * horizontalScale;
        activity.getResources().getDisplayMetrics().scaledDensity = Resources.getSystem().getDisplayMetrics().scaledDensity * horizontalScale;
    }

    /**
     * 竖向适配
     *
     * @param activity
     */
    public void changeToVerticalDensity(Activity activity) {
        Logger.d(TAG, "=========changeToVerticalDensity");
        if (state == State.VERTICAL) return;
        state = State.VERTICAL;
        activity.getResources().getDisplayMetrics().density = Resources.getSystem().getDisplayMetrics().density * verticalScale;
        activity.getResources().getDisplayMetrics().scaledDensity = Resources.getSystem().getDisplayMetrics().scaledDensity * verticalScale;
    }

    /**
     * 取消适配
     *
     * @param activity
     */
    public void resetDensity(Activity activity) {
        Logger.d(TAG, "=========resetDensity");
        if (state == State.NORMAL) return;
        state = State.NORMAL;
        activity.getResources().getDisplayMetrics().density = Resources.getSystem().getDisplayMetrics().density;
        activity.getResources().getDisplayMetrics().scaledDensity = Resources.getSystem().getDisplayMetrics().scaledDensity;
    }

    /**
     * 处理自定义适配 activity
     *
     * @param activity
     */
    private void setCustomAdapt(Activity activity) {
        if (((CustomAdapt) activity).isBaseOnWidth()) {
            changeToHorizontalDensity(activity);
        } else {
            changeToVerticalDensity(activity);
        }
    }

    /**
     * 计算适配参数
     *
     * @param activity
     */
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
