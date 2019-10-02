package com.allumez.celpipcafe.JsonData;

import android.content.Context;
import android.widget.Toast;

import com.allumez.celpipcafe.GetterAndSetter.ForumParentName;
import com.allumez.celpipcafe.GetterAndSetter.ForumTopicTitles;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonData_TopicTitleForum {

    Context context;
    public static  String[] topic_title;
    public static final String JSON_ARRAY      = "data";
    public static final String KEY_topic_title = "topic_title";

    private String json;

    public JsonData_TopicTitleForum(String json) {
        this.json = json;
    }
    public List<ForumTopicTitles> parseJSON() {
        List<ForumTopicTitles> celpipCafeForumQuestionsTopicTitle= new ArrayList<>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            JSONArray users = jsonObject.getJSONArray(JSON_ARRAY);
            celpipCafeForumQuestionsTopicTitle= new ArrayList<>();
            topic_title = new String[users.length()];
            for (int i = 0; i < users.length(); i++) {
               JSONObject jo = users.getJSONObject(i);
               ForumTopicTitles _forumTopicTitles__class = new ForumTopicTitles(jo.getString(KEY_topic_title));
               celpipCafeForumQuestionsTopicTitle.add(_forumTopicTitles__class);
               topic_title  [i]            = jo.getString(KEY_topic_title);
            }
        } catch (JSONException e) {
            Toast.makeText(context, "Error"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return celpipCafeForumQuestionsTopicTitle;
    }

}
