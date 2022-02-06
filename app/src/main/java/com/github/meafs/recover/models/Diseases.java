package com.github.meafs.recover.models;

import java.util.Arrays;
import java.util.List;

public class Diseases {
    private static final List<String> diseases = Arrays.asList(
            "(vertigo) Paroymsal  Positional Vertigo.0", "AIDS.1", "Acne.2",
            "Alcoholic hepatitis.3", "Allergy.4", "Arthritis.5", "Bronchial Asthma.6",
            "Cervical spondylosis.7", "Chicken pox.8", "Chronic cholestasis.9",
            "Common Cold.10", "Dengue.11", "Diabetes .12", "Dimorphic hemmorhoids(piles).13",
            "Drug Reaction.14", "Fungal infection.15", "GERD.16", "Gastroenteritis.17",
            "Heart attack.18", "Hepatitis B.19", "Hepatitis C.20", "Hepatitis D.21", "Hepatitis E.22",
            "Hypertension .23", "Hyperthyroidism.24", "Hypoglycemia.25", "Hypothyroidism.26",
            "Impetigo.27", "Jaundice.28", "Malaria.29", "Migraine.30", "Osteoarthristis.31",
            "Paralysis (brain hemorrhage).32", "Peptic ulcer diseae.33", "Pneumonia.34",
            "Psoriasis.35", "Tuberculosis.36", "Typhoid.37", "Urinary tract infection.38",
            "Varicose veins.39", "hepatitis A.40"
    );

    public static String getDisease(int diseaseIndex) {
        return diseases.get(diseaseIndex).split("\\.")[0];
    }
}
