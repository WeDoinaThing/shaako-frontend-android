package com.github.meafs.recover.utils;


import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import static android.content.Context.WINDOW_SERVICE;

public class FontHelper {
    public static void adjustFontScale(Context context, Configuration configuration) {
        if (configuration.fontScale != 1.00) {
            Log.w("FONT", "fontScale=" + configuration.fontScale);
            configuration.fontScale = (float) 1.00;
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            WindowManager wm = (WindowManager) context.getSystemService(WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(metrics);
            metrics.scaledDensity = configuration.fontScale * metrics.density;
            context.getApplicationContext().getResources().updateConfiguration(configuration, metrics);
        }
    }
}