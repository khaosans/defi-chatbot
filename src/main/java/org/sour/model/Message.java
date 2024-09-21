package org.sour.model;

public class Message {
    private String content;
    private String agentName; // {{ edit_1: Added agent name field }}

    // Constructor
    public Message(String content, String agentName) {
        this.content = content;
        this.agentName = agentName; // {{ edit_2: Initialize agent name }}
    }

    // Getters and Setters
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAgentName() { // {{ edit_3: Getter for agent name }}
        return agentName;
    }

    public void setAgentName(String agentName) { // {{ edit_4: Setter for agent name }}
        this.agentName = agentName;
    }
}