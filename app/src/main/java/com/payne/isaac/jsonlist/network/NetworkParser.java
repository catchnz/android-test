package com.payne.isaac.jsonlist.network;

import com.payne.isaac.jsonlist.model.DataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by isaac on 2/10/17.
 */

public class NetworkParser {

    public static ArrayList<DataModel> parseDataModel(JSONArray jsonArray) throws JSONException {
        ArrayList<DataModel> result = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            result.add(
                    new DataModel(
                            obj.getInt("id"),
                            obj.getString("title"),
                            obj.getString("subtitle"),
                            obj.getString("content")));
        }

        return result;
    }

}
