package com.github.meafs.recover.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.meafs.recover.R;
import com.github.meafs.recover.activites.ContentRunnerActivity;
import com.github.meafs.recover.models.ContentModel;
import com.github.meafs.recover.utils.FetchThumbnail;
import com.github.meafs.recover.utils.Speak;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContentRecylerAdapter extends RecyclerView.Adapter<ContentRecylerAdapter.ViewHolder> {

    private ArrayList<ContentModel> list = new ArrayList<>();
    private final Context context;
    private FetchThumbnail fetchThumbnail;
    private TextToSpeech ttsObject;

    public ContentRecylerAdapter(ArrayList<ContentModel> list, Context context, TextToSpeech ttsObject) {
        this.list = list;
        this.context = context;
        this.ttsObject = ttsObject;
    }

    public ContentRecylerAdapter(ArrayList<ContentModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_card, parent, false);

        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.title.setText(list.get(position).getFields().getTitle());
        String[] tags = list.get(position).getFields().getTags().split(",");
        for (String tag : tags) {

            Chip chip = new Chip(context);
            ChipDrawable chipDrawable = ChipDrawable.createFromAttributes(context, null,0,R.style.MaterialComponents_Chip_Thin);
            chip.setChipDrawable(chipDrawable);
            chip.setText(tag.trim());
            holder.chipGroup.addView(chip);
        }
        fetchThumbnail = new FetchThumbnail(list.get(position).getFields().getAssociatedLink());
        Glide.with(context)
                .load(fetchThumbnail.getThumbnailUrl())
                .into(holder.imageView);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Speak speak = new Speak(context);
//                speak.speak(ttsObject, list.get(position).getFields().getTitle());

//                Intent youtube = new Intent(Intent.ACTION_VIEW, Uri.parse(list.get(position).getFields().getAssociatedLink()));
//                context.startActivity(youtube);
                Bundle bundle = new Bundle();
                bundle.putSerializable("contentModel", list.get(position));
                context.startActivity(new Intent(context, ContentRunnerActivity.class).putExtras(bundle));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(ArrayList<ContentModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final CardView cardView;
        private final TextView title;
        private final ChipGroup chipGroup;
        private final ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.contentcard);
            title = itemView.findViewById(R.id.title);
            chipGroup = itemView.findViewById(R.id.chip_group);
            imageView = itemView.findViewById(R.id.thumbnail);
        }
    }
}
