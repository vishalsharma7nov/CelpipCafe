package com.allumez.celpipcafe.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.allumez.celpipcafe.Activities.CelpipCafe_SubForumName_Activity;
import com.allumez.celpipcafe.GetterAndSetter.ForumParentName;
import com.allumez.celpipcafe.R;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class CelpipCafe_ForumParentName_Adapter extends BaseAdapter{

    Context c;
    List<ForumParentName> list;

    public CelpipCafe_ForumParentName_Adapter(Context c, List<ForumParentName> list)
    {
        this.c=c;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater in=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=in.inflate(R.layout.celpipcafe_forumparentname,null);
        TextView t1= convertView.findViewById(R.id.textViewName);
        t1.setText(list.get(position).getForum_name());
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c.getApplicationContext(), CelpipCafe_SubForumName_Activity.class);
                intent.putExtra("forum_id",list.get(position).getForum_id());
                intent.putExtra("forum_name",list.get(position).getForum_name());
                c.startActivity(intent);
            }
        });
        return convertView;
    }
}