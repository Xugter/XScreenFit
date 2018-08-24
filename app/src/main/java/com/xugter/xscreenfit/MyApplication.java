package com.xugter.xscreenfit;

import android.app.Application;

import com.xugter.autofit.AutoFitHelper;

/**
 * Created by xubo on 2018/8/23.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new AutoFitHelper.AutoFitHelperBuilder(this)
                .setBaseWidth(360)
                .setBaseHeight(640)
                .setDefaultTurnedOn(true)
                .setDefaultHorizontal(true)
                .create();
    }
}
