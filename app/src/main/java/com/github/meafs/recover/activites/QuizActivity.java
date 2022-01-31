package com.github.meafs.recover.activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.meafs.recover.R;
import com.github.meafs.recover.adapters.QuizListAdapter;
import com.github.meafs.recover.databinding.ActivityQuizBinding;
import com.github.meafs.recover.models.QuizModel;
import com.github.meafs.recover.viewmodels.QuizViewModel;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    ArrayList<QuizModel> quizModelArrayList;
    QuizViewModel quizViewModel;

    private ActivityQuizBinding binding;
    private static final String TAG = "QuizActivity";
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz);
        binding.setQuizActivity(this);
        binding.toolbar.setNavigationOnClickListener(view-> startActivity(new Intent(this, MainActivity.class)));

        quizModelArrayList = new ArrayList<>();
        sharedPreferences = getSharedPreferences("CHW", Context.MODE_PRIVATE);
        fetchQuiz();
    }
    private void showQuiz(ArrayList<QuizModel> quizModelArrayList){
        QuizListAdapter quizListAdapter = new QuizListAdapter(quizModelArrayList, this);
        binding.recylerview.setHasFixedSize(true);
        binding.recylerview.setLayoutManager(new LinearLayoutManager(this));
        binding.recylerview.setAdapter(quizListAdapter);
        binding.progressBar.setVisibility(View.GONE);
        binding.recylerview.setVisibility(View.VISIBLE);
    }
    private void fetchQuiz(){
        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        quizViewModel.init(sharedPreferences.getString("authToken", ""));

        quizViewModel.getQuizResponseLiveData().observe(this, quizModels -> {
            if (quizModels.size() != 0) {
                Log.d(TAG, String.valueOf(quizModels));
                quizModelArrayList.addAll(quizModels);
                showQuiz(quizModelArrayList);
            }
        });
    }
}