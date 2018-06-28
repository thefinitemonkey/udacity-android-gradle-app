package com.example.angelsface;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Magic extends Fragment {
    // Wanted to use ButterKnife here, but for some reason the R.id values don't show up
    TextView mJokeDisplay;


    public Magic() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_magic, container, false);
        ButterKnife.bind(this, view);

        mJokeDisplay = view.findViewById(R.id.tv_joke_display);

        String jokeText = getArguments().getString(
                getResources().getString(R.string.joke_argument));
        mJokeDisplay.setText(jokeText);

        return view;
    }
}
