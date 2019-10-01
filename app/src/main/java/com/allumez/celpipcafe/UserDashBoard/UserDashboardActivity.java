package com.allumez.celpipcafe.UserDashBoard;

import android.content.Intent;
import android.os.Bundle;

import com.allumez.celpipcafe.ExpandableListView.ExpandableViewAdapter;
import com.allumez.celpipcafe.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDashboardActivity extends AppCompatActivity {
    ExpandableViewAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard_);
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
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView();
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
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        // preparing list data
        prepareListData();
        listAdapter = new ExpandableViewAdapter(this, listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);
        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                if(groupPosition == 0 )
                {
                }
                else if(groupPosition == 1 )
                {
                }
                else if(groupPosition == 2 )
                {
                }
                else if(groupPosition == 3 )
                {
                }
                else if(groupPosition == 4 )
                {
                }
                else if(groupPosition == 5 )
                {
                }
                else if(groupPosition == 6 )
                {
                }
                else if(groupPosition == 7 )
                {
                }
                else if(groupPosition == 8 )
                {
                }
                return false;
            }
        });

        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

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

                if(childPosition == 0)
                {

                }
                else if(childPosition == 1)
                {

                }
                else if(childPosition == 2)
                {

                }
                // TODO Auto-generated method stub
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
