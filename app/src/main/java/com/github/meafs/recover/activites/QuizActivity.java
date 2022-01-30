package com.github.meafs.recover.activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.github.meafs.recover.R;
import com.github.meafs.recover.models.QuizModel;
import com.github.meafs.recover.viewmodels.QuizViewModel;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    ArrayList<String> exampleQuiz;
    ArrayList<String> quizList;
    QuizViewModel quizViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        quizList = new ArrayList<>();

        Button placeholderButton = findViewById(R.id.placeholder_button);
        SharedPreferences pref = getSharedPreferences("CHW", Context.MODE_PRIVATE);

        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        quizViewModel.init(pref.getString("authToken", ""));

        quizViewModel.getQuizResponseLiveData().observe(this, new Observer<List<QuizModel>>() {
            @Override
            public void onChanged(List<QuizModel> quizModels) {
                if (quizModels.size() != 0) {
                    for (int i = 0; i < quizModels.size(); i++) {
                        quizList.addAll(quizModels.get(i).getQuizzes());
                    }
                }
            }
        });
//        exampleQuiz = new ArrayList<>();
//        generateQuiz();

        placeholderButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, QuizRunnerActivity.class);
            intent.putStringArrayListExtra("quizStrings", quizList);
            startActivity(intent);
        });
    }

    private void generateQuiz() {
        String quiz1 = "What's Bugsnax?; Bug, Snack, Both, None; 1";
        String quiz2 = "Who's Kero Kero Bonito?; Sarah, Bonito, Both, None; 3";
        String quiz3 = "Is it true?; True, False; 2";
        exampleQuiz.add(quiz1);
        exampleQuiz.add(quiz2);
        exampleQuiz.add(quiz3);
    }
}