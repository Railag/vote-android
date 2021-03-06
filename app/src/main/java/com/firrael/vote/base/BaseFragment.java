package com.firrael.vote.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firrael.vote.App;
import com.firrael.vote.MainActivity;

import nucleus.view.NucleusFragment;

/**
 * Created by firrael on 27.05.2016.
 */
public abstract class BaseFragment<P extends BasePresenter> extends NucleusFragment<P> {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String title = getTitle();
        if (!TextUtils.isEmpty(title)) {
            getMainActivity().setTitle(title);
        }

        View view = inflater.inflate(getViewId(), container, false);

        initView(view);

        return view;
    }

    protected MainActivity getMainActivity() {
        return App.getMainActivity();
    }

    protected void startLoading() {
        if (getMainActivity() != null)
            getMainActivity().startLoading();
    }

    protected void stopLoading() {
        if (getMainActivity() != null)
            getMainActivity().stopLoading();
    }

    protected abstract String getTitle();

    protected abstract int getViewId();

    protected void initView(View v) {
        getMainActivity().setCurrentFragment(this);
        // initialization
    }

    protected void toast(String message) {
        if (getActivity() != null)
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    protected void toast(int resId) {
        toast(getString(resId));
    }
}