package com.github.meafs.recover.adapters;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.github.meafs.recover.R;
import com.github.meafs.recover.models.SymptomCategories;

import java.util.ArrayList;

public class SymptomsAdapter extends RecyclerView.Adapter<SymptomsAdapter.MyViewHolder> {
    private ArrayList<SymptomCategories> dummyParentDataItems;

    public SymptomsAdapter(ArrayList<SymptomCategories> dummyParentDataItems) {
        this.dummyParentDataItems = dummyParentDataItems;
    }

    @Override
    public SymptomsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.symptom_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SymptomsAdapter.MyViewHolder holder, int position) {
        SymptomCategories dummyParentDataItem = dummyParentDataItems.get(position);
        holder.textView_parentName.setText(dummyParentDataItem.getParentName());
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
            TextView currentTextView = (TextView) holder.linearLayout_childItems.getChildAt(textViewIndex);
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
        private LinearLayout linearLayout_childItems;

        MyViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            textView_parentName = itemView.findViewById(R.id.tv_parentName);
            linearLayout_childItems = itemView.findViewById(R.id.ll_child_items);
            linearLayout_childItems.setVisibility(View.GONE);
            int intMaxNoOfChild = 0;
            for (int index = 0; index < dummyParentDataItems.size(); index++) {
                int intMaxSizeTemp = dummyParentDataItems.get(index).getChildDataItems().size();
                if (intMaxSizeTemp > intMaxNoOfChild) intMaxNoOfChild = intMaxSizeTemp;
            }
            for (int indexView = 0; indexView < intMaxNoOfChild; indexView++) {
                CheckBox checkBox = new CheckBox(context);
                checkBox.setId(indexView);
                checkBox.setGravity(Gravity.LEFT);
                TextView textView = new TextView(context);
                textView.setId(indexView);
                textView.setPadding(10, 10, 10, 10);
                textView.setGravity(Gravity.CENTER);
//                textView.setBackground(ContextCompat.getDrawable(context, R.drawable.background_sub_module_text));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                textView.setOnClickListener(this);
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked){
                                Toast.makeText(context, "" + textView.getText().toString(), Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(context, "UNCHECKED "+ textView.getText().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                );
                linearLayout_childItems.setOrientation(LinearLayout.VERTICAL);
                linearLayout_childItems.addView(checkBox, layoutParams);
                linearLayout_childItems.addView(textView, layoutParams);
            }
            textView_parentName.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.tv_parentName) {
                if (linearLayout_childItems.getVisibility() == View.VISIBLE) {
                    linearLayout_childItems.setVisibility(View.GONE);
                } else {
                    linearLayout_childItems.setVisibility(View.VISIBLE);
                }
            } else {
                TextView textViewClicked = (TextView) view;
                Toast.makeText(context, "" + textViewClicked.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        }


    }
}