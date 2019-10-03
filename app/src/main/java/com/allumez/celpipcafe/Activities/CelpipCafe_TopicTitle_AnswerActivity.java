package com.allumez.celpipcafe.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.allumez.celpipcafe.Adapter.CelpipCafe_ForumTopicTitleAnswer_Adapter;
import com.allumez.celpipcafe.Adapter.CelpipCafe_SubForumName_Adapter;
import com.allumez.celpipcafe.GetterAndSetter.ForumParentName;
import com.allumez.celpipcafe.GetterAndSetter.ForumTopicTitlesAnswer;
import com.allumez.celpipcafe.JsonData.JsonData_Forum;
import com.allumez.celpipcafe.JsonData.JsonData_TopicTitleAnswerForum;
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

public class CelpipCafe_TopicTitle_AnswerActivity extends AppCompatActivity {

    protected boolean doubleBackToExitPressedOnce = false;
    protected List<ForumTopicTitlesAnswer> celpipCafeForumTopicTitlesAnswer;
    protected String API ;
    protected ListView listViewTopicTitlesAnswer;
    protected TextView textViewTopicTitleAnswer,textViewFullAnswer;
    protected String topicid,topictitle;
    protected ScrollView scrollViewFullAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celpip_cafe__topic_title__answer);
        Intent intent = getIntent();
        topicid = intent.getStringExtra("topicid");
        topictitle = intent.getStringExtra("topictitle");
        SharedPreferences bb = getSharedPreferences("my_prefs", 0);
        String forum_id = bb.getString("forum_id", "forum_id");
        API = "http://www.celpipcafe.com/api/answers.php?forumid="+forum_id+"&&topicid="+topicid;
        listViewTopicTitlesAnswer = findViewById(R.id.listViewForumTopicTitlesAnswer);
        textViewTopicTitleAnswer = findViewById(R.id.textViewForumTopicTitleAnswer);
        textViewFullAnswer = findViewById(R.id.textViewFullAnswer);
        scrollViewFullAnswer = findViewById(R.id.scrollViewFullAnswer);
        listViewTopicTitlesAnswer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listViewTopicTitlesAnswer.setVisibility(View.GONE);
                scrollViewFullAnswer.setVisibility(View.VISIBLE);
                textViewFullAnswer.setText(celpipCafeForumTopicTitlesAnswer.get(i).getPost_text());
            }
        });
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
                                textViewTopicTitleAnswer.setText(topictitle);
                            }
                        } catch (JSONException e) {
                            loading.dismiss();
                            Toast.makeText(getApplicationContext(), "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), "Error "+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        else
        {
            this.doubleBackToExitPressedOnce = true;
            listViewTopicTitlesAnswer.setVisibility(View.VISIBLE);
            scrollViewFullAnswer.setVisibility(View.GONE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        }
    }

    private void showJSON(String json) {
        JsonData_TopicTitleAnswerForum jsonData_topicTitleAnswerForum = new JsonData_TopicTitleAnswerForum(json);
        celpipCafeForumTopicTitlesAnswer= jsonData_topicTitleAnswerForum.parseJSON();
        CelpipCafe_ForumTopicTitleAnswer_Adapter ca = new CelpipCafe_ForumTopicTitleAnswer_Adapter(this, celpipCafeForumTopicTitlesAnswer);
        listViewTopicTitlesAnswer.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
}
