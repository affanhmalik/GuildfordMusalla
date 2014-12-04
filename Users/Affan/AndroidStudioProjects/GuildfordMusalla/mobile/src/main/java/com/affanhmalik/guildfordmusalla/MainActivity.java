package com.affanhmalik.guildfordmusalla;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public boolean sampleDataServiceImplementation(){
        /* Implement the data stuff here and get data through the AsyncTask class*/

        BackgroundREST sample = new BackgroundREST();

        sample.execute("Some params");

        return true;
    }

    private class BackgroundREST extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            /* Maybe do a toast saying "contacting server for data" */
        }

        @Override
        protected String doInBackground(String... params) {
            /* Do whatever and then send the result which is picked up by onPostExecute */
            String a = "asdf";
            return a;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
