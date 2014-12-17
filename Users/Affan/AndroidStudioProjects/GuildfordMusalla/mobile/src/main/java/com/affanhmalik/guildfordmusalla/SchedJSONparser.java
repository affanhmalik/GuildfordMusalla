package com.affanhmalik.guildfordmusalla;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Affan on 12/12/2014.
 */
public class SchedJSONparser {

    public static DataModel parseFeed(String content) throws JSONException {

//        List<DataModel> datalist = new ArrayList<>();

        DataModel sched = new DataModel();
        JSONObject data = new JSONObject(content);


        JSONArray times = data.getJSONArray("times");
        JSONObject fajr = times.getJSONObject(0);
        JSONObject zuhr = times.getJSONObject(1);
        JSONObject asr = times.getJSONObject(2);
        JSONObject maghrib = times.getJSONObject(3);
        JSONObject isha = times.getJSONObject(4);

        sched.setFajr(fajr.getString("fajr"));
        sched.setZuhr(zuhr.getString("zuhr"));
        sched.setAsr(asr.getString("asr"));
        sched.setMaghrib(maghrib.getString("maghrib"));
        sched.setIsha(isha.getString("isha"));

        /*JSONArray times = new JSONArray(data.getString("date"));

        JSONObject fajr = times.getJSONObject(0);
        JSONObject zuhr = times.getJSONObject(1);
        JSONObject asr = times.getJSONObject(2);
        JSONObject maghrib = times.getJSONObject(3);
        JSONObject isha = times.getJSONObject(4);

        sched.setFajr(fajr.getString("Fajr"));
        sched.setZuhr(zuhr.getString("Zuhr"));
        sched.setAsr(asr.getString("Asr"));
        sched.setMaghrib(maghrib.getString("Maghrib"));
        sched.setIsha(isha.getString("Isha"));*/

        return sched;
    }
}
