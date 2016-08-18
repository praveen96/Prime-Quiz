package com.example.praveen.primequiz;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private int randomNumber;
    private final Random randomGenerator = new Random();
    private boolean answer = false;
    private String number = "";
    private TextView question;
    private Button yesButton, noButton, nextButton;

    /* Set the UI as it looks like when the app starts without any changes due to user responses */
    private void setDefault() {
        question.setText(number);
        // set background colors for buttons
        yesButton.setBackgroundColor(Color.argb(26, 0, 0, 0));
        noButton.setBackgroundColor(Color.argb(26, 0, 0, 0));
        nextButton.setBackgroundColor(Color.argb(26, 0, 0, 0));
        // set text colors for buttons
        yesButton.setTextColor(Color.argb(255, 0, 0, 0));
        noButton.setTextColor(Color.argb(255, 0, 0, 0));
        nextButton.setTextColor(Color.argb(255, 0, 0, 0));
        // enable the buttons to get user response
        yesButton.setEnabled(true);
        noButton.setEnabled(true);
        nextButton.setEnabled(true);
    }

    /* Function to check whether number is prime or not */
    private boolean isPrime(int number) {
        if (number == 1)    //as 1 is not a prime number
            return false;

        // check if number is prime for number > 1
        int squareRoot = (int) Math.sqrt((double) number);
        boolean primeFlag = true;   // indicates whether number is prime or not
        // using the property that if num is a divisor of number, then number/num is also a divisor of number
        for (int num = 2; num <= squareRoot; num++) {
            if ((number % num) == 0) {
                primeFlag = false;
                break;
            }
        }

        return primeFlag;
    }

    /* Function to generate next question on pressing next button or after any user response
    * parameter mode is used to check whether the activity was just created or the user clicked the next button
    * so that we don't delay the first question on app start due to fade animation used
    * if mode = true, then the activity was just created, else next button was clicked
    * */
    private void nextQuestion(boolean mode) {
        // disable the buttons, so that user cannot submit multiple responses
        yesButton.setEnabled(false);
        noButton.setEnabled(false);
        nextButton.setEnabled(false);

        randomNumber = randomGenerator.nextInt(1000) + 1;   // generates random number in [1, 1000]
        number = " " + Integer.toString(randomNumber);  // makes the string to put in TextView(id = question)
        answer = isPrime(randomNumber);     // stores the correct response

        if (mode) {
            setDefault();
        } else {
            /* run the animation in a separate thread to prevent it from executing immediately in the activity thread,
            so that the animation and other GUI changes are actually seen by the user
            instead of being executed instantaneously without the user noticing it */
            findViewById(R.id.screen).animate().alpha(0f).setDuration(500).withEndAction(new Runnable() {
                @Override
                public void run() {
                    findViewById(R.id.screen).animate().alpha(1f).setDuration(500);
                    setDefault();
                }
            });

            if (answer)
                Log.i("Generated number " + Integer.toString(randomNumber) + " is", "Prime");
            else
                Log.i("Generated number " + Integer.toString(randomNumber) + " is", "Not Prime");
        }
    }

    // Override onClick method in order to handle button clicks for all the three buttons using a single function
    @Override
    public void onClick(View view) {
        int id = view.getId();  // get the id of button that was clicked

        // Handle
        switch (id) {
            case R.id.yesButton:    //if clicked button was "YES" button
                if (answer) {
                    Log.d("", "");
                    Toast.makeText(getApplicationContext(), "Woah! Correct Response", Toast.LENGTH_SHORT).show();
                    yesButton.setBackgroundColor(Color.argb(125, 0, 255, 0));
                } else {
                    Log.d("", "");
                    Toast.makeText(getApplicationContext(), "Sorry...Incorrect Response", Toast.LENGTH_SHORT).show();
                    yesButton.setBackgroundColor(Color.argb(175, 255, 0, 0));
                }
                yesButton.setTextColor(Color.argb(255, 255, 255, 255));
                nextQuestion(false);
                break;
            case R.id.noButton:     // if clicked button was "NO" button
                if (!answer) {
                    Log.d("", "");
                    Toast.makeText(getApplicationContext(), "Woah! Correct Response", Toast.LENGTH_SHORT).show();
                    noButton.setBackgroundColor(Color.argb(125, 0, 255, 0));
                } else {
                    Log.d("", "");
                    Toast.makeText(getApplicationContext(), "Sorry...Incorrect Response", Toast.LENGTH_SHORT).show();
                    noButton.setBackgroundColor(Color.argb(175, 255, 0, 0));
                }
                noButton.setTextColor(Color.argb(255, 255, 255, 255));
                nextQuestion(false);
                break;
            case R.id.nextButton:       // if the clicked button was "NEXT" button
                nextButton.setBackgroundColor(Color.argb(255, 71, 83, 200));
                nextButton.setTextColor(Color.argb(255, 255, 255, 255));
                nextQuestion(false);
                break;
            default:
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("", "Inside OnCreate");
        setContentView(R.layout.activity_quiz);

        // initialize the view elements, i.e., TextViews, Buttons etc.
        question = (TextView) findViewById(R.id.question);
        yesButton = (Button) findViewById(R.id.yesButton);
        noButton = (Button) findViewById(R.id.noButton);
        nextButton = (Button) findViewById(R.id.nextButton);

        nextQuestion(true);

        // Using this(i.e., QuizActivity class to handle onClick event
        yesButton.setOnClickListener(this);
        noButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i("", "Inside onSaveInstance");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("", "Inside OnStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("", "Inside OnPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("", "Inside OnResume");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("", "Inside OnStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("", "Inside OnDestroy");
    }

    @SuppressWarnings("EmptyMethod")
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
            Log.i("Orientation", "Landscape");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            //Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
            Log.i("Orientation", "Portrait");
        }
    }
}
