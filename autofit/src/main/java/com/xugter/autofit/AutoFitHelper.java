package com.xugter.autofit;

import android.app.Application;

/**
 * Created by xubo on 2018/8/23.
 */

public class AutoFitHelper {

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

        public void create() {
            application.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacksImpl());
        }
    }
}
