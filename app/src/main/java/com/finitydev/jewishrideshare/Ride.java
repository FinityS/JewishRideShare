package com.finitydev.jewishrideshare;


/**
 * Created by finit on 12/11/2017.
 */

public class Ride {

    private String startingCity;
    private String startingState;
    private String endingCity;
    private String endingState;
    private Date date;
    private Time time;
    private int space;
    private String gender;
    private String notes;


    public Ride(String startingCity, String startingState, String endingCity, String endingState, Date date, Time time, int space, String gender) {
        this.startingCity = startingCity;
        this.startingState = startingState;
        this.endingCity = endingCity;
        this.endingState = endingState;
        this.date = date;
        this.time = time;
        this.space = space;
        this.gender = gender;
    }

    public Ride(String startingCity, String startingState, String endingCity, String endingState, Date date, Time time, int space, String gender, String notes) {
        this.startingCity = startingCity;
        this.startingState = startingState;
        this.endingCity = endingCity;
        this.endingState = endingState;
        this.date = date;
        this.time = time;
        this.space = space;
        this.gender = gender;
        this.notes = notes;
    }

    public Ride(String startingCity, String startingState, String endingCity, String endingState,  int space, String gender, String notes) {
        this.startingCity = startingCity;
        this.startingState = startingState;
        this.endingCity = endingCity;
        this.endingState = endingState;
        this.space = space;
        this.gender = gender;
        this.notes = notes;
    }

    public Ride(String startingCity, String startingState, String endingCity, String endingState, Time time,  int space, String gender, String notes) {
        this.startingCity = startingCity;
        this.startingState = startingState;
        this.endingCity = endingCity;
        this.endingState = endingState;
        this.space = space;
        this.gender = gender;
        this.notes = notes;
        this.time = time;
    }

    public String getStartingCity() {
        return startingCity;
    }

    public void setStartingCity(String startingCity) {
        this.startingCity = startingCity;
    }

    public String getStartingState() {
        return startingState;
    }

    public void setStartingState(String startingState) {
        this.startingState = startingState;
    }

    public String getEndingCity() {
        return endingCity;
    }

    public void setEndingCity(String endingCity) {
        this.endingCity = endingCity;
    }

    public String getEndingState() {
        return endingState;
    }

    public void setEndingState(String endingState) {
        this.endingState = endingState;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
