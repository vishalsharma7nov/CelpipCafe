package com.allumez.celpipcafe.JsonData;

import android.content.Context;
import android.widget.Toast;

import com.allumez.celpipcafe.GetterAndSetter.ForumTopicTitles;
import com.allumez.celpipcafe.GetterAndSetter.ForumTopicTitlesAnswer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonData_TopicTitleAnswerForum {

    Context context;
    public static  String[] topic_id;
    public static  String[] topic_title;
    public static final String JSON_ARRAY      = "data";
    public static final String KEY_post_id    = "post_id";
    public static final String KEY_post_text = "post_text";
    private String json;

    public JsonData_TopicTitleAnswerForum(String json) {
        this.json = json;
    }
    public List<ForumTopicTitlesAnswer> parseJSON() {
        List<ForumTopicTitlesAnswer> celpipCafeForumQuestionsTopicTitle= new ArrayList<>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            JSONArray users = jsonObject.getJSONArray(JSON_ARRAY);
            celpipCafeForumQuestionsTopicTitle= new ArrayList<>();
            topic_id = new String[users.length()];
            topic_title = new String[users.length()];
            for (int i = 0; i < users.length(); i++) {
               JSONObject jo = users.getJSONObject(i);
               ForumTopicTitlesAnswer forumTopicTitlesAnswer = new ForumTopicTitlesAnswer(jo.getString(KEY_post_id),jo.getString(KEY_post_text));
               celpipCafeForumQuestionsTopicTitle.add(forumTopicTitlesAnswer);
               topic_id     [i]            = jo.getString(KEY_post_id);
               topic_title  [i]            = jo.getString(KEY_post_text);
            }
        } catch (JSONException e) {
            Toast.makeText(context, "Error"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return celpipCafeForumQuestionsTopicTitle;
    }

}
