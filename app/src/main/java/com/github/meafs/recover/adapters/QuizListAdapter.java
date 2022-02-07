package com.github.meafs.recover.adapters;

import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.github.meafs.recover.R;
import com.github.meafs.recover.activites.QuizRunnerActivity;
import com.github.meafs.recover.models.QuizModel;
import com.github.meafs.recover.utils.Speak;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.ViewHolder> {
    private final ArrayList<QuizModel> quizModels;
    private final Context context;
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
        holder.textView.setText(quizModel.getTitle().trim());
        holder.tvDuration.setText(quizModel.getQuizzes().size()+" Questions");
        holder.constraintLayout.setOnClickListener(view -> {
            Speak speak = new Speak(context);
            speak.speak(ttsObject, holder.textView.getText().toString());
            Intent intent = new Intent(context, QuizRunnerActivity.class);
            intent.putStringArrayListExtra("quizStrings", new ArrayList<>(quizModel.getQuizzes()));
            context.startActivity(intent);
        });

        String[] tags = quizModel.getTags().split(",");
        generateChips(tags, holder);
    }

    public void generateChips(String[] tags, ViewHolder holder){
        for (String tag : tags) {
            Chip chip = new Chip(context);
            ChipDrawable chipDrawable = ChipDrawable.createFromAttributes(context, null, 0, R.style.MaterialComponents_Chip_Thin);
            chip.setChipDrawable(chipDrawable);
            chip.setText(tag.trim());
            chip.setOnClickListener(view -> {
                Speak speak = new Speak(context);
                speak.speak(ttsObject, tag);
            });
            holder.chipGroup.addView(chip);
        }
    }
    @Override
    public int getItemCount() {
        return quizModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private TextView tvDuration;
        private ChipGroup chipGroup;
        private ConstraintLayout constraintLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.quiz_name);
            this.tvDuration = itemView.findViewById(R.id.tv_duration);
            this.constraintLayout = itemView.findViewById(R.id.quiz_list_layout);
            this.chipGroup = itemView.findViewById(R.id.chip_group);
        }
    }
}
