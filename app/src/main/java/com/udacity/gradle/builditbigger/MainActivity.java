package com.udacity.gradle.builditbigger;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.angelsface.Magic;
import com.example.stevemillerband.TheJoker;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.frame_joke_display)
    View mFrameJokeDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
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
        String joke = TheJoker.getJoke();

        // Remove any fragments from the joke display frame layout
        FragmentManager fm = getSupportFragmentManager();
        List<Fragment> frags = fm.getFragments();
        if (frags != null) {
            for (Fragment frag : frags) {
                if (frag.getView() == null) return;

                View container = (View) frag.getView().getParent();
                if (container.equals(mFrameJokeDisplay)) {
                    fm.beginTransaction().remove(frag).commit();
                }
            }
        }

        // Create the bundle with the joke text
        Bundle bundle = new Bundle();
        bundle.putString(
                getResources().getString(com.example.angelsface.R.string.joke_argument), joke);
        Magic magicFragment = new Magic();
        magicFragment.setArguments(bundle);

        // Add the fragment to the display area
        fm.beginTransaction().add(R.id.frame_joke_display, magicFragment).commit();


        //Toast.makeText(this, joke, Toast.LENGTH_SHORT).show();
    }


}
