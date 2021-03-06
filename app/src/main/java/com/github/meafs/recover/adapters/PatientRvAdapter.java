package com.github.meafs.recover.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.github.meafs.recover.R;
import com.github.meafs.recover.activites.PatientDetails;
import com.github.meafs.recover.models.Document;
import com.github.meafs.recover.utils.Speak;

import java.util.List;
import java.util.Random;

public class PatientRvAdapter extends RecyclerView.Adapter<PatientRvViewHolder> {

    private final List<Document> pData;
    private final Context mContext;
    private TextToSpeech ttsObject;

    public PatientRvAdapter(Context mContext, List<Document> pData, TextToSpeech ttsObject) {
        this.pData = pData;
        this.mContext = mContext;
        this.ttsObject = ttsObject;
    }

    public PatientRvAdapter(List<Document> pData, Context mContext) {
        this.pData = pData;
        this.mContext = mContext;
    }

    @Override
    public PatientRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_rv_item,
                parent, false);
        return new PatientRvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PatientRvViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.mIcon.setText(pData.get(position).getName().substring(0, 1));
        holder.mName.setText(pData.get(position).getName());
        holder.mArea.setText(pData.get(position).getRegion());
        holder.mPriority.setText(pData.get(position).getId());
//        holder.mFire.setImageResource(pData.get(position).getFire());
        Random mRandom = new Random();
        final int color = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
        ((GradientDrawable) holder.mIcon.getBackground()).setColor(color);
        holder.mFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.mFire.getColorFilter() != null) {
                    holder.mFire.clearColorFilter();
                } else {
                    holder.mFire.setColorFilter(ContextCompat.getColor(mContext,
                            R.color.colorGreen));
                }
            }
        });

        holder.rv_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Speak speak = new Speak(view.getContext());
                speak.speak(ttsObject, holder.mName.getText().toString());

                Intent mIntent = new Intent(mContext, PatientDetails.class);
                mIntent.putExtra("name", holder.mName.getText().toString());
                mIntent.putExtra("area", holder.mArea.getText().toString());
                mIntent.putExtra("priority", holder.mPriority.getText().toString());
//                mIntent.putExtra("time", holder.mEmailTime.getText().toString());
                mIntent.putExtra("age", pData.get(position).getDob());
                mIntent.putExtra("weight", pData.get(position).getWeight());
                mIntent.putExtra("sex", pData.get(position).getSex());
                mIntent.putExtra("icon", holder.mIcon.getText().toString());
                mIntent.putExtra("contact", pData.get(position).getContact());
                mIntent.putExtra("colorIcon", color);
                mIntent.putExtra("comorbidity", pData.get(position).getComorbidity());
                mIntent.putExtra("history", pData.get(position).getPatientHistory());
                mContext.startActivity(mIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return pData.size();
    }
}

class PatientRvViewHolder extends RecyclerView.ViewHolder {

    TextView mIcon;
    TextView mName;
    TextView mArea;
    TextView mPriority;
    ImageView mFire;
    ConstraintLayout rv_layout;

    PatientRvViewHolder(View itemView) {
        super(itemView);

        mIcon = itemView.findViewById(R.id.pIcon);
        mName = itemView.findViewById(R.id.pName);
        mArea = itemView.findViewById(R.id.pArea);
        mPriority = itemView.findViewById(R.id.pPriority);
        mFire = itemView.findViewById(R.id.ivFire);
        rv_layout = itemView.findViewById(R.id.layout);
    }
}