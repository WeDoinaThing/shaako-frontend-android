package com.github.meafs.recover.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.beloo.widget.chipslayoutmanager.util.log.Log;
import com.bumptech.glide.Glide;
import com.github.meafs.recover.R;
import com.github.meafs.recover.databinding.ActivityContentRunnerBinding;
import com.github.meafs.recover.models.ContentModel;
import com.github.meafs.recover.utils.FetchThumbnail;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;

public class ContentRunnerActivity extends AppCompatActivity {
    private static final String TAG = "ContentRunActivity";

    private ActivityContentRunnerBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_content_runner);
        binding.setContentRunnerActivity(this);

        if(getIntent().hasExtra("contentModel")){
            ContentModel contentModel = (ContentModel) getIntent().getSerializableExtra("contentModel");
            renderView(contentModel);
        }
    }

    private void renderView(ContentModel contentModel){
        binding.tvTitle.setText(contentModel.getFields().getTitle());

        String[] tags = contentModel.getFields().getTags().split(",");
        for (String tag : tags) {
            Chip chip = new Chip(this);
            ChipDrawable chipDrawable = ChipDrawable.createFromAttributes(this, null,0,R.style.MaterialComponents_Chip_Thin);
            chip.setChipDrawable(chipDrawable);
            chip.setText(tag.trim());
            binding.chipGroup.addView(chip);
        }

        FetchThumbnail fetchThumbnail = new FetchThumbnail(contentModel.getFields().getAssociatedLink());
        Glide.with(this)
                .load(fetchThumbnail.getHDThumbnailUrl())
                .into(binding.ivContentThumbnail);

        binding.tvDetails.getSettings().setJavaScriptEnabled(true);
        binding.tvDetails.loadData(contentModel.getFields().getDetails(), "text/html; charset=utf-8", "UTF-8");

        binding.button.setOnClickListener(view -> {
            Intent youtube = new Intent(Intent.ACTION_VIEW, Uri.parse(contentModel.getFields().getAssociatedLink()));
            startActivity(youtube);
        });

        binding.toolbar.setNavigationOnClickListener( view -> {
            onBackPressed();
        });
    }
}