package com.example.dingu.smartcradle;

/**
 * Created by dingu on 11/4/18.
 */

public class CradleSensorData {

    Boolean babyCrying,bedWet;

    public CradleSensorData() {
    }

    public CradleSensorData(Boolean babyCrying, Boolean bedWet) {
        this.babyCrying = babyCrying;
        this.bedWet = bedWet;
    }

    public Boolean getBabyCrying() {
        return babyCrying;
    }

    public void setBabyCrying(Boolean babyCrying) {
        this.babyCrying = babyCrying;
    }

    public Boolean getBedWet() {
        return bedWet;
    }

    public void setBedWet(Boolean bedWet) {
        this.bedWet = bedWet;
    }


    @Override
    public String toString() {
        return "Cradle Data : "+getBedWet() + "  " + getBabyCrying();
    }
}
