package com.example.agent;

public class MyItem {
    private String time;
    private String activityName;
    private String clientName;
    private String activityStatus;

    // Constructor
    public MyItem(String time, String activityName, String clientName, String activity_status) {
        this.time = time;
        this.activityName = activityName;
        this.clientName = clientName;
        this.activityStatus = activity_status;
    }

    public MyItem(String time, String clientName) {
        this.time = time;
        this.clientName = clientName;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }

    // Getters and setters
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}

