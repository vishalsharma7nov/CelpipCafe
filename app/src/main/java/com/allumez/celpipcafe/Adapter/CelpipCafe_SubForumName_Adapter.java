package com.allumez.celpipcafe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.allumez.celpipcafe.GetterAndSetter.CelpipCafe_ForumParentName_GetterAndSetter_Class;
import com.allumez.celpipcafe.R;

import java.util.List;


public class CelpipCafe_SubForumName_Adapter extends BaseAdapter{

    Context c;
    List<CelpipCafe_ForumParentName_GetterAndSetter_Class> list;

    public CelpipCafe_SubForumName_Adapter(Context c, List<CelpipCafe_ForumParentName_GetterAndSetter_Class> list)
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
        return convertView;
    }
}