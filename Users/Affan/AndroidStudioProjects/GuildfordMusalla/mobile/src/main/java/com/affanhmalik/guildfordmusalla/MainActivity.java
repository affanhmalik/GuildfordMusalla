package com.affanhmalik.guildfordmusalla;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    ProgressBar loader;
    TextView resultBox;
    TextView fajrTime;
    TextView zuhrTime;
    TextView asrTime;
    TextView maghribTime;
    TextView ishaTime;
    DataModel sched;


    List<DataModel> prSchedule;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

//        loader = (ProgressBar) findViewById(R.id.progressBar);



        final Button button = (Button) findViewById(R.id.getData);
//        final TextView output = (TextView) findViewById();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sampleDataServiceImplementation();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting()){
            return true;
        }else{
            return false;
        }
    }

    public boolean sampleDataServiceImplementation(){
        /* Implement the data stuff here and get data through the AsyncTask class*/
        if (isOnline()){
            requestAsyncData("http://private-e86d1-novella1.apiary-mock.com/login");
        }else{
            Toast.makeText(this, "No Network Connectivity", Toast.LENGTH_LONG);
        }


        return false;
    }

    private void requestAsyncData(String uri) {
        MainActivity.BackgroundREST sample = new MainActivity.BackgroundREST();

        sample.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, uri);
    }


    private class BackgroundREST extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            /* Maybe do a toast saying "contacting server for data" */

            /*loader.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), "Beginning...", Toast.LENGTH_SHORT).show();*/
        }

        @Override
        protected String doInBackground(String... params) {
            /* Do whatever and then send the result which is picked up by onPostExecute */

            // Get JSON data from API
            String content = HttpManager.getData(params[0]);



            return content;




        }

        @Override
        protected void onPostExecute(String content) {

            //Parse the data

            SchedJSONparser parseSched = new SchedJSONparser();

            try {
                sched = parseSched.parseFeed(content);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            fajrTime = (TextView) findViewById(R.id.fajrTime);
            zuhrTime = (TextView) findViewById(R.id.zuhrTime);
            asrTime = (TextView) findViewById(R.id.asrTime);
            maghribTime = (TextView) findViewById(R.id.maghribTime);
            ishaTime = (TextView) findViewById(R.id.ishaTime);

            /*fajrTime.setText(sched.getFajr());
            zuhrTime.setText(sched.getZuhr());
            asrTime.setText(sched.getAsr());
            maghribTime.setText(sched.getMaghrib());
            ishaTime.setText(sched.getIsha());*/


            fajrTime.setText("11:11");
            zuhrTime.setText("11:11");
            asrTime.setText("11:11");
            maghribTime.setText("11:11");
            ishaTime.setText("11:11");


            //Test data input
            resultBox = (TextView) findViewById(R.id.dataSample);
            resultBox.setText(sched.getFajr().toString());


            /*loader.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "s", Toast.LENGTH_SHORT).show();*/
        }

        @Override
        protected void onProgressUpdate(String... values) {

        }
    }
}
