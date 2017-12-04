package com.firrael.vote;

import android.os.Bundle;

import com.firrael.vote.base.BasePresenter;

import icepick.State;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Railag on 31.05.2016.
 */
public class SplashPresenter extends BasePresenter<SplashFragment> {

    @State
    String login;

    @State
    String token;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        VoteService service = App.restService();

        /*restartableLatestCache(REQUEST_SPLASH,
                () -> service.startupLogin(login, token)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread()),
                SplashFragment::onSuccess,
                SplashFragment::onError);*/
    }

    public void request(String login, String token) {
        this.login = login;
        this.token = token;
  //      start(REQUEST_SPLASH);
    }
}
