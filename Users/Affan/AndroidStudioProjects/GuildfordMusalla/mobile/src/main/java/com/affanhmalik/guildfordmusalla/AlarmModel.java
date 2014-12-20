package com.affanhmalik.guildfordmusalla;

/**
 * Created by Affan on 12/19/2014.
 */
public class AlarmModel {

    // Create vars for fields
    protected int date;
    protected int fajr;
    protected int zuhr;
    protected int asr;
    protected int maghrib;
    protected int isha;



    // Constructor with default values
    public AlarmModel() {
        this.date = 1;
        this.fajr = 0;
        this.zuhr = 0;
        this.asr = 0;
        this.maghrib = 0;
        this.isha = 0;
    }


    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getFajr() {
        return fajr;
    }

    public void setFajr(int fajr) {
        this.fajr = fajr;
    }

    public int getZuhr() {
        return zuhr;
    }

    public void setZuhr(int zuhr) {
        this.zuhr = zuhr;
    }

    public int getAsr() {
        return asr;
    }

    public void setAsr(int asr) {
        this.asr = asr;
    }

    public int getMaghrib() {
        return maghrib;
    }

    public void setMaghrib(int maghrib) {
        this.maghrib = maghrib;
    }

    public int getIsha() {
        return isha;
    }

    public void setIsha(int isha) {
        this.isha = isha;
    }
}
