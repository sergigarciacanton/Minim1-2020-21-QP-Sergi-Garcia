package edu.upc.dsa.models;

public class Tracing {
    String date;
    String user_id;
    String description;

    public Tracing(String date, String user_id, String description) {
        this.date = date;
        this.user_id = user_id;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
