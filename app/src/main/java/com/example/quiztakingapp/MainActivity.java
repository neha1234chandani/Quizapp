package com.example.quiztakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView totalQuestionTextView;
    TextView questionTextView;
    Button ansA , ansB, ansC , ansD;
    Button submitButton;

    int score = 0;
    int totalQuestion = Quesionanswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionTextView = findViewById(R.id.tatal_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitButton = findViewById(R.id.submit);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitButton.setOnClickListener(this);

        totalQuestionTextView.setText("Total question" + totalQuestion);

        loadNewQuestion();

    }
    @Override
    public void onClick(View view){


        ansA.setBackgroundColor(Color.GRAY);
        ansB.setBackgroundColor(Color.GRAY);
        ansC.setBackgroundColor(Color.GRAY);
        ansD.setBackgroundColor(Color.GRAY);

        Button clickedButton = (Button) view;
        if (clickedButton.getId()==R.id.submit){

            if (selectedAnswer.equals(Quesionanswer.correctAnswer[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();


        }else {
//            choice button clicked
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }

    }
    void loadNewQuestion(){

        if (currentQuestionIndex == totalQuestion){
            finishQuiz();
            return;
        }


        questionTextView.setText(Quesionanswer.question[currentQuestionIndex]);
        ansA.setText(Quesionanswer.choices[currentQuestionIndex][0]);
        ansB.setText(Quesionanswer.choices[currentQuestionIndex][1]);
        ansC.setText(Quesionanswer.choices[currentQuestionIndex][2]);
        ansD.setText(Quesionanswer.choices[currentQuestionIndex][3]);

    }
    void finishQuiz(){
        String passStatus = "";
        if (score > totalQuestion*0.60){
            passStatus = "Passed";
        }else {
            passStatus = "Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is " + score + "out of " + totalQuestion)
                .setPositiveButton("Restart" ,((dialogInterface, i) -> restartQuiz()))
                .setCancelable(false)
                .show();

    }

    void restartQuiz(){
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();

    }

}