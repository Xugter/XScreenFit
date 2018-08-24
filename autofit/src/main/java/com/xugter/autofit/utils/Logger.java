package com.xugter.autofit.utils;

import android.util.Log;

import com.xugter.autofit.Config;

/**
 * Created by xubo on 2018/8/24.
 */

public class Logger {

    public static void d(String tag, String content) {
        if (Config.debug) {
            Log.d(tag, content);
        }
    }

    public static void i(String tag, String content) {
        Log.i(tag, content);
    }

    public static void e(String tag, String content) {
        Log.e(tag, content);
    }

}
