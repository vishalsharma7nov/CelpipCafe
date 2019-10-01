package com.allumez.celpipcafe.JsonData;

import android.content.Context;
import android.widget.Toast;

import com.allumez.celpipcafe.GetterAndSetter.CelpipCafe_ForumParentName_GetterAndSetter_Class;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonData_Forum{

    Context context;
    public static  String[] forum_name;
    public static  String[] forum_id;
    public static  String[] parent_id;
    public static final String JSON_ARRAY                  = "data";
    public static final String KEY_forum_name              = "forum_name";
    public static final String KEY_forum_id                = "forum_id";
    public static final String KEY_parent_id               = "parent_id";

    private String json;

    public JsonData_Forum(String json) {
        this.json = json;
    }
    public List<CelpipCafe_ForumParentName_GetterAndSetter_Class> parseJSON() {
        List<CelpipCafe_ForumParentName_GetterAndSetter_Class> celpipCafeForumParentNameGetterAndSetterClassList = new ArrayList<>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            JSONArray users = jsonObject.getJSONArray(JSON_ARRAY);
            celpipCafeForumParentNameGetterAndSetterClassList = new ArrayList<>();
            forum_name      = new String[users.length()];
            forum_id        = new String[users.length()];
            parent_id       = new String[users.length()];
            for (int i = 0; i < users.length(); i++) {
               JSONObject jo = users.getJSONObject(i);
               CelpipCafe_ForumParentName_GetterAndSetter_Class celpipCafeForumParentNameGetterAndSetterClass = new CelpipCafe_ForumParentName_GetterAndSetter_Class(jo.getString(KEY_forum_name), jo.getString(KEY_forum_id),jo.getString(KEY_parent_id));
               celpipCafeForumParentNameGetterAndSetterClassList.add(celpipCafeForumParentNameGetterAndSetterClass);
               forum_name  [i]            = jo.getString(KEY_forum_name);
               forum_id    [i]        = jo.getString(KEY_forum_id);
               parent_id   [i]          = jo.getString(KEY_parent_id);
            }
        } catch (JSONException e) {
            Toast.makeText(context, "Error"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return celpipCafeForumParentNameGetterAndSetterClassList;
    }

}
