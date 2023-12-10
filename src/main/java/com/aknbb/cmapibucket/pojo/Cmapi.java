package com.aknbb.cmapibucket.pojo;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

public class Cmapi {
    private String channel;
    private String title;
    private ArrayList<String> labels;
    private JsonNode message;


    public Cmapi(String channel, String title, ArrayList<String> labels, JsonNode message) {
        this.channel = channel;
        this.title = title;
        this.labels = labels;
        this.message = message;
    }

    public Cmapi() {
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<String> labels) {
        this.labels = labels;
    }

    public JsonNode getMessage() {
        return message;
    }

    public void setMessage(JsonNode message) {
        this.message = message;
    }
}
