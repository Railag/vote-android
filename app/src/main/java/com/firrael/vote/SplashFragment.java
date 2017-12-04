package com.firrael.vote;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;


import com.firrael.vote.base.BaseFragment;

import nucleus.factory.RequiresPresenter;

/**
 * Created by Railag on 31.05.2016.
 */
@RequiresPresenter(SplashPresenter.class)
public class SplashFragment extends BaseFragment<SplashPresenter> {

    public static SplashFragment newInstance() {

        Bundle args = new Bundle();

        SplashFragment fragment = new SplashFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startLoading();
        getMainActivity().transparentStatusBar();
        getMainActivity().hideToolbar();

        if (savedInstanceState == null) {
            startLoading();

            User user = User.get(getActivity());
            String token = user.getToken();

            if (!TextUtils.isEmpty(token))
                getPresenter().request(user.getLogin(), token);
            else {
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    stopLoading();
                    getMainActivity().toStart();
                }, 3500);
            }
        }
    }

    @Override
    protected String getTitle() {
        return getString(R.string.app_name);
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_splash;
    }

    public void onSuccess(UserResult result) {
        stopLoading();
        if (result == null) {
            onError(new IllegalArgumentException());
            return;
        }
        if (result.invalid()) {
            getMainActivity().toStart();
            return;
        }

        User.save(result, getActivity());
        getMainActivity().toMenu();
    }

    public void onError(Throwable error) {
        error.printStackTrace();
        stopLoading();
        getMainActivity().toStart();
    }
}