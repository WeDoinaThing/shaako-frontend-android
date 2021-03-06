package com.github.meafs.recover.adapters;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.meafs.recover.R;
import com.github.meafs.recover.models.StaticRvModel;

import java.util.ArrayList;

public class StaticRvAdapter extends RecyclerView.Adapter<StaticRvAdapter.StaticRvViewHolder> {

    private final ArrayList<StaticRvModel> items;
    int row_index = -1;

    public StaticRvAdapter(ArrayList<StaticRvModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public StaticRvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_item, parent, false);
        StaticRvViewHolder staticRvViewHolder = new StaticRvViewHolder(view);
        return staticRvViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRvViewHolder holder, int position) {
        StaticRvModel currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.numberView.setText(currentItem.getNumber());
        holder.textView.setText(currentItem.getText());
        holder.linearLayout.setOnClickListener(view -> {
            row_index = position;
            notifyDataSetChanged();
        });
        holder.linearLayout.setBackgroundResource(R.drawable.static_rv_bg);
        holder.linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(currentItem.getColorResource())));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class StaticRvViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView numberView;
        ImageView imageView;
        LinearLayout linearLayout;

        public StaticRvViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.static_rv_im);
            numberView = itemView.findViewById(R.id.static_rv_number);
            textView = itemView.findViewById(R.id.static_rv_text);
            linearLayout = itemView.findViewById(R.id.linearLayout);

        }
    }
}
