package com.affanhmalik.guildfordmusalla;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    // Declare global vars
    ProgressBar loader;
    TextView resultBox;
    TextView fajrTime;
    TextView zuhrTime;
    TextView asrTime;
    TextView maghribTime;
    TextView ishaTime;
    DataModel sched;
    List<DataModel> prSchedule;

    // Currently making calls to this URI (Mock API) for json data
    String test_server_uri = "http://private-e86d1-novella1.apiary-mock.com/login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
        *  This method gets call after UI has been initialized.
        * */


         super.onCreate(savedInstanceState);

        // Assign XML UI activity to this java file
        setContentView(R.layout.activity_main);

        // Assign ProgressBar var to the PB Spinner UI item
        loader = (ProgressBar) findViewById(R.id.progress1);

        // Call this method to kick start the chain of callbacks that with fetch data in the background
        getServerData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_refresh:
                refresh();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void refresh() {
        // As the name suggests, re-run the data fetch process
        getServerData();
    }

    protected boolean isOnline(){
        /*
        *  This method checks network connection and responds accordingly
        * */

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting()){
            return true;
        }else{
            return false;
        }
    }

    public boolean getServerData(){
        /* Implement the data stuff here and get data through the AsyncTask class*/
        if (isOnline()){
            requestAsyncData(test_server_uri);
        }else{
            Toast.makeText(this, "No Network Connectivity", Toast.LENGTH_LONG);
        }


        return false;
    }

    private void requestAsyncData(String uri) {
        /*
        * THis method takes the URI and initiates n AsyncTask while will do the data grab&parse in the background
        * */

         MainActivity.BackgroundREST sample = new MainActivity.BackgroundREST();

        sample.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, uri);
    }

    private class BackgroundREST extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            /* Stuff to be done before thread execution, e.g. showing the loading spinner */

            loader.setVisibility(View.VISIBLE);
//            Toast.makeText(getApplicationContext(), "Beginning...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {
            /* Do the data grab and then send the result which is picked up by onPostExecute */

            // Get JSON data from API
            String content = HttpManager.getData(params[0]);

            return content;




        }

        @Override
        protected void onPostExecute(String content) {

            //Parse the data into Java objects

            SchedJSONparser parseSched = new SchedJSONparser();

            try {
                sched = parseSched.parseFeed(content);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Assign vars to UI elements
            fajrTime = (TextView) findViewById(R.id.fajrTime);
            zuhrTime = (TextView) findViewById(R.id.zuhrTime);
            asrTime = (TextView) findViewById(R.id.asrTime);
            maghribTime = (TextView) findViewById(R.id.maghribTime);
            ishaTime = (TextView) findViewById(R.id.ishaTime);

            fajrTime.setText(sched.getFajr());
            zuhrTime.setText(sched.getZuhr());
            asrTime.setText(sched.getAsr());
            maghribTime.setText(sched.getMaghrib());
            ishaTime.setText(sched.getIsha());



            //Test data input
            /*resultBox = (TextView) findViewById(R.id.dataSample);
            resultBox.setText(sched.getFajr().toString());*/


            loader.setVisibility(View.GONE);
//            Toast.makeText(getApplicationContext(), "s", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(String... values) {

        }
    }

    public void toggleAlarm(View view) {
        // This method will process the alarm triggers
    }
}
