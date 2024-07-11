package com.example.conexatest.conexatest.models;

public class People {
    private int uid;
    private String name;
    /*private String birth_year;
    private String eye_color;
    private String gender;
    private String hair_color;
    private int height;
    private int mass;
    private String skin_color;
    private String homeworld;
    private List<String> films;
    private List<String> species;
    private List<String> starships;
    private List<String> vehicles;*/
    private String url;
    /*private Date created;
    private Date edited;*/

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
