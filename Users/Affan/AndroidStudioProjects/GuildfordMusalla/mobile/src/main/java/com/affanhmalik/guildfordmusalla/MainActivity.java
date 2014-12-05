package com.affanhmalik.guildfordmusalla;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    ProgressBar loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loader = (ProgressBar) findViewById(R.id.progressBar);

//        Button click test

        final Button button = (Button) findViewById(R.id.button1);

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
            loader.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), "Beginning...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {
            /* Do whatever and then send the result which is picked up by onPostExecute */
            String a = "asdf";

            /* Publish progress part */

            for (int i=0; i < 10 ; i++) {
                publishProgress("Yada yada yada" + i);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            return a;
        }

        @Override
        protected void onPostExecute(String s) {
            loader.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "s", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(String... values) {

        }
    }
}
