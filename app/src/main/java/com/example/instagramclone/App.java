package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("SAYhJmBw7dNB3Tam169zPMxwRnrlBYEfjqmO8cbO")
                // if defined
                .clientKey("zhX0nr1XvABCFKqPCYJQw2cR0ImliGS0zR3cwgxu")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
