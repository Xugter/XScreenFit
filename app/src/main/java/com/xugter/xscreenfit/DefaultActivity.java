package com.xugter.xscreenfit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.xugter.autofit.adapt.CancelAdapt;
import com.xugter.autofit.utils.ScreenUtils;

/**
 * Created by xubo on 2018/8/23.
 */

public class DefaultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);
    }
}
