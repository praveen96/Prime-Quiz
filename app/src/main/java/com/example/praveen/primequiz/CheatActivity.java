package com.example.praveen.primequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Praveen Kumar Jhanwar
 */

public class CheatActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("", "Inside OnCreate");
        setContentView(R.layout.activity_cheat);

        Intent intent = getIntent();

        TextView factorsView = (TextView)findViewById(R.id.factors);

        // Compute all factors of the number and store them in the string factors
        String factors = "The Factors are: 1, ";
        int number = intent.getIntExtra("number", 0);
        for(int i = 2 ; i < number; i++)
        {
            if((number % i) == 0)
                factors += (i + ", ");
        }
        factors += number;
        factorsView.setText(factors);
    }
}