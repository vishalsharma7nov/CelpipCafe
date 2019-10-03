package com.allumez.celpipcafe.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.allumez.celpipcafe.Adapter.CelpipCafe_ForumParentName_Adapter;
import com.allumez.celpipcafe.GetterAndSetter.ForumParentName;
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

public class CelpipCafe_ForumParentName_Activity extends AppCompatActivity {

    protected String API = "http://www.celpipcafe.com/api/forums.php";
    protected List<ForumParentName> celpipCafeForumParentName;
    protected ListView listViewForumParentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_parent_name);
        listViewForumParentName = findViewById(R.id.listViewParentName);
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
                      Toast.makeText(getApplicationContext(), "Error "+error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json) {
        JsonData_Forum jsonData_forum = new JsonData_Forum(json);
        celpipCafeForumParentName = jsonData_forum.parseJSON();
        CelpipCafe_ForumParentName_Adapter ca = new CelpipCafe_ForumParentName_Adapter(this, celpipCafeForumParentName);
        listViewForumParentName.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
}
