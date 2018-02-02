package com.finitydev.jewishrideshare;

/**
 * Created by finit on 12/24/2017.
 */

public class Time {

    private int hour;
    private int minutes;

    public Time(int hour, int minutes) {
        this.hour = hour;
        this.minutes = minutes;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
    @Override
    public String toString(){
        if(hour < 13) {
            return ("" + hour + ":" + minutes + " AM");
        }
        else {
            int pm = hour-12;
            return ("" + pm + ":" + minutes + " PM");
        }
    }
}
