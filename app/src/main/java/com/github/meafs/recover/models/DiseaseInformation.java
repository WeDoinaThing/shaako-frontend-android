package com.github.meafs.recover.models;

public class DiseaseInformation {

//         "Drug Reaction.14", "Fungal infection.15",  "Gastroenteritis.17",
//        "Hepatitis B.19", "Hepatitis C.20", "Hepatitis D.21", "Hepatitis E.22",
//         "Hyperthyroidism.24", "Hypoglycemia.25", "Hypothyroidism.26",
//         "Impetigo.27", "Migraine.30", "Osteoarthristis.31",
//         "Varicose veins.39", "hepatitis A.40"
    private static final String CommonCold = "<p style=\"color:red;\">Patient should see a an Otolaryngologist</p>" +
            "<h4>Symptoms :</h4> <p>" +
            "<ul><li>Runny or stuffy nose</li>" +
            "<li>Sore throat</li>" +
            "<li>Cough</li>" +
            "<li>Congestion</li>" +
            "<li>Slight body aches or a mild headache</li>" +
            "<li>Low-grade fever </li>" +
            "<li>Generally feeling unwell/li>" +
            "<li>Sneezing </li></ul>"+
            "<h4>Risk Factors:</h4>" +
            "<ul><li>Age</li>" +
            "<li>Weakened immune system</li>" +
            "<li>Smoking</li>" +
            "<li>Time of year</li></ul>";

    private static final String ChronicCholestasis = "<p style=\"color:red;\">Patient should see a a specialist in gastroenterology or hepatology.</p>" +
            "<h4>Symptoms :</h4> <p>" +
            "<ul><li>Jaundice, which is a yellowing of your skin and the white of your eyes.</li>" +
            "<li>dark urine.</li>" +
            "<li>light-colored stool.</li>" +
            "<li>pain in your abdomen.</li>" +
            "<li>fatigue.</li>" +
            "<li>nausea.</li></ul>"+
            "<h4>Risk Factors:</h4>" +
            "<ul><li>preexisting hepatobiliary disease</li>" +
            "<li>personal or family history of ICP</li>" +
            "<li>advanced maternal age</li></ul>";

    private static final String CervicalSpondylosis = "<p style=\"color:red;\">Patient should see a Neurologist</p>" +
            "<h4>Symptoms :</h4> <p>" +
            "<ul><li>Neck pain or stiffness. This may be the main symptom. Pain may get worse when you move your neck.</li>" +
            "<li>A nagging soreness in the neck.</li>" +
            "<li>Muscle spasms.</li>" +
            "<li>A clicking, popping or grinding sound when you move your neck.</li>" +
            "<li>Headaches.</li>" +
            "<li>Dizziness.</li></ul>"+
            "<h4>Risk Factors:</h4>" +
            "<ul><li>having a genetic tendency</li>" +
            "<li>having obesity or being overweight</li>" +
            "<li>having a sedentary lifestyle with a lack of exercise</li>" +
            "<li>having injured the spine or undergone spinal surgery</li>" +
            "<li>smoking</li></ul>";

    private static final String BronchialAsthma = "<p style=\"color:red;\">Patient should see a pulmonologist</p>" +
            "<h4>Symptoms :</h4> <p>" +
            "<ul><li>Shortness of breath.</li>" +
            "<li>Chest tightness or pain.</li>" +
            "<li>Wheezing when exhaling, which is a common sign of asthma in children.</li>" +
            "<li>Trouble sleeping caused by shortness of breath, coughing or wheezing.</li>" +
            "<li>Coughing or wheezing attacks that are worsened by a respiratory virus, such as a cold or the flu.</li></ul>"+
            "<h4>Risk Factors:</h4>" +
            "<ul><li>Having a blood relative with asthma, such as a parent or sibling.</li>" +
            "<li>Having another allergic condition</li>" +
            "<li>Being a smoker</li>" +
            "<li>Exposure to secondhand smoke</li>" +
            "<li>Being overweight</li></ul>";

