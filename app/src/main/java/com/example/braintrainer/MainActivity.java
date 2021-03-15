package com.example.braintrainer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;
import android.view.View.OnClickListener;

public class MainActivity extends Activity  {

    Button firstButton;
    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    TextView sumTextView;
    TextView timerTextView;
    Button TryAgainButton;
    RelativeLayout gameRelativeLayout;
    TextView resultTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView pointsTextView;
    int score = 0;
    int numberOfQuestions = 0;

//


    public void TryAgain(View view) {

        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("60s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        TryAgainButton.setVisibility(View.INVISIBLE);

        createQuestion();

        new CountDownTimer(60100, 1000) { // Timer

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");

            }

            @Override
            public void onFinish() {

                TryAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Total score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

            }
        }.start();


    }

    public void createQuestion() {

        Random rand = new Random();

        int a = rand.nextInt(31);
        int b = rand.nextInt(31);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int incorrectAnswer;

        for (int i=0; i<4; i++) {

            if (i == locationOfCorrectAnswer) {

                answers.add(a + b);

            } else {

                incorrectAnswer = rand.nextInt(43);

                while (incorrectAnswer == a + b) {

                    incorrectAnswer = rand.nextInt(43);

                }

                answers.add(incorrectAnswer);

            }

        }

        btn0.setText(Integer.toString(answers.get(0)));
        btn1.setText(Integer.toString(answers.get(1)));
        btn2.setText(Integer.toString(answers.get(2)));
        btn3.setText(Integer.toString(answers.get(3)));



    }

    public void SelectAnswer(View v) {

        if (v.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {

            score++;
            resultTextView.setText("Correct Answer!");
            resultTextView.setTextColor(Color.GREEN);
        } else {

            resultTextView.setText("Wrong Answer!");
            resultTextView.setTextColor(Color.RED);

        }

        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        createQuestion();


    }

    public void start(View v) {

        firstButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);

        TryAgain(findViewById(R.id.playAgainButton));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstButton = (Button)findViewById(R.id.startButton);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        btn0 = (Button)findViewById(R.id.button0);
        btn1 = (Button)findViewById(R.id.button1);
        btn2 = (Button)findViewById(R.id.button2);
        btn3 = (Button)findViewById(R.id.button3);
        btn4 =  (Button)findViewById(R.id.button4);

        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointsTextView = (TextView)findViewById(R.id.pointsTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        TryAgainButton = (Button)findViewById(R.id.playAgainButton);
        gameRelativeLayout = (RelativeLayout)findViewById(R.id.gameRelativeLayout);
        btn4.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.drawable.mtrl_tabs_default_indicator,
                menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_bar_activity_content) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }


}