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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.github.meafs.recover.R;
import com.github.meafs.recover.models.NotificationModel;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    int row_index = -1;
    private final ArrayList<NotificationModel> items;

    public NotificationAdapter(ArrayList<NotificationModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
        NotificationViewHolder staticRvViewHolder = new NotificationViewHolder(view);
        return staticRvViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        NotificationModel currentItem = items.get(position);
        holder.numberView.setText(currentItem.getNumber());
        holder.textView.setText(currentItem.getText());
        holder.linearLayout.setOnClickListener(view -> {
            row_index = position;
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView numberView;
        CardView linearLayout;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            numberView = itemView.findViewById(R.id.notif_date);
            textView = itemView.findViewById(R.id.notif_text);
            linearLayout = itemView.findViewById(R.id.notif_card);

        }
    }
}
