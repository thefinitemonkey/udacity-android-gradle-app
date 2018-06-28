package com.example.stevemillerband;

public class TheJoker {
    private static String[] mJokeList = {
            "Hi there. You can call me the space cowboy.",
            "Heya! Just call me the gangster of love.",
            "Well, hello. Please, call me Maurice.",
            "I've had people call me a picker.",
            "There are some who say I'm a grinner.",
            "Ask the right people and they'll tell you I'm a lover.",
            "You'll probably hear me referred to as a sinner.",
            "I have a bunch of music, but I enjoy playing it in the sun mostly.",
            "I'm totally a joker. Right there in my class name if you look at my source code."
    };

    private static int mCount = mJokeList.length;

    public static String getJoke() {
        // Get a random "joke" to return
        int jokePos = (int) Math.round(Math.random() * (mCount - 1));
        return mJokeList[jokePos];
    }
}
