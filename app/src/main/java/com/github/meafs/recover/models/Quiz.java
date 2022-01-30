package com.github.meafs.recover.models;

import java.util.ArrayList;

public class Quiz {
    private String question;
    private ArrayList<String> choices;
    private Integer answer;

    public Quiz(String question, ArrayList<String> choices, Integer answer){
        this.question = question;
        this.choices = choices;
        this.answer = answer;
    }
    public Quiz(){

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }
}
