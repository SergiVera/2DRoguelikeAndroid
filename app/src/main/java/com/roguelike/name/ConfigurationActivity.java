package com.roguelike.name;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.roguelike.name.UnityPlayerActivity;

public class ConfigurationActivity extends AppCompatActivity {
    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(mContext, UnityPlayerActivity.class);
        startActivity(intent);
    }
}
