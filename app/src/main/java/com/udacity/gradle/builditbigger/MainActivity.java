package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.angelsface.JokeDisplayActivity;
import com.example.angelsface.Magic;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements EndpointAsyncTask.EndpointTaskNotification {

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

    public void tellJoke(View view) {
        // Get a joke from TheJoker in the SteveMillerBand library
        //String joke = TheJoker.getJoke();

        // Get a joke from the "joker" web service in GCE
        EndpointAsyncTask task = new EndpointAsyncTask();
        task.setListener(this);
        task.execute();
    }

    @Override
    public void endpointTaskComplete(String s) {
        displayJoke(s);
    }

    public void displayJoke(String joke) {
        // Create intent to navigate to the JokeDisplayActivity with the joke data
        Intent intent = new Intent(this, JokeDisplayActivity.class);
        intent.putExtra(getResources().getString(R.string.joke_extra), joke);
        startActivity(intent);
    }
}
