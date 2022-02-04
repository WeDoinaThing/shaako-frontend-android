package com.github.meafs.recover.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.github.meafs.recover.R;
import com.github.meafs.recover.models.EmergencyModel;
import com.github.meafs.recover.utils.Speak;

import java.util.ArrayList;

public class EmergencyAdapter extends RecyclerView.Adapter<EmergencyAdapter.ViewHolder> {

    private ArrayList<EmergencyModel> list = new ArrayList<>();
    private Context context;
    private TextToSpeech ttsObject;

    public EmergencyAdapter(ArrayList<EmergencyModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public EmergencyAdapter(ArrayList<EmergencyModel> list, Context context, TextToSpeech ttsObject) {
        this.list = list;
        this.context = context;
        this.ttsObject = ttsObject;
    }


    public void setList(ArrayList<EmergencyModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emergency_card, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.title.setText(list.get(position).getName());
        holder.number.setText(list.get(position).getPhoneNumber());

        Speak speak = new Speak(holder.cardView.getContext());

        holder.dial.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + list.get(position).getPhoneNumber()));
            view.getContext().startActivity(intent);

        });

        holder.cardView.setOnClickListener(view -> {
            speak.speak(ttsObject, list.get(position).getName());
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView dial;
        private TextView title;
        private TextView number;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.emergencyCard);
            dial = itemView.findViewById(R.id.dial_button);
            title = itemView.findViewById(R.id.hospitalTitle);
            number = itemView.findViewById(R.id.number);

        }
    }
}
