package com.xugter.xscreenfit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xugter.autofit.adapt.CancelAdapt;

/**
 * Created by xubo on 2018/8/24.
 */

public class CancelActivity extends AppCompatActivity implements CancelAdapt {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);
    }
}
