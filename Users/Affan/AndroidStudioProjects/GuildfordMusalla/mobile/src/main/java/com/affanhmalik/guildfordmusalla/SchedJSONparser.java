package com.affanhmalik.guildfordmusalla;

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
        JSONObject date = data.getJSONObject("date");

        sched.setDate(date.getInt("date"));

        JSONArray times = new JSONArray(data.getString("date"));

        JSONObject fajr = times.getJSONObject(0);
        JSONObject zuhr = times.getJSONObject(1);
        JSONObject asr = times.getJSONObject(2);
        JSONObject maghrib = times.getJSONObject(3);
        JSONObject isha = times.getJSONObject(4);

        sched.setFajr(fajr.getString("Fajr"));
        sched.setZuhr(zuhr.getString("Zuhr"));
        sched.setAsr(asr.getString("Asr"));
        sched.setMaghrib(maghrib.getString("Maghrib"));
        sched.setIsha(isha.getString("Isha"));

        return sched;
    }
}