    private static final String Allergy = "<p style=\"color:red;\">Patient should see an Allergist</p>" +
            "<h4>Symptoms :</h4> <p>" +
            "<ul><li>Sneezing and an Itchy, Runny or Blocked Nose </li>" +
            "<li>Itchy, Red, Watering eyes </li>" +
            "<li>Wheezing, Chest Tightness, Shortness of Breath and Cough</li>" +
            "<li>Raised, Itchy, Red Rash</li>" +
            "<li>Swollen Lips, Tongue, Eyes of Face</li>" +
            "<li>Dry, Red and Cracked Skin</li></ul>" +
            "<h4>Risk Factors:</h4>" +
            "<ul><li>Have a family history of asthma or allergies</li>" +
            "<li>Are a child</li>" +
            "<li>Have Asthma</li></ul>";

    private static final String TB = "<p style=\"color:red;\">Patient should see a doctor specializing in throat and chest</p>" +
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

    private static final String Diabetes = "<p style=\"color:red;\">Patient should see a doctor specializing in Endocrinology</p>" +
            "<h4>Symptoms: </h4> <p> " +
            "<ul> <li>increased hunger, but extreme fatigue</li>" +
            "<li>increased thirst</li>" +
            "<li>weight loss</li>" +
            "<li>frequent urination - more than 4-7 times in 24 hours</li>" +
            "<li>blurry vision</li>" +
            "<li>sores that don’t heal</li>" +
            "<li>Dry mouth and itchy skin</li>" +
            "<li>Yeast Infection</li></ul>" +
            "<h4> Risk Factors: </h4>" +
            "<ul><li>Family history </li> " +
            "<li>Disease in the pancreas </li>" +
            "<li>Sedentary lifestyle</li>" +
            "<li>Overweight</li></ul>";

    private static final String AIDS = "<p style=\"color:red;\">Patient should see a doctor specializing in Medicine</p>" +
            "<h4>Symptoms: </h4> <p> " +
            "<ul>" +
            "<li> Rapid weight loss</li>" +
            "<li> Recurring fever or profuse night sweats</li>" +
            " <li> Extreme and unexplained tiredness</li>" +
            "<li> Prolonged swelling of the lymph glands in the armpits, groin, or neck</li>" +
            "<li> Diarrhea that lasts for more than a week</li>" +
            "<li> Sores of the mouth, anus, or genitals</li>" +
            "<li> Red, brown, pink, or purplish blotches on or under the skin or inside the mouth, nose, or eyelids</li>" +
            "<li> Memory loss, depression, and other neurologic disorders</li>" +
            "</ul>" +
            "<h4>Risk Factors: </h4> <p> " +
            "<ul>" +
            "<li> Having job in healthcare, prison or labs</li>" +
            "<li> Having previous history of STI </li>" +
            "<li> Drug injections, blood transfusions</li>" +
            "<li>Having unprotected sex </li>" +
            "</ul>";

