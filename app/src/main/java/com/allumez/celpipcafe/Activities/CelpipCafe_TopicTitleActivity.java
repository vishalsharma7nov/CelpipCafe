package com.allumez.celpipcafe.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.allumez.celpipcafe.Adapter.CelpipCafe_ForumParentName_Adapter;
import com.allumez.celpipcafe.Adapter.CelpipCafe_ForumTopicTitle_Adapter;
import com.allumez.celpipcafe.GetterAndSetter.ForumTopicTitles;
import com.allumez.celpipcafe.JsonData.JsonData_Forum;
import com.allumez.celpipcafe.JsonData.JsonData_TopicTitleForum;
import com.allumez.celpipcafe.R;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CelpipCafe_TopicTitleActivity extends AppCompatActivity {

    private String API;
    private List<ForumTopicTitles> celpipCafeForumTopicTitles;
    private ListView listViewForumTopicTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celpip_cafe__questions);
        listViewForumTopicTitles = findViewById(R.id.listViewForumTopicTitles);
        Intent intent = getIntent();
        String forum_id = intent.getStringExtra("forum_id");
        API = "http://celpipcafe.com/api/questions.php?forumid="+forum_id;
        sendRequest();
    }
    private void sendRequest() {
        final ProgressDialog loading = ProgressDialog.show(this,"Loading","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(API,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            int abc = Integer.parseInt(obj.getString("status"));
                            if (abc !=1 )
                            {
                                loading.dismiss();
                                Toast.makeText(getApplicationContext(), "Nothing To Show!", Toast.LENGTH_SHORT).show();
                            }
                            else if (abc == 1)
                            {
                                loading.dismiss();
                                showJSON(response);
                                Toast.makeText(getApplicationContext(), "Welcome To Forum!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            loading.dismiss();
                            Toast.makeText(getApplicationContext(), "Error  "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), "Error  "+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json) {
        JsonData_TopicTitleForum jsonData_topicTitleForum = new JsonData_TopicTitleForum(json);
        celpipCafeForumTopicTitles = jsonData_topicTitleForum.parseJSON();
        CelpipCafe_ForumTopicTitle_Adapter ca = new CelpipCafe_ForumTopicTitle_Adapter(this, celpipCafeForumTopicTitles);
        listViewForumTopicTitles.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
}
