package com.payne.isaac.jsonlist.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

/**
 * Created by isaac on 2/10/17.
 */

public class NetworkHandler {
    private final String TAG = "NetworkHandler";
    private RequestQueue queue;

    /**
     * Simple constructor which takes Context and creates a Queue
     * @param c the Context to be used
     */
    //TODO: move to singleton
    public NetworkHandler(Context c) {
        queue = Volley.newRequestQueue(c);
    }

    /**
     * Makes the network request via Volley and returns a JSONArray via a callback
     *
     * @param url The URL to make the request to
     * @param responseListener The callback to call once we have a result
     */
    public void makeRequest(String url, final OnResponseListener responseListener) {
        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        responseListener.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        responseListener.onFailure(error.getMessage());
                    }
                });

        // Add the request to the RequestQueue.
        queue.add(jsonRequest);
    }


    /**
     * A callback that notifies clients if we successfully received a response.
     */
    public interface OnResponseListener {
        void onSuccess(JSONArray jsonArray);

        void onFailure(String errorMessage);
    }

}
