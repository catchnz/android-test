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

    /**
     *  Parses a JSONArray into an ArrayList of DataModels
     * @param jsonArray The JSONArray to be parse
     * @return The objects from the JSONArray parsed into an ArrayList<DataModel>
     * @throws JSONException
     */
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
