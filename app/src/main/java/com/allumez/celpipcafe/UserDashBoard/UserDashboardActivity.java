package com.allumez.celpipcafe.UserDashBoard;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.allumez.celpipcafe.Adapter.CelpipCafe_ForumParentName_Adapter;
import com.allumez.celpipcafe.ExpandableListView.ExpandableViewAdapter;
import com.allumez.celpipcafe.GetterAndSetter.ForumParentName;
import com.allumez.celpipcafe.JsonData.JsonData_Forum;
import com.allumez.celpipcafe.R;
import com.allumez.celpipcafe.UserDashBoard.Overview.FrontPageActivity;
import com.allumez.celpipcafe.UserDashBoard.Overview.ManageAttachmentsActivity;
import com.allumez.celpipcafe.UserDashBoard.Overview.ManageBookmarksActivity;
import com.allumez.celpipcafe.UserDashBoard.Overview.ManageDraftsActivity;
import com.allumez.celpipcafe.UserDashBoard.Overview.ManageNotificationsActivity;
import com.allumez.celpipcafe.UserDashBoard.Overview.ManageSubscriptionActivity;
import com.allumez.celpipcafe.UserDashBoard.Profile.AccountSettingsActivity;
import com.allumez.celpipcafe.UserDashBoard.Profile.AvatarActivity;
import com.allumez.celpipcafe.UserDashBoard.Profile.LoginKeysActivity;
import com.allumez.celpipcafe.UserDashBoard.Profile.SignatureActivity;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDashboardActivity extends AppCompatActivity {

    ExpandableViewAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private  ListView listViewDashboardParentForumName;
    private String API = "http://www.celpipcafe.com/api/forums.php";
    private List<ForumParentName> celpipCafeForumParentName;
    private TextView textViewUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard_);
        Intent intent = getIntent();
        String Username = intent.getStringExtra("username");
        // ---views id---
        listViewDashboardParentForumName = findViewById(R.id.listViewDashboardParentForumName);
        textViewUsername = findViewById(R.id.textViewUsername);
        textViewUsername.setText("Welcome "+Username);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView();
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
        celpipCafeForumParentName = jsonData_forum.parseJSON();
        CelpipCafe_ForumParentName_Adapter ca = new CelpipCafe_ForumParentName_Adapter(this, celpipCafeForumParentName);
        listViewDashboardParentForumName.setAdapter(ca);
        ca.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_dashboard_, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void navigationView()
    {
        // get the list view
        expListView = findViewById(R.id.lvExp);
        // preparing list data
        prepareListData();
        listAdapter = new ExpandableViewAdapter(this, listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);
        // List view Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {

                return false;
            }
        });

        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition == 0)
                {
                    expListView.collapseGroup(1);
                    expListView.collapseGroup(2);
                    expListView.collapseGroup(3);
                    expListView.collapseGroup(4);
                    expListView.collapseGroup(5);
                }
                else if (groupPosition == 1)
                {
                    expListView.collapseGroup(0);
                    expListView.collapseGroup(2);
                    expListView.collapseGroup(3);
                    expListView.collapseGroup(4);
                    expListView.collapseGroup(5);
                }
                else if (groupPosition == 2)
                {
                    expListView.collapseGroup(0);
                    expListView.collapseGroup(1);
                    expListView.collapseGroup(3);
                    expListView.collapseGroup(4);
                    expListView.collapseGroup(5);
                }
                else if (groupPosition == 3)
                {
                    expListView.collapseGroup(0);
                    expListView.collapseGroup(1);
                    expListView.collapseGroup(2);
                    expListView.collapseGroup(4);
                    expListView.collapseGroup(5);
                }
                else if (groupPosition == 4)
                {
                    expListView.collapseGroup(0);
                    expListView.collapseGroup(1);
                    expListView.collapseGroup(2);
                    expListView.collapseGroup(3);
                    expListView.collapseGroup(5);
                }
                else if (groupPosition == 5)
                {
                    expListView.collapseGroup(0);
                    expListView.collapseGroup(1);
                    expListView.collapseGroup(2);
                    expListView.collapseGroup(3);
                    expListView.collapseGroup(4);
                }
            }
        });
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
            if (groupPosition == 0)
            {
                if(childPosition == 0)
                {
                    Intent intent = new Intent(getApplicationContext(), FrontPageActivity.class);
                    startActivity(intent);
                }
                else if(childPosition == 1)
                {
                    Intent intent = new Intent(getApplicationContext(), ManageSubscriptionActivity.class);
                    startActivity(intent);
                }
                else if(childPosition == 2)
                {
                    Intent intent = new Intent(getApplicationContext(), ManageBookmarksActivity.class);
                    startActivity(intent);
                }
                else if(childPosition == 3)
                {
                    Intent intent = new Intent(getApplicationContext(), ManageDraftsActivity.class);
                    startActivity(intent);
                }
                else if(childPosition == 4)
                {
                    Intent intent = new Intent(getApplicationContext(), ManageAttachmentsActivity.class);
                    startActivity(intent);
                }
                else if(childPosition == 5)
                {
                    Intent intent = new Intent(getApplicationContext(), ManageNotificationsActivity.class);
                    startActivity(intent);
                }
            }
            else if (groupPosition == 1)
            {
                    if(childPosition == 0)
                    {
                        Intent intent = new Intent(getApplicationContext(), SignatureActivity.class);
                        startActivity(intent);
                    }
                    else if(childPosition == 1)
                    {
                        Intent intent = new Intent(getApplicationContext(), AvatarActivity.class);
                        startActivity(intent);
                    }
                    else if(childPosition == 2)
                    {
                        Intent intent = new Intent(getApplicationContext(), AccountSettingsActivity.class);
                        startActivity(intent);
                    }
                    else if(childPosition == 3)
                    {
                        Intent intent = new Intent(getApplicationContext(), LoginKeysActivity.class);
                        startActivity(intent);
                    }
                }

                return false;
            }
        });
    }
    public void prepareListData()
    {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        // Adding child data
        listDataHeader.add("OVERVIEW");
        listDataHeader.add("PROFILE");
        listDataHeader.add("BOARD PREFERENCES");
        listDataHeader.add("PRIVATE MESSAGE");
        listDataHeader.add("USER GROUP");
        listDataHeader.add("FRIEND AND FOE");
        // Adding child data
        List<String> overview = new ArrayList<String>();
        overview.add("FRONT PAGE");
        overview.add("MANAGE SUBSCRIPTION");
        overview.add("MANAGE BOOKMARKS");
        overview.add("MANAGE DRAFTS");
        overview.add("MANAGE ATTACHMENTS");
        overview.add("MANAGE NOTIFICATION");
        List<String> profile       = new ArrayList<String>();
        profile.add("EDIT SIGNATURE");
        profile.add("EDIT AVATAR");
        profile.add("EDIT ACCOUNT SETTINGS");
        profile.add("MANAGE “REMEMBER ME” LOGIN KEYS");
        List<String> board_preference    = new ArrayList<String>();
        board_preference.add("EDIT GLOBAL SETTINGS");
        board_preference.add("EDIT POSTING DEFAULTS");
        board_preference.add("EDIT DISPLAY OPTIONS");
        board_preference.add("EDIT NOTIFICATION OPTIONS");
        List<String> private_messages  = new ArrayList<String>();
        private_messages.add("COMPOSE MESSAGE");
        private_messages.add("MANAGE PM DRAFTS");
        private_messages.add("INBOX");
        private_messages.add("OUTBOX");
        private_messages.add("SENT MESSAGES");
        private_messages.add("RULES,FOLDERS AND SETTINGS");
        List<String> user_groups     = new ArrayList<String>();
        user_groups.add("EDIT MEMBERSHIPS");
        user_groups.add("MANAGE GROUPS");
        List<String> friends_and_foe= new ArrayList<String>();
        friends_and_foe.add("MANAGE FRIENDS");
        friends_and_foe.add("MANAGE FOES");

        listDataChild.put(listDataHeader.get(0), overview);
        listDataChild.put(listDataHeader.get(1), profile);
        listDataChild.put(listDataHeader.get(2), board_preference);
        listDataChild.put(listDataHeader.get(3), private_messages);
        listDataChild.put(listDataHeader.get(4), user_groups);
        listDataChild.put(listDataHeader.get(5), friends_and_foe);
    }
}
