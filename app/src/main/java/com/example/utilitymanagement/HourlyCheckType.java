package com.example.utilitymanagement;

public class HourlyCheckType {
    private String timeCheck;
    private String personCheck;

    public HourlyCheckType() {
    }

    public HourlyCheckType(String timeCheck, String personCheck) {
        this.timeCheck = timeCheck;
        this.personCheck = personCheck;
    }

    public String getTimeCheck() {
        return timeCheck;
    }

    public void setTimeCheck(String timeCheck) {
        this.timeCheck = timeCheck;
    }

    public String getPersonCheck() {
        return personCheck;
    }

    public void setPersonCheck(String personCheck) {
        this.personCheck = personCheck;
    }
}
