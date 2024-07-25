package com.example.java_sqlite;

public class Data {
    private String id, name, type, episode, status;

    public Data() {
    }

    public Data(String id, String name, String type, String episode, String status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.episode = episode;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}