    private static final String Malaria = "<p style=\"color:red;\">Patient should see a doctor specializing in Endocrinology</p>" +
            "<h4>Symptoms: </h4> <p> " +
            "<ul>" +
            "<li>Flu like symptoms (may be cyclic in 6-24 hours), eg : high fever and chills, fatigue, night sweats, shaking/shivering</li>" +
            "<li>Muscle pain, low energy, rapid heart rate, nausea, mild jaundice, vomiting, and diarrhea. </li>" +
            "</ul>" +
            "<h4>Risk Factors: </h4> <p> " +
            "<ul>" +
            "<li>Young children and infants </li>" +
            "<li>Older adults </li>" +
            "<li>Travelers coming from areas with no malaria </li>" +
            "<li>Pregnant women and their unborn children</li>" +
            "</ul>";
    private static final String Vertigo = "<p style=\"color:red;\">Patient should see a doctor specializing in Endocrinology</p>" +
            "<h4>Symptoms: </h4> <p> " +
            "<ul>" +
            "<li>Dizziness and double visions </li>" +
            "<li> Sweating, Nausea or Vomiting</li> " +
            "<li> Difficulty swallowing or sliurred speech</li>" +
            " <li> Weak limbs and balance problems</li>" +
            "</ul>" +
            "<h4>Risk Factors: </h4> <p> " +
            "<ul>" +
            "<li>Having any medical condition that affects balance or your ears, Having an inner ear infection </li>" +
            "<li> Experiencing high levels of stress, Taking certain drugs, especially antidepressants or antipsychotics</li>" +
            "<li> Suffering a head injury</li>" +
            "<li> Being a woman or being aged over 50 or being an alcoholic</li>" +
            "</ul>";
    private static final String alcoholic_hepa = "<p style=\"color:red;\">Patient should see a doctor specializing in Endocrinology</p>" +
            "<h4>Symptoms: </h4> <p> " +
            "<ul>" +
            "<li>Loss of appetite</li>" +
            "<li>Nausea and vomiting</li>" +
            "<li>Abdominal tenderness</li>" +
            "<li>Fatigue and weakness</li>" +
            "<li>Confusion and behavior changes due to a buildup of toxins</li>" +
            "</ul>" +
            "<h4>Risk Factors: </h4> <p> " +
            "<ul>" +
            "<li>Being a woman or being overweight or being black or Hispanic</li>" +
            "<li> Family history</li>" +
            "<li> Binge drinking tendencies</li>" +
            "</ul>";
    private static final String Acne = "<p style=\"color:red;\">Patient should see a doctor specializing in Endocrinology</p>" +
            "<h4>Symptoms: </h4> <p> " +
            "<ul>" +
            " <li>Pimples (pustules), which are papules with pus at their tips</li>" +
            "<li> Large, solid, painful lumps under the skin (nodules)</li>" +
            "<li>Whiteheads (closed plugged pores) or Blackheads (open plugged pores)</li>" +
            "<li> Small red, tender bumps (papules)</li>" +
            "</ul>" +
            "<h4>Risk Factors: </h4> <p> " +
            "<ul>" +
            " <li>most common in teenagers</li>" +
            " <li> common during puberty or pregnancy</li>" +
            " <li> family history </li>" +
            "<li> skin comes into contact with oil or oily lotions and creams or friction or pressure </li>" +
            "</ul>";
    private static final String arthritis = "<p style=\"color:red;\">Patient should see a doctor specializing in Endocrinology</p>" +
            "<h4>Symptoms: </h4> <p> " +
            "<ul>" +
            "<li>pain and stiffness in joints</li>" +
            " <li>swelling and redness in joints</li> " +
            "<li>Decreased range of motion</li>" +
            "</ul>" +
            "<h4>Risk Factors: </h4> <p> " +
            "<ul>" +
            "<li>Family history</li> " +
            "<li>Risk increases with age</li>" +
            "<li>Previous injuries in joints</li>" +
            "<li>Being a woman pr being obese</li>" +
            "</ul>";
    private static final String chicken_pox = "<p style=\"color:red;\">Patient should see a doctor specializing in Endocrinology</p>" +
            "<h4>Symptoms: </h4> <p> " +
            "<ul>" +
            "<li>Raised pink or red bumps (papules), which break out over several days</li> " +
            "<li>Headache,Tiredness and a general feeling of being unwell (malaise)</li> " +
            "<li>Small fluid-filled blisters (vesicles), which form in about one day and then break and leak</li>" +
            "<li>Crusts and scabs, which cover the broken blisters and take several more days to heal</li>" +
            "</ul>" +
            "<h4>Risk Factors: </h4> <p> " +
            "<ul>" +
            "<li>haven't had the chickenpox vaccine.</li>" +
            "<li>work in child care or school settings to be vaccinated</li> " +
            "</ul>";
    private static final String dengue = "<p style=\"color:red;\">Patient should see a doctor specializing in Endocrinology</p>" +
            "<h4>Symptoms: </h4> <p> " +
            "<ul>" +
            "<li>NauseaVomiting</li>" +
            "<li>Swollen glands and rash</li>" +
            " <li> Pain behind the eyes</li>" +
            "<li>Fatigue, irritability or restlessness</li> " +
            "<li>Bleeding from your gums or nose Blood in your urine, stools or vomit, Bleeding under the skin, which might look like bruising</li>" +
            "</ul>" +
            "<h4>Risk Factors: </h4> <p> " +
            "<ul>" +
            "<li> Being in tropical and subtropical areas</li>" +
            "<li> Previous infection with a dengue fever</li>" +
            "</ul>";
    private static final String piles = "<p style=\"color:red;\">Patient should see a doctor specializing in Endocrinology</p>" +
            "<h4>Symptoms: </h4> <p> " +
            "<ul>" +
            "<li>Pain or discomfort, swelling, Itching or irritation in your anal region</li>" +
            "<li>e small amounts of bright red blood on your toilet tissue or in the toilet.</li>" +
            " <li>A hard lump near your anus</li>" +
            "<li>A hemorrhoid to push through the anal opening</li> " +
            "</ul>" +
            "<h4>Risk Factors: </h4> <p> " +
            "<ul>" +
            "<li> risk of hemorrhoids increases with age</li>" +
            "<li>n risk of hemorrhoids increases with pregnancy</li>" +
            "</ul>";
    private static final String gerd = "<p style=\"color:red;\">Patient should see a doctor specializing in Endocrinology</p>" +
            "<h4>Symptoms: </h4> <p> " +
            "<ul>" +
            "<li>A burning sensation in your chest (heartburn), usually after eating, which might be worse at night</li>" +
            "<li>Regurgitation of food or sour liquid</li>" +
            " <li>Difficulty swallowing</li>" +
            "<li>Sensation of a lump in your throat</li> " +
            "<li>New or worsening asthma</li>" +
            "</ul>" +
            "<h4>Risk Factors: </h4> <p> " +
            "<ul>" +
            "<li>Eating large meals or eating late at night</li>" +
            "<li>Eating certain foods (triggers) such as fatty or fried foods</li>" +
            "<li>Drinking certain beverages, such as alcohol or coffee</li>" +
            "<li>Taking certain medications, such as aspirin</li>" +
            "<li>Obesity or pregnancy</li>" +
            "</ul>";
    private static final String uti = "<p style=\"color:red;\">Patient should see a doctor specializing in Endocrinology</p>" +
            "<h4>Symptoms: </h4> <p> " +
            "<ul>" +
            "<li>A strong, persistent urge to urinate</li>" +
            "<li>A burning sensation when urinating</li>" +
            " <li>Passing frequent, small amounts of urine that appears cloudy or  red, bright pink or cola-colored — a sign of blood in the urine</li>" +
            "<li> Strong-smelling urine</li> " +
            "<li> Pelvic pain</li>" +
            "</ul>" +
            "<h4>Risk Factors: </h4> <p> " +
            "<ul>" +
            "<li>Being a woman</li>" +
            "<li>Being sexually active</li>" +
            "<li>Menopause or  recent urinary procedure</li>" +
            "<li>Having Diabetes and other diseases that impair the immune system</li>" +
            "<li>Urinary tract abnormalities</li>" +
            "<li>Certain types of birth control like diaphragm</li>" +
            "</ul>";

