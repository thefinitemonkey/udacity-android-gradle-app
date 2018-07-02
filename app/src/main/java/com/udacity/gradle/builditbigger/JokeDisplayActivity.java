package com.udacity.gradle.builditbigger;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.angelsface.Magic;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JokeDisplayActivity extends AppCompatActivity {

    @BindView(R.id.frame_joke_display)
    View mFrameJokeDisplay;
    private String mJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        ButterKnife.bind(this);

        String extraName = getResources().getString(R.string.joke_extra);
        if (getIntent().hasExtra(extraName)) {
            mJoke = getIntent().getStringExtra(extraName);
            displayJoke(mJoke);
        }
    }

    public void displayJoke(String joke) {
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
