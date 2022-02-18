package com.github.meafs.recover.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.github.meafs.recover.R;
import com.github.meafs.recover.models.ReminderModel;
import com.github.meafs.recover.utils.dbManager;

import java.util.ArrayList;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.myviewholder> {
    ArrayList<ReminderModel> dataholder = new ArrayList<>();                                               //array list to hold the reminders
    private Context context;

    public ReminderAdapter(ArrayList<ReminderModel> dataholder) {

        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_item, parent, false);  //inflates the xml file in recyclerview
        context = parent.getContext();
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.mTitle.setText(dataholder.get(position).getTitle());                                 //Binds the single reminder objects to recycler view
        holder.mDate.setText(dataholder.get(position).getDate());
        holder.mTime.setText(dataholder.get(position).getTime());
        holder.mPatient.setText(dataholder.get(position).getPname());
        holder.reminder_rv_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder adb = new AlertDialog.Builder(context);
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete " + position);
                final int positionToRemove = position;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String title = (String) ((TextView) view.findViewById(R.id.txtTitle)).getText();
                        String date = (String) ((TextView) view.findViewById(R.id.txtDate)).getText();
                        String time = (String) ((TextView) view.findViewById(R.id.txtTime)).getText();
                        String pname = (String) ((TextView) view.findViewById(R.id.txtPname)).getText();

                        String result = new dbManager(context).deleteReminder(title, date, time, pname);
                        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();

                        dataholder.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, getItemCount());
                    }
                });
                adb.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder {

        TextView mTitle, mDate, mTime, mPatient;
        CardView reminder_rv_layout;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            mTitle = itemView.findViewById(R.id.txtTitle);                               //holds the reference of the materials to show data in recyclerview
            mDate = itemView.findViewById(R.id.txtDate);
            mTime = itemView.findViewById(R.id.txtTime);
            mPatient = itemView.findViewById(R.id.txtPname);
            reminder_rv_layout = itemView.findViewById(R.id.reminder_item_layout);
        }
    }
}