    private static final String typhoid = "<p style=\"color:red;\">Patient should see a doctor specializing in Endocrinology</p>" +
            "<h4>Symptoms: </h4> <p> " +
            "<ul>" +
            "<li>Fever that starts low and increases daily, possibly reaching as high as 104.9 F (40.5 C)</li>" +
            "<li>Headache Weakness and fatigue Muscle aches</li>" +
            " <li>Loss of appetite and weight loss</li>" +
            "<li>Extremely swollen stomach</li> " +
            "<li> Become delirious</li>" +
            "</ul>" +
            "<h4>Risk Factors: </h4> <p> " +
            "<ul>" +
            "<li>Being in areas where typhoid fever is established</li>" +
            "<li>Work as a clinical microbiologist handling Salmonella typhi bacteria</li>" +
            "<li>Have close contact with someone who is infected or has recently been infected with typhoid fever\n</li>" +
            "<li>Drink water polluted by sewage that contains Salmonella typhi</li>" +
            "</ul>";
    private static final String pneumonia = "<p style=\"color:red;\">Patient should see a doctor specializing in Endocrinology</p>" +
            "<h4>Symptoms: </h4> <p> " +
            "<ul>" +
            "<li>Chest pain when you breathe or cough</li>" +
            "<li>Lower than normal body temperature (in adults older than age 65 and people with weak immune systems)</li>" +
            " <li>Nausea, vomiting or diarrhea</li>" +
            "<li>Confusion or changes in mental awareness (in adults age 65 and older)</li> " +
            "<li>Fatigue, Fever, sweating and shaking chills</li>" +
            "</ul>" +
            "<h4>Risk Factors: </h4> <p> " +
            "<ul>" +
            "<li>Children who are 2 years old or younger and People who are age 65 or older</li>" +
            "<li> Being  in a hospital intensive care unit</li>" +
            "<li> If already have asthma, chronic obstructive pulmonary disease (COPD) or heart disease or other immuno-suppressing diseases</li>" +
            "</ul>";
    private static final String jaundice = "<p style=\"color:red;\">Patient should see a doctor specializing in Endocrinology</p>" +
            "<h4>Symptoms: </h4> <p> " +
            "<ul>" +
            "<li>a yellow tinge to the skin and the whites of the eyes, normally starting at the head and spreading down the body</li>" +
            "<li>pale stools,  dark urine</li>" +
            " <li> fever, vomiting, itchiness, weight loss and Flu-like symptoms.</li>" +
            "</ul>" +
            "<h4>Risk Factors: </h4> <p> " +
            "<ul>" +
            "<li>Premature babies or significant bruising during birth or If the mother's blood type is different from her baby's</li>" +
            "<li> East Asian ancestry increases risk of developing jaundice</li>" +
            "<li>People who have hepatitis and drink excessive alcohol</li>" +
            "</ul>";
    private static final String heart_attack = "<p style=\"color:red;\">Patient should see a doctor specializing in Endocrinology</p>" +
            "<h4>Symptoms: </h4> <p> " +
            "<ul>" +
            "<li>Premature</li>" +
            "<li>Pressure, tightness, pain, or a squeezing or aching sensation in your chest or arms that may spread to your neck, jaw or back</li>" +
            " <li>Nausea, indigestion, heartburn or abdominal pain</li>" +
            "<li>Cold sweat Fatigue Lightheadedness or sudden dizziness</li> " +
            "<li></li>" +
            "</ul>" +
            "<h4>Risk Factors: </h4> <p> " +
            "<ul>" +
            "<li>Men age 45 or older and women age 55 or older are at higher risks.</li>" +
            "<li> Having high blood pressure, high cholesterol or obesity</li>" +
            "<li> Taking tobacco or other illicit drugs</li>" +
            "<li> having family history or diabetes</li>" +
            "</ul>";
    private static final String peptic_ulcer = "<p style=\"color:red;\">Patient should see a doctor specializing in Endocrinology</p>" +
            "<h4>Symptoms: </h4> <p> " +
            "<ul>" +
            "<li>Gnawing or burning pain in your middle or upper stomach between meals or at night.</li>" +
            "<li>Pain that temporarily disappears if you eat something or take an antacid.</li>" +
            "<li>Dark or black stool (due to bleeding).</li>" +
            "<li>Nausea or vomiting.</li>" +
            "<li>Severe pain in your mid- to upper abdomen.</li>" +
            "</ul>" +
            "<ul>" +
            "<li>Frequent use of nonsteroidal anti-inflammatory drugs</li>" +
            "<li>A family history of ulcers.</li>" +
            "<li>Illness such as liver, kidney or lung disease.</li>" +
            "<li>Regularly drinking alcohol or Smoking.</li>" +
            "</ul>";
    private static final String paralysis = "<p style=\"color:red;\">Patient should see a doctor specializing in Endocrinology</p>" +
            "<h4>Symptoms: </h4> <p> " +
            "<ul>" +
            "<li>Sudden, extremely severe headache\n</li>" +
            "<li>Seizures with no previous history of seizures\n</li>" +
            "<li>Weakness in an arm or leg</li>" +
            "<li>Difficulty speaking or understanding speech, Difficulty swallowing, Difficulty writing or reading</li>" +
            "<li>Loss of fine motor skills, such as hand tremors, Loss of coordination, Loss of balance, Loss of consciousness</li>" +
            "<li>Weakness in an arm or leg</li>" +
            "</ul>" +
            "<ul>" +
            "<li>Older age</li>" +
            "<li>Cigarette smoking or Drug abuse, particularly the use of cocaine</li>" +
            "<li>Heavy alcohol consumption</li>" +
            "<li>High blood pressure (hypertension)</li>" +
            "<li>Family history of brain aneurysm</li>" +
            "</ul>";
    private static final String psoriasis = "<p style=\"color:red;\">Patient should see a doctor specializing in Endocrinology</p>" +
            "<h4>Symptoms: </h4> <p> " +
            "<ul>" +
            "<li>dry or cracked skin that can bleed\n</li>" +
            "<li>thickened, pitted, or ridged nails</li>" +
            "<li>swollen and stiff joints</li>" +
            "<li>  appears as itchy, red patches of skin covered with silvery scales</li>" +
            "<li> can range from a few flaky spots to large scaly areas</li>" +
            "</ul>" +
            "<ul>" +
            "<li> areas of skin where vaccinations, sunburns, scratches, or other injuries have occurred.</li>" +
            "<li> a compromised immune system</li>" +
            "<li>Having a parent with psoriasis</li>" +
            "<li> people with fairer complexions are typically more likely to develop psoriasis</li>" +
            "<li> Obesity, tobacco use, alcohol consumptions</li>" +
            "<li>living in colder climates</li>" +
            "</ul>";
    private static final String hypertension = "<p style=\"color:red;\">Patient should see a doctor specializing in Endocrinology</p>" +
            "<h4>Symptoms: </h4> <p> " +
            "<ul>" +
            "<li>Most people with high blood pressure have no signs or symptoms, even if blood pressure readings reach dangerously high levels</li>" +
            "<li>There’s only one way to know if you have high blood pressure: Have a doctor or other health professional measure it</li>" +
            "</ul>" +
            "<ul>" +
            "<li> risk of hypertension increases with age</li>" +
            "<li>  High blood pressure is particularly common among people of African heritage </li>" +
            "<li> Family history</li>" +
            "<li> Obesity and lack of physical activity</li>" +
            "<li> Using tobacco, drinking alcohols, too much salt or too little potassium in diet</li>" +
            "<li> High level of stress or having immuno-comprimised disease</li>" +
            "</ul>";
    private static final String impetigo = "<p style=\"color:red;\">Patient should see a doctor specializing in Endocrinology</p>" +
            "<h4>Symptoms: </h4> <p> " +
            "<ul>" +
            "<li> Reddish sores, often around the nose and mouth</li>" +
            "<li> Sores quickly rupture, ooze for a few days and then form a honey-colored crust</li>" +
            "<li> Sores can spread to other areas of the body through touch, clothing and towels. </li>" +
            "<li> Itching and soreness are generally mild.</li>" +
            "</ul>" +
            "<ul>" +
            "<li> occurs most commonly in children ages 2 to 5</li>" +
            "<li> spreads easily within families, in crowded settings, such as schools and child care facilities, and from participating in sports that involve skin-to-skin contact.</li>" +
            "<li>are more common in warm, humid weather</li>" +
            "<li>Children with other skin conditions, such as atopic dermatitis (eczema), are more likely to develop impetigo. Older adults, people with diabetes or people with a weakened immune system are also more likely to get it.</li>" +
            "</ul>";
    public static String getDiabetes() {
        return Diabetes;
    }

    public static String getAIDS() {
        return AIDS;
    }

    public static String getMalaria() {
        return Malaria;
    }

    public static String getVertigo() {
        return Vertigo;
    }

    public static String getAlcoholic_hepa() {
        return alcoholic_hepa;
    }

    public static String getAcne() {
        return Acne;
    }

    public static String getArthritis() {
        return arthritis;
    }

    public static String getChicken_pox() {
        return chicken_pox;
    }

    public static String getDengue() {
        return dengue;
    }

    public static String getPiles() {
        return piles;
    }

    public static String getPeptic_ulcer() {
        return peptic_ulcer;
    }

    public static String getParalysis() {
        return paralysis;
    }

    public static String getPsoriasis() {
        return psoriasis;
    }

    public static String getHypertension() {
        return hypertension;
    }

    public static String getImpetigo() {
        return impetigo;
    }

    public static String getGerd() {
        return gerd;
    }

    public static String getUti() {
        return uti;
    }

    public static String getTyphoid() {
        return typhoid;
    }

    public static String getPneumonia() {
        return pneumonia;
    }

    public static String getJaundice() {
        return jaundice;
    }

    public static String getHeart_attack() {
        return heart_attack;
    }

    public static String getTB() {
        return TB;
    }
}