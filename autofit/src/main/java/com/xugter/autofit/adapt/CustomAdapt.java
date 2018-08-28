package com.xugter.autofit.adapt;

/**
 * 用于特殊定制的 Activity，默认开启适配
 * Created by xubo on 2018/8/23.
 */

public interface CustomAdapt {

    /**
     * 适配方向是否为横向
     *
     * @return
     */
    boolean isBaseOnWidth();

    /**
     * 是否在 onresume 恢复 Density
     * 这样会不影响 toast，dialog 等使用的 density
     *
     * @return
     */
    boolean resetOnResume();
}
