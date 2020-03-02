package com.scc.threestarlabel.utils;

import android.annotation.SuppressLint;

import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import timber.log.Timber;

/**
 * Created by SAM on 11/27/2019.
 */
public class SendTopicPush {

    private static OkHttpClient mClient;

    private static String urlString = "https://fcm.googleapis.com/fcm/send";

    @SuppressLint("CheckResult")
    public static void sendPush(CompositeDisposable disposable, String title, String body) {
        if (mClient == null) {
            mClient = new OkHttpClient();
        }

        disposable.add(Observable.fromCallable(() -> {
            try {
                JSONObject dataJson = new JSONObject();
                dataJson.put("title", title);
                dataJson.put("body", body);

                JSONObject json = new JSONObject();
                json.put("to", "/topics/" + Constants.FIREBASE_TOPIC);
                json.put("notification", dataJson);
                json.put("priority", "high");
                json.put("sound", "default");
//                json.put("click_action", "notification_click");


                Request request = new Request.Builder()
                        .header("Authorization", "key=" + Constants.FIREBASE_API_KEY)
                        .url(urlString)
                        .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString()))
                        .build();

                Response response = mClient.newCall(request).execute();

                if (response.body() != null) {
                    Timber.d("doInBackground: %s", response.body().string());
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> Timber.d("sendPush: %s", result)));
    }
}
