package com.allumez.celpipcafe.GetterAndSetter;

public class ForumTopicTitlesAnswer {

    String post_id;
    String post_text;

    public ForumTopicTitlesAnswer(String post_id, String post_text) {
        this.post_id = post_id;
        this.post_text = post_text;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getPost_text() {
        return post_text;
    }

    public void setPost_text(String post_text) {
        this.post_text = post_text;
    }
}
