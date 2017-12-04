package com.firrael.vote;

import android.content.Context;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by railag on 04.12.2017.
 */

public class FcmInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = FcmInstanceIDService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        String id = FirebaseInstanceId.getInstance().getId();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        Log.d(TAG, "Id: " + id);

        VoteService service = App.restService();
        Context context = getBaseContext();
        User user = User.get(context);

        User.saveFcmToken(refreshedToken, context);

    /*    service.sendFCMToken(user.getId(), refreshedToken)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError);*/
    }

  /*  private void onSuccess(SendFCMTokenResult result) {
        if (result.invalid()) {
            Log.e(TAG, result.error);
            return;
        }

        Log.i(TAG, result.result);

        User.fcmSaved(getBaseContext());
    }

    private void onError(Throwable throwable) {
        throwable.printStackTrace();
    }*/
}