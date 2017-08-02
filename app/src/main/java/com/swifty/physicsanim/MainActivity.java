package com.swifty.physicsanim;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by swifty on 2/8/2017.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Fling(View view) {
        Intent intent = new Intent(this, FlingActivity.class);
        startActivity(intent);
    }

    public void Spring(View view) {
        Intent intent = new Intent(this, SpringActivity.class);
        startActivity(intent);
    }

}
