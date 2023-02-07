package com.realcoders.realcoderlinkedinspring3.customer;

public class Customer {

    private Integer id;
    private String usarname;
    private String email;
    private String password;
    private String fullname;
    private Integer age;

    private static Integer idCounter = 0;

    private Integer generateId(){
        return idCounter++;
    }

    public Customer() {
    }

    public Customer(String usarname, String email, String password, String fullname, Integer age) {
        this.id = generateId();
        this.usarname = usarname;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.age = age;
    }

    public Customer(Integer id, String usarname, String email, String password, String fullname, Integer age) {
        this.id = id;
        this.usarname = usarname;
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

    public String getUsarname() {
        return usarname;
    }

    public void setUsarname(String usarname) {
        this.usarname = usarname;
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
