package com.example.ahmedel_diasty.mas.Sqlite;

public class RowObject {
    private String activityName;
    private String description;
    private String time;

    public RowObject(String activityName, String description, String time) {
        this.activityName = activityName;
        this.description = description;
        this.time = time;
    }

    public RowObject() {
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
