package com.github.meafs.recover.models;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.pchmn.materialchips.model.ChipInterface;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symptopm symptopm = (Symptopm) o;
        return Objects.equals(Symptom, symptopm.Symptom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Symptom);
    }
}
