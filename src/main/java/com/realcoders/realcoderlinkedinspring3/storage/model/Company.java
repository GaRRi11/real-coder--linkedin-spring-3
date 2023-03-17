package com.realcoders.realcoderlinkedinspring3.storage.model;

public class Company {

    private Integer id;

    private Integer user_id;

    private String name;

    private Integer founding_year;

    private String description;

    private String location;

    private String website;


    public Company(
            Integer id,
            String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getFounding_year() {
        return founding_year;
    }

    public void setFounding_year(Integer founding_year) {
        this.founding_year = founding_year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public Company(Integer id, Integer user_id, String name, Integer founding_year, String description, String location, String website) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.founding_year = founding_year;
        this.description = description;
        this.location = location;
        this.website = website;
    }

    public Company(Integer id, String name, Integer user_id) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
    }

    public Company(String name,Integer user_id) {
        this.user_id = user_id;
        this.name = name;
    }

    public Company(String name) {
        this.name = name;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Company() {
    }

    private static Integer idCounter = 1;

    public Integer generateId(){
        return idCounter++;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Integer getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(Integer idCounter) {
        Company.idCounter = idCounter;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
