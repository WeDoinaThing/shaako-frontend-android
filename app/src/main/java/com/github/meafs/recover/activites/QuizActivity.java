package com.github.meafs.recover.activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.github.meafs.recover.R;
import com.github.meafs.recover.databinding.ActivityQuizBinding;
import com.github.meafs.recover.models.QuizModel;
import com.github.meafs.recover.viewmodels.QuizViewModel;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    ArrayList<String> exampleQuiz;
    ArrayList<String> quizList;
    QuizViewModel quizViewModel;

    private ActivityQuizBinding binding;

    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz);
        binding.setQuizActivity(this);
        binding.toolbar.setNavigationOnClickListener(view-> startActivity(new Intent(this, MainActivity.class)));
        quizList = new ArrayList<>();

        sharedPreferences = getSharedPreferences("CHW", Context.MODE_PRIVATE);
        fetchQuiz();

//        placeholderButton.setOnClickListener(view -> {
//            Intent intent = new Intent(this, QuizRunnerActivity.class);
//            intent.putStringArrayListExtra("quizStrings", quizList);
//            startActivity(intent);
//        });
    }

    private void fetchQuiz(){
        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        quizViewModel.init(sharedPreferences.getString("authToken", ""));

        quizViewModel.getQuizResponseLiveData().observe(this, quizModels -> {
            if (quizModels.size() != 0) {
                for (int i = 0; i < quizModels.size(); i++) {
                    quizList.addAll(quizModels.get(i).getQuizzes());
                }
            }
        });
    }
}