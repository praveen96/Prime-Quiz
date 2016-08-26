package com.example.praveen.primequiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Praveen Kumar Jhanwar
 */

public class HintActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("", "Inside OnCreate");
        setContentView(R.layout.activity_hint);
    }
}
