package com.example.ahmedel_diasty.mas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Schedule {

    @SerializedName("subjectName")
    @Expose
    private String subjectName;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("startTime")
    @Expose
    private String startTime;

    @SerializedName("Location")
    @Expose
    private String Location;

    public Schedule(String subjectName , String type , String startTime , String location) {
        this.subjectName = subjectName;
        this.type=type;
        this.startTime=startTime;
        this.Location = location;
    }

    public Schedule() {
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    @Override
    public String toString() {
        return "SubjectName{" +
                "subjectName='" + subjectName + '\'' +
                '}';
    }
}
