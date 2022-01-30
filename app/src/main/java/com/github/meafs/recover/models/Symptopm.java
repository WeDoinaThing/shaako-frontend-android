package com.github.meafs.recover.models;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.pchmn.materialchips.model.ChipInterface;

public class Symptopm implements ChipInterface {

    private String Symptom;

    public Symptopm(String symptom) {
        this.Symptom = symptom;
    }

    @Override
    public Object getId() {
        return null;
    }

    @Override
    public Uri getAvatarUri() {
        return null;
    }

    @Override
    public Drawable getAvatarDrawable() {
        return null;
    }

    @Override
    public String getLabel() {
        return Symptom;
    }

    @Override
    public String getInfo() {
        return null;
    }
}
