package com.github.meafs.recover.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.github.meafs.recover.models.ContentModel;
import com.github.meafs.recover.utils.FetchThumbnail;

import java.util.ArrayList;

public class ContentRecylerAdapter extends RecyclerView.Adapter<ContentRecylerAdapter.ViewHolder> {

    private ArrayList<ContentModel> list = new ArrayList<>();
    private final Context context;
    private FetchThumbnail fetchThumbnail;

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
        holder.details.setText(list.get(position).getFields().getDetails());
        fetchThumbnail = new FetchThumbnail(list.get(position).getFields().getAssociatedLink());
        Glide.with(context)
                .load(fetchThumbnail.getThumbnailUrl())
                .into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent youtube = new Intent(Intent.ACTION_VIEW, Uri.parse(list.get(position).getFields().getAssociatedLink()));
                context.startActivity(youtube);

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
        private final ImageView imageView;
        private final TextView title;
        private final TextView details;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.contentcard);
            imageView = itemView.findViewById(R.id.thumbnail);
            title = itemView.findViewById(R.id.title);
            details = itemView.findViewById(R.id.details);
        }
    }
}
