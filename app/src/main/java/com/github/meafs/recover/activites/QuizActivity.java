package com.github.meafs.recover.activites;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.github.meafs.recover.R;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    ArrayList<String> exampleQuiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Button placeholderButton = findViewById(R.id.placeholder_button);

        exampleQuiz = new ArrayList<>();
        generateQuiz();

        placeholderButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, QuizRunnerActivity.class);
            intent.putStringArrayListExtra("quizStrings", exampleQuiz);
            startActivity(intent);
        });
    }

    private void generateQuiz(){
        String quiz1 = "What's Bugsnax?; Bug, Snack, Both, None; 1";
        String quiz2 = "Who's Kero Kero Bonito?; Sarah, Bonito, Both, None; 3";
        String quiz3 = "Is it true?; True, False; 2";
        exampleQuiz.add(quiz1);
        exampleQuiz.add(quiz2);
        exampleQuiz.add(quiz3);
    }
}