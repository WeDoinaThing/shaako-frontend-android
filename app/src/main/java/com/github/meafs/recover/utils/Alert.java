package com.github.meafs.recover.utils;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

public class Alert {
    private Context context;
    private String alertMessage;
    private String accept;
    private String deny;
    private AlertDialog.Builder alertBuilder;

    public Alert(Context context, String alertMessage, String accept, String deny) {
        this.context = context;
        this.alertMessage = alertMessage;
        this.accept = accept;
        this.deny = deny;
    }

//    public AlertDialog.Builder CreateAlert() {
//        return new AlertDialog.Builder(context).setTitle(alertMessage)
//                .setPositiveButton(accept,  new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(context, "Hey", Toast.LENGTH_SHORT).show();
//                    }})
//                .setNegativeButton(deny, null)
//                .setMessage(alertMessage);
//

//    public AlertDialog.Builder CreateAlert() {
//
//
//        alertBuilder = new AlertDialog.Builder(context).setMessage("Are you sure you want to delete this entry?")
//               .setView(R.layout.alert_layout);
//
////                    .setPositiveButton(accept, new DialogInterface.OnClickListener() {
////                        public void onClick(DialogInterface dialog, int which) {
////                            Toast.makeText(context, "Hey", Toast.LENGTH_SHORT).show();
////                        }
////                    })
////                    .setNegativeButton(deny, null)
////                    .setMessage(alertMessage);
//
//        alertBuilder
//
//        }


}
