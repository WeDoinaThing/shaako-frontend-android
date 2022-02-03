package com.github.meafs.recover.adapters;

import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.github.meafs.recover.R;
import com.github.meafs.recover.activites.QuizRunnerActivity;
import com.github.meafs.recover.models.QuizModel;
import com.github.meafs.recover.utils.Speak;

import java.util.ArrayList;

public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.ViewHolder> {
    private ArrayList<QuizModel> quizModels;
    private Context context;
    private TextToSpeech ttsObject;

    public QuizListAdapter(ArrayList<QuizModel> quizModels, Context context, TextToSpeech ttsObject) {
        this.quizModels = quizModels;
        this.context = context;
        this.ttsObject = ttsObject;
    }

    public QuizListAdapter(ArrayList<QuizModel> quizModels, Context context) {
        this.quizModels = quizModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.rv_item_quiz, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final QuizModel quizModel = quizModels.get(position);
        holder.textView.setText(quizModel.getTitle());
        holder.constraintLayout.setOnClickListener(view -> {
            Speak speak = new Speak(context);
            speak.speak(ttsObject, holder.textView.getText().toString());
            Intent intent = new Intent(context, QuizRunnerActivity.class);
            intent.putStringArrayListExtra("quizStrings", new ArrayList<>(quizModel.getQuizzes()));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return quizModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ConstraintLayout constraintLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.quiz_name);
            constraintLayout = itemView.findViewById(R.id.quiz_list_layout);
        }
    }
}
