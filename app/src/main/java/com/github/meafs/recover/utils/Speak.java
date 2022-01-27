package com.github.meafs.recover.utils;

import android.content.Context;
import android.speech.tts.TextToSpeech;


public class Speak {
    private final Context context;
    private Speak tts;

    public Speak(Context context) {
        this.context = context;
    }

    public void speak(TextToSpeech engine, String text) {
        engine.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
