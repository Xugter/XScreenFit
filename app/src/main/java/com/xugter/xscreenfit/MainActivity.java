package com.xugter.xscreenfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToDefault(View view) {
        startActivity(new Intent(this, DefaultActivity.class));
    }

    public void goToVertical(View view) {
        startActivity(new Intent(this, VerticalActivity.class));
    }

    public void goToHorizontal(View view) {
        startActivity(new Intent(this, HorizontalActivity.class));
    }

}
