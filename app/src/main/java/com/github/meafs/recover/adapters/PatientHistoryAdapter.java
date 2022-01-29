package com.github.meafs.recover.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.meafs.recover.R;

import java.util.ArrayList;

public class PatientHistoryAdapter extends RecyclerView.Adapter<PatientHistoryAdapter.ViewHolder> {
    private ArrayList<String> history_list;

    public PatientHistoryAdapter(ArrayList<String> history_list){
        this.history_list = history_list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View historyItems = layoutInflater.inflate(R.layout.patient_history_rv_item, parent, false);
        return new ViewHolder(historyItems);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String history = history_list.get(position);
        holder.historyDetails.setText(history.split("\\|")[0].trim());
        holder.date.setText(history.split("\\|")[1].trim());
    }

    @Override
    public int getItemCount() {
        return history_list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView historyDetails;
        public TextView date;
        public ViewHolder(View itemView) {
            super(itemView);
            this.historyDetails = itemView.findViewById(R.id.history_details);
            this.date = itemView.findViewById(R.id.history_date);
        }
    }
}
