package com.github.meafs.recover.models;

public class DiseaseInformation {


    private static final String TB = "Tuberculosis\n" +
            "\tSymptoms : \n" +
            "cough lasting more than 3 weeks\n" +
            "coughing up blood or sputum (phlegm)\n" +
            "chest pain\n" +
            "Shortness of breath\n" +
            "Swollen lymph nodes\n" +
            "Fever, chills, night sweats\n" +
            "\tRisk Factors: \n" +
            "having diabetes, end stage kidney disease, or cancers\n" +
            "malnutrition\n" +
            "using tobacco or alcohol for long periods of time\n" +
            "a diagnosis of HIV or having another immune-system-compromising situation\n" ;


    public static String getTB() {
        return TB;
    }
}
