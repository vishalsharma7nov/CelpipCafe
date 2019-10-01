package com.allumez.celpipcafe.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.allumez.celpipcafe.Adapter.CelpipCafe_ForumParentName_Adapter;
import com.allumez.celpipcafe.Adapter.CelpipCafe_SubForumName_Adapter;
import com.allumez.celpipcafe.GetterAndSetter.CelpipCafe_ForumParentName_GetterAndSetter_Class;
import com.allumez.celpipcafe.JsonData.JsonData_Forum;
import com.allumez.celpipcafe.R;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CelpipCafe_SubForumName_Activity extends AppCompatActivity {

    protected String API;
    protected ListView listViewSubForumName;
    protected List<CelpipCafe_ForumParentName_GetterAndSetter_Class> celpipCafeForumParentNameGetterAndSetterClassList;
    protected String forum_id,forum_name;
    protected TextView textViewParentForumName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celpip_cafe__sub_forum_name_);
        listViewSubForumName = findViewById(R.id.listViewSubForumName);
        textViewParentForumName = findViewById(R.id.textViewParentForumName);
        Intent intent = getIntent();
        forum_id = intent.getStringExtra("forum_id");
        forum_name = intent.getStringExtra("forum_name");
        API =  "http://www.celpipcafe.com/api/subForums.php?p="+forum_id;
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
                                textViewParentForumName.setText(forum_name);
                                Toast.makeText(getApplicationContext(), "Welcome To "+forum_name+"!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json) {
        JsonData_Forum jsonData_forum = new JsonData_Forum(json);
        celpipCafeForumParentNameGetterAndSetterClassList = jsonData_forum.parseJSON();
        CelpipCafe_SubForumName_Adapter ca = new CelpipCafe_SubForumName_Adapter(this, celpipCafeForumParentNameGetterAndSetterClassList);
        listViewSubForumName.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
}

