package com.github.meafs.recover.models;

import java.util.ArrayList;

public class SymptomCategories {
    private String parentName;
    private ArrayList<SymptomChild> childDataItems;

    public SymptomCategories(String parentName, ArrayList<SymptomChild> childDataItems) {
        this.parentName = parentName;
        this.childDataItems = childDataItems;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public ArrayList<SymptomChild> getChildDataItems() {
        return childDataItems;
    }

    public void setChildDataItems(ArrayList<SymptomChild> childDataItems) {
        this.childDataItems = childDataItems;
    }
}
