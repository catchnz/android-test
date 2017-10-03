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

    //TODO: move to singleton
    public NetworkHandler(Context c) {
        queue = Volley.newRequestQueue(c);
    }

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
