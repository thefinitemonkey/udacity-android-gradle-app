package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.util.Pair;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private MyApi myApiService = null;
    private Context context;
    private EndpointTaskNotification mEndpointListener;

    public void setListener(EndpointTaskNotification listener) {
        mEndpointListener = listener;
    }

    @Override
    protected String doInBackground(Pair<Context, String>... pairs) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(
                    AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(),
                    null
            ).setRootUrl("http://10.0.2.2:8080/_ah/api").setGoogleClientRequestInitializer(
                    new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }

        context = pairs[0].first;
        try {
            return myApiService.getJoke().execute().getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        mEndpointListener.endpointTaskComplete(s);
    }

    interface EndpointTaskNotification {
        void endpointTaskComplete(String s);
    }
}
