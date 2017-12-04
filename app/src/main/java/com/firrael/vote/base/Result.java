package com.firrael.vote.base;

/**
 * Created by railag on 04.12.2017.
 */

public class Result {
    public String error;
    public String result;

    public boolean invalid() {
        return error != null;
    }
}

