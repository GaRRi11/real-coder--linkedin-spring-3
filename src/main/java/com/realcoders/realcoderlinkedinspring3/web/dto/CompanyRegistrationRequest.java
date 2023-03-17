package com.realcoders.realcoderlinkedinspring3.web.dto;

public class CompanyRegistrationRequest {

    private String name;

    private Integer founding_year;

    private String description;

    private String location;

    private String website;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
