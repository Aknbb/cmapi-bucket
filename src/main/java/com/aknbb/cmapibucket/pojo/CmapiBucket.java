package com.aknbb.cmapibucket.pojo;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.HashMap;

public class CmapiBucket {
    private static HashMap<String, HashMap<String, Payload>> messages = new HashMap<>();

    public CmapiBucket() {
    }

    public HashMap<String, HashMap<String, Payload>> getMessages() {
        return messages;
    }

    public void addMessage(Cmapi cmapi) {
        String channel = cmapi.getChannel();
        String title = cmapi.getTitle();
        JsonNode cmapiMessage = cmapi.getMessage();
        Payload payload = new Payload(cmapi.getLabels(), cmapiMessage);
        if (channel != null && title != null && cmapiMessage != null) {
            messages.computeIfAbsent(channel, k -> new HashMap<>());
            messages.get(channel).put(title, payload);
        }
    }

    public Payload removeMessage(String channel, String title) {
        return messages.get(channel).remove(title);
    }

    public class Payload {
        private ArrayList<String> labels;
        private JsonNode message;

        public Payload(ArrayList<String> labels, JsonNode message) {
            this.labels = labels;
            this.message = message;
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

}
