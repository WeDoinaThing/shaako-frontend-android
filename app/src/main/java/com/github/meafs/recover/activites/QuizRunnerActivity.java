package com.github.meafs.recover.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.SingleValueDataSet;
import com.anychart.charts.CircularGauge;
import com.gelitenight.waveview.library.WaveView;
import com.github.meafs.recover.R;
import com.github.meafs.recover.databinding.ActivityQuizRunnerBinding;
import com.github.meafs.recover.models.Quiz;
import com.github.meafs.recover.utils.WaveHelper;

import java.util.ArrayList;
import java.util.Arrays;

public class QuizRunnerActivity extends AppCompatActivity {
    private static final String TAG = "QuizRunnerActivity";

    private ArrayList<Quiz> quizArrayList;
    private ActivityQuizRunnerBinding binding;

    private Integer correctAnswers;
    private Integer index;

    private WaveHelper waveHelper;
    private int mBorderColor = Color.parseColor("#444444");
    private int mBorderWidth = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz_runner);
        binding.setQuizRunnerActivity(this);
        binding.btnBack.setOnClickListener(view -> new Intent(this, QuizActivity.class));
        index = 0;
        correctAnswers = 0;

        parseQuizzes();
    }

    private void evaluateResult(){
        binding.layoutQuiz.setVisibility(View.GONE);
        binding.layoutScore.setVisibility(View.VISIBLE);

        String correctText = "You've got "+correctAnswers+" out of "+quizArrayList.size()+" answers correct.";
        binding.tvCorrect.setText(correctText);

        Integer percentile = Integer.valueOf(correctAnswers) * 100 / quizArrayList.size();
        String percentileString = percentile+"%";
        binding.scoreText.setText(percentileString);
        showAchievedScore(percentile);
    }

    private void showAchievedScore(Integer score){
        binding.scoreVisualizer.setBorder(mBorderWidth, mBorderColor);
        Float fScore = Float.valueOf(score)/100.0f;
        waveHelper = new WaveHelper(binding.scoreVisualizer, fScore);
        waveHelper.start();
        binding.scoreVisualizer.setShapeType(WaveView.ShapeType.CIRCLE);
        binding.scoreVisualizer.setWaveColor(
                Color.parseColor("#b8f1ed"),
                Color.parseColor("#b8f1ed"));

    }

    private void renderQuiz(Quiz quiz){
        clearRadioGroup();
        binding.layoutQuiz.setVisibility(View.VISIBLE);
        binding.tvQuestion.setText(quiz.getQuestion());
        for(String choice: quiz.getChoices()){
            binding.choiceGroup.addView(getRadioButton(choice, quiz.getChoices().indexOf(choice)+1));
        }
    }

    private void runQuizzes(Integer index){
        renderQuiz(quizArrayList.get(index));
        Integer correctAnswerIndex = quizArrayList.get(index).getAnswer();
        index++;
        Integer tempIndex = index;
        binding.btSubmitAnswer.setOnClickListener(view -> {
            if(correctAnswerIndex.equals(binding.choiceGroup.getCheckedRadioButtonId()))
                correctAnswers++;

            if(tempIndex<quizArrayList.size())
                runQuizzes(tempIndex);
            else{
                evaluateResult();
            }
        });
    }

    private void parseQuizzes(){
        quizArrayList = new ArrayList<>();

        if(getIntent().hasExtra("quizStrings")) {
            ArrayList<String> quizStrings = getIntent().getStringArrayListExtra("quizStrings");
            for(String quizzes: quizStrings){
                String[] deconstructQuiz = quizzes.split(";");
                String question = deconstructQuiz[0];
                String choices = deconstructQuiz[1];
                String answer = deconstructQuiz[2].trim();

                ArrayList<String> choiceArrayList = new ArrayList<>(Arrays.asList(choices.split(",")));

                Quiz quiz = new Quiz(question, choiceArrayList, Integer.valueOf(answer));
                quizArrayList.add(quiz);
                runQuizzes(index);
            }
        }else{
            onBackPressed();
        }
    }

    private RadioButton getRadioButton(String text, Integer index){
        RadioButton radioButton = new RadioButton(getApplicationContext());
        radioButton.setText(text);
        radioButton.setTextColor(Color.parseColor("#000000"));
        radioButton.setTextSize(18.0f);
        radioButton.setTypeface(ResourcesCompat.getFont(this, R.font.nunito));
        radioButton.setId(index);
        return radioButton;
    }

    private void clearRadioGroup(){
        binding.choiceGroup.clearCheck();
        binding.choiceGroup.removeAllViews();
    }
}