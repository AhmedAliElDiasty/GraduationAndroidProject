package com.example.mohamed_hany.question.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("admin_Questions")
    private List<Question> questions;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> musics) {
        this.questions = questions;
    }

    // ...
}