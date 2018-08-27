package com.xugter.autofit;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;

/**
 * Created by xubo on 2018/8/23.
 */

public class AutoFitHelper {

    private static ActivityLifecycleCallbacksImpl activityLifecycleCallbacks = new ActivityLifecycleCallbacksImpl();

    public static void changeToHorizontal(Activity activity) {
        activityLifecycleCallbacks.changeToHorizontalDensity(activity);
    }

    public static void changeToVertical(Activity activity) {
        activityLifecycleCallbacks.changeToVerticalDensity(activity);
    }

    public static void resetDensity(Activity activity) {
        activityLifecycleCallbacks.resetDensity(activity);
    }

    public static class AutoFitHelperBuilder {

        private Application application;

        public AutoFitHelperBuilder(Application application) {
            this.application = application;
        }

        public AutoFitHelperBuilder setBaseWidth(float width) {
            Config.baseWidth = width;
            return this;
        }

        public AutoFitHelperBuilder setBaseHeight(float height) {
            Config.baseHeight = height;
            return this;
        }

        public AutoFitHelperBuilder setDefaultTurnedOn(boolean defaultTurnedOn) {
            Config.defaultTurnedOn = defaultTurnedOn;
            return this;
        }

        public AutoFitHelperBuilder setDefaultHorizontal(boolean defaultHorizontal) {
            Config.defaultHorizontal = defaultHorizontal;
            return this;
        }

        public AutoFitHelperBuilder setDebug(boolean debug) {
            Config.debug = debug;
            return this;
        }

        public void create() {
            application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    activityLifecycleCallbacks.configChanged();
                }

                @Override
                public void onLowMemory() {

                }
            });
        }
    }
}
