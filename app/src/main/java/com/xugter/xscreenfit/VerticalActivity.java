package com.xugter.xscreenfit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xugter.autofit.adapt.CustomAdapt;

/**
 * Created by xubo on 2018/8/23.
 */

public class VerticalActivity extends AppCompatActivity implements CustomAdapt{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical);
    }

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public boolean resetOnResume() {
        return false;
    }
}
