package com.jhost.quizzerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private final int PROGRESSBAR_INCREMENT = 8;

    private ProgressBar statusBar;

    private TextView statusText;
    private TextView questionText;

    private Button trueBtn;
    private Button falseBtn;

    private int currentQuestion = 0;

    private int[] questions;
    private Map<Integer, Boolean> questionAnswers;
    private int correctAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();

        refreshAll();

        setClickEvent(trueBtn, true);
        setClickEvent(falseBtn, false);
    }

    private void init(){
        initializeItems();
        initializeBtns();
        initializeTextViews();
    }

    private void initializeItems(){
        this.statusBar = findViewById(R.id.status_bar);

        this.questions = new int[] {
                R.string.question_1,
                R.string.question_2,
                R.string.question_3,
                R.string.question_4,
                R.string.question_5,
                R.string.question_6,
                R.string.question_7,
                R.string.question_8,
                R.string.question_9,
                R.string.question_10,
                R.string.question_11,
                R.string.question_12,
                R.string.question_13,
        };

        this.questionAnswers = new HashMap<Integer, Boolean>();
        this.questionAnswers.put(R.string.question_1, true);
        this.questionAnswers.put(R.string.question_2, true);
        this.questionAnswers.put(R.string.question_3, true);
        this.questionAnswers.put(R.string.question_4, true);
        this.questionAnswers.put(R.string.question_5, true);
        this.questionAnswers.put(R.string.question_6, false);
        this.questionAnswers.put(R.string.question_7, true);
        this.questionAnswers.put(R.string.question_8, false);
        this.questionAnswers.put(R.string.question_9, true);
        this.questionAnswers.put(R.string.question_10, true);
        this.questionAnswers.put(R.string.question_11, false);
        this.questionAnswers.put(R.string.question_12, false);
        this.questionAnswers.put(R.string.question_13, true);
    }

    private void initializeBtns(){
        this.trueBtn = findViewById(R.id.true_btn);
        this.falseBtn = findViewById(R.id.false_btn);
    }

    private void initializeTextViews(){
        this.questionText = findViewById(R.id.question_text);
        this.statusText = findViewById(R.id.score_text);
    }

    private void refreshAll(){
        if (currentQuestion >= questions.length){
            AlertDialog.Builder alb = new AlertDialog.Builder(this);
            alb.setTitle("Game over!");
            alb.setCancelable(false);
            alb.setMessage("Yo scored: " + correctAnswers + "\nTotal questions: " + questions.length);
            alb.setPositiveButton("Close app", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alb.show();
        } else {
            this.refreshQuestionText();
            this.refreshStatus();
        }

    }

    private void refreshQuestionText(){
        this.questionText.setText(questions[currentQuestion]);
    }

    private void refreshStatus(){
        this.statusText.setText(correctAnswers + "");
    }

    private void setClickEvent(Button btn, boolean answer){
        btn.setOnClickListener(event -> {
            if (isAnswerCorrect(questions[currentQuestion], answer)){
                onCorrect();
            } else {
                onWrong();
            }
            currentQuestion++;

            statusBar.incrementProgressBy(PROGRESSBAR_INCREMENT);

            refreshAll();
        });
    }

    private boolean isAnswerCorrect(int questionNumber, boolean value){
        return questionAnswers.get(questionNumber) == value;
    }

    private void onCorrect(){
        correctAnswers++;
        Toast.makeText(this, R.string.correct_answer, Toast.LENGTH_SHORT).show();
    }

    private void onWrong(){
        Toast.makeText(this, R.string.wrong_answer, Toast.LENGTH_SHORT).show();
    }
}