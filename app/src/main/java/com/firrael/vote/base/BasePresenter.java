package com.firrael.vote.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

import icepick.Icepick;
import nucleus.presenter.RxPresenter;
import nucleus.view.ViewWithPresenter;

/**
 * Created by railag on 23.11.2017.
 */

public class BasePresenter<V extends ViewWithPresenter> extends RxPresenter<V> {

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        Icepick.restoreInstanceState(this, savedState);
    }

    @Override
    protected void onSave(@NonNull Bundle state) {
        super.onSave(state);
        Icepick.saveInstanceState(this, state);
    }
}
