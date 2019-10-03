package com.allumez.celpipcafe.GetterAndSetter;

public class ForumTopicTitles {

    String topic_id;
    String topic_title;

    public ForumTopicTitles(String topic_id, String topic_title) {
        this.topic_id = topic_id;
        this.topic_title = topic_title;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public String getTopic_title() {
        return topic_title;
    }

    public void setTopic_title(String topic_title) {
        this.topic_title = topic_title;
    }
}
