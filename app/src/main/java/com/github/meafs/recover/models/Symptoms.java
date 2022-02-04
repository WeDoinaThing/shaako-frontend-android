package com.github.meafs.recover.models;

import java.util.ArrayList;
import java.util.Arrays;

public class Symptoms {
    private static final ArrayList<String> skinAndNails = new ArrayList<String>(Arrays.asList(
            "itching.0", "skin_rash.1", "nodal_skin_eruptions.2",
            "yellowish_skin.32", "pus_filled_pimples.122", "blackheads.123",
            "scurring.124", "skin_peeling.125", "silver_like_dusting.126",
            "small_dents_in_nails.127", "inflammatory_nails.128", "blister.129",
            "yellow_crust_ooze.131", "red_spots_over_body.99", "brittle_nails.72",
            "bruising.66", "dischromic _patches.102"));
    private static final  ArrayList<String> stomachAndAppetite = new ArrayList<String>(Arrays.asList(
            "stomach_pain.7","acidity.8","ulcers_on_tongue.9","vomiting.11",
            "abdominal_pain.39","pain_during_bowel_movements.59","swelling_of_stomach.46",
            "indigestion.30","stomach_bleeding.114","distention_of_abdomen.115","belly_pain.100",
            "excessive_hunger.74","increased_appetite.104","loss_of_appetite.35","nausea.34"));
    private static final  ArrayList<String> face = new ArrayList<String>(Arrays.asList(
            "pain_behind_the_eyes.36","runny_nose.54","blurred_and_distorted_vision.49",
            "yellowing_of_eyes.43","redness_of_eyes.52","sunken_eyes.26","puffy_face_and_eyes.70",
            "visual_disturbances.110","red_sore_around_nose.130","watering_from_eyes.103",
            "loss_of_smell.88","drying_and_tingling_lips.76","sinus_pressure.53"
    ));
    private static final  ArrayList<String> throatAndChest = new ArrayList<String>(Arrays.asList(
            "phlegm.50","chest_pain.56","breathlessness.27","cough.24","mucoid_sputum.107",
            "rusty_sputum.108","blood_in_sputum.118","patches_in_throat.22","throat_irritation.51",
            "congestion.55"
    ));
    private static final  ArrayList<String> muscleLimbsBonesVessels = new ArrayList<String>(Arrays.asList(
            "muscle_pain.97","joint_pain.6","muscle_wasting.10","neck_pain.63","cramps.65",
            "knee_pain.78","hip_joint_pain.79","back_pain.37","weakness_in_limbs.57",
            "swollen_legs.68","muscle_weakness.80","stiff_neck.81","swelling_joints.82",
            "movement_stiffness.83","spinning_movements.84","cold_hands_and_feets.17",
            "painful_walking.121","swollen_extremeties.73","swelled_lymph_nodes.47",
            "swollen_blood_vessels.69","prominent_veins_on_calf.119"
    ));
    private static final  ArrayList<String> excretion = new ArrayList<String>(Arrays.asList(
            "spotting_ urination.13","dark_urine.33","constipation.38","diarrhoea.40",
            "yellow_urine.42","bloody_stool.61","irritation_in_anus.62","pain_in_anal_region.60",
            "bladder_discomfort.89","foul_smell_of urine.90","continuous_feel_of_urine.91",
            "polyuria.105","burning_micturition.12","passage_of_gases.92"
    ));
    private static final  ArrayList<String> riskFactors = new ArrayList<String>(Arrays.asList(
            "receiving_blood_transfusion.111","receiving_unsterile_injections.112",
            "history_of_alcohol_consumption.116","family_history.106","obesity.67",
            "irregular_sugar_level.23","acute_liver_failure.44","extra_marital_contacts.75",
            "enlarged_thyroid.71"
    ));
    private static final  ArrayList<String> mental = new ArrayList<String>(Arrays.asList(
            "depression.95","abnormal_menstruation.101","altered_sensorium.98",
            "lack_of_concentration.109","anxiety.16","mood_swings.18","restlessness.20",
            "irritability.96","unsteadiness.86")
    );
    private static final  ArrayList<String> others = new ArrayList<String>(Arrays.asList(
            "malaise.48","slurred_speech.77","loss_of_balance.85","weakness_of_one_body_side.87",
            "internal_itching.93","toxic_look_(typhos).94","coma.113","fluid_overload.1.117",
            "palpitations.120","continuous_sneezing.3","shivering.4","chills.5","fatigue.14",
            "weight_gain.15","weight_loss.19","lethargy.21","high_fever.25","sweating.28",
            "headache.31","mild_fever.41","fluid_overload.45","dehydration.29","fast_heart_rate.58",
            "dizziness.64"
    ));

    public static ArrayList<String> getSkinAndNails() {
        return skinAndNails;
    }

    public static ArrayList<String> getStomachAndAppetite() {
        return stomachAndAppetite;
    }

    public static ArrayList<String> getFace() {
        return face;
    }

    public static ArrayList<String> getThroatAndChest() {
        return throatAndChest;
    }

    public static ArrayList<String> getMuscleLimbsBonesVessels() {
        return muscleLimbsBonesVessels;
    }

    public static ArrayList<String> getExcretion() {
        return excretion;
    }

    public static ArrayList<String> getRiskFactors() {
        return riskFactors;
    }

    public static ArrayList<String> getMental() {
        return mental;
    }

    public static ArrayList<String> getOthers() {
        return others;
    }

    public static Integer getIndexOfDisease(String text){
        return Integer.valueOf(text.split("\\.")[1]);
    }
}
