package com.github.meafs.recover.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.github.meafs.recover.R;
import com.github.meafs.recover.activites.DiseaseInference;
import com.github.meafs.recover.models.SymptomCategories;

import java.util.ArrayList;

public class SymptomsAdapter extends RecyclerView.Adapter<SymptomsAdapter.MyViewHolder> {
    private final ArrayList<SymptomCategories> dummyParentDataItems;
    private final Button button;
    private final ArrayList<String> symptoms_selection = new ArrayList<>();

    public SymptomsAdapter(ArrayList<SymptomCategories> dummyParentDataItems, Button button) {
        this.dummyParentDataItems = dummyParentDataItems;
        this.button = button;
    }

    @Override
    public SymptomsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.symptom_list, parent, false);
        return new MyViewHolder(itemView, button);
    }

    @Override
    public void onBindViewHolder(SymptomsAdapter.MyViewHolder holder, int position) {
        SymptomCategories dummyParentDataItem = dummyParentDataItems.get(position);
        holder.textView_parentName.setText(dummyParentDataItem.getParentName());
        holder.textView_parentLogo.setImageResource(dummyParentDataItem.getLogo());
        //
        int noOfChildTextViews = holder.linearLayout_childItems.getChildCount();
        for (int index = 0; index < noOfChildTextViews; index++) {
            TextView currentTextView = (TextView) holder.linearLayout_childItems.getChildAt(index);
            currentTextView.setVisibility(View.VISIBLE);
        }

        int noOfChild = dummyParentDataItem.getChildDataItems().size();
        if (noOfChild < noOfChildTextViews) {
            for (int index = noOfChild; index < noOfChildTextViews; index++) {
                TextView currentTextView = (TextView) holder.linearLayout_childItems.getChildAt(index);
                currentTextView.setVisibility(View.GONE);
            }
        }
        for (int textViewIndex = 0; textViewIndex < noOfChild; textViewIndex++) {
            CheckBox currentTextView = (CheckBox) holder.linearLayout_childItems.getChildAt(textViewIndex);
            currentTextView.setText(dummyParentDataItem.getChildDataItems().get(textViewIndex).getChildName());
        }
    }

    @Override
    public int getItemCount() {
        return dummyParentDataItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private final TextView textView_parentName;
        private final ImageView textView_parentLogo;
        private final LinearLayout linearLayout_childItems;
        private final CardView cardView;
        private final Button selection_done;

        MyViewHolder(View itemView, Button button) {
            super(itemView);
            context = itemView.getContext();
            textView_parentName = itemView.findViewById(R.id.tv_parentName);
            textView_parentLogo = itemView.findViewById(R.id.symp_cat);
            linearLayout_childItems = itemView.findViewById(R.id.ll_child_items);
            cardView = itemView.findViewById(R.id.card_view);
            selection_done = button;
            linearLayout_childItems.setVisibility(View.GONE);
            int intMaxNoOfChild = 0;
            for (int index = 0; index < dummyParentDataItems.size(); index++) {
                int intMaxSizeTemp = dummyParentDataItems.get(index).getChildDataItems().size();
                if (intMaxSizeTemp > intMaxNoOfChild) intMaxNoOfChild = intMaxSizeTemp;
            }
            for (int indexView = 0; indexView < intMaxNoOfChild; indexView++) {
                CheckBox checkBox = new CheckBox(context);
                checkBox.setId(indexView);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                            if (isChecked) {
                                symptoms_selection.add(checkBox.getText().toString());
//                                Log.i("SYMPADD", symptoms_selection.toString() + symptoms_selection.size());

                            } else {
                                symptoms_selection.remove(symptoms_selection.size() - 1);
//                                Log.i("SYMPREM", symptoms_selection.toString() + symptoms_selection.size());

                            }
                        }
                );
                linearLayout_childItems.setOrientation(LinearLayout.VERTICAL);
                linearLayout_childItems.addView(checkBox, layoutParams);
            }
            selection_done.setOnClickListener(v -> {
                if (symptoms_selection.size() < 4) {
                    Toast.makeText(context, "Please Select at least 3 Symptoms", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(context, DiseaseInference.class);
                    intent.putStringArrayListExtra("SymptomsList", symptoms_selection);
                    v.getContext().startActivity(intent);
                    ((Activity) v.getContext()).finish();
                }
            });

            cardView.setOnClickListener(view -> {
                if (linearLayout_childItems.getVisibility() == View.VISIBLE) {
                    linearLayout_childItems.setVisibility(View.GONE);
                } else {
                    linearLayout_childItems.setVisibility(View.VISIBLE);
                }
            });
        }
    }
}