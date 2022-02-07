package com.github.meafs.recover.models;

public class DiseaseInformation {


    private static final String TB ="<p style=\"color:red;\">Patient should see a doctor specializing in throat and chest</p>" +
            "<h4>Symptoms :</h4> <p>" +
            "<ul><li>Cough lasting more than 3 weeks</li>" +
            "<li>Coughing up blood or sputum (phlegm)</li>" +
            "<li>Chest pain</li>" +
            "<li>Shortness of breath</li>" +
            "<li>Swollen lymph nodes</li>" +
            "<li>Fever, chills, night sweats</li></ul>" +
            "<h4>Risk Factors:</h4>" +
            "<ul><li>Having diabetes, End stage Kidney disease, or Cancers</li>" +
            "<li>Malnutrition</li>" +
            "<li>Using tobacco or alcohol for long periods of time</li>" +
            "<li>a diagnosis of HIV or having another immune-system-compromising situation</li></ul>";
    public static String getTB() {
        return TB;
    }
}
