package com.realcoders.realcoderlinkedinspring3.customer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerRegistrationRequest {

    private String username;
    private String email;
    private String password;
    private String fullname;
    private Integer age;

    @JsonCreator
    public CustomerRegistrationRequest(@JsonProperty("username") String username,
                                       @JsonProperty("email") String email,
                                       @JsonProperty("password") String password,
                                       @JsonProperty("fullname") String fullname,
                                       @JsonProperty("age") Integer age) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
