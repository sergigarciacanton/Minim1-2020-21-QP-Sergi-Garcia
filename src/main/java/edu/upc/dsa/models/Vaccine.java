package edu.upc.dsa.models;

public class Vaccine {
    String trademark;
    String date;
    String user_id;

    public Vaccine(String trademark, String date, String user_id) {
        this.trademark = trademark;
        this.date = date;
        this.user_id = user_id;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
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
}
