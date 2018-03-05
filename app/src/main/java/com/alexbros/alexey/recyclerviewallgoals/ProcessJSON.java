package com.alexbros.alexey.recyclerviewallgoals;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProcessJSON extends AsyncTask<String, Void, String> {
    private List<ListElement> list = new ArrayList<>();

    @Override
    protected String doInBackground(String... strings) {

        String result;
        String urlString = strings[0];
        HTTPConnection httpDataHandler = new HTTPConnection();
        result = httpDataHandler.GetHTTPData(urlString);
        ListElement listElement;

        if(result != null){
            try{
                JSONObject rootObject = new JSONObject(result);
                JSONObject response = rootObject.getJSONObject(Constants.RESPONSE_OBJECT);
                JSONObject items = response.getJSONObject("items");
                JSONObject Overview = items.getJSONObject("Overview");
                JSONArray leagues = Overview.getJSONArray("leagues");

                for(int i = 0; i < leagues.length(); i++) {
                    JSONObject leaguesJSONObject = leagues.getJSONObject(i);
                    JSONArray events = leaguesJSONObject.getJSONArray("events");

                    for(int j = 0; j < events.length(); j++) {
                        JSONObject eventsJSONObject = events.getJSONObject(j);
                        JSONArray participants = eventsJSONObject.getJSONArray("participants");

                        listElement = new ListElement();
                        for(int k = 0; k < participants.length(); k++) {
                            JSONObject participantsJSONObject = participants.getJSONObject(k);
                            if(k == 0)
                                listElement.setFirstTeamName(participantsJSONObject.getString("name"));
                            else
                                listElement.setSecondTeamName(participantsJSONObject.getString("name"));
                        }
                        list.add(listElement);
                    }
                }
            }
            catch(JSONException e){
                e.printStackTrace();
            }
        }
        return result;
    }

    List<ListElement> getListElements()
    {
        return list;
    }
}
