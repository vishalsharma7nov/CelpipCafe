package com.allumez.celpipcafe.GetterAndSetter;

public class CelpipCafe_ForumParentName_GetterAndSetter_Class {

    String forum_name;
    String forum_id;
    String parent_id;

    public CelpipCafe_ForumParentName_GetterAndSetter_Class(String forum_name, String forum_id, String parent_id) {
        this.forum_name = forum_name;
        this.forum_id = forum_id;
        this.parent_id = parent_id;
    }

    public String getForum_name() {
        return forum_name;
    }

    public void setForum_name(String forum_name) {
        this.forum_name = forum_name;
    }

    public String getForum_id() {
        return forum_id;
    }

    public void setForum_id(String forum_id) {
        this.forum_id = forum_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }
}
