package com.realcoders.realcoderlinkedinspring3.user;

public class UserDTO {

    Integer id;
    String username;
    String email;
    String password;
    String fullname;
    Integer age;

    public UserDTO( String username, String email, String password, String fullname, Integer age) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.age = age;
    }

    public UserDTO(Integer id, String username, String email, String password, String fullname, Integer age) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
