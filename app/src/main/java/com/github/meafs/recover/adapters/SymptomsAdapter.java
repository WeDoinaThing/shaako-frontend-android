package com.github.meafs.recover.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.github.meafs.recover.R;
import com.github.meafs.recover.activites.DiseaseInference;
import com.github.meafs.recover.models.SymptomCategories;

import java.util.ArrayList;

public class SymptomsAdapter extends RecyclerView.Adapter<SymptomsAdapter.MyViewHolder> {
    private ArrayList<SymptomCategories> dummyParentDataItems;
    private Button  button;
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

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Context context;
        private TextView textView_parentName;
        private ImageView textView_parentLogo;
        private LinearLayout linearLayout_childItems;
        private Button selection_done;
        public ArrayList<String> symptoms_selection = new ArrayList<>();

        MyViewHolder(View itemView, Button button) {
            super(itemView);
            context = itemView.getContext();
            textView_parentName = itemView.findViewById(R.id.tv_parentName);
            textView_parentLogo = itemView.findViewById(R.id.symp_cat);
            linearLayout_childItems = itemView.findViewById(R.id.ll_child_items);
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
                    if (isChecked){
                                Toast.makeText(context, "" + checkBox.getText().toString(), Toast.LENGTH_SHORT).show();
                                symptoms_selection.add(checkBox.getText().toString());
                        Log.i("SYMPADD", symptoms_selection.toString());

                    }
                    else {
                                Toast.makeText(context, "UNCHECKED "+ checkBox.getText().toString(), Toast.LENGTH_SHORT).show();
                                symptoms_selection.remove(symptoms_selection.size()-1);
                        Log.i("SYMPREM", symptoms_selection.toString());

                    }
                }
                );
                linearLayout_childItems.setOrientation(LinearLayout.VERTICAL);
                linearLayout_childItems.addView(checkBox, layoutParams);
            }
            textView_parentName.setOnClickListener(this);
            Log.i("SYMPPPPPP", symptoms_selection.toString());
            selection_done.setOnClickListener(v -> {
                Log.d("CLICKED" , symptoms_selection.toString());
                Intent intent = new Intent(context, DiseaseInference.class);
                intent.putStringArrayListExtra("SymptomsList", symptoms_selection);
                v.getContext().startActivity(intent);
            });

        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.tv_parentName) {
                if (linearLayout_childItems.getVisibility() == View.VISIBLE) {
                    linearLayout_childItems.setVisibility(View.GONE);
                } else {
                    linearLayout_childItems.setVisibility(View.VISIBLE);
                }
            }
//            else if (view.getId() == R.id.disease_selection_done){
//                System.out.println();
//                Intent intent = new Intent(view.getContext(), DiseaseInference.class);
//                intent.putExtra("SymptomsList", symptoms_selection);
//                view.getContext().startActivity(intent);
//            }
            else {
                TextView textViewClicked = (TextView) view;
                Toast.makeText(context, "" + textViewClicked.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        }


    }
}