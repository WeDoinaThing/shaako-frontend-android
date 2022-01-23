package com.github.meafs.recover.adapters;

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

    private ArrayList<StaticRvModel> items;
    int row_index = -1;
    public StaticRvAdapter (ArrayList<StaticRvModel> items) {
        this.items = items;
    }
    @NonNull
    @Override
    public StaticRvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_item, parent, false) ;
        StaticRvViewHolder staticRvViewHolder = new StaticRvViewHolder(view);
        return  staticRvViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRvViewHolder holder, int position) {
        StaticRvModel currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.textView.setText(currentItem.getText());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                notifyDataSetChanged();
            }
        });
        if (row_index == position) {
            holder.linearLayout.setBackgroundResource(R.drawable.static_rv_bg);
        }
        else {
            holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected_bg);

        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class  StaticRvViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;

        public   StaticRvViewHolder (@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.static_rv_im);
            textView = itemView.findViewById(R.id.static_rv_text);
            linearLayout = itemView.findViewById(R.id.linearLayout);

        }
    }
}
