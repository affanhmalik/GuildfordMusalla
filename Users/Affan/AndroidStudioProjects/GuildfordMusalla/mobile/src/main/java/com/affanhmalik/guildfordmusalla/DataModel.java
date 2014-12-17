package com.affanhmalik.guildfordmusalla;

/**
 * Created by Affan on 12/12/2014.
 */
public class DataModel {

    // Create vars for fields
    protected int date;
    protected String fajr;
    protected String zuhr;
    protected String asr;
    protected String maghrib;
    protected String isha;


    public void setFajr(String fajr){
        this.fajr = fajr;
    }

    public String getFajr(){
        return this.fajr;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getZuhr() {
        return zuhr;
    }

    public void setZuhr(String zuhr) {
        this.zuhr = zuhr;
    }

    public String getAsr() {
        return asr;
    }

    public void setAsr(String asr) {
        this.asr = asr;
    }

    public String getMaghrib() {
        return maghrib;
    }

    public void setMaghrib(String maghrib) {
        this.maghrib = maghrib;
    }

    public String getIsha() {
        return isha;
    }

    public void setIsha(String isha) {
        this.isha = isha;
    }
}
