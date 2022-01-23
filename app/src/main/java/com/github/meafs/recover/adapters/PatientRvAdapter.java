package com.github.meafs.recover.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.github.meafs.recover.R;
import com.github.meafs.recover.models.PatientRvModel;

import java.util.List;
import java.util.Random;

public class PatientRvAdapter extends RecyclerView.Adapter<PatientRvViewHolder> {

    private List<PatientRvModel> pData;
    private Context mContext;

    public PatientRvAdapter(Context mContext, List<PatientRvModel> pData) {
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
    public void onBindViewHolder(final PatientRvViewHolder holder, int position) {
        holder.mIcon.setText(pData.get(position).getName().substring(0, 1));
        holder.mName.setText(pData.get(position).getName());
        holder.mArea.setText(pData.get(position).getArea());
        holder.mPriority.setText(pData.get(position).getPriority());
        holder.mFire.setImageResource(pData.get(position).getFire());
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

//        holder.rv_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent mIntent = new Intent(mContext, DetailActivity.class);
//                mIntent.putExtra("sender", holder.mSender.getText().toString());
//                mIntent.putExtra("title", holder.mEmailTitle.getText().toString());
//                mIntent.putExtra("details", holder.mEmailDetails.getText().toString());
//                mIntent.putExtra("time", holder.mEmailTime.getText().toString());
//                mIntent.putExtra("icon", holder.mIcon.getText().toString());
//                mIntent.putExtra("colorIcon", color);
//                mContext.startActivity(mIntent);
//            }
//        });

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
RelativeLayout rv_layout;
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