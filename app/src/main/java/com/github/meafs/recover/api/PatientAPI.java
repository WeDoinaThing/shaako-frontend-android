package com.github.meafs.recover.api;

import android.annotation.SuppressLint;
import android.util.Base64;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class PatientAPI {
    private String databaseId;
    private String containerId;
    private String method;
    private static String key = "xwu06IODbACkMxfVePHQ4j1JZIXp1gAXO9JS622VLFwYuV1VWq96HgesTIVzW1YRteRnz8TEbQIjaCzfhr3MFA==";

    public PatientAPI(String databaseId, String containerId, String method) {
        this.databaseId = databaseId;
        this.containerId = containerId;
        this.method = method;
    }

    public String getResourceId() {
        return "dbs/" + databaseId + "/colls/" + containerId;
    }

    public String getDate() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat fmt = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
        fmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = fmt.format(Calendar.getInstance().getTime()) + " GMT";
        return date.toLowerCase();
    }

    public String getStirngToSign() {

        return method.toLowerCase() + "\n"
                + "docs".toLowerCase() + "\n"
                + getResourceId() + "\n"
                + getDate() + "\n"
                + "" + "\n";
    }


    public String generateAuthHeader(String masterKeyBase64) throws Exception {

        byte[] masterKeyBytes = Base64.decode(masterKeyBase64, Base64.NO_WRAP);
        Mac mac = Mac.getInstance("HMACSHA256");
        mac.init(new SecretKeySpec(masterKeyBytes, "HMACSHA256"));

        String signature = Base64.encodeToString(mac.doFinal(getStirngToSign().toLowerCase().getBytes("UTF-8")), Base64.NO_WRAP);

        String authHeader = URLEncoder.encode("type=master&ver=1.0&sig=" + signature, "UTF-8");

        return authHeader;
    }
